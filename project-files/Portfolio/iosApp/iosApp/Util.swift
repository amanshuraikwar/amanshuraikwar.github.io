//
//  Util.swift
//  iosApp
//
//  Created by Amanshu Raikwar on 31/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

class Util {
    public static func onMain(predicate: () -> ()) {
        DispatchQueue.main.sync {
            predicate()
        }
    }
}
