//
//  PortfolioLinkButton.swift
//  iosApp
//
//  Created by amanshu raikwar on 13/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PortfolioLinkButton: View {
    let name: String
    let url: String
    
    var body: some View {
        Button {
            UIApplication.shared.open(NSURL(string: url)! as URL)
        } label: {
            HStack {
                Text(name)
                    .fontWeight(.medium)
                    .foregroundColor(.primary)
                Spacer()
                Image(systemName: "chevron.right")
            }
            .padding(.horizontal)
            .padding(.vertical, 8)
            .background(Color(.systemGray5))
            .clipShape(RoundedRectangle(cornerSize: CGSize(width: 12.0, height: 12.0), style: .circular))
        }
    }
}
