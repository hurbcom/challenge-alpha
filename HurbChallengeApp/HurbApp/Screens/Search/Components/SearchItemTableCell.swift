//
//  SearchItemTableCell.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 20/01/23.
//

import UIKit

final class SearchItemTableCell: UITableViewCell {

    static let reuseIdentifier: String = "SearchItemTableCell"

    private let cardView: UIView = {
        let cardView = UIView()
        cardView.translatesAutoresizingMaskIntoConstraints = false
        cardView.backgroundColor = .white
        cardView.layer.cornerRadius = 4.0
        cardView.clipsToBounds = true
        return cardView
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

    private func setupView() {
        backgroundColor = .clear
    }

    private func setupLayoutConstraints() {

        contentView.addSubview(cardView)
        cardView.addSubview(nameLabel)
        cardView.addSubview(locationLabel)
        cardView.addSubview(priceLabel)

        NSLayoutConstraint.activate([

            cardView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 8.0),
            cardView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 8.0),
            cardView.centerXAnchor.constraint(equalTo: contentView.centerXAnchor),
            cardView.centerYAnchor.constraint(equalTo: contentView.centerYAnchor),

            nameLabel.topAnchor.constraint(equalTo: cardView.topAnchor, constant: 8.0),
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

    func updateContentData(model: SearchResult) {

        nameLabel.text = model.name

        if let modelAddress = model.address {

            let addressLocationFormatted = [
                modelAddress.city,
                modelAddress.state,
                modelAddress.country
            ].compactMap { $0 }

            locationLabel.text = addressLocationFormatted.joined(separator: ", ")
        }

        priceLabel.text = CurrencyFormatter.getPriceFormatted(price: model.price)
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
