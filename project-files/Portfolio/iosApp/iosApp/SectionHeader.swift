//
//  SectionHeader.swift
//  iosApp
//
//  Created by amanshu raikwar on 31/08/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SectionHeader: View {
    let title: String
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            Text(title)
                .font(.headline)
                .frame(
                    maxWidth: .infinity,
                    alignment: .leading
                )
            
            Spacer()
                .frame(height: 2)
            
            Spacer()
                .frame(width: 48, height: 2)
                .background(Color(.systemBlue))
                .clipShape(Capsule())
        }
        .frame(
            maxWidth: .infinity,
            alignment: .leading
        )
        .padding(.horizontal)
        .padding(.vertical, 8)
        .background(Color(.systemGray6))
    }
}

struct SectionHeader_Previews: PreviewProvider {
    static var previews: some View {
        SectionHeader(title: "title")
    }
}
