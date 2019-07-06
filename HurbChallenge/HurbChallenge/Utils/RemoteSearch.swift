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
    case fetchFailed
}

class RemoteSearch {
    
    private let baseUrlString: String = "https://www.hurb.com/search/api"
    private var latestPagination: Pagination? = nil
    var canceled = false
    var searchTerm: String
    
    required init(term: String) {
        searchTerm = term
    }
    
    func loadNextPage() -> Promise<[SearchResultElement]> {
        return Promise<[SearchResultElement]> { resolve, reject in
            let url = self.nextPageUrl()
            UIApplication.shared.isNetworkActivityIndicatorVisible = true
            URLSession
                .shared
                .pageTask(with: url, completionHandler: { (page, _, error) in
                    guard let page = page, error == nil else {
                        if !self.canceled {
                            reject(error ?? RemoteError.fetchFailed)
                        }
                        return
                    }
                    self.latestPagination = page.pagination
                    DispatchQueue.main.async {
                        UIApplication.shared.isNetworkActivityIndicatorVisible = true
                    }
                    if !self.canceled {
                        resolve(page.results ?? [])
                    }
                }).resume()
        }
    }
    
    func hasMoreToLoad() -> Bool {
        guard let latestPagination = latestPagination else {
            return false
        }
        return latestPagination.nextPage != nil
    }
}

fileprivate extension RemoteSearch {
    
    func initialPageUrl() -> URL {
        let queryItems = [URLQueryItem(name: "q", value: self.searchTerm), URLQueryItem(name: "sort", value: "stars")]
        let components = NSURLComponents(string: baseUrlString)!
        components.queryItems = queryItems
        return components.url!
    }
    
    func nextPageUrl() -> URL {
        let url = self.latestPagination?.fixedNextPageUrl() ?? self.initialPageUrl()
        return url
    }
}
