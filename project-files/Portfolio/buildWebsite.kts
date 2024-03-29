import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

println("Deleting build...")
File(".") exec "rm -rf build"

println("Creating build...")
File(".") exec "mkdir build"

generateEmptyBlogListFile()
generateEmptyBlogListFileJson()

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
            generateBlogMdSourceJson(file)
        }
} catch (e: Exception) {
    println("Exception while parsing markdown files!")
    println("${e.message}")
}

println("Creating blog list file...")
createBlogListFile(blogListEntryList)

println("Creating blog list json file...")
createBlogListFileJson(blogListEntryList)

generateEmptyDataStoreFile("PageType.HOME")
buildAndCopyDirectoryContents("home", deleteAssets = false)

println("Copying contents of build/home/* to build/...")
File(".") exec "cp -R build/home/ build/"

println("Deleting build/home...")
File(".") exec "rm -rf build/home"

println("Creating directory build/tests...")
File(".") exec "mkdir build/tests"
println("Creating directory build/tests/shared...")
File(".") exec "mkdir build/tests/shared"

println("Running tests in :shared for Android/JVM target...")
File(".") exec "./gradlew :shared:cleanTestDebugUnitTest :shared:testDebugUnitTest"
println("Creating directory build/tests/shared/android-jvm...")
File(".") exec "mkdir build/tests/shared/android-jvm"
println("Copying contents from shared/build/reports/tests/testDebugUnitTest/ to build/tests/shared/android-jvm/...")
File(".") exec "cp -R shared/build/reports/tests/testDebugUnitTest/ build/tests/shared/android-jvm/"

try {
    println("Running tests in :shared for Js Browser...")
    File(".") exec "./gradlew :shared:cleanJsBrowserTest :shared:jsBrowserTest --stacktrace"
    println("Creating directory build/tests/shared/js-browser...")
    File(".") exec "mkdir build/tests/shared/js-browser"
    println("Copying contents from shared/build/reports/tests/jsBrowserTest/ to build/tests/shared/js-browser/...")
    File(".") exec "cp -R shared/build/reports/tests/jsBrowserTest/ build/tests/shared/js-browser/"
} catch (e: Exception) {
    // do nothing
    println("Something went wrong while running tests in :shared for Js Browser.")
    println(e)
}

File(".") exec "cp -R html/ build/"

println("Deleting build/js...")
File(".") exec "rm -rf build/js"

println("Deleting build/tmp...")
File(".") exec "rm -rf build/tmp"

println("Deleting .map files...")
File(".") exec "find build -name \"*.map\" -type f -delete"

data class BlogListEntry(
    val title: String,
    val date: String,
    val firstParagraph: String,
    val link: String
)

fun createBlogListFileJson(blogListEntryList: List<BlogListEntry>): File {
    val outputFile = File(
        "web/src/jsMain/resources/api/blog_list.json"
    )
    if (outputFile.exists()) {
        outputFile.delete()
    }

    outputFile.bufferedWriter().use { out ->
        out.write("{\n")
        out.write("\t\"blog_list\":[\n")

        blogListEntryList
            .sortedByDescending {
                var formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                LocalDate.parse(
                    it.date,
                    formatter
                )
            }
            .forEachIndexed { index, entry ->
                println("Creating blog entry for ${entry.link}")
                out.write(
                    """
                    {
                        "title": "${entry.title}",
                        "date": "${entry.date}",
                        "firstParagraph": "${entry.firstParagraph}",
                        "link": "${entry.link}",
                        "id": "${entry.link.split("/").last()}"
                    }${if (index == blogListEntryList.size - 1) "" else ","}
                    """.trimIndent()
                        .split("\n")
                        .map {
                            it.prependIndent().prependIndent()
                        }
                        .reduce { acc, s -> "$acc\n$s" }
                )

                out.write("\n")
            }
        out.write("\t]\n")
        out.write("}\n")
    }

    return outputFile
}

fun generateEmptyBlogListFileJson(): File {
    val outputFile = File(
        "web/src/jsMain/resources/api/blog_list.json"
    )
    if (outputFile.exists()) {
        outputFile.delete()
    }

    outputFile.bufferedWriter().use { out ->
        out.write("{\n")
        out.write("\t\"blog_list\": []\n")
        out.write("}\n")
    }

    return outputFile
}

fun generateEmptyBlogListFile(): File {
    val outputFile = File(
        "shared/src/jsMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedBlogListDataStore.kt"
    )
    if (outputFile.exists()) {
        outputFile.delete()
    }

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")
        out.write("\n")
        out.write("import io.github.amanshuraikwar.portfolio.blog.BlogListDataItem\n")
        out.write("\n")
        out.write("/**\n * Note: generated by buildWebsite.kts, do not edit!\n */\n")
        out.write("class GeneratedBlogListDataStore {\n")
        out.write("\n")
        out.write("\tfun getBlogListData(): List<BlogListDataItem> {\n")
        out.write("\t\treturn listOf()\n")
        out.write("\t}\n")
        out.write("}")
    }

    return outputFile
}

