//
//  ErrorCustom.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 14/11/22.
//

import Foundation

enum CustomError: Error {
    case invalidURL
    case encodingFailed
    case statusCode(_ statusCode: Int, _ customMessage: String?)
    case custom(_ message: String)
    case unknown
}
