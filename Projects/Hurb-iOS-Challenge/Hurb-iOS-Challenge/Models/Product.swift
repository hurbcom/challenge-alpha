//
//  Product.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 06/01/23.
//

import UIKit

struct Product: Codable, Identifiable, Hashable {
    
    static func == (lhs: Product, rhs: Product) -> Bool {
        return lhs.id == rhs.id
    }
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(self.id)
    }
    
    private enum CodingKeys: String, CodingKey {
        
        case id
        case url
        case category
        case description
        case medias = "gallery"
        case price
        case location = "address"
        case name
        case amenities
    }
    
    var id: String?
    let url: String?
    let category: Category
    let description: String
    let medias: [Media]
    let price: Price
    let location: Location
    let name: String
    let amenities: [Amenity]
}

// Decode methods
extension Product {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        self.id = try? container.decodeIfPresent(String.self, forKey: .id)
        self.url = try? container.decodeIfPresent(String.self, forKey: .url)
        self.category = try container.decode(Category.self, forKey: .category)
        self.description = try container.decode(String.self, forKey: .description)
        self.medias = try container.decode([Media].self, forKey: .medias)
        self.price = try container.decode(Price.self, forKey: .price)
        self.location = try container.decode(Location.self, forKey: .location)
        self.name = try container.decode(String.self, forKey: .name)
        self.amenities = try container.decode([Amenity].self, forKey: .amenities)
    }
    
    func getFormattedAddress() -> String {
        
        return "\(self.location.city ?? ""), \(self.location.country ?? "")"
    }
    
    func getFormattedPrice() -> String? {
        
        let amountValue: Double = self.price.amount
        let formatter: NumberFormatter = NumberFormatter.currencyFormatter(from: self.price.currency)
        return formatter.string(for: amountValue)
    }
    
    func getPriceTopText() -> String? {
        
        switch self.category {
                
            case .hotel: return "Diárias a partir de"
            case .package: return "A partir de"
            case .activity: return "A partir de"
                
            default:
                return nil
        }
    }
    
    func getPriceBottomText() -> String? {
        
        switch self.category {
                
            case .hotel: return "+ Taxas"
            case .package: return "+ Taxas | até 12x no cartão"
            case .activity: return "em até 12x"
                
            default:
                return nil
        }
    }
}
