//
//  ProductDetailsWorkerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class ProductDetailsWorkerSpy: ProductDetailsWorkerProtocol {

    var invokedDoSomeWork = false
    var invokedDoSomeWorkCount = 0

    func doSomeWork() {
        invokedDoSomeWork = true
        invokedDoSomeWorkCount += 1
    }
}
