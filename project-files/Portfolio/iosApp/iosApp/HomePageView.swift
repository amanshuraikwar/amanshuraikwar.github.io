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
    @Binding var screenState: ScreenState
    
    var body: some View {
        switch screenState {
        case .fetching:
            ProgressView()
        case .success(let data):
            NavigationView {
                List {
                    ForEach(Array(data.enumerated()), id: \.1) { index, project in
                        NavigationLink(
                            destination: BlogPageView(pageId: project.id)
                        ) {
                            VStack(
                                alignment: .leading,
                                spacing: 0
                            ) {
                                Text(project.title)
                                    .font(PortfolioFonts.title)
                                    .fontWeight(.bold)
                                    .multilineTextAlignment(.leading)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .fixedSize(horizontal: false, vertical: true)
                                    .padding(.top, 12)
                                
                                Text(project.firstParagraph)
                                    .font(PortfolioFonts.body)
                                    .fontWeight(.regular)
                                    .multilineTextAlignment(.leading)
                                    .lineLimit(3)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 8)
                                
                                Text(project.date)
                                    .font(PortfolioFonts.footnote)
                                    .foregroundColor(.accentColor)
                                    .fontWeight(.medium)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 12)
                                    .padding(.bottom, 12)
                            }
                            .frame(
                                maxWidth: .infinity,
                                alignment: .leading
                            )
                        }
                    }
                }
                .listStyle(InsetGroupedListStyle())
                .navigationTitle("Blog")
            }
        }
    }
}
