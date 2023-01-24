//
//  SearchDetailViewModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import Foundation

protocol SearchDetailViewModelDelegate: AnyObject {
    func viewModel(contentData item: SearchResult)
    func viewModel(didShareButtonTouched activityItems: [Any])
}

protocol SearchDetailViewModelProtocol {
    func loadContentData()
    func favoriteButtonTouched()
    func shareButtonTouched()
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

    func favoriteButtonTouched() {

    }

    func shareButtonTouched() {

        guard let shareURL = URL(string: item.url) else {
            return
        }

        let activityItems: [Any] = [
            shareURL
        ]

        delegate?.viewModel(didShareButtonTouched: activityItems)
    }

}
