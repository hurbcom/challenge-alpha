//
//  AmenityItemView.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import UIKit

final class AmenityItemView: UIView {

    var text: String? {
        didSet {
            descriptionLabel.text = self.text
        }
    }

    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }()
    private let descriptionLabel: UILabel = {
        let descriptionLabel = UILabel()
        descriptionLabel.textColor = .defaultText
        return descriptionLabel
    }()
    private let imageView: UIImageView = {
        let imageView = UIImageView()
        imageView.image = .checkmarkCircle
        imageView.contentMode = .scaleAspectFit
        imageView.setContentHuggingPriority(.defaultHigh, for: .horizontal)
        imageView.tintColor = .defaultText
        return imageView
    }()

    init() {
        super.init(frame: .zero)
        buildView()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

}

extension AmenityItemView: ViewCodeProtocol {

    func setupView() {

    }

    func setupLayoutConstraints() {

        addSubview(stackView)

        stackView.addArrangedSubview(imageView)
        stackView.addArrangedSubview(descriptionLabel)

        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: topAnchor, constant: 8.0),
            stackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 8.0),
            stackView.centerXAnchor.constraint(equalTo: centerXAnchor),
            stackView.centerYAnchor.constraint(equalTo: centerYAnchor),
        ])
    }

}
