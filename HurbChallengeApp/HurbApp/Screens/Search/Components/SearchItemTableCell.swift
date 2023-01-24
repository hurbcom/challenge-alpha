//
//  SearchItemTableCell.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 20/01/23.
//

import UIKit
import Kingfisher

final class SearchItemTableCell: UITableViewCell {

    static let reuseIdentifier: String = "SearchItemTableCell"

    private let cardView: UIView = {
        let cardView = UIView()
        cardView.translatesAutoresizingMaskIntoConstraints = false
        cardView.backgroundColor = .white
        cardView.layer.cornerRadius = 8.0
        cardView.layer.masksToBounds = false
        cardView.layer.shadowRadius = 4.0
        cardView.layer.shadowOpacity = 0.3
        cardView.layer.shadowColor = UIColor.gray.cgColor
        cardView.layer.shadowOffset = CGSize(width: 1, height: 5)
        return cardView
    }()
    private let photoImageView: UIImageView = {
        let photoImageView = UIImageView()
        photoImageView.translatesAutoresizingMaskIntoConstraints = false
        photoImageView.contentMode = .scaleAspectFill
        photoImageView.clipsToBounds = true
        photoImageView.layer.cornerRadius = 8.0
        photoImageView.layer.maskedCorners = [.layerMaxXMinYCorner, .layerMinXMinYCorner]
        return photoImageView
    }()
    private let nameLabel: UILabel = {
        let nameLabel = UILabel()
        nameLabel.translatesAutoresizingMaskIntoConstraints = false
        nameLabel.numberOfLines = 0
        nameLabel.font = .systemFont(ofSize: 14, weight: .bold)
        nameLabel.setContentHuggingPriority(.defaultHigh, for: .vertical)
        nameLabel.setContentCompressionResistancePriority(.required, for: .vertical)
        return nameLabel
    }()
    private let locationLabel: UILabel = {
        let locationLabel = UILabel()
        locationLabel.translatesAutoresizingMaskIntoConstraints = false
        locationLabel.font = .systemFont(ofSize: 12, weight: .light)

        return locationLabel
    }()
    private let priceLabel: UILabel = {
        let priceLabel = UILabel()
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        return priceLabel
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
        setupLayoutConstraints()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func setSelected(_ selected: Bool, animated: Bool) { }

    func updateContentData(model: SearchResult) {

        nameLabel.text = model.name

        if let modelAddress = model.address {
            locationLabel.text = AddressLocationFormatter.getLocationFormatter(address: modelAddress)
        }

        priceLabel.text = CurrencyFormatter.getPriceFormatted(price: model.price)

        if let urlString = model.gallery.first?.url, let photoURL = URL(string: urlString) {
            photoImageView.kf.setImage(with: photoURL)
        }

    }

}

extension SearchItemTableCell: ViewCodeProtocol {

    func setupView() {
        backgroundColor = .clear
    }

    func setupLayoutConstraints() {

        contentView.addSubview(cardView)
        cardView.addSubview(photoImageView)
        cardView.addSubview(nameLabel)
        cardView.addSubview(locationLabel)
        cardView.addSubview(priceLabel)

        NSLayoutConstraint.activate([

            cardView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 8.0),
            cardView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 8.0),
            cardView.centerXAnchor.constraint(equalTo: contentView.centerXAnchor),
            cardView.centerYAnchor.constraint(equalTo: contentView.centerYAnchor),

            photoImageView.topAnchor.constraint(equalTo: cardView.topAnchor),
            photoImageView.leadingAnchor.constraint(equalTo: cardView.leadingAnchor),
            photoImageView.centerXAnchor.constraint(equalTo: cardView.centerXAnchor),
            photoImageView.heightAnchor.constraint(equalToConstant: 100.0),

            nameLabel.topAnchor.constraint(equalTo: photoImageView.bottomAnchor, constant: 8.0),
            nameLabel.leadingAnchor.constraint(equalTo: cardView.leadingAnchor, constant: 8.0),
            nameLabel.centerXAnchor.constraint(equalTo: cardView.centerXAnchor),

            locationLabel.topAnchor.constraint(equalTo: nameLabel.bottomAnchor, constant: 4.0),
            locationLabel.leadingAnchor.constraint(equalTo: cardView.leadingAnchor, constant: 8.0),
            locationLabel.centerXAnchor.constraint(equalTo: cardView.centerXAnchor),

            priceLabel.topAnchor.constraint(equalTo: locationLabel.bottomAnchor, constant: 8.0),
            priceLabel.leadingAnchor.constraint(equalTo: cardView.leadingAnchor, constant: 8.0),
            priceLabel.centerXAnchor.constraint(equalTo: cardView.centerXAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: cardView.bottomAnchor, constant: -8.0),

        ])
    }

}

struct AddressLocationFormatter {

    static func getLocationFormatter(address: SearchResultAddress) -> String {

        let addressList = [
            address.city,
            address.state,
            address.country
        ].compactMap { $0 }

        return addressList.joined(separator: ", ")
    }

}

struct CurrencyFormatter {

    static func getPriceFormatted(price: SearchResultPrice) -> String? {

        let numberFormatter = NumberFormatter()
        numberFormatter.numberStyle = .currency
        numberFormatter.currencyCode = price.currency
        numberFormatter.maximumFractionDigits = 2

        let numberAmount = NSNumber(value: price.amount)

        return numberFormatter.string(from: numberAmount)
    }
}


//struct Shadow
