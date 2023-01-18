//
//  ResultViewModel.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 18/01/23.
//

import Foundation

class ResultViewModel: ResultServiceProtocol {
    
    private var resultModel = [Result]()
    private var resultService : Result? = nil
    
    func success(result: [Result]) {
        self.resultService = result[0].results
    }
    
    func error(error: Error) {
        print("Erro ao carregar os filmes")
    }
}
