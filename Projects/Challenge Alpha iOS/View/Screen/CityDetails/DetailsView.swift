//
//  CityDetailsTableViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

class DetailsView: UIView {
    
    // MARK: - Private Properties UI
    private lazy var hotelImageView: UIImageView = {
        let hotelImageView = UIImageView()
        hotelImageView.contentMode = .scaleToFill
        hotelImageView.translatesAutoresizingMaskIntoConstraints = false
        return hotelImageView
    }()
    
    private lazy var hoteNamelLabel: UILabel = {
        let hotelLabel = UILabel()
        hotelLabel.font = .systemFont(ofSize: 15, weight: .medium)
        hotelLabel.textColor = .black
        hotelLabel.lineBreakMode = .byWordWrapping
        hotelLabel.numberOfLines = 0
        hotelLabel.textAlignment = .center
        hotelLabel.translatesAutoresizingMaskIntoConstraints = false
        return hotelLabel
    }()
    
    private lazy var descriptionLabel: UILabel = {
        let descriptionLabel = UILabel()
        descriptionLabel.font = .systemFont(ofSize: 12, weight: .regular)
        descriptionLabel.textColor = .black
        descriptionLabel.numberOfLines = 3
        descriptionLabel.textAlignment = .justified
        descriptionLabel.lineBreakMode = .byTruncatingTail
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        return descriptionLabel
    }()
    
    private lazy var priceLabel: UILabel = {
        let priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 15, weight: .bold)
        priceLabel.textColor = .systemGreen
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.textAlignment = .left
        return priceLabel
    }()
    
    private lazy var shareButton: UIButton = {
        let shareButton = UIButton()
        if let image = UIImage(named: "share") {
            shareButton.setImage(image, for: .normal)}
//        shareButton.addTarget(self, action:#selector(self. ), for: .touchUpInside)
        shareButton.translatesAutoresizingMaskIntoConstraints = false
        return shareButton
    }()
    
    private lazy var stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        return stackView
    }()
    
    // MARK: - Init
     init() {
        super.init(frame: .zero)
         setupView() 
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
   
    }
    
}

extension DetailsView: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        self.addSubview(hotelImageView)
        self.addSubview(hoteNamelLabel)
        stackView.addArrangedSubview(priceLabel)
        stackView.addArrangedSubview(shareButton)
        self.addSubview(descriptionLabel)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            hotelImageView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            hotelImageView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            hotelImageView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            hoteNamelLabel.topAnchor.constraint(equalTo: hotelImageView.bottomAnchor, constant: 10.0),
            hoteNamelLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            hoteNamelLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            stackView.topAnchor.constraint(equalTo: hoteNamelLabel.bottomAnchor, constant: 10.0),
            stackView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            stackView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            descriptionLabel.topAnchor.constraint(equalTo: stackView.bottomAnchor, constant: 10.0),
            descriptionLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            descriptionLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor),
        ])
    }

    func setupAdditionalConfiguration() {}
}

