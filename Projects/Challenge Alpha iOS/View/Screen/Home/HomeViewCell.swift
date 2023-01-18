//
//  HomeViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class HomeViewCell: UITableViewCell {
    
    static let identifier = "HomeViewCell"
    
    // MARK: - Private Properties UI
    lazy var imageCity: UIImageView = {
        let image = UIImageView()
        image.translatesAutoresizingMaskIntoConstraints = false
        image.clipsToBounds = true
        image.layer.cornerRadius = 18.0
        image.layer.masksToBounds = true
        image.contentMode = .scaleToFill
        return image
    }()
    
    lazy var hotelTituloLabel: UILabel = {
        let hotelLabel = UILabel()
        hotelLabel.font = .systemFont(ofSize: 15, weight: .medium)
        hotelLabel.textColor = .black
        hotelLabel.lineBreakMode = .byWordWrapping
        hotelLabel.numberOfLines = 0
        hotelLabel.translatesAutoresizingMaskIntoConstraints = false
        return hotelLabel
    }()
    
    lazy var priceLabel: UILabel = {
        let  priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 15, weight: .bold)
        priceLabel.textColor = .systemGreen
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        return priceLabel
    }()
    
    
    // MARK: - Init
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setup() {
        
    }
    
}

extension HomeViewCell: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {}

    func setupConstraints() {}

    func setupAdditionalConfiguration() {}
}
