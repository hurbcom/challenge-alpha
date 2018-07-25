//
//  Price.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Price : Codable {
	let currentPrice : Double?
	let oldPrice : Double?
	let sku : String?
	let originalAmountPerDay : Double?
	let amountPerDay : Double?
	let amount : Double?

	enum CodingKeys: String, CodingKey {

		case currentPrice = "current_price"
		case oldPrice = "old_price"
		case sku 
		case originalAmountPerDay 
		case amountPerDay 
		case amount 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		currentPrice = try values.decodeIfPresent(Double.self, forKey: .currentPrice)
		oldPrice = try values.decodeIfPresent(Double.self, forKey: .oldPrice)
		sku = try values.decodeIfPresent(String.self, forKey: .sku)
		originalAmountPerDay = try values.decodeIfPresent(Double.self, forKey: .originalAmountPerDay)
		amountPerDay = try values.decodeIfPresent(Double.self, forKey: .amountPerDay)
		amount = try values.decodeIfPresent(Double.self, forKey: .amount)
	}

}