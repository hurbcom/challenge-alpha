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
    // MARK: - Properties

    /// Variable to enable or disable view's logs
    static var logEnabled: Bool = true
    let provider: AlphaAPI

    // MARK: - Lifecycle

    /**
    Initializes a new View Model with logging

    - Parameters:
       - provider: The provider that the view model shoud use to make requests

    - Returns: A Base View View Model
    */
    init(provider: AlphaAPI) {
        self.provider = provider
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
