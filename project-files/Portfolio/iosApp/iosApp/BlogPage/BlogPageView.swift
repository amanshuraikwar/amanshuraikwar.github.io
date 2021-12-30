//
//  BlogPageView.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 30/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BlogPageView: View {
    let pageId: String
    @StateObject var viewModel = BlogPageViewModel()
    
    var body: some View {
        ZStack {
            switch viewModel.screenState {
            case .fetching:
                ProgressView()
            case .success(let data):
                List {
                    ForEach(Array(data.enumerated()), id: \.1) { index, mdNode in
                        VStack {
                            if let h1 = mdNode as? MdNode.H1 {
                                Text(h1.text)
                                    .font(PortfolioFonts.largeTitle)
                                    .fontWeight(.bold)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                            }
                            
                            if let h3 = mdNode as? MdNode.H3 {
                                Text(h3.text)
                                    .font(PortfolioFonts.title)
                                    .fontWeight(.medium)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 24)
                            }
                            
                            if let p = mdNode as? MdNode.P {
                                Text(p.text)
                                    .font(PortfolioFonts.body)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                                    .padding(.top, 8)
                            }
                            
                            if let date = mdNode as? MdNode.Date {
                                Text(date.text)
                                    .font(PortfolioFonts.footnote)
                                    .foregroundColor(.accentColor)
                                    .fontWeight(.medium)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )
                            }
                            
                            if let code = mdNode as? MdNode.Code {
                                ScrollView(.horizontal) {
                                    Text(code.code)
                                        .font(PortfolioFonts.calloutMonospaced)
                                        .padding(8)
                                        .frame(
                                            maxWidth: .infinity,
                                            alignment: .leading
                                        )
                                }
                                .background(Color(.systemGray6))
                                .padding(.top, 8)
                            }
                            
                            if let img = mdNode as? MdNode.Img {
                                AsyncImage(
                                    url: URL(
                                        string: img.url
                                    )
                                ) { image in
                                    image.resizable().scaledToFit()
                                } placeholder: {
                                    Color(.systemGray6)
                                }
                                .frame(height: 256)
                                .padding(.top, 8)
                                
                                Text(img.label)
                                    .font(PortfolioFonts.caption)
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .center
                                    )
                                    .padding(.top, 2)
                            }
                            
                            if let btn = mdNode as? MdNode.Btn {
                                PrimaryButton(
                                    text: btn.text,
                                    action: {
                                        UIApplication.shared.open(
                                            NSURL(string: btn.url)! as URL
                                        )
                                    },
                                    iconSystemName: nil
                                )
                                    .padding(.top, 8)
                            }
                        }
                    }
                    .listRowSeparator(.hidden)
                }
                .listStyle(PlainListStyle())
            }
        }
        
        .onAppear {
            Task.init {
                await viewModel.getBlogPageData(pageId)
            }
        }
    }
}

//struct BlogPageView_Previews: PreviewProvider {
//    static var previews: some View {
//        BlogPageView()
//    }
//}
