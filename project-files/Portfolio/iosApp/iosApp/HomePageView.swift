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
                    LazyVStack {
                        Text(portfolioData.intro)
                            .font(.callout)
                            .frame(
                                maxWidth: .infinity,
                                alignment: .leading
                            )
                            .padding(.horizontal)
                        
                        Section(
                            header: SectionHeader(title: "Projects")
                                .frame(
                                    maxWidth: .infinity,
                                    alignment: .leading
                                )
                                .padding(.leading)
                                .padding(.vertical)
                                .padding(.top)
                        ) {
                            ForEach(Array(portfolioData.apps.enumerated()), id: \.1) { index, project in
                                VStack(
                                    alignment: .leading
                                ) {
                                    URLImage(
                                        URL(string: project.artUrl)!
                                    ) {
                                    } inProgress: { progress in
                                    } failure: { error, retry in
                                    } content: { image in
                                        image
                                            .resizable()
                                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                                            .aspectRatio(1.5, contentMode: .fill)
                                            .transition(AnyTransition.opacity.animation(.linear(duration: 0.3)))
                                    }
                                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                                    .aspectRatio(1.5, contentMode: .fill)
                                    .clipShape(RoundedRectangle(cornerSize: CGSize(width: 16.0, height: 16.0), style: .circular))
                                    .padding()
                                    
                                    Text(project.title)
                                        .font(.title2)
                                        .fontWeight(.bold)
                                        .padding(.horizontal)
                                    
                                    Text(project.description_)
                                        .font(.callout)
                                        .padding(.top, 2)
                                        .padding(.leading)
                                    
                                    if project.maintained {
                                        Text("ACTIVELY MAINTAINED")
                                            .font(
                                                .system(
                                                    .caption2,
                                                    design: .monospaced
                                                )
                                            )
                                            .padding(.horizontal, 8)
                                            .padding(.vertical, 4)
                                            .background(Color(.systemGray5))
                                            .clipShape(Capsule())
                                            .padding(.top, 4)
                                            .padding(.horizontal)
                                    } else {
                                        Text("NO LONGER MAINTAINED")
                                            .font(
                                                .system(
                                                    .caption2,
                                                    design: .monospaced
                                                )
                                            )
                                            .padding(.horizontal, 8)
                                            .padding(.vertical, 4)
                                            .background(Color(.systemGray5))
                                            .clipShape(Capsule())
                                            .padding(.top, 4)
                                            .padding(.horizontal)
                                    }
                                    
                                    if index != portfolioData.apps.count - 1 {
                                        Spacer()
                                            .frame(height: 24)
                                    }
                                }
                            }
                        }
                        
                        Section(
                            header: SectionHeader(title: "Experience")
                                .frame(
                                    maxWidth: .infinity,
                                    alignment: .leading
                                )
                                .padding(.leading)
                                .padding(.vertical)
                                .padding(.top)
                        ) {
                            ForEach(Array(portfolioData.experience.enumerated()), id: \.1) { index, experienceItem in
                                VStack(
                                    alignment: .leading
                                ) {
                                    Text(experienceItem.title)
                                        .font(.title2)
                                        .fontWeight(.bold)
                                        .frame(
                                            maxWidth: .infinity,
                                            alignment: .leading
                                        )
                                        .padding(.horizontal)
                                    
                                    HStack {
                                        Image(systemName: "mappin")
                                            .resizable()
                                            .scaledToFit()
                                            .frame(width: 16, height: 16)
                                        
                                        Text(experienceItem.location)
                                            .font(.callout)
                                            .frame(
                                                maxWidth: .infinity,
                                                alignment: .leading
                                            )
                                            .padding(.leading, 4)
                                    }
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 4)
                                    .padding(.horizontal)
                                    
                                    HStack {
                                        Image(systemName: "calendar")
                                            .resizable()
                                            .scaledToFit()
                                            .frame(width: 16, height: 16)
                                        
                                        Text(experienceItem.dateRange)
                                            .font(.callout)
                                            .frame(
                                                maxWidth: .infinity,
                                                alignment: .leading
                                            )
                                            .padding(.leading, 4)
                                    }
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 4)
                                    .padding(.horizontal)
                                    
                                    ForEach(Array(experienceItem.content.enumerated()), id: \.1) { index, contentItem in
                                        HStack(
                                            alignment: .top
                                        ) {
                                            Image(systemName: "arrow.right")
                                                .resizable()
                                                .scaledToFit()
                                                .frame(width: 16, height: 16)
                                                .padding(.top, 4)
                                            
                                            Text(contentItem)
                                                .font(.callout)
                                                .frame(
                                                    maxWidth: .infinity,
                                                    alignment: .leading
                                                )
                                                .padding(.leading, 4)
                                        }
                                        .frame(
                                            maxWidth: .infinity,
                                            alignment: .leading
                                        )
                                        .padding(.top, 2)
                                        .padding(.horizontal)
                                    }
                                    .padding(.top, 2)
                                }
                                .padding(.vertical)
                            }
                        }
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
