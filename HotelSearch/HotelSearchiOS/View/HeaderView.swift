//
//  HeaderView.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 16/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

final public class HeaderView: UIView {
    
    // MARK: - UI Components
    
    private(set) public lazy var stackView: UIStackView = {
        let imageViews: [UIImageView] = (0 ..< (self.stars ?? 0)).compactMap { _ in
            return self.makeImageView()
        }
        let stackView = UIStackView(arrangedSubviews: imageViews)
        stackView.axis = .horizontal
        stackView.spacing = 4
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }()
    
    private(set) public lazy var label: UILabel = {
        let label = UILabel()
        label.text = "N/A"
        label.textColor = .systemGray
        label.font = .systemFont(ofSize: 14)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    // MARK: - Properties
    
    private var stars: Int?
    
    // MARK: - Life Cycle
    
    public init(stars: Int?) {
        super.init(frame: .zero)
        self.stars = stars
        self.setupUI()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupUI()
    }
    
}

private extension HeaderView {
    
    // MARK: - Setup
    
    func setupUI() {
        self.backgroundColor = .white
        (self.stars ?? -1) > 0 ? self.addStackView() : self.addLabel()
    }
    
    func addStackView() {
        self.addSubview(self.stackView)
        let stackWidth = self.stackView.widthAnchor.constraint(equalToConstant: 0)
        stackWidth.priority = .defaultLow
        self.addConstraints([
            self.stackView.centerYAnchor.constraint(equalTo: self.centerYAnchor),
            self.stackView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            self.stackView.heightAnchor.constraint(equalToConstant: 20),
            stackWidth
        ])
    }
    
    func addLabel() {
        self.addSubview(self.label)
        self.addConstraints([
            self.label.centerYAnchor.constraint(equalTo: self.centerYAnchor),
            self.label.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16)
        ])
    }
    
    func makeImageView() -> UIImageView {
        let imageView = UIImageView(image: UIImage(systemName: "star.fill"))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.widthAnchor.constraint(equalTo: imageView.heightAnchor).isActive = true
        imageView.tintColor = #colorLiteral(red: 1, green: 0.9215686275, blue: 0, alpha: 1)
        return imageView
    }
    
}



