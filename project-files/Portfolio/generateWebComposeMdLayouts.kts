import java.io.File
import java.util.concurrent.TimeUnit

val file = File("markdown")

try {
    file
    .listFiles { file -> 
        !file.isDirectory() 
    }
    .forEach { file ->
        println("generating for ${file.name}")
        generateHtml(file)
    }
} finally {
    generateEmptyMdDataStoreFile()
}

fun generateEmptyMdDataStoreFile() {
    val outputFile = File("shared/src/commonMain/kotlin/io/github/amanshuraikwar/portfolio/MdDataStore.kt")
    outputFile.delete()
    
    outputFile.bufferedWriter().use { out -> 
        out.write("package io.github.amanshuraikwar.portfolio\n")
        
        out.write("\n")
        
        out.write("import io.github.amanshuraikwar.portfolio.markdown.MdNode\n")
        
        out.write("\n")
        
        out.write("class MdDataStore {\n")
        
        out.write("\n")
        
        out.write("\tfun mdEnabled(): Boolean {\n")
        out.write("\t\treturn false\n")
        out.write("\t}\n")

        out.write("\n")

        out.write("\tfun getData(): List<MdNode> {\n")
        out.write("\t\treturn listOf()\n")
        out.write("\t}\n")

        out.write("}\n")
    }
}

fun generateHtml(file: File) {
    val outputFile = File("shared/src/commonMain/kotlin/io/github/amanshuraikwar/portfolio/MdDataStore.kt")
    outputFile.delete()

    outputFile.bufferedWriter().use { out -> 
        out.write("package io.github.amanshuraikwar.portfolio\n")
        
        out.write("\n")
        
        out.write("import io.github.amanshuraikwar.portfolio.markdown.MdNode\n")
        
        out.write("\n")
        
        out.write("class MdDataStore {\n")
        
        out.write("\n")

        out.write("\tfun mdEnabled(): Boolean {\n")
        out.write("\t\treturn true\n")
        out.write("\t}\n")

        out.write("\n")
        
        out.write("\tfun getData(): List<MdNode> {\n")

        out.write("\t\treturn listOf(\n")

        file.forEachLine { line ->
            when {
                line.startsWith("!Btn[") -> {
                    val (label, url) = line
                        .drop(5)
                        .trim()
                        .split("]", "(", ")")
                        .filter {
                            it.trim().isNotBlank()
                        }
                        .take(2)

                    out.write(
                        """
                        MdNode.Btn(
                            text = "${label.trim()}",
                            url = "${url.trim()}",
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )

                    out.write("\n")
                }
                line.startsWith("![") -> {
                    val (label, url) = line
                        .drop(2)
                        .trim()
                        .split("]", "(", ")")
                        .filter {
                            it.trim().isNotBlank()
                        }
                        .take(2)

                    out.write(
                        """
                        MdNode.Img(
                            label = "${label.trim()}",
                            url = "${url.trim()}",
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )

                    out.write("\n")
                }
                line.startsWith("###") -> {
                    out.write(
                        """
                        MdNode.H3(
                            text = "${line.drop(3).trim()}"
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )
                    out.write("\n")
                }
                line.startsWith("#Date") -> {
                    out.write(
                        """
                        MdNode.Date(
                            text = "${line.drop(5).trim()}"
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )
                    out.write("\n")
                }
                line.startsWith("#") -> {
                    out.write(
                        """
                        MdNode.H1(
                            text = "${line.drop(1).trim()}"
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )
                    out.write("\n")
                }
                line.trim().isNotBlank() -> {
                    out.write(
                        """
                        MdNode.P(
                            text = "${line.trim()}"
                        ),
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )
                    out.write("\n")
                }
                else -> {
                    out.write(
                        """
                        MdNode.Spacer,
                        """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                    )
                    out.write("\n")
                }
            }
        }
        out.write("\t\t)\n")
        out.write("\t}\n")
        out.write("}\n")
    }

    val dirName = file.name.dropLast(3)
    println("Building...")
    File(".") exec "./gradlew :web:jsBrowserDistribution"
    println("Deleting existing directory ../../docs/$dirName...")
    File(".") exec "rm -rf ../../docs/$dirName"
    println("Creating directory ../../docs/$dirName...")
    File(".") exec "mkdir ../../docs/$dirName"
    println("Copying contents from web/build/distributions/ to ../../docs/$dirName/...")
    File(".") exec "cp -R web/build/distributions/ ../../docs/$dirName/"
}

/**
 * Shorthand for [File.execute]. Assumes that all spaces are argument separators,
 * so no argument may contain a space.
 * ```kotlin
 *  // Example
 *  directory exec "git status"
 *
 *  // This fails since `'A` and `message'` will be considered as two arguments
 *  directory exec "git commit -m 'A message'"
 * ```
 */
infix fun File.exec(command: String): String {
    val arguments = command.split(' ').toTypedArray()
    return execute(*arguments)
}

/**
 * Executes command. Arguments may contain strings. More appropriate than [File.exec]
 * when using dynamic arguments.
 * ```kotlin
 *  // Example
 *  directory.execute("git", "commit", "-m", "A message")
 * ```
 */
fun File.execute(vararg arguments: String): String {
    val process = ProcessBuilder(*arguments)
        .directory(this)
        .start()
        .also { it.waitFor(20, TimeUnit.SECONDS) }

    if (process.exitValue() != 0) {
        throw Exception(process.errorStream.bufferedReader().readText())
    }
    return process.inputStream.bufferedReader().readText()
}