//
//  SearchResultsDataSource.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 06/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation
import Promises

// MARK: - Section
// Essa classe interna serve para facilitar a definição das propriedades específicas de cada seção
fileprivate class Section {
    private var _title: String
    var items: [SearchResultElement]
    var index: Int
    var title: String? { return items.count > 0 ? _title : nil }
    
    required init(title: String, index: Int) {
        self._title = title
        self.index = index
        self.items = [SearchResultElement]()
    }
}

// MARK: -
class SearchResultsDataSource {
    
    // MARK: Propriedades privadas
    private var fiveStarsSection = Section(title: "5 estrelas", index: 0)
    private var fourStarsSection = Section(title: "4 estrelas", index: 1)
    private var threeStarsSection = Section(title: "3 estrelas", index: 2)
    private var twoStarsSection = Section(title: "2 estrelas", index: 3)
    private var oneStarSection = Section(title: "1 estrela", index: 4)
    private var ticketsSection = Section(title: "Tickets", index: 5)
    private var packagesSection = Section(title: "Pacotes", index: 6)
}

// MARK: - Métodos Públicos

extension SearchResultsDataSource {
    
    /**
     Adiciona os novos resultados às seções corretas e retorna uma promise com os indexPaths das novas células.
     
     - parameter list: A lista de resultados a ser adicionada
     */
    func update(with list: [SearchResultElement]) -> Promise<[IndexPath]> {
        return Promise<[IndexPath]> { resolve, reject in
            var indexPaths = [IndexPath]()
            for (_, item) in list.enumerated() {
                guard let section = self.section(for: item) else { continue }
                section.items.append(item)
                indexPaths.append(IndexPath(row: section.items.count - 1, section: section.index))
            }
            resolve(indexPaths)
        }
    }
    
    /**
     Retorna o título de uma seção.
     
     - parameter index: O índica da seção desejada
     - returns: O título da seção, se existir
    */
    func titleForHeaderInSection(_ index: Int) -> String? {
        guard let section = section(for: index) else { return nil }
        return section.title
    }
    
    /**
     Retorna a quantidade de seções existentes.
     
     - returns: A quantidade de seções
    */
    func numberOfSections() -> Int {
        return 7
    }
    
    /**
     Retorna a quantidade de items em uma dada seção.
     
     - parameter index: O índice da seção desejada
     - returns: A quantidade de itens na seção indicada
    */
    func numberOfRowsInSection(_ index: Int) -> Int {
        guard let section = section(for: index) else { return 0 }
        return section.items.count
    }
    
    /**
     Retorna o item no indexPath especificado
     
     - parameter indexPath: O indexPath do item desejado
     - returns: O item no indexPath especificado, se existir
    */
    func item(at indexPath: IndexPath) -> SearchResultElement? {
        guard let section = section(for: indexPath.section) else { return nil }
        return section.items[indexPath.row]
    }
}

// MARK: - Métodos privados

fileprivate extension SearchResultsDataSource {
    
    /**
     Retorna a seção no índice especificado
     
     - parameter index: O indice da seção desejada
     - returns: A seção no indice especificado, se houver
    */
    func section(for index: Int) -> Section? {
        switch index {
        case fiveStarsSection.index: return fiveStarsSection
        case fourStarsSection.index: return fourStarsSection
        case threeStarsSection.index: return threeStarsSection
        case twoStarsSection.index: return twoStarsSection
        case oneStarSection.index: return oneStarSection
        case ticketsSection.index: return ticketsSection
        case packagesSection.index: return packagesSection
        default: return nil
        }
    }
    
    /**
     Retorna a seção do item especificado
     
     - parameter item: O item de quem a seção desejamos encontrar
     - returns: A seção no item especificado, se houver
     */
    func section(for item: SearchResultElement) -> Section? {
        if let stars = item.stars {
            switch stars {
            case 5: return fiveStarsSection
            case 4: return fourStarsSection
            case 3: return threeStarsSection
            case 2: return twoStarsSection
            case 1: return oneStarSection
            default: return nil
            }
        } else if let isTicket = item.isTicket, isTicket == true {
            return ticketsSection
        } else if let isPackage = item.isPackage, isPackage == true {
            return packagesSection
        } else {
            return nil
        }
    }
}
