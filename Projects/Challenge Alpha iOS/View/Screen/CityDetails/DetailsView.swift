//
//  CityDetailsTableViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

class DetailsView: UIView {
    
    var url = URL(string: "https://www.google.com.br")
    
    // MARK: - Private Properties UI
    lazy var hotelImageView: UIImageView = {
        let hotelImageView = UIImageView()
        hotelImageView.contentMode = .scaleAspectFill
        hotelImageView.backgroundColor = .red
        hotelImageView.translatesAutoresizingMaskIntoConstraints = false
        hotelImageView.layer.masksToBounds = true
        hotelImageView.isAccessibilityElement = true
        hotelImageView.accessibilityTraits = .image
        return hotelImageView
    }()
    
    lazy var hoteNamelLabel: UILabel = {
        let hotelLabel = UILabel()
        hotelLabel.font = .systemFont(ofSize: 18, weight: .bold)
        hotelLabel.textColor = .black
        hotelLabel.lineBreakMode = .byWordWrapping
        hotelLabel.numberOfLines = 0
        hotelLabel.textAlignment = .center
        hotelLabel.textColor = .black
        hotelLabel.translatesAutoresizingMaskIntoConstraints = false
        hotelLabel.isAccessibilityElement = true
        hotelLabel.accessibilityTraits = .staticText
        return hotelLabel
    }()
    
    lazy var descriptionLabel: UILabel = {
        let descriptionLabel = UILabel()
        descriptionLabel.font = .systemFont(ofSize: 16, weight: .regular)
        descriptionLabel.textColor = .black
        descriptionLabel.numberOfLines = 10
        descriptionLabel.textAlignment = .justified
        descriptionLabel.lineBreakMode = .byTruncatingTail
        descriptionLabel.textColor = .black
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.isAccessibilityElement = true
        descriptionLabel.accessibilityTraits = .staticText
        return descriptionLabel
    }()
    
    lazy var priceLabel: UILabel = {
        let priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 18, weight: .bold)
        priceLabel.textColor = .systemGreen
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.textAlignment = .left
        priceLabel.isAccessibilityElement = true
        priceLabel.accessibilityTraits = .staticText
        return priceLabel
    }()
    
    lazy var shareButton: UIButton = {
        let shareButton = UIButton()
        if let image = UIImage(named: "share") {
            shareButton.setImage(image, for: .normal)}
        shareButton.addTarget(self, action:#selector(self.presentShareSheet), for: .touchUpInside)
        shareButton.translatesAutoresizingMaskIntoConstraints = false
        shareButton.isAccessibilityElement = true
        shareButton.accessibilityTraits = .button
        shareButton.accessibilityLabel = "Compartilhar"
        return shareButton
    }()
    
    private lazy var stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        stackView.isAccessibilityElement = false
        return stackView
    }()
    
    private lazy var containerView: UIView = {
        let container = UIView()
        container.translatesAutoresizingMaskIntoConstraints = false
        container.layer.cornerRadius = 32.0
        container.layer.masksToBounds = true
        container.backgroundColor = .white
        container.isAccessibilityElement = false
        container.layer.shadowColor = UIColor.black.cgColor
        container.layer.shadowOpacity = 0.5
        container.layer.shadowOffset = CGSize(width: 5, height: 5)
        container.layer.shadowRadius = 5
        return container
    }()
    
    private lazy var scrollView: UIScrollView = {
        let scroll = UIScrollView()
        scroll.translatesAutoresizingMaskIntoConstraints = false
        scroll.isAccessibilityElement = false
        return scroll
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
    
    @objc private func presentShareSheet(){
        guard let url = url else { return }
        
        let av = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController?.present(av, animated: true)
        if UIDevice.current.userInterfaceIdiom == .phone{
            av.popoverPresentationController?.sourceView = UIApplication.shared.windows.first
            av.popoverPresentationController?.sourceRect = CGRect(x: UIScreen.main.bounds.width / 2.1, y: UIScreen.main.bounds.height / 1.0, width: 200, height: 200)
        }
    }
}

extension DetailsView: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        addSubview(scrollView)
        scrollView.addSubview(containerView)

        addSubview(containerView)
        containerView.addSubview(hoteNamelLabel)
        containerView.addSubview(shareButton)
        containerView.addSubview(stackView)
        containerView.addSubview(descriptionLabel)
        stackView.addArrangedSubview(priceLabel)
        
        addSubview(hotelImageView)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            
            scrollView.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            scrollView.widthAnchor.constraint(equalTo: self.widthAnchor),
            scrollView.topAnchor.constraint(equalTo: self.topAnchor),
            scrollView.bottomAnchor.constraint(equalTo: self.bottomAnchor),

            containerView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 300.0),
            containerView.centerXAnchor.constraint(equalTo: scrollView.centerXAnchor),
            containerView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor, constant: 20.0),
            containerView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor, constant: -20.0),
//            containerView.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor, constant: -80.0),
            containerView.widthAnchor.constraint(equalToConstant: 300.0),
            containerView.heightAnchor.constraint(equalToConstant: 300.0),


            hotelImageView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 20.0),
            hotelImageView.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            hotelImageView.widthAnchor.constraint(equalToConstant: 400.0),
            hotelImageView.heightAnchor.constraint(equalToConstant: 264.0),

            hoteNamelLabel.topAnchor.constraint(equalTo: containerView.topAnchor, constant: 10.0),
            hoteNamelLabel.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20.0),
            hoteNamelLabel.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -20.0),

            stackView.topAnchor.constraint(equalTo: hoteNamelLabel.bottomAnchor, constant: 10.0),
            stackView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20.0),
            stackView.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -20.0),

            shareButton.topAnchor.constraint(equalTo: hoteNamelLabel.bottomAnchor, constant: 10.0),
            shareButton.leadingAnchor.constraint(equalTo: self.stackView.trailingAnchor, constant: -20.0),
            shareButton.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -10.0),
            shareButton.widthAnchor.constraint(equalToConstant: 30),
            shareButton.heightAnchor.constraint(equalToConstant: 30),

            descriptionLabel.topAnchor.constraint(equalTo: stackView.bottomAnchor, constant: 10.0),
            descriptionLabel.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20.0),
            descriptionLabel.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -20.0),
        ])
    }
    func setupAdditionalConfiguration() {}
}
