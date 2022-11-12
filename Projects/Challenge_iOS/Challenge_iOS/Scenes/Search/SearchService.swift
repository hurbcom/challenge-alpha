//
//  SearchService.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation

protocol SearchServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping ([String]) -> Void)
    func fetchResultsFrom(query: String, completion: ([String]) -> Void)
}

struct SearchService: SearchServiceProtocol {
    
    // MARK: Properties
    
    // MARK: Methods
    func getSuggestionsFrom(text: String, completion: @escaping ([String]) -> Void) {
        
        completion(["Sugestão 1"])
        
        DispatchQueue.global().asyncAfter(deadline: .now() + 1) {
            completion(["Sugestão 1", "Sugestão 2", "Sugestão 3"])
        }
        DispatchQueue.global().asyncAfter(deadline: .now() + 2) {
            completion(["Sugestão 1", "Sugestão 2", "Sugestão 3", "Sugestão 4", "Sugestão 5", "Sugestão 6"])
        }
    }
    
    func fetchResultsFrom(query: String, completion: ([String]) -> Void) {
        completion(["Resultado 1", "Resulrado 2"])
    }
}
