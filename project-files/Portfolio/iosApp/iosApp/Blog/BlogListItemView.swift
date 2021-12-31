//
//  BlogListItemView.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BlogListItemView: View {
    let data: BlogListDataItem
    
    var body: some View {
        VStack(
            alignment: .leading,
            spacing: 0
        ) {
            Text(data.title)
                .font(PortfolioFonts.title)
                .fontWeight(.bold)
                .multilineTextAlignment(.leading)
                .frame(
                    maxWidth: .infinity,
                    alignment: .leading
                )
                .fixedSize(horizontal: false, vertical: true)
                .padding(.top, 12)
            
            Text(data.firstParagraph)
                .font(PortfolioFonts.body)
                .fontWeight(.regular)
                .multilineTextAlignment(.leading)
                .lineLimit(3)
                .frame(
                    maxWidth: .infinity,
                    alignment: .leading
                )
                .padding(.top, 8)
            
            Text(data.date)
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

//struct BlogListItemView_Previews: PreviewProvider {
//    static var previews: some View {
//        BlogListItemView()
//    }
//}
