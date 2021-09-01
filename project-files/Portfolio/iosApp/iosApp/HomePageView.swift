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
            Text("Compiling...")
        case .success(let portfolioData):
            NavigationView {
                ScrollView {
                    LazyVStack(
                        spacing: 8,
                        pinnedViews: [.sectionHeaders]
                    ) {
                        Text(portfolioData.intro)
                            .font(.callout)
                            .frame(
                                maxWidth: .infinity,
                                alignment: .leading
                            )
                            .padding(.horizontal)
                        
                        Section(
                            header: SectionHeader(title: "Projects")
                        ) {
                            ForEach(Array(portfolioData.apps.enumerated()), id: \.1) { index, project in
                                VStack(
                                    alignment: .leading
                                ) {
                                    ProjectView(project: project)
                                    
                                    if index != portfolioData.apps.count - 1 {
                                        Spacer()
                                            .frame(height: 24)
                                    }
                                }
                            }
                        }
                        
                        Section(
                            header: SectionHeader(title: "Experience")
                        ) {
                            ForEach(Array(portfolioData.experience.enumerated()), id: \.1) { index, experienceItem in
                                ExperienceItemView(data: experienceItem)
                                    .padding(.vertical)
                            }
                        }
                        
                        Section(
                            header: SectionHeader(title: "Links")
                        ) {
                            ForEach(Array(portfolioData.links.enumerated()), id: \.1) { index, linkData in
                                PortfolioLinkButton(name: linkData.title, url: linkData.url)
                                    .padding(.horizontal)
                            }
                        }
                        
                        Text(portfolioData.madeWith)
                            .padding()
                            .font(.largeTitle)
                            .foregroundColor(Color(.systemGray5))
                    }
                }
                .id(UUID())
                .navigationBarTitle(portfolioData.name)
                .environment(
                    \.urlImageOptions,
                    URLImageOptions(
                        loadOptions: [ .loadOnAppear, .cancelOnDisappear ]
                    )
                )
            }
        }
    }
}
