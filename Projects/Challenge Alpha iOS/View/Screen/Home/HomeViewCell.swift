//
//  HomeViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class HomeViewCell: UITableViewCell {
    
    static let identifier = "HomeViewCell"
    
    private let homeCell: HomeVIew = {
        let homeCell = HomeVIew()
        homeCell.translatesAutoresizingMaskIntoConstraints = false
        return homeCell
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
            super.init(style: style, reuseIdentifier: reuseIdentifier)
            backgroundColor = .clear
        setupView()
        }
   
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configureCell(with homeCell: Result) {
        homeCell.setup(image: homeCell.image, name: homeCell.name, price: homeCell.price)
    }
    
    
    // MARK: - Init
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: - Functions
    func setupCell(with hotel: Result) {
        hotelTituloLabel.text = hotel.name
        priceLabel.text = "R$\(hotelprices)"
        imageHotel.image = UIImage(named: image)
        }
    
}

extension HomeViewCell: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        self.addSubview(homeCell)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            homeCell.topAnchor.constraint(equalTo: self.topAnchor),
            homeCell.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            homeCell.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 20),
            homeCell.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -20),
        ])
    }

    func setupAdditionalConfiguration() {}
}
