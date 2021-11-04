//
//  HUGRequestLoggingInterceptor.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Apollo
import os.log
import Foundation

internal final class HUGRequestLoggingInterceptor: ApolloInterceptor {
    
    internal func interceptAsync<Operation: GraphQLOperation>(chain: RequestChain,
                                                              request: HTTPRequest<Operation>,
                                                              response: HTTPResponse<Operation>?,
                                                              completion: @escaping (Result<GraphQLResult<Operation.Data>, Error>) -> Void) {
        let jsonData = try? JSONSerialization.data(withJSONObject: request.operation.variables.jsonValue, options: [])
        if let jsonString = jsonData?.prettyJson {
            os_log("🌐 📤 Variables: %s", log: HUGLogger.networkingLog(), type: .debug, "\(jsonString)")
        } else {
            os_log("🌐 📤 Could not convert data to string", log: HUGLogger.networkingLog(), type: .error)
        }
        
        chain.proceedAsync(request: request,
                           response: response,
                           completion: completion)
    }
}
