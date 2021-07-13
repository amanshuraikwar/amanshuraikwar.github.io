//
//  HomePageView.swift
//  iosApp
//
//  Created by amanshu raikwar on 12/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomePageView: View {
    var homePageDataList: [HomePageData]
    
    var body: some View {
        List {
            ForEach(homePageDataList, id: \.hash) { data in
                if data is HomePageData.Heading {
                    HeadingView(data: data as! HomePageData.Heading)
                } else if data is HomePageData.MyLinks {
                    let myLinks = data as! HomePageData.MyLinks
                    Section(header: Text(myLinks.heading)) {
                        ForEach(myLinks.linkDataList, id: \.hash) { linkData in
                            PortfolioLinkButton(
                                name: linkData.name,
                                url: linkData.url
                            )
                        }
                    }
                } else if data is HomePageData.LastUpdated {
                    Text((data as! HomePageData.LastUpdated).message)
                } else if data is HomePageData.MadeWith {
                    Text((data as! HomePageData.MadeWith).message)
                        .font(.caption)
                        .padding(.bottom, 2)
                }
            }
        }
        .listStyle(InsetGroupedListStyle())
    }
}
