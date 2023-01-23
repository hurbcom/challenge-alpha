//
//  SearchDetailViewModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import Foundation

protocol SearchDetailViewModelDelegate: AnyObject {
    func viewModel(contentData item: SearchResult)
}

protocol SearchDetailViewModelProtocol {
    func loadContentData()
}

final class SearchDetailViewModel {

    weak var delegate: SearchDetailViewModelDelegate?

    private let item: SearchResult

    init(item: SearchResult) {
        self.item = item
    }

}

extension SearchDetailViewModel: SearchDetailViewModelProtocol {

    func loadContentData() {
        delegate?.viewModel(contentData: item)
    }

}
