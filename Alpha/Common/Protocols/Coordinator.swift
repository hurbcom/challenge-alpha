//
//  Coordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

/// Protocol that all Coordinators must conform to
protocol Coordinator: class {
    /// Child Coordinators
    var childCoordinators: [Coordinator] { get set }
    /// Start Coordinator
    func start()
}

extension Coordinator {
    /**
    Adds a coordinator to the child coordinators stack

    - Parameter coordinator: The coordinator that should be added
    */
    func store(coordinator: Coordinator) {
        childCoordinators.append(coordinator)
    }
    /**
    Removes a coordinator from the stack

    - Parameter coordinator: The coordinator that should be removed
    */
    func free(coordinator: Coordinator) {
        childCoordinators = childCoordinators.filter { $0 !== coordinator }
    }
}