fun createBlogListFile(blogListEntryList: List<BlogListEntry>): File {
    val outputFile = File(
        "shared/src/jsMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedBlogListDataStore.kt"
    )
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.blog.BlogListDataItem\n")

        out.write("\n")

        out.write("/**\n * Note: generated by buildWebsite.kts, do not edit!\n */\n")

        out.write("class GeneratedBlogListDataStore {\n")

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

fun buildAndCopyDirectoryContents(dirName: String, deleteAssets: Boolean = true) {
    println("Building for $dirName...")
    File(".") exec "./gradlew :web:jsBrowserDistribution"
    println("Deleting existing directory build/$dirName...")
    File(".") exec "rm -rf build/$dirName"
    println("Creating directory build/$dirName...")
    File(".") exec "mkdir build/$dirName"
    println("Copying contents from web/build/distributions/ to build/$dirName/...")
    File(".") exec "cp -R web/build/distributions/ build/$dirName/"

    if (deleteAssets) {
        println("Deleting directory build/$dirName/assets...")
        File(".") exec "rm -rf build/$dirName/assets"
        println("Deleting directory build/$dirName/api...")
        File(".") exec "rm -rf build/$dirName/api"
    }
}

fun generateEmptyDataStoreFile(pageType: String) {
    val outputFile =
        File("shared/src/jsMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedWebPageBuildDataStore.kt")
    if (outputFile.exists()) {
        outputFile.delete()
    }

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.model.MdNode\n")

        out.write("\n")

        out.write("/**\n * Note: generated by buildWebsite.kts, do not edit!\n */\n")

        out.write("class GeneratedWebPageBuildDataStore : WebPageBuildDataStore {\n")

        out.write("\n")

        out.write("\toverride fun getPageType(): PageType {\n")
        out.write("\t\treturn $pageType\n")
        out.write("\t}\n")

        out.write("\n")

        out.write("\toverride fun getData(): List<MdNode> {\n")
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
        File("shared/src/jsMain/kotlin/io/github/amanshuraikwar/portfolio/GeneratedWebPageBuildDataStore.kt")
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("package io.github.amanshuraikwar.portfolio\n")

        out.write("\n")

        out.write("import io.github.amanshuraikwar.portfolio.model.MdNode\n")

        out.write("\n")

        out.write("/**\n * Note: generated by buildWebsite.kts, do not edit!\n */\n")

        out.write("class GeneratedWebPageBuildDataStore : WebPageBuildDataStore {\n")

        out.write("\n")

        out.write("\toverride fun getPageType(): PageType {\n")
        out.write("\t\treturn PageType.MD\n")
        out.write("\t}\n")

        out.write("\n")

        out.write("\toverride fun getData(): List<MdNode> {\n")

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
                    val language = if (line.trim().length > 3) {
                        line.trim().drop(3).trim()
                    } else {
                        ""
                    }
                    var codeBlockLines = mutableListOf<String>()
                    while (++i < lines.size && !lines[i].startsWith("```")) {
                        val lastLine = lines[i]
                        codeBlockLines.add(lastLine)
                    }

                    out.write("\t\t\tMdNode.Code(\n")
                    out.write("\t\t\t\tlanguage = \"$language\",\n")
                    out.write("\t\t\t\tcode = \"\"\"\n")
                    codeBlockLines.forEach {
                        out.write("\t\t\t\t\t$it\n")
                    }
                    out.write("\t\t\t\t\"\"\".trimIndent()\n")
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

fun generateBlogMdSourceJson(file: File) {
    var title = ""
    var date = ""
    var firstParagraph = ""

    val dirName = file.name.dropLast(3)

    val outputFile =
        File("web/src/jsMain/resources/api/$dirName.json")
    outputFile.delete()

    outputFile.bufferedWriter().use { out ->
        out.write("{\n")

        out.write("\t\"blog_data\": [\n")

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
                        {
                            "text": "${label.trim()}",
                            "url": "${url.trim()}",
                            "type": "BTN"
                        }${if (i == lines.size - 1) "" else ","}
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
                        {
                            "label": "${label.trim()}",
                            "url": "${url.trim()}",
                            "type": "IMG"
                        }${if (i == lines.size - 1) "" else ","}
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
                        {
                            "text": "${line.drop(3).trim()}",
                            "type": "H3"
                        }${if (i == lines.size - 1) "" else ","}
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
                        {
                            "text": "${line.drop(5).trim()}",
                            "type": "DATE"
                        }${if (i == lines.size - 1) "" else ","}
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
                        {
                            "text": "${line.drop(1).trim()}",
                            "type": "H1"
                        }${if (i == lines.size - 1) "" else ","}
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
                    val language = if (line.trim().length > 3) {
                        line.trim().drop(3).trim()
                    } else {
                        ""
                    }
                    var codeBlockLines = mutableListOf<String>()
                    while (++i < lines.size && !lines[i].startsWith("```")) {
                        val lastLine = lines[i]
                        codeBlockLines.add(lastLine)
                    }
                    val codeBlockString = codeBlockLines.fold("") { acc, cur ->
                        "$acc$cur\\n".trimIndent()
                    }.replace("\"", "\\\"")

                    out.write(
                        """
                        {
                            "language": "$language",
                            "type": "CODE",
                            "code": "$codeBlockString"
                        }${if (i == lines.size - 1) "" else ","}
                        """.trimIndent()
                            .split("\n")
                            .map {
                                it.prependIndent().prependIndent().prependIndent()
                            }
                            .reduce { acc, s -> "$acc\n$s" }
                    )
                }
                line.trim().isNotBlank() -> {
                    out.write(
                        """
                        {
                            "text": "${line.trim()}",
                            "type": "P"
                        }${if (i == lines.size - 1) "" else ","}
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
                        {
                            "type": "SPACER"
                        }${if (i == lines.size - 1) "" else ","}
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
        out.write("\t]\n")
        out.write("}\n")
    }
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