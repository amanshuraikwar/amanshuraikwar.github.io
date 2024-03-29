//
//  ViewModel.swift
//  iosApp
//
//  Created by amanshu raikwar on 17/08/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class ViewModel: ObservableObject {
    @Published var screenState: ScreenState = ScreenState.fetching
    
    private let repository: PortfolioRepository = PortfolioRepository(
        portfolioApi: PortfolioApi(
            client: Factory.shared.createHttpClient(
                json: Factory.shared.createJson(),
                enableNetworkLogs: true
            ),
            baseUrl: "https://amanshuraikwar.github.io/api"
        )
    )
    
    func getPortfolioData() async {
        do {
            let blogListData = try await repository.getBlogListData()
            DispatchQueue.main.sync {
                self.screenState = .success(data: blogListData)
            }
        }
        catch {
            print("Task error: \(error)")
        }
    }
}
