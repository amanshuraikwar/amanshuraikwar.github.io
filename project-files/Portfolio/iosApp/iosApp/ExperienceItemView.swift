//
//  ExperienceItemView.swift
//  iosApp
//
//  Created by amanshu raikwar on 01/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ExperienceItemView: View {
    let data: ExperienceData
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            Text(data.title)
                .font(.title2)
                .fontWeight(.bold)
                .frame(
                    maxWidth: .infinity,
                    alignment: .leading
                )
                .padding(.horizontal)
            
            HStack {
                Image(systemName: "mappin")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 16, height: 16)
                
                Text(data.location)
                    .font(.callout)
                    .frame(
                        maxWidth: .infinity,
                        alignment: .leading
                    )
                    .padding(.leading, 4)
            }
            .frame(
                maxWidth: .infinity,
                alignment: .leading
            )
            .padding(.top, 4)
            .padding(.horizontal)
            
            HStack {
                Image(systemName: "calendar")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 16, height: 16)
                
                Text(data.dateRange)
                    .font(.callout)
                    .frame(
                        maxWidth: .infinity,
                        alignment: .leading
                    )
                    .padding(.leading, 4)
            }
            .frame(
                maxWidth: .infinity,
                alignment: .leading
            )
            .padding(.top, 4)
            .padding(.horizontal)
            
            ForEach(Array(data.content.enumerated()), id: \.1) { index, contentItem in
                HStack(
                    alignment: .top
                ) {
                    Image(systemName: "arrow.right")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 16, height: 16)
                        .padding(.top, 4)
                    
                    Text(contentItem)
                        .font(.callout)
                        .frame(
                            maxWidth: .infinity,
                            alignment: .leading
                        )
                        .padding(.leading, 4)
                }
                .frame(
                    maxWidth: .infinity,
                    alignment: .leading
                )
                .padding(.top, 2)
                .padding(.horizontal)
            }
            .padding(.top, 2)
        }
    }
}
