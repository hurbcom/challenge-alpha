//
//  HomeView.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit
import HUGraphQL

class HomeVIew: UIView {
    
    var viewModel = ResultViewModel()
    typealias Result = HUGraphQL.SearchQuery.Data.Search.Result
    
    // MARK: - Properties UI
    lazy var imageHotel: UIImageView = {
        let image = UIImageView()
        image.translatesAutoresizingMaskIntoConstraints = false
        image.clipsToBounds = true
        image.layer.cornerRadius = 18.0
        image.layer.masksToBounds = true
        image.isAccessibilityElement = true
        image.accessibilityTraits = .image
        image.contentMode = .scaleToFill
        return image
    }()
    
    lazy var hotelTituloLabel: UILabel = {
        let hotelLabel = UILabel()
        hotelLabel.font = .systemFont(ofSize: 15, weight: .medium)
        hotelLabel.textColor = .black
        hotelLabel.lineBreakMode = .byWordWrapping
        hotelLabel.numberOfLines = 0
        hotelLabel.isAccessibilityElement = true
        hotelLabel.accessibilityTraits = .staticText
        hotelLabel.translatesAutoresizingMaskIntoConstraints = false
        return hotelLabel
    }()
    
    lazy var priceLabel: UILabel = {
        let  priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 15, weight: .bold)
        priceLabel.textColor = .systemGreen
        priceLabel.isAccessibilityElement = true
        priceLabel.accessibilityTraits = .staticText
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        return priceLabel
    }()
    
    private lazy var textStackView: UIStackView = {
        let stack = UIStackView()
        stack.translatesAutoresizingMaskIntoConstraints = false
        stack.axis = .vertical
        stack.distribution = .fillProportionally
        stack.isAccessibilityElement = false
        stack.spacing = 8
        return stack
    }()
    
    private lazy var mainStackView: UIStackView = {
        let stack = UIStackView()
        stack.translatesAutoresizingMaskIntoConstraints = false
        stack.axis = .horizontal
        stack.distribution = .fillProportionally
        stack.spacing = 16.0
        stack.isAccessibilityElement = false
        stack.alignment = .center
        return stack
    }()
    
    func setupCell(hotel: Result) {
        hotelTituloLabel.text = hotel.name
        let price = String(hotel.price.amount)
        priceLabel.text = "R$\(price)"
        let urlImage = hotel.gallery[0].url ?? ""
        DispatchQueue.main.async {
            self.imageHotel.loadFrom(URLAdress: urlImage)
        }
    }
    
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

extension HomeVIew: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        addSubview(mainStackView)
        
        mainStackView.addArrangedSubview(imageHotel)
        mainStackView.addArrangedSubview(textStackView)
        
        textStackView.addArrangedSubview(hotelTituloLabel)
        textStackView.addArrangedSubview(priceLabel)
    }

    func setupConstraints() {
        NSLayoutConstraint.activate([
            mainStackView.topAnchor.constraint(equalTo: topAnchor, constant: 16.0),
            mainStackView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -16.0),
            mainStackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 24.0),
            mainStackView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -24.0),
            
            imageHotel.widthAnchor.constraint(equalToConstant: 90),
            imageHotel.heightAnchor.constraint(equalToConstant: 90),
        ])
    }

    func setupAdditionalConfiguration() {}
}
