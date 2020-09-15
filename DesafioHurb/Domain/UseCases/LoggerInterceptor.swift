//
//  LoggerInterceptor.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

final class LoggerInterceptor {
    static let shared = LoggerInterceptor()
    
    private init() {}
    
    func intercept(request: URLRequest) {
        print("\n=================================")
        let method = request.httpMethod ?? "<no method>"
        let url = request.url?.absoluteString ?? "<no url>"
        
        print("\(method) \(url)")
        
        if let headers = request.allHTTPHeaderFields {
            print("HEADERS")
            print(headers as AnyObject)
        }
    }
    
    func intercept(response: URLResponse?, data: Data?, error: Error?, for request: URLRequest) {
        print("Status Code: \((response as? HTTPURLResponse)?.statusCode ?? 0)")
        print("BODY")
        print(prettyPrintedJSON(for: request.httpBody))
        
        print("RESPONSE")
        print(prettyPrintedJSON(for: data))
        
        if let error = error {
            print("ERROR")
            dump(error)
        }
        print("=================================\n")
    }
    
    private func prettyPrintedJSON(for data: Data?) -> String {
        if let json = try? JSONSerialization.jsonObject(with: data ?? Data(), options: []),
            let prettyPrintedData = try? JSONSerialization.data(withJSONObject: json, options: .prettyPrinted) {
            return String(data: prettyPrintedData, encoding: .utf8) ?? "<empty>"
        }
        
        return String(data: data ?? Data(), encoding: .utf8) ?? "<empty>"
    }
}
