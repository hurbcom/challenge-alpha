//
//  ImageDataLoader.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 16/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public protocol ImageDataLoaderTask {
    func cancel()
}

public protocol ImageDataLoader {
    typealias Result = Swift.Result<Data, Error>
    
    func loadImageData(from url: URL, completion: @escaping (Result) -> Void) -> ImageDataLoaderTask
}
