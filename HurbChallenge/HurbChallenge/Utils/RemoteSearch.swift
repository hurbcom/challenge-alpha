//
//  RemoteSearch.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation
import Promises

enum RemoteError: Error {
    case loadFailed
    case noMorePagesToLoad
}

class RemoteSearch {
    
    private let baseUrlString: String = "https://www.hurb.com/search/api"
    private var latestPagination: Pagination? = nil
    var canceled = false
    var searchTerm: String
    var isInitialLoad: Bool { return latestPagination == nil }
    var hasMoreToLoad: Bool { return latestPagination?.nextPage != nil }
    
    required init(term: String) {
        searchTerm = term
    }
}

// MARK: - Métodos públicos

extension RemoteSearch {
    
    /**
     Carrega a próxima página, se houver, e retorna a lista de resultados.
     
     - returns: Uma promise contendo a lista de resultados da página seguinte
    */
    func loadMore() -> Promise<[SearchResultElement]> {
        return Promise<[SearchResultElement]> { resolve, reject in
            
            // O carregamento só ocorre caso seja o carregamento inicial (latestPagination == nil) ou ainda tenhamos mais páginas a carregar (hsMoreToLoad == true)
            guard self.isInitialLoad || self.hasMoreToLoad else {
                reject(RemoteError.noMorePagesToLoad)
                return
            }
            
            let url = self.nextPageUrl()
            URLSession
                .shared
                .pageTask(with: url, completionHandler: { (page, _, error) in
                    guard let page = page, error == nil else {
                        if !self.canceled {
                            reject(error ?? RemoteError.loadFailed)
                        }
                        return
                    }
                    self.latestPagination = page.pagination
                    if !self.canceled {
                        resolve(page.results ?? [])
                    }
                }).resume()
        }
    }
}

// MARK: - Métodos Privados

fileprivate extension RemoteSearch {
    
    /**
     Retorna a url para a página inicial da busca com o termo desejado, incluindo os queryParameters necessários.
     
     - returns: A url da página inicial da busca com o termo desejado, incluindo os queryParameters necessários
    */
    func initialPageUrl() -> URL {
        let queryItems = [URLQueryItem(name: "q", value: self.searchTerm), URLQueryItem(name: "sort", value: "stars")]
        let components = NSURLComponents(string: baseUrlString)!
        components.queryItems = queryItems
        return components.url!
    }
    
    /**
     Retorna a url da próxima página válida ou, caso não exista, retorna a url da página inicial
     
     - returns: A url da próxima página a ser buscada
    */
    func nextPageUrl() -> URL {
        let url = self.latestPagination?.fixedNextPageUrl() ?? self.initialPageUrl()
        return url
    }
}
