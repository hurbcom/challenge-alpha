//
//  StoryboardError.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 09/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// A type representing an error related to the creation of ViewControllers from Storyboards
enum StoryboardError: Error {
    
    /// A case indicating that there was no Storyboard file with the expected name
    case noFile(fileName: String)
    
    /// A case indicating that the Storyboard was not correctely configured
    case badConfiguration(className: String)
    
    /// A message describing the error
    var message: String {
        switch self {
        case .noFile(let fileName):
            return "Cannot instantiate ViewController via Storyboard because there's no Storyboard file named \(fileName). "
        case .badConfiguration(let className):
            return "The class \(className) cannot be instantiated via Storyboard. If you did create a Storyboard for this View Controller, make sure it is correctly configured. "
        }
    }
}

extension StoryboardError: LocalizedError {
    
    /// The localized description of the `Error`
    public var errorDescription: String? {
        return message
    }
}
