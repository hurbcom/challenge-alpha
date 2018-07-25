//
//  ResponseTime.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct ResponseTime : Codable {
	let searchEngine : Int?
	let total : Int?

	enum CodingKeys: String, CodingKey {

		case searchEngine 
		case total 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		searchEngine = try values.decodeIfPresent(Int.self, forKey: .searchEngine)
		total = try values.decodeIfPresent(Int.self, forKey: .total)
	}

}