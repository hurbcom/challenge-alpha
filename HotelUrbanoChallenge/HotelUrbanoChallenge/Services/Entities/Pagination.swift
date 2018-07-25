//
//  Pagination.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Pagination : Codable {
	let count : Int?
	let firstPage : String?
	let nextPage : String?
	let previousPage : String?
	let lastPage : String?

	enum CodingKeys: String, CodingKey {

		case count 
		case firstPage 
		case nextPage 
		case previousPage 
		case lastPage 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		count = try values.decodeIfPresent(Int.self, forKey: .count)
		firstPage = try values.decodeIfPresent(String.self, forKey: .firstPage)
		nextPage = try values.decodeIfPresent(String.self, forKey: .nextPage)
		previousPage = try values.decodeIfPresent(String.self, forKey: .previousPage)
		lastPage = try values.decodeIfPresent(String.self, forKey: .lastPage)
	}

}