package io.github.amanshuraikwar.portfolio.markdown

sealed class MdNode {
    data class H1(val text: String): MdNode()
    data class H3(val text: String): MdNode()
    data class H5(val text: String): MdNode()
    data class P(val text: String): MdNode()
    data class Img(val label: String, val url: String): MdNode()
    object Spacer: MdNode()
    data class Date(val text: String): MdNode()
    data class Btn(val text: String, val url: String) : MdNode()
}