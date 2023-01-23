//
//  SearchResultModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 22/01/23.
//

import HUGraphQL

typealias SearchResultPrice = HUGraphQL.SearchQuery.Data.Search.Result.Price
typealias SearchResultAddress = HUGraphQL.SearchQuery.Data.Search.Result.Address

typealias SearchResult = HUGraphQL.SearchQuery.Data.Search.Result

struct SearchResultModel {

    let name: String

    let smallDescription: String
    let longDescription: String

    let category: String

    let isAvailable: Bool

    init(searchResult: SearchResult) {
        self.name = searchResult.name
        self.smallDescription = searchResult.smallDescription
        self.longDescription =  searchResult.description
        self.category = searchResult.category
        self.isAvailable = searchResult.isAvailable
    }

}
