//
//  ProjectView.swift
//  iosApp
//
//  Created by amanshu raikwar on 01/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage

struct ProjectView: View {
    let project: AppData
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            ZStack {
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
            
            ProjectMaintainedView(maintained: project.maintained)
                .padding(.top, 4)
                .padding(.horizontal)
            
            HStack {
                Spacer()
                
                Link(destination: URL(string: getUrl(appLink: project.appLinks[0]))!) {
                    Image(systemName: "link")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 24, height: 24)
                }
                .padding(8)
                .background(Color(.systemGray5))
                .clipShape(RoundedRectangle(cornerSize: CGSize(width: 12.0, height: 12.0), style: .circular))
            }
            .padding(.horizontal)
            .padding(.top, 4)
        }
    }
    
    func getUrl(appLink: AppLink) -> String {
        var url: String = ""
        if appLink is AppLink.Github {
            url = (project.appLinks[0] as! AppLink.Github).repoUrl
        }
        if appLink is AppLink.PlayStore {
            url = (project.appLinks[0] as! AppLink.PlayStore).playStoreListingUrl
        }
        if appLink is AppLink.Download {
            url = (project.appLinks[0] as! AppLink.Download).downloadUrl
        }
        if appLink is AppLink.Other {
            url = (project.appLinks[0] as! AppLink.Other).url
        }
        return url
    }
}
