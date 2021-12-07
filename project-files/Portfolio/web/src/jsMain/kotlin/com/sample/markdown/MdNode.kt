package com.sample.markdown

sealed class MdNode {
    data class H1(val text: String): MdNode()
    data class H3(val text: String): MdNode()
    data class H5(val text: String): MdNode()
    data class P(val text: String): MdNode()
    data class Img(val label: String, val url: String): MdNode()
    data class Spacer(val px: Int): MdNode()
}