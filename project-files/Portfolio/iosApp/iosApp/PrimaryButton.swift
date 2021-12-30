//
//  PrimaryButton.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PrimaryButton: View {
    let text: String
    let action: () -> Void
    let iconSystemName: String?
    
    var body: some View {
        Button(action: action) {
            HStack {
                Text(text)
                    .font(PortfolioFonts.body)
                    .fontWeight(.bold)
                
                if let iconSystemName = iconSystemName {
                    Image(systemName: iconSystemName)
                }
            }
        }
        .foregroundColor(.primary)
        .padding(14)
        .frame(
            maxWidth: .infinity,
            alignment: .center
        )
        .background(Color.accentColor)
        .cornerRadius(16)
    }
}
