//
//  SuggestionViewModel.swift
//  Hurb
//
//  Created by Alexandre Papanis on 10/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

class SuggestionViewModel {
    
    //MARK: - Variables
    private let suggestion: Suggestion
    
    //MARK: - Init
    init(_ suggestion: Suggestion) {
        self.suggestion = suggestion
    }
    
    //MARK: - Hotel properties
    var name: String {
        return suggestion.text ?? ""
    }
    
    var type: String {
        switch suggestion.suggestionType {
        case "city": return "Cidade"
        case "estado": return "Estado"
        case "country": return "País"
        case "hotel": return "Hotel"
        case "package": return "Pacote"
        default: return ""
        }
    }
}
