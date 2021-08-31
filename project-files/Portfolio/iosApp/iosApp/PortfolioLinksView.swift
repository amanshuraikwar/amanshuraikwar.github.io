//
//  PortfolioLinksView.swift
//  iosApp
//
//  Created by amanshu raikwar on 15/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PortfolioLinksView: View {
    let heading: String
    let links: [LinkData]
    
    var body: some View {
        Section(
            header: Text(heading)
        ) {
            ForEach(links, id: \.hash) { linkData in
                PortfolioLinkButton(
                    name: linkData.title,
                    url: linkData.url
                )
            }
        }
    }
}
