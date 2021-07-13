//
//  HeadingView.swift
//  iosApp
//
//  Created by amanshu raikwar on 12/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HeadingView: View {
    let data: HomePageData.Heading
    
    var body: some View {
        VStack {
            Text(data.name)
                .font(.largeTitle)
                .frame(maxWidth: .infinity, alignment: .leading))
            
            Text(data.intro)
                .font(.body)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.top, 2)
                .padding(.bottom, 2)
        }
    }
}
