//
//  CityDetailsViewController.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

class DetailsViewController: UIViewController {
    
    private var result: Result
    
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
    
    init(result: Result) {
        self.result = result
        super.init(nibName: nil, bundle: nil)
        guard let imageURL = image.image else { return }
        configureImage(imageURL)
        getDetails()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func getDetails() {
        hotelImageView.image = UIImage(named: image)
        hoteNamelLabel.text = result.hotelLabel
        descriptionLabel.text = result.descriptionLabel
        priceLabel.text = "R$ \(result.price)"
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
            details.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            details.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20),
        ])
    }

    func setupAdditionalConfiguration() {}
}

