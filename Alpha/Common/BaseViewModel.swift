//
//  BaseViewModel.swift
//  Alpha
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import os.log

/// Base class for all View Models of the project
class BaseViewModel: NSObject {
    static var logEnabled: Bool = true

    override init() {
        super.init()
        if BaseViewModel.logEnabled {
            os_log("🧠 👶 %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }

    deinit {
        if BaseViewModel.logEnabled {
            os_log("🧠 ⚰️ %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }
}
