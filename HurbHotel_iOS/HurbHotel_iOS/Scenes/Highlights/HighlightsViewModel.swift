//
//  HighlightsViewModel.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

final class HighlightsViewModel {
    
    // MARK: Properties
    private let service = HighlightsService()
    var highlights: Highlights?
    var shouldReloadData: (() -> ())?
    var didFailure: ((String?) -> ())?
    
    // MARK: Methods
    func fetchHighlights() {
        service.fetchHighlights(success: { [weak self] highlights in
            self?.highlights = highlights
            self?.shouldReloadData?()
        }) { [weak self] error in
            self?.didFailure?(error)
        }
    }
}
