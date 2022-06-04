//
//  LastSeenTableViewCell.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import UIKit
import Kingfisher

class LastSeenTableViewCell: UITableViewCell {
    
    // MARK: - Properties
    var hotelResult: LastSeenHotel? {
        didSet {
            configureCell()
        }
    }
        
    private var hotelNameLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 13, weight: .regular)
        label.numberOfLines = 3
        label.textAlignment = .left
        label.textColor = .black
        label.setDimensions(height: 16)
        return label
    }()
    
    private var hotelDescriptionLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 12, weight: .light)
        label.numberOfLines = .max
        label.textAlignment = .left
        label.textColor = .black
        label.lineBreakMode = .byWordWrapping
        label.sizeToFit()
        return label
    }()
    
    private var hotelImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.layer.cornerRadius = 8
        imageView.clipsToBounds = true
        imageView.setDimensions(width: Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING.width, height: 130)
        return imageView
    }()
    
    private var starLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.textAlignment = .left
        label.textColor = .black
        label.setDimensions(height: 16)
        label.font = UIFont.systemFont(ofSize: 12, weight: .light)
        return label
    }()
    
    private lazy var stackView: UIStackView = {
        let stack = UIStackView(arrangedSubviews: [hotelImageView, hotelNameLabel, starLabel, hotelDescriptionLabel])
        stack.spacing = 8
        stack.alignment = .leading
        stack.axis = .vertical
        stack.distribution = .equalSpacing
        return stack
    }()

    // MARK: - Lifecycle
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Helper Methods
    
    private func configureCell() {
        guard let hotelResult = hotelResult else { return }
        let viewModel = LastSeenViewModel()
        viewModel.hotelResult = hotelResult

        hotelNameLabel.text = viewModel.hotelNameText
        hotelImageView.kf.setImage(with: viewModel.hotelImageURL)
        hotelDescriptionLabel.text = viewModel.hotelSmallDescriptionText
        starLabel.text = viewModel.hotelStars
        
        /// Configure the description height.
        let descriptionHeight = hotelDescriptionLabel.text?.height(constraintedWidth: Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING.width,
                                                                   font: UIFont.systemFont(ofSize: 13, weight: .light))
        hotelDescriptionLabel.setDimensions(height: descriptionHeight)
        
        setNeedsLayout()
    }
}

// MARK: - CodeView
extension LastSeenTableViewCell: CodeView {
    func buildViewHierarchy() {
        addSubview(stackView)
    }
    
    func setupConstraints() {
        stackView.anchor(top: topAnchor,
                         leading: leadingAnchor,
                         bottom: bottomAnchor,
                         trailling: trailingAnchor,
                         paddingTop: 16,
                         paddingLeading: 16,
                         paddingBottom: 16,
                         paddingTrailling: 16)
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = .white
    }
}
extension String {
    func height(constraintedWidth width: CGFloat, font: UIFont) -> CGFloat {
        let label =  UILabel(frame: CGRect(x: 0, y: 0, width: width, height: .greatestFiniteMagnitude))
        label.numberOfLines = 0
        label.text = self
        label.font = font
        label.sizeToFit()

        return label.frame.height
     }
}
