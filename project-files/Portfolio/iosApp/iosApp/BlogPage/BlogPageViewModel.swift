//
//  BlogPageViewModel.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 30/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class BlogPageViewModel: ObservableObject {
    @Published var screenState: BlogPageScreenState = .fetching

    private let repository: PortfolioRepository = PortfolioRepository(
        portfolioApi: PortfolioApi(
            client: Factory.shared.createHttpClient(
                json: Factory.shared.createJson(),
                enableNetworkLogs: true
            ),
            baseUrl: "https://amanshuraikwar.github.io/api"
        )
    )
    
    func getBlogPageData(_ pageId: String) async {
        do {
            let blogPageData = try await repository.getBlogPageData(pageId: pageId)
            DispatchQueue.main.sync {
                self.screenState = .success(data: blogPageData)
            }
        }
        catch {
            print("Task error: \(error)")
        }
    }
}

enum BlogPageScreenState {
    case fetching
    case success(data: [MdNode])
}
