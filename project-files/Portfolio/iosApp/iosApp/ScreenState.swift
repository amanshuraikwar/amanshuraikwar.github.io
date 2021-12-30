//
//  ScreenState.swift
//  iosApp
//
//  Created by amanshu raikwar on 17/08/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

enum ScreenState {
    case fetching
    case success(data: [BlogListDataItem])
}
