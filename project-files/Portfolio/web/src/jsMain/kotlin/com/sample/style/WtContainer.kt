package com.sample.style

import org.jetbrains.compose.web.css.*

object WtContainer : StyleSheet(AppStylesheet) {
    val wtContainer by style {
        property("margin-left", "auto")
        property("margin-right", "auto")
        property("box-sizing", "border-box")
        property("padding-left", 22.px)
        property("padding-right", 22.px)
        property("max-width", 750.px)

        media(mediaMaxWidth(750.px)) {
            self style {
                property("max-width", 100.percent)
                property("padding-left", 48.px)
                property("padding-right", 48.px)
            }
        }
    }

    val wtContainerSm by style {
        property("margin-left", "auto")
        property("margin-right", "auto")
        property("box-sizing", "border-box")
        property("padding-left", 12.px)
        property("padding-right", 12.px)
        property("max-width", 750.px)
        property("width", 750.px)

        media(mediaMaxWidth(750.px)) {
            self style {
                property("max-width", 100.percent)
                property("padding-left", 12.px)
                property("padding-right", 12.px)
            }
        }
    }

    val wtContainerWide by style {
        property("box-sizing", "border-box")
        property("padding", "3% 6%")
        property("width", 100.percent)
        property("max-width", 100.percent)

        media(mediaMaxWidth(750.px)) {
            self style {
                property("max-width", 100.percent)
                property("padding-left", 12.px)
                property("padding-right", 12.px)
            }
        }
    }

    val wtContainerHero by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        justifyContent(JustifyContent.SpaceBetween)
        alignItems(AlignItems.Center)
        width(100.percent)

        media(mediaMaxWidth(750.px)) {
            self style {
                display(DisplayStyle.None)
            }
        }
    }

    val wtContainerHeroSm by style {
        display(DisplayStyle.None)

        media(mediaMaxWidth(750.px)) {
            self style {
                width(100.percent)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.FlexStart)
                alignItems(AlignItems.Stretch)
            }
        }
    }

    val wtContainerHeroLinks by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        gap(16.px)

        media(mediaMaxWidth(750.px)) {
            self style {
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Stretch)
            }
        }
    }
}