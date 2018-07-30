//
//  Filters.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Filters : Codable {
	let attributes : [Attributes]?
	let countries : [Countries]?
	let cities : [Cities]?
	let departureCities : [DepartureCities]?
	let district : [District]?
	let duration : [Duration]?
	let food : [Food]?
	let people : [People]?
	let prices : [Prices]?
	let priceInterval : PriceInterval?
	let productType : [ProductType]?
	let regulation : [Regulation]?
	let rooms : [Rooms]?
	let stars : [Stars]?
	let states : [States]?

	enum CodingKeys: String, CodingKey {

		case attributes 
		case countries 
		case cities 
		case departureCities 
		case district 
		case duration 
		case food
		case people 
		case prices 
		case priceInterval 
		case productType 
		case regulation 
		case rooms 
		case stars 
		case states 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		attributes = try values.decodeIfPresent([Attributes].self, forKey: .attributes)
		countries = try values.decodeIfPresent([Countries].self, forKey: .countries)
		cities = try values.decodeIfPresent([Cities].self, forKey: .cities)
		departureCities = try values.decodeIfPresent([DepartureCities].self, forKey: .departureCities)
		district = try values.decodeIfPresent([District].self, forKey: .district)
		duration = try values.decodeIfPresent([Duration].self, forKey: .duration)
		food = try values.decodeIfPresent([Food].self, forKey: .food)
		people = try values.decodeIfPresent([People].self, forKey: .people)
		prices = try values.decodeIfPresent([Prices].self, forKey: .prices)
		priceInterval = try values.decodeIfPresent(PriceInterval.self, forKey: .priceInterval)
		productType = try values.decodeIfPresent([ProductType].self, forKey: .productType)
		regulation = try values.decodeIfPresent([Regulation].self, forKey: .regulation)
		rooms = try values.decodeIfPresent([Rooms].self, forKey: .rooms)
		stars = try values.decodeIfPresent([Stars].self, forKey: .stars)
		states = try values.decodeIfPresent([States].self, forKey: .states)
	}

}