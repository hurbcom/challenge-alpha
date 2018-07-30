//
//  QuantityDescriptors.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Regulation : Codable {
	let term : String?
	let filter : String?
	let count : Int?

	enum CodingKeys: String, CodingKey {

		case term 
		case filter 
		case count 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		term = try values.decodeIfPresent(String.self, forKey: .term)
		filter = try values.decodeIfPresent(String.self, forKey: .filter)
		count = try values.decodeIfPresent(Int.self, forKey: .count)
	}

}