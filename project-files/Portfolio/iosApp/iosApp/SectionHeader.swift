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
                .frame(width: 48, height: 4)
                .background(Color(.systemBlue))
                .clipShape(Capsule())
                .padding(.top, 1)
        }
    }
}

struct SectionHeader_Previews: PreviewProvider {
    static var previews: some View {
        SectionHeader(title: "title")
    }
}
