//
//  CityDetailsTableViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

class CityDetailsView: UIView {
    
    // MARK: - Private Properties UI
    private let hotelImageView: UIImageView = {
        let hotelImageView = UIImageView()
        hotelImageView.contentMode = .scaleToFill
        hotelImageView.translatesAutoresizingMaskIntoConstraints = false
        return hotelImageView
    }()
    
    private let hotelLabel: UILabel = {
        let hotelLabel = UILabel()
        hotelLabel.font = .systemFont(ofSize: 15, weight: .medium)
        hotelLabel.textColor = .black
        hotelLabel.lineBreakMode = .byWordWrapping
        hotelLabel.numberOfLines = 0
        hotelLabel.translatesAutoresizingMaskIntoConstraints = false
        return hotelLabel
    }()
    
    private let descriptionLabel: UILabel = {
        let descriptionLabel = UILabel()
        descriptionLabel.font = .systemFont(ofSize: 12, weight: .regular)
        descriptionLabel.textColor = .black
        descriptionLabel.numberOfLines = 3
        descriptionLabel.textAlignment = .justified
        descriptionLabel.lineBreakMode = .byTruncatingTail
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        return descriptionLabel
    }()
    
    private let priceLabel: UILabel = {
        let priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 15, weight: .bold)
        priceLabel.textColor = .systemGreen
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        return priceLabel
    }()
    
    private lazy var shareButton: UIButton = {
        let shareButton = UIButton()
        if let image = UIImage(named: "share") {
            shareButton.setImage(image, for: .normal)}
//        shareButton.addTarget(self, action:#selector(self.presentShareSheet), for: .touchUpInside)
        shareButton.translatesAutoresizingMaskIntoConstraints = false
        return shareButton
    }()
    
}
