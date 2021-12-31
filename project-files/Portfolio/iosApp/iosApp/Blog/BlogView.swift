//
//  BlogView.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct BlogView: View {
    @StateObject var viewModel = BlogViewModel()
    
    var body: some View {
        ZStack {
            switch viewModel.screenState {
            case .fetching:
                ProgressView()
            case .success(let data):
                List {
                    ForEach(
                        Array(data.enumerated()),
                        id: \.1
                    ) { index, blogListDataItem in
                        NavigationLink(
                            destination: BlogPageView(
                                pageId: blogListDataItem.id
                            )
                        ) {
                            BlogListItemView(data: blogListDataItem)
                        }
                    }
                }
                .listStyle(PlainListStyle())
            }
        }
        .onAppear {
            Task.init {
                await viewModel.getBlogListData()
            }
        }
    }
}

//struct BlogView_Previews: PreviewProvider {
//    static var previews: some View {
//        BlogView()
//    }
//}
