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
    
    private let repository: PortfolioRepository
    init(repository: PortfolioRepository) {
        self.repository = repository
    }
    
    func getPortfolioData() {
        repository.getPortfolioData(callback: { data in
            DispatchQueue.main.async {
                self.screenState = ScreenState.success(data: data)
            }
        })
    }
}
