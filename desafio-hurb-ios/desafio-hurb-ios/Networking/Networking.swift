//
//  Networking.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

enum NetworkingError: Error {
    case badURL
    case requestError
    case decodingError
}

class Networking {
    
    static let HURB_URL: String = "https://www.hurb.com/"
    static let defaultSession = URLSession(configuration: .default)
    static var dataTask: URLSessionDataTask?
    
    static func hurbAPICall(with endpoint: Endpoint, result: @escaping (Result<Data, NetworkingError>)->()) {
        
        dataTask?.cancel()
        
        let completeURLString = HURB_URL + endpoint.endpoint
        guard let requestURL = URL(string: completeURLString) else {
            result(.failure(NetworkingError.badURL))
            return
        }
        
        var request = URLRequest(url: requestURL)
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpMethod = endpoint.method.rawValue
        request.httpBody = endpoint.parameters
        
        let dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            
            print("Requesting at: \(requestURL.absoluteString)")
            if let parameters = endpoint.parameters {
                print("With parameters: \(String(describing: String(data: parameters, encoding: .utf8)))")
            }
            
            if error != nil {
                result(.failure(NetworkingError.requestError))
            }
            else if let data = data, let response = response as? HTTPURLResponse, response.statusCode == 200 {
                DispatchQueue.main.async {
                    result(.success(data))
                }
            }
            else {
                DispatchQueue.main.async {
                    result(.failure(NetworkingError.requestError))
                }
            }
        }
        
        dataTask.resume()
    }
        
}
