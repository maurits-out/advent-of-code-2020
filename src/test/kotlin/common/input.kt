package common

import java.io.File

fun readInput(path: String): String {
    val resource = ClassLoader.getSystemResource(path)
    val file = File(resource.file)
    return file.readText().trim()
}

fun readInputAsLines(path: String): List<String> {
    return readInput(path)
        .lines()
        .filterNot { it.isEmpty() }
}
