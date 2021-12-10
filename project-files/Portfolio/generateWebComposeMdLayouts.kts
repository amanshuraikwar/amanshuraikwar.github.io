import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

println("Deleting build...")
File(".") exec "rm -rf build"

println("Creating build...")
File(".") exec "mkdir build"

generateEmptyDataStoreFile("PageType.PROJECTS")
buildAndCopyDirectoryContents("projects")

generateEmptyDataStoreFile("PageType.BACKGROUND")
buildAndCopyDirectoryContents("background")

generateEmptyDataStoreFile("PageType.ABOUT_ME")
buildAndCopyDirectoryContents("me")

val blogListEntryList = mutableListOf<BlogListEntry>()

println("Parsing markdown files...")

val mdFilesDir = File("markdown")
try {
    val mdFileList = mdFilesDir.listFiles { file ->
        !file.isDirectory()
    }

    println("${mdFileList.size} markdown files found...")

    mdFileList
        .forEach { file ->
            println("Parsing ${file.name}...")
            blogListEntryList.add(generateBlogMdSource(file))
        }
} catch (e: Exception) {
    println("Exception while parsing markdown files!")
    println("${e.message}")
}

println("Creating blog list file...")
createBlogListFile(blogListEntryList)

generateEmptyDataStoreFile("PageType.HOME")
buildAndCopyDirectoryContents("home")

println("Copying contents of build/home/* to build/...")
File(".") exec "cp -R build/home/ build/"

println("Deleting build/home...")
File(".") exec "rm -rf build/home"

data class BlogListEntry(
    val title: String,
    val date: String,
    val firstParagraph: String,
    val link: String
)

fun createBlogListFile(blogListEntryList: List<BlogListEntry>): File {
    val outputFile = File(
        "shared/src/commonMain/kotlin/io/github/amanshuraikwar/portfolio/BlogListDataStore.kt"
    )
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.markdown.BlogListDataItem\n")

        out.write("\n")

        out.write("class BlogListDataStore {\n")

        out.write("\n")

        out.write("\tfun getBlogListData(): List<BlogListDataItem> {\n")

        out.write("\t\treturn listOf(\n")

        blogListEntryList
            .sortedByDescending {
                var formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                LocalDate.parse(
                    it.date,
                    formatter
                )
            }
            .forEach { entry ->
                println("Creating blog entry for ${entry.link}")
                out.write(
                    """
                    BlogListDataItem(
                        title = "${entry.title}",
                        date = "${entry.date}",
                        firstParagraph = "${entry.firstParagraph}",
                        link = "${entry.link}",
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

        out.write("\t\t)\n")
        out.write("\t}\n")
        out.write("}")
    }

    return outputFile
}

fun buildAndCopyDirectoryContents(dirName: String) {
    println("Building for $dirName...")
    File(".") exec "./gradlew :web:jsBrowserDistribution"
    println("Deleting existing directory build/$dirName...")
    File(".") exec "rm -rf build/$dirName"
    println("Creating directory build/$dirName...")
    File(".") exec "mkdir build/$dirName"
    println("Copying contents from web/build/distributions/ to build/$dirName/...")
    File(".") exec "cp -R web/build/distributions/ build/$dirName/"
}

fun generateEmptyDataStoreFile(pageType: String) {
    val outputFile =
        File("shared/src/commonMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedDataStore.kt")
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.markdown.MdNode\n")

        out.write("\n")

        out.write("class GeneratedDataStore {\n")

        out.write("\n")

        out.write("\tfun getPageType(): PageType {\n")
        out.write("\t\treturn $pageType\n")
        out.write("\t}\n")

        out.write("\n")

        out.write("\tfun getData(): List<MdNode> {\n")
        out.write("\t\treturn listOf()\n")
        out.write("\t}\n")

        out.write("}\n")
    }
}

fun generateBlogMdSource(file: File): BlogListEntry {
    var title = ""
    var date = ""
    var firstParagraph = ""

    val outputFile =
        File("shared/src/commonMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedDataStore.kt")
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.markdown.MdNode\n")

        out.write("\n")

        out.write("class GeneratedDataStore {\n")

        out.write("\n")

        out.write("\tfun getPageType(): PageType {\n")
        out.write("\t\treturn PageType.MD\n")
        out.write("\t}\n")

        out.write("\n")

        out.write("\tfun getData(): List<MdNode> {\n")

        out.write("\t\treturn listOf(\n")

        val lines = file.readLines()

        var i = 0
        while (i < lines.size) {
            val line = lines[i]
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

                    if (date == "") {
                        date = line.drop(5).trim()
                    }
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

                    if (title == "") {
                        title = line.drop(1).trim()
                    }
                }
                line.startsWith("```") -> {
                    var codeBlockLines = mutableListOf<String>()
                    while (++i < lines.size && !lines[i].startsWith("```")) {
                        val lastLine = lines[i]
                        codeBlockLines.add(lastLine)
                    }

                    out.write("\t\t\tMdNode.Code(\n")
                    out.write("\t\t\t\tlines = listOf(\n")
                    codeBlockLines.forEach {
                        out.write("\t\t\t\t\t\"$it\",\n")
                    }
                    out.write("\t\t\t\t)\n")
                    out.write("\t\t\t),\n")
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

                    if (firstParagraph == "") {
                        firstParagraph = line.trim()
                    }
                }
                i != lines.size - 1 && lines[i + 1].trim().isBlank() -> {
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
                    i++
                }
                else -> {
                    // do nothing if its a single empty line
                }
            }
            i++
        }
        out.write("\t\t)\n")
        out.write("\t}\n")
        out.write("}\n")
    }

    val dirName = file.name.dropLast(3)
    buildAndCopyDirectoryContents(dirName)

    return BlogListEntry(
        title = title,
        date = date,
        firstParagraph = firstParagraph,
        link = "https://amanshuraikwar.github.io/$dirName"
    )
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
        .also { it.waitFor() }

    if (process.exitValue() != 0) {
        throw Exception(process.errorStream.bufferedReader().readText())
    }
    return process.inputStream.bufferedReader().readText()
}