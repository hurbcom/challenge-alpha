//
//  HotelListViewModel.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

class HotelListViewModel: ObservableObject {
    
    @Published var hotelsGroup = [AccommodationGroup]()
    @Published var isLoading = true
    @Published var showMsgError = false

    init() {
        fetchHotelList()
    }
    
    // MARK: - Methods
    /// load date from api
    fileprivate func fetchHotelList() {
        
        let apiService = APIService()
        apiService.getAccommodations(query: "buzios", page: 3) { (accommodation, error) in
            self.isLoading = false
            if let error = error {
                self.showMsgError = true
                print(error)
                return
            }
            dump(accommodation)
            self.groupValues(accommodation: accommodation!)
        }

    }
    // MARK: - Methods
    /// agroup values for stars or package
    fileprivate func groupValues (accommodation: [Accommodation]) {
        
        // filtering hotels and grouping by stars
        let filteredItems = accommodation.filter { $0.stars != nil }
        let grouped = Dictionary(grouping: filteredItems, by: { ($0.stars!) }).sorted { $0.0 > $1.0
        }
        let sectionsStars = grouped.map({ AccommodationGroup(stars: $0.key, value: $0.value) })

        // filtering and grouping packages
        var listAccommodation = [AccommodationGroup]()

        let filtesPackage = accommodation.filter { $0.isPackage != nil }
        if !filtesPackage.isEmpty {
            
            //create list agroup accommodation
            let acGroup = AccommodationGroup(stars: 0, value: filtesPackage)
            listAccommodation.append(acGroup)
        }
    
        //merge list hotels and packeges
        listAccommodation.append(contentsOf: sectionsStars)
        
        self.hotelsGroup = listAccommodation
    }
}
