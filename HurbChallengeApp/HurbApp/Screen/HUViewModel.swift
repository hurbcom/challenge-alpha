//
//  HUViewModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import Foundation

protocol HUViewModelProtocol {
    func loadData()
}

final class HUViewModel {

    init() {
        print("[DEBUG] HomeViewModel \(#function)")
    }

    deinit {
        print("[DEBUG] HomeViewModel \(#function)")
    }

}

extension HUViewModel: HUViewModelProtocol {

    func loadData() {
        print("[DEBUG] HomeViewModel \(#function)")
    }

}
