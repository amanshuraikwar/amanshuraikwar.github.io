//
//  BlogViewModel.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class BlogViewModel: ObservableObject {
    @Published var screenState: BlogViewScreenState = .fetching
    
    private let repo = Di.get().portfolioRepository
    
    func getBlogListData() async {
        do {
            let blogListData = try await repo.getBlogListData()
            Util.onMain {
                self.screenState = .success(data: blogListData)
            }
        }
        catch {
            print("Task error: \(error)")
        }
    }
}

enum BlogViewScreenState {
    case fetching
    case success(data: [BlogListDataItem])
}
