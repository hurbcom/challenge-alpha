//
//  HUGService.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Apollo
import os.log
import Foundation

public final class HUGService {
    /// Log is enabled
    private var logEnabled: Bool
    private var endpoint = URL(string: "https://graphql.ghucdn.net/graphql")!
    
    public var client: ApolloClient {
        let client = URLSessionClient()
        let cache = InMemoryNormalizedCache()
        let store = ApolloStore(cache: cache)
        let provider = HUGNetworkInterceptorProvider(store: store, client: client, logEnabled: logEnabled)
        let requestChainTransport = RequestChainNetworkTransport(interceptorProvider: provider, endpointURL: endpoint)
        return ApolloClient(networkTransport: requestChainTransport, store: store)
    }
    
    public init(enableLog: Bool = true) {
        self.logEnabled = enableLog
        
        if enableLog {
            os_log("🌐 👶 %@", log: HUGLogger.lifecycleLog(), type: .info, "\(self)")
        }
    }
    
    deinit {
        if self.logEnabled {
            os_log("🌐 ⚰️ %@", log: HUGLogger.lifecycleLog(), type: .info, "\(self)")
        }
    }
}
