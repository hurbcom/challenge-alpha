//
//  DAO.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 05/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Class resposible to treat the data received from the API
class DataManager {
    
    // MARK: - Properties
    
    fileprivate var networkAdapter = NetworkAdapter()
    
    var experiences: [Experience] = []
    
    var hotels: [Experience] {
        experiences.lazy.filter({ ($0.isHotel ?? false) == true }).sorted(by: >)
    }
    
    var packages: [Experience] {
        experiences.lazy.filter({ ($0.isPackage ?? false) == true })
    }
    
    weak var coordinatorComunicationDelegate: CoordinatorComunicationDelegate?
    
    // MARK: - Initializer
    init() {
       getExperiences()
    }
    
    // MARK: - Methods
    
    /// Makes the request to API and "saves" the information in the experiences array
    private func getExperiences() {
        self.networkAdapter.getAPIRespose { (result) in
            switch result {
            case .success(let apiResponse):
                self.experiences = apiResponse.results
                self.coordinatorComunicationDelegate?.coordinator?.instantiateView(for: .feedPrepared)
            case .failure(let error):
                debugPrint("Error in the API query: \(error)")
                self.coordinatorComunicationDelegate?.coordinator?.instantiateView(for: .loadingError)
            }
        }
    }
}
