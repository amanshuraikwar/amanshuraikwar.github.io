//
//  Di.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class Di {
    private static let instance = Di()
    
    let portfolioRepository: PortfolioRepository = PortfolioRepository(
        portfolioApi: PortfolioApi(
            client: Factory.shared.createHttpClient(
                json: Factory.shared.createJson(),
                enableNetworkLogs: true
            ),
            baseUrl: "https://amanshuraikwar.github.io/api"
        )
    )
    
    private init() {}
    
    static func get() -> Di {
        return instance
    }
}
