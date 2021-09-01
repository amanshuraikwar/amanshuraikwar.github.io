//
//  ProjectMaintainedView.swift
//  iosApp
//
//  Created by amanshu raikwar on 01/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ProjectMaintainedView: View {
    let maintained: Bool
    
    var body: some View {
        let text: String
        if maintained {
            text = "ACTIVELY MAINTAINED"
        } else {
            text = "NO LONGER MAINTAINED"
        }
        
        return HStack {
            Image(systemName: "chevron.left.slash.chevron.right")
                .resizable()
                .scaledToFit()
                .frame(width: 16, height: 16)
            
            Spacer()
                .frame(width: 8)
            
            Text(text)
                .font(
                    .system(
                        .caption2,
                        design: .monospaced
                    )
                )
        }
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(Color(.systemGray5))
        .clipShape(Capsule())
    }
}
