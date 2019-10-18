//
//  ViewModelType.swift
//  Alpha
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

/// Protocol that all ViewModels must conform to
protocol ViewModelType {
    /// Events consumed by the View Model
    associatedtype Input
    /// Output streams created by the view model
    associatedtype Output
    /**
    Transform all the inputs into outputs

    - Parameter input: All the inputs required by the view model

    - Returns: The ViewModel's outputs.
    */
    func transform(input: Input) -> Output
}
