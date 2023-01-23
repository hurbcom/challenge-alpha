//
//  CityDetailsViewController.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit
import HUGraphQL

class DetailsViewController: UIViewController {
    
    var url = URL(string: "https://www.google.com.br")
    typealias Result = HUGraphQL.SearchQuery.Data.Search.Result

    private var requestService = HotelService()
    private var viewModel = ResultViewModel()
    
    private let details: DetailsView = {
        let details = DetailsView()
        details.translatesAutoresizingMaskIntoConstraints = false
        return details
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.setBackground()
        setupView()
    }
    
    func getDetails(hotel: Result) {
        details.hoteNamelLabel.text = hotel.name
        let price = String(hotel.price.amount)
        details.priceLabel.text = "R$\(price)"
        details.descriptionLabel.text = hotel.smallDescription
        let urlImage = hotel.gallery[0].url ?? ""
        DispatchQueue.main.async {
            self.details.hotelImageView.loadFrom(URLAdress: urlImage)
        }
    }
    
    init(result: Result) {
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension DetailsViewController: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        view.addSubview(details)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            details.topAnchor.constraint(equalTo: view.topAnchor),
            details.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            details.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            details.trailingAnchor.constraint(equalTo: view.trailingAnchor),
        ])
    }

    func setupAdditionalConfiguration() {}
}

