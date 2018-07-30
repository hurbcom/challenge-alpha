//
//  QuantityDescriptors.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct QuantityDescriptors : Codable {
	let maxChildren : Int?
	let maxAdults : Int?
	let maxFreeChildrenAge : Int?

	enum CodingKeys: String, CodingKey {

		case maxChildren 
		case maxAdults 
		case maxFreeChildrenAge 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		maxChildren = try values.decodeIfPresent(Int.self, forKey: .maxChildren)
		maxAdults = try values.decodeIfPresent(Int.self, forKey: .maxAdults)
		maxFreeChildrenAge = try values.decodeIfPresent(Int.self, forKey: .maxFreeChildrenAge)
	}

}