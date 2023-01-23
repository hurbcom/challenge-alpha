//
//  SearchDetailViewController.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import UIKit

final class SearchDetailViewController: UIViewController {

    private let viewModel: SearchDetailViewModelProtocol

    private let scrollableView: ScrollableView = {
        let scrollableView = ScrollableView()
        scrollableView.translatesAutoresizingMaskIntoConstraints = false
        return scrollableView
    }()

    private let galleryImageView: UIImageView = {
        let galleryImageView = UIImageView()
        galleryImageView.translatesAutoresizingMaskIntoConstraints = false
        return galleryImageView
    }()
    private let addressLabel: UILabel = {
        let addressLabel = UILabel()
        addressLabel.font = .systemFont(ofSize: 12.0, weight: .light)
        addressLabel.translatesAutoresizingMaskIntoConstraints = false
        return addressLabel
    }()
    private let nameLabel: UILabel = {
        let nameLabel = UILabel()
        nameLabel.font = .systemFont(ofSize: 20.0, weight: .bold)
        nameLabel.translatesAutoresizingMaskIntoConstraints = false
        return nameLabel
    }()
    private let descriptionLabel: UILabel = {
        let descriptionLabel = UILabel()
        descriptionLabel.font = .systemFont(ofSize: 12.0, weight: .regular)
        descriptionLabel.numberOfLines = 2
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        return descriptionLabel
    }()
    private let priceLabel: UILabel = {
        let priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 14.0, weight: .bold)
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        return priceLabel
    }()

    private let amenityStackView: UIStackView = {
        let amenityStackView = UIStackView()
        amenityStackView.axis = .vertical
        amenityStackView.spacing = 8.0
        amenityStackView.translatesAutoresizingMaskIntoConstraints = false
        return amenityStackView
    }()
    private let amenityHeaderLabel: UILabel = {
        let amenityHeaderLabel = UILabel()
        amenityHeaderLabel.text = "Comodidades"
        amenityHeaderLabel.font = .systemFont(ofSize: 20.0, weight: .bold)
        return amenityHeaderLabel
    }()

    init(viewModel: SearchDetailViewModelProtocol) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        return nil
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        buildView()
        viewModel.loadContentData()
    }

}

extension SearchDetailViewController: ViewCodeProtocol {

    func setupView() {
        view.backgroundColor = .background
    }

    func setupLayoutConstraints() {

        view.addSubview(scrollableView)
        NSLayoutConstraint.activate([
            scrollableView.topAnchor.constraint(equalTo: view.topAnchor),
            scrollableView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            scrollableView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            scrollableView.trailingAnchor.constraint(equalTo: view.trailingAnchor)
        ])

        scrollableView.contentView.addSubview(galleryImageView)
        scrollableView.contentView.addSubview(nameLabel)
        scrollableView.contentView.addSubview(addressLabel)
        scrollableView.contentView.addSubview(priceLabel)
        scrollableView.contentView.addSubview(descriptionLabel)
        scrollableView.contentView.addSubview(amenityStackView)

        amenityStackView.addArrangedSubview(amenityHeaderLabel)

        NSLayoutConstraint.activate([

            galleryImageView.topAnchor.constraint(equalTo: scrollableView.contentView.topAnchor),
            galleryImageView.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor),
            galleryImageView.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),
            galleryImageView.heightAnchor.constraint(equalToConstant: 240.0),

            nameLabel.topAnchor.constraint(equalTo: galleryImageView.bottomAnchor, constant: 8.0),
            nameLabel.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor, constant: 8.0),
            nameLabel.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),

            addressLabel.topAnchor.constraint(equalTo: nameLabel.bottomAnchor, constant: 8.0),
            addressLabel.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor, constant: 8.0),
            addressLabel.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),

            priceLabel.topAnchor.constraint(equalTo: addressLabel.bottomAnchor, constant: 8.0),
            priceLabel.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor, constant: 8.0),
            priceLabel.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),

            descriptionLabel.topAnchor.constraint(equalTo: priceLabel.bottomAnchor, constant: 8.0),
            descriptionLabel.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor, constant: 8.0),
            descriptionLabel.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),

            amenityStackView.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 16.0),
            amenityStackView.leadingAnchor.constraint(equalTo: scrollableView.contentView.leadingAnchor, constant: 8.0),
            amenityStackView.bottomAnchor.constraint(equalTo: scrollableView.contentView.bottomAnchor, constant: 16.0),
            amenityStackView.centerXAnchor.constraint(equalTo: scrollableView.contentView.centerXAnchor),

        ])
    }

}

extension SearchDetailViewController: SearchDetailViewModelDelegate {

    func viewModel(contentData item: SearchResult) {

        title = item.name
        if let urlString = item.gallery.first?.url, let photoURL = URL(string: urlString) {
            galleryImageView.kf.setImage(with: photoURL)
        }

        if let modelAddress = item.address {
            addressLabel.text = AddressLocationFormatter.getLocationFormatter(address: modelAddress)
        }

        nameLabel.text = item.name

        priceLabel.text = CurrencyFormatter.getPriceFormatted(price: item.price)

        descriptionLabel.text = item.description

        let amenityNameList = item.amenities.compactMap { $0.name }

        amenityNameList.forEach { amenity in

            let amenityLabel = UILabel()
            amenityLabel.text = amenity

            amenityStackView.addArrangedSubview(amenityLabel)
        }

//        item.isAvailable

//        item.category

    }

}
