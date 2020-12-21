package day21

class AllergenAssessment(input: List<String>) {

    data class Ingredient(val name: String)
    data class Allergen(val name: String)
    data class Food(val ingredients: Set<Ingredient>, val allergens: Set<Allergen>)

    private val regex = """(.+) \(contains (.+)\)""".toRegex()
    private val foods: List<Food>
    private val mapping: Map<Allergen, Ingredient>

    init {
        foods = input.map { parseFood(it) }
        val candidates = determineIngredientCandidatesPerAllergen(foods)
        mapping = deriveAllergenMapping(candidates)
    }

    fun part1() = foods
        .map { it.ingredients }
        .flatten()
        .filter { it !in mapping.values }
        .groupBy { it }
        .values
        .sumBy { it.size }

    fun part2() = mapping
        .toList()
        .sortedBy { (allergen, _) -> allergen.name }
        .joinToString(separator = ",") { (_, ingredient) -> ingredient.name }

    private fun parseFood(line: String): Food = regex.matchEntire(line)
        ?.destructured
        ?.let { (ingredients, allergens) ->
            return Food(
                ingredients.split(' ').map { Ingredient(it) }.toSet(),
                allergens.split(", ").map { Allergen(it) }.toSet()
            )
        }!!

    private fun determineIngredientCandidatesPerAllergen(foods: List<Food>) =
        foods.flatMap { food -> splitAllergens(food) }
            .groupBy({ (allergen, _) -> allergen }, { (_, ingredients) -> ingredients })
            .mapValues { entry -> intersection(entry.value) }

    private fun intersection(ingredientsSets: List<Set<Ingredient>>) =
        ingredientsSets.reduce { acc, s -> acc.intersect(s) }

    private fun splitAllergens(food: Food) =
        food.allergens.map { allergen -> allergen to food.ingredients }

    private fun deriveAllergenMapping(candidates: Map<Allergen, Set<Ingredient>>): Map<Allergen, Ingredient> {
        if (candidates.isEmpty()) {
            return emptyMap()
        }
        val partial = candidates.filterValues { it.size == 1 }.mapValues { it.value.single() }
        val remaining = candidates.filterKeys { it !in partial }.mapValues { it.value.minus(partial.values) }
        return listOf(partial.toList(), deriveAllergenMapping(remaining).toList()).flatten().toMap()
    }
}
