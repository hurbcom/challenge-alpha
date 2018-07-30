//
//  Address.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation 

struct Address : Codable {
	let city : String?
	let country : String?
	let idCity : Int?
	let idCountry : Int?
	let idState : Int?
	let state : String?
	let street : String?
	let zipcode : String?
	let geoLocation : GeoLocation?

	enum CodingKeys: String, CodingKey {

		case city
		case country 
		case idCity = "id_city"
		case idCountry = "id_country"
		case idState = "id_state"
		case state 
		case street
		case zipcode 
		case geoLocation 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		city = try values.decodeIfPresent(String.self, forKey: .city)
		country = try values.decodeIfPresent(String.self, forKey: .country)
		idCity = try values.decodeIfPresent(Int.self, forKey: .idCity)
		idCountry = try values.decodeIfPresent(Int.self, forKey: .idCountry)
		idState = try values.decodeIfPresent(Int.self, forKey: .idState)
		state = try values.decodeIfPresent(String.self, forKey: .state)
		street = try values.decodeIfPresent(String.self, forKey: .street)
		zipcode = try values.decodeIfPresent(String.self, forKey: .zipcode)
		geoLocation = try values.decodeIfPresent(GeoLocation.self, forKey: .geoLocation)
	}

}
