//
//  Meta.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Meta : Codable {
	let count : Int?
	let offset : Int?
	let query : String?
	let warning : String?
	let countWithAvailabilityInPage : Int?
	let responseTime : ResponseTime?
	let countHotel : Int?
	let countPackage : Int?

	enum CodingKeys: String, CodingKey {

		case count 
		case offset 
		case query 
		case warning 
		case countWithAvailabilityInPage 
		case responseTime
		case countHotel 
		case countPackage 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		count = try values.decodeIfPresent(Int.self, forKey: .count)
		offset = try values.decodeIfPresent(Int.self, forKey: .offset)
		query = try values.decodeIfPresent(String.self, forKey: .query)
		warning = try values.decodeIfPresent(String.self, forKey: .warning)
		countWithAvailabilityInPage = try values.decodeIfPresent(Int.self, forKey: .countWithAvailabilityInPage)
		responseTime = try values.decodeIfPresent(ResponseTime.self, forKey: .responseTime)
		countHotel = try values.decodeIfPresent(Int.self, forKey: .countHotel)
		countPackage = try values.decodeIfPresent(Int.self, forKey: .countPackage)
	}

}