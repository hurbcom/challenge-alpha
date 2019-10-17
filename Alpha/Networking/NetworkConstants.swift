//
//  NetworkConstants.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

/// All the constants avaible to use in the API call
struct NetworkConstants {
    /**
        Avaible headers for the API request

        [More info here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers)
    */
    enum HTTPHeaderField: String {
        /// Authorization header
        case authentication = "Authorization"
        /// Content-Type header
        case contentType = "Content-Type"
        /// Accept Type header
        case acceptType = "Accept"
        /// Accept-Encoding header
        case acceptEncoding = "Accept-Encoding"
    }

    /// A Enum for the **Content-Type** header
    enum ContentType: String {
        /// `application/json` Content-Type
        case json = "application/json"
    }
}
