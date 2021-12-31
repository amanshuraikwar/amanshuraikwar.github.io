//
//  HomePageView.swift
//  iosApp
//
//  Created by amanshu raikwar on 12/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage

struct HomePageView: View {
    var body: some View {
        TabView {
            PortfolioNavigationView(
                AnyView(
                    BlogView()
                ),
                title: "Blog"
            )
            .ignoresSafeArea()
            .tabItem {
                Label(
                    "Blog",
                    systemImage: "doc.text.image.fill"
                )
            }
            
            PortfolioNavigationView(
                AnyView(
                    Text("projecs")
                ),
                title: "Projects"
            )
            .ignoresSafeArea()
            .tabItem {
                Label(
                    "Projects",
                    systemImage: "hammer.fill"
                )
            }
            
            PortfolioNavigationView(
                AnyView(
                    Text("background")
                ),
                title: "Background"
            )
            .ignoresSafeArea()
            .tabItem {
                Label(
                    "Background",
                    systemImage: "building.2.fill"
                )
            }
            
            PortfolioNavigationView(
                AnyView(
                    Text("me")
                ),
                title: "Me"
            )
            .ignoresSafeArea()
            .tabItem {
                Label(
                    "About Me",
                    systemImage: "person.crop.circle.fill"
                )
            }
        }
    }
}
