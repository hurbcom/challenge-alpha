//
//  ResultViewModel.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 18/01/23.
//

import Foundation
import HUGraphQL

//MARK: - Protocol
protocol ResultViewModelProtocol {
        func reloadData()
}

class ResultViewModel {
    
    var service = HotelService.shared
    var delegate: ResultViewModelProtocol?
    var hotels: [HUGraphQL.SearchQuery.Data.Search.Result] = []
    
    
    func searchHotel(_ informations: String) {
        service.request(search: informations) { result in
            if let infoAPI = result {
                for hotel in infoAPI {
                    if let hotelResult = hotel {
                        self.hotels.append(hotelResult)
                    }
                }
                self.delegate?.reloadData()
            }
        }
    }
}
