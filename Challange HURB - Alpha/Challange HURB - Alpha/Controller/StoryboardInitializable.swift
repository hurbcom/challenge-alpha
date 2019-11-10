//
//  StoryboardInitializable.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 05/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

/// A type that can be initialized from a Storyboard
protocol StoryboardInitializable where Self: UIViewController {
    
    /// A type representing errors thrown during an initialization from a Storyboard
    associatedtype StoryboardInitializationError: Error
    
    /// The name of the Storyboard file
    static var storyboardName: String { get }
    
    /// The ViewController indentifier in the Storyboard file
    static var storyboardID: String { get }
    
    /**
     Initializes a Storyboard named `storyboardName` and returns it's initial
     View Controller
     */
    static func initializeFromStoryboard() throws -> Self
    
}

extension StoryboardInitializable where Self: UIViewController {
    
    typealias StoryboardInitializationError = StoryboardError
    
    static func initializeFromStoryboard() throws -> Self {
        
        // Get the bundle for self
        let bundle = Bundle(for: self)
        // Check if the storyboard exists
        // Use extension "storyboardc" to check for the compiled version of a storyboard
        guard let _ = bundle.path(forResource: storyboardName, ofType: "storyboardc") else {
            throw StoryboardInitializationError.noFile(fileName: storyboardName)
        }
        
        let storyboard = UIStoryboard(name: storyboardName, bundle: bundle)
        let viewController = storyboard.instantiateViewController(identifier: storyboardID)
        
        guard let vc = viewController as? Self else {
            throw StoryboardInitializationError.badConfiguration(className: String(describing: Self.self))
        }
        
        return vc
    }
}
