//
//  BaseCoordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//
import os.log

/// The coordinator that holds all coordinators
class BaseCoordinator: Coordinator {
    static var logEnabled: Bool = true
    var childCoordinators: [Coordinator] = []
    /// Is called when the coordinator is completed
    var isCompleted: (() -> ())?

    func start() {
        fatalError("Children should implement `start`.")
    }

    init() {
        if BaseCoordinator.logEnabled {
            os_log("🧭 👶 %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }

    deinit {
        if BaseCoordinator.logEnabled {
            os_log("🧭 ⚰️ %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }
}
