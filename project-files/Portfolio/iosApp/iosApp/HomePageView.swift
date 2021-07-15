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
        let item = homePageDataList.first(where: { $0 is HomePageData.Heading })
        
        NavigationView {
            List {
                ForEach(homePageDataList, id: \.hash) { data in
                    if data is HomePageData.Heading {
                        HeadingView(data: data as! HomePageData.Heading)
                    } else if data is HomePageData.MyLinks {
                        let myLinks = data as! HomePageData.MyLinks
                        PortfolioLinksView(
                            heading: myLinks.heading,
                            links: myLinks.linkDataList
                        )
                    } else if data is HomePageData.LastUpdated {
                        Section {
                            Text((data as! HomePageData.LastUpdated).message)
                        }
                    } else if data is HomePageData.MadeWith {
                        Section {
                            Text((data as! HomePageData.MadeWith).message)
                        }
                    }
                }
            }
            .listStyle(InsetGroupedListStyle())
            .navigationBarTitle((item as! HomePageData.Heading).name)
        }
    }
}
