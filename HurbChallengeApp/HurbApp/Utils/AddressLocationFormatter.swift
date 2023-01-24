//
//  AddressLocationFormatter.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 24/01/23.
//

import Foundation

struct AddressLocationFormatter {

    static func getLocationFormatter(address: SearchResultAddress) -> String {

        let addressList = [
            address.city,
            address.state,
            address.country
        ].compactMap { $0 }

        return addressList.joined(separator: ", ")
    }

}
