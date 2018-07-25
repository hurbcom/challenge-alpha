//
//  Hotels.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Hotels : Codable {
	let meta : Meta?
	let filters : Filters?
	let results : [Results]?
	let pagination : Pagination?

	enum CodingKeys: String, CodingKey {

		case meta 
		case filters 
		case results 
		case pagination 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		meta = try values.decodeIfPresent(Meta.self, forKey: .meta)
		filters = try values.decodeIfPresent(Filters.self, forKey: .filters)
		results = try values.decodeIfPresent([Results].self, forKey: .results)
		pagination = try values.decodeIfPresent(Pagination.self, forKey: .pagination)
	}

}