//
//  EndpointTypeProtocol.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 26/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

protocol EndpointType {
    var baseURL: URL { get }
    var path: String { get }
}
