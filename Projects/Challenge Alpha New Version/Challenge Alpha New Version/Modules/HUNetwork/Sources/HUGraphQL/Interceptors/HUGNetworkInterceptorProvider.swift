//
//  HUGNetworkInterceptorProvider.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Apollo

internal struct HUGNetworkInterceptorProvider: InterceptorProvider {
    private let store: ApolloStore
    private let client: URLSessionClient
    private let logEnabled: Bool
    
    internal init(store: ApolloStore, client: URLSessionClient, logEnabled: Bool = true) {
        self.store = store
        self.client = client
        self.logEnabled = logEnabled
    }
    
    internal func interceptors<Operation: GraphQLOperation>(for operation: Operation) -> [ApolloInterceptor] {
        var array: [ApolloInterceptor] = [
            MaxRetryInterceptor(),
            CacheReadInterceptor(store: self.store),
            NetworkFetchInterceptor(client: client),
            JSONResponseParsingInterceptor(cacheKeyForObject: self.store.cacheKeyForObject),
            AutomaticPersistedQueryInterceptor(),
            CacheWriteInterceptor(store: self.store),
        ]
        
        if logEnabled {
            array.append(HUGResponseLoggingInterceptor())
            array.append(HUGRequestLoggingInterceptor())
        }
        
        return array
    }
}

