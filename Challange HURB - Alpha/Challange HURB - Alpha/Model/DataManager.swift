//
//  DAO.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 05/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation
import os.log

/// Class resposible to treat the data received from the API
class DataManager {
    
    // MARK: - Properties
    
    fileprivate var networkAdapter: NetworkAdapter?
    
    var experiences: [Experience] = []
    
    var hotels: [Experience] {
        experiences.lazy.filter({ ($0.isHotel ?? false) == true }).sorted(by: >)
    }
    
    var packages: [Experience] {
        experiences.lazy.filter({ ($0.isPackage ?? false) == true })
    }
    
    weak var coordinatorComunicationDelegate: CoordinatorComunicationDelegate?
    
    // MARK: - Initializer
    init(state: DataManagerState) {
        switch state {
        case .test:
            networkAdapter = NetworkAdapter(state: .test)
        case .production:
            networkAdapter = NetworkAdapter(state: .production)
        }
        getExperiences()
    }
    
    // MARK: - Methods
    
    /// Makes the request to API and "saves" the information in the experiences array
    private func getExperiences() {
        self.networkAdapter!.getAPIRespose(query: "buzios", page: 2) { (result) in
            switch result {
            case .success(let apiResponse):
                os_log(.info, "Query made successfully")
                dump(apiResponse)
                self.experiences = apiResponse.results
                self.coordinatorComunicationDelegate?.coordinator?.instantiateView(for: .feedPrepared)
            case .failure(let error):
                os_log(.error, "Error in the API query:")
                dump(error)
                self.coordinatorComunicationDelegate?.coordinator?.instantiateView(for: .loadingError)
            }
        }
    }
}

enum DataManagerState {
    case test, production
}
