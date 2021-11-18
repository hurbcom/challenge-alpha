import UIKit

struct SearchResult {
    let name: String
    let description: String
    let cover: String
    let price: String
    let address: String
    let category: ResultCategory
    let gallery: [ResultImage]
}

struct ResultImage {
    let url: String
    let name: String
}

enum ResultCategory: String {
    case hotel, package, activity, none

    var name: String {
        switch self {
            case .hotel: return "Hotel"
            case .package: return "Pacote"
            case .activity: return "Atividade"
            case .none: return ""
        }
    }

    var icon: UIImage? {
        switch self {
            case .hotel: return UIImage(systemName: "building.2.crop.circle")
            case .package: return UIImage(systemName: "shippingbox.circle")
            case .activity: return UIImage(systemName: "bolt.circle")
            case .none: return UIImage(systemName: "questionmark.circle")
        }
    }

    var color: UIColor {
        switch self {
            case .hotel: return .systemBlue
            case .package: return .systemOrange
            case .activity: return .systemGreen
            case .none: return .lightGray
        }
    }
}
