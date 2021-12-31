package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.model.MdNode

fun List<Map<String, String>>.toMdNodes(): List<MdNode> {
    val mdNodeList = mutableListOf<MdNode>()
    forEach { map ->
        when (map["type"]) {
            "H1" -> {
                mdNodeList.add(
                    MdNode.H1(
                        text = map["text"] ?: ""
                    )
                )
            }
            "H3" -> {
                mdNodeList.add(
                    MdNode.H3(
                        text = map["text"] ?: ""
                    )
                )
            }
            "P" -> {
                mdNodeList.add(
                    MdNode.P(
                        text = map["text"] ?: ""
                    )
                )
            }
            "IMG" -> {
                mdNodeList.add(
                    MdNode.Img(
                        label = map["label"] ?: "",
                        url = map["url"]?.let {
                            if (it.startsWith("../")) {
                                "https://amanshuraikwar.github.io/${it.drop(3)}"
                            } else {
                                it
                            }
                        } ?: ""
                    )
                )
            }
            "Spacer" -> {
                mdNodeList.add(
                    MdNode.Spacer
                )
            }
            "DATE" -> {
                mdNodeList.add(
                    MdNode.Date(
                        text = map["text"] ?: ""
                    )
                )
            }
            "BTN" -> {
                mdNodeList.add(
                    MdNode.Btn(
                        text = map["text"] ?: "",
                        url = map["url"] ?: ""
                    )
                )
            }
            "CODE" -> {
                mdNodeList.add(
                    MdNode.Code(
                        code = map["code"] ?: "",
                        language = map["language"] ?: ""
                    )
                )
            }
        }
    }
    return mdNodeList
}