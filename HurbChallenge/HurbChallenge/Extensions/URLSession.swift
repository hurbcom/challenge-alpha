//
//  URLSession.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

/**
 Essa extensão foi criada para facilitar o acesso à API, abstraindo a conversão de um JSON para um modelo interno que seja Codable
 */
extension URLSession {
    
    /**
     Retorna uma sessionDataTask que já se responsabiliza por transformar o retorno no formato desejado.
     
     - parameter url: A url a ser acessada
     - parameter completionHandler: O closure a ser executado com o resultado
     - returns: Uma URLSessionDataTask
    */
    fileprivate func codableTask<T: Codable>(with url: URL, completionHandler: @escaping (T?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        return self.dataTask(with: url) { data, response, error in
            guard let data = data, error == nil else {
                completionHandler(nil, response, error)
                return
            }
            do {
                let obj = try JSONDecoder().decode(T.self, from: data)
                completionHandler(obj, response, nil)
            } catch let ex {
                print(ex)
                completionHandler(nil, response, ex)
            }
            
        }
    }
    
    /**
     Esse método abstrai a criação de uma sessionDataTask para obter um objeto no tipo Page da url especificada
     
     - parameter url: A url a ser acessada
     - parameter completionHandler: O closure a ser executado com o resultado
     - returns: Uma URLSessionDataTask
    */
    func pageTask(with url: URL, completionHandler: @escaping (Page?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        return self.codableTask(with: url, completionHandler: completionHandler)
    }
}
