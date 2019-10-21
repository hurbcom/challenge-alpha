//
//  AlphaNetworkManager.swift
//  Alpha
//
//  Created by Theo Mendes on 20/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import Moya
import RxMoya
import RxSwift

struct AlphaNetworkManager: AlphaAPI {
    private let provider = MoyaProvider<HurbAPI>(plugins: [NetworkLoggerPlugin()])

    static let shared = AlphaNetworkManager()

    private init() {}

    func search(query: String, page: Int) -> Single<HurbResponse> {
        return provider.rx
            .request(.search(query: query, page: page))
            .filterSuccessfulStatusAndRedirectCodes()
            .map(HurbResponse.self)
    }
}
