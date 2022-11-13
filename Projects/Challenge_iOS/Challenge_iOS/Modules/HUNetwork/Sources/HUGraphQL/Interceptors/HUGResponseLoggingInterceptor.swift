//
//  HUGResponseLoggingInterceptor.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Apollo
import os.log

internal final class HUGResponseLoggingInterceptor: ApolloInterceptor {
    enum ResponseLoggingError: Error {
        case notYetReceived
    }
            
    internal func interceptAsync<Operation: GraphQLOperation>(
        chain: RequestChain,
        request: HTTPRequest<Operation>,
        response: HTTPResponse<Operation>?,
        completion: @escaping (Result<GraphQLResult<Operation.Data>, Error>) -> Void) {
            defer {
                // Even if we can't log, we still want to keep going.
                chain.proceedAsync(request: request,
                                response: response,
                                completion: completion)
            }
        
            guard let receivedResponse = response else {
                chain.handleErrorAsync(ResponseLoggingError.notYetReceived,
                                   request: request,
                                   response: response,
                                   completion: completion)
                return
            }
        
            os_log("🌐 📥 HTTP Response: %s", log: HUGLogger.networkingLog(), type: .debug, "\(receivedResponse.httpResponse)")
        
            if let stringData = receivedResponse.rawData.prettyJson {
                os_log("🌐 📥 Data: %s", log: HUGLogger.networkingLog(), type: .debug, "\(stringData)")
            } else {
                os_log("🌐 📥 Could not convert data to string", log: HUGLogger.networkingLog(), type: .error)
            }
    }
}

