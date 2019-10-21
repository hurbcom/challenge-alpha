//
//  AlphaAPI.swift
//  Alpha
//
//  Created by Theo Mendes on 20/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa

protocol AlphaAPI {
    func search(query: String, page: Int) -> Single<HurbResponse>
}
