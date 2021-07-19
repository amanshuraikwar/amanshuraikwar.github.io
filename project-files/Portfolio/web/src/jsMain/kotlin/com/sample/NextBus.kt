package com.sample

import androidx.compose.runtime.Composable
import com.sample.components.HrefButton
import com.sample.style.*
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.dom.*

@Composable
fun NextBus(
    onMadeByClick: () -> Unit,
) {
    Section {
        Div(
            attrs = {
                classes(WtContainer.wtContainer)
            }
        ) {
            Div(
                attrs = {
                    classes(
                        WtRows.wtRow,
                        WtRows.wtRowSizeM,
                    )
                }
            ) {
                Div(
                    attrs = {
                        classes(
                            WtCols.wtColSm12,
                            WtCols.wtCol6,
                            WtOffsets.wtTopOffset96,
                            WtOffsets.wtTopOffsetSm24
                        )
                    }
                ) {
                    Img(
                        src = "assets/nextbus.png",
                        attrs = {
                            classes(
                                WtCols.wtCol12,
                                WtCols.wtColSm0,
                            )
                        }
                    )

                    Img(
                        src = "assets/nextbus-land-55.png",
                        attrs = {
                            classes(
                                WtCols.wtCol0,
                                WtCols.wtColSm12,
                            )
                        }
                    )
                }

                Div(
                    attrs = {
                        classes(
                            WtCols.wtColSm12,
                            WtCols.wtCol6,
                            WtOffsets.wtTopOffset96,
                            WtOffsets.wtTopOffsetSm48
                        )
                    }
                ) {

                    H1(
                        attrs = {
                            classes(
                                WtTexts.wtH2,
                            )
                            style {
                                color(AppCSSVariables.colorPrimary.value())
                            }
                        }
                    ) {
                        Text("Next Bus SG")
                    }

                    P(
                        attrs = {
                            classes(
                                WtTexts.wtText1,
                                WtOffsets.wtTopOffset24,
                                WtOffsets.wtTopOffsetSm12,
                            )
                        }
                    ) {
                        Text("A beautiful bus timing app for Singapore built with Jetpack Compose")
                    }

                    P(
                        attrs = {
                            classes(
                                WtTexts.wtText1,
                                WtOffsets.wtTopOffset24,
                                WtOffsets.wtTopOffsetSm12,
                            )
                        }
                    ) {
                        Text("View nearby bus stops and see bus timings")
                    }

                    P(
                        attrs = {
                            classes(
                                WtTexts.wtText1,
                                WtOffsets.wtTopOffset24,
                                WtOffsets.wtTopOffsetSm12,
                            )
                        }
                    ) {
                        Text("See the timings for your most frequent buses at a glance")
                    }

                    P(
                        attrs = {
                            classes(
                                WtTexts.wtText1,
                                WtOffsets.wtTopOffset24,
                                WtOffsets.wtTopOffsetSm12,
                            )
                        }
                    ) {
                        Text("Experience the app in both Light and Dark mode :)")
                    }

                    HrefButton(
                        link = "https://play.google.com/store/apps/details?id=io.github.amanshuraikwar.nxtbuz.release",
                        fillWidth = true
                    ) {
                        Span(
                            attrs = {
                                classes(
                                    WtTexts.wtText2,
                                )
                                style {
                                    color(AppCSSVariables.colorBackground.value())
                                }
                            }
                        ) {
                            Text("GET IT ON")
                        }

                        Br()

                        Span(
                            attrs = {
                                classes(
                                    WtTexts.wtSubtitle2,
                                )
                                style {
                                    color(AppCSSVariables.colorBackground.value())
                                }
                            }
                        ) {
                            Text("PLAY STORE")
                        }
                    }

                    com.sample.components.Button(
                        onClick = onMadeByClick
                    ) {
                        Text("Made by Amanshu Raikwar")
                    }
                }
            }

            Div(
                attrs = {
                    classes(
                        WtRows.wtRow,
                        WtRows.wtRowSizeM,
                        WtOffsets.wtTopOffset96,
                        WtOffsets.wtTopOffsetSm48
                    )
                }
            ) {
                Div(
                    attrs = {
                        classes(
                            WtCols.wtColSm12,
                            WtCols.wtCol0,
                        )
                    }
                ) {
                    Img(
                        src = "assets/nextbus-land-4.png",
                        attrs = {
                            classes(
                                WtCols.wtCol12,
                            )
                        }
                    )
                }
            }

            Div(
                attrs = {
                    classes(
                        WtRows.wtRow,
                        WtRows.wtRowSizeM,
                        WtOffsets.wtTopOffsetSm48
                    )
                }
            ) {}
        }
    }
}