//
//  HomeCollectionViewCell.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 23/05/22.
//

import UIKit
import Kingfisher

class HomeCollectionViewCell: UICollectionViewCell {
    // MARK: - Properties    
    var hotelResult: HotelResult? {
        didSet {
            configureCell()
        }
    }
    
    private var imageStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.clipsToBounds = true
        return stackView
    }()
    
    lazy var imageScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.setDimensions(width: Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING.width,
                                 height: 180)
        scrollView.isPagingEnabled = true
        scrollView.showsHorizontalScrollIndicator = false
        scrollView.delegate = self
        scrollView.backgroundColor = .clear
        scrollView.bounces = false
        scrollView.layer.cornerRadius = 8
        scrollView.clipsToBounds = true
        return scrollView
    }()
    
    private var imagePageControl: UIPageControl = {
        let pageControl = UIPageControl()
        pageControl.pageIndicatorTintColor = .lightGray
        pageControl.currentPageIndicatorTintColor = .white
        pageControl.setDimensions(height: 6)
        return pageControl
    }()
    
    private var hotelDescriptionLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 2
        label.textAlignment = .left
        label.textColor = .black
        return label
    }()
    
    private var originalPriceLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 1
        label.textAlignment = .left
        label.textColor = .black
        return label
    }()
    
    private var finalPriceLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 1
        label.textAlignment = .left
        label.textColor = .black
        return label
    }()
    
    private var freeCancelationLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 1
        label.textAlignment = .left
        label.textColor = .freeCancelationGreen
        label.font = UIFont.systemFont(ofSize: 13, weight: .regular)
        return label
    }()
    
    private var hotelAmenitiesLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 2
        label.textAlignment = .left
        label.textColor = .gray
        return label
    }()
    
    private var starLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.textAlignment = .left
        label.textColor = .black
        label.font = UIFont.systemFont(ofSize: 13, weight: .light)
        return label
    }()
    
    // MARK: - Lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Helper Methods
    
    private func configureCell() {
        guard let hotelResult = hotelResult else { return }
        let viewModel = HomeViewModel()
        viewModel.hotelResult = hotelResult
        
        hotelDescriptionLabel.attributedText = viewModel.hotelDescriptionText
        finalPriceLabel.attributedText = viewModel.hotelFinalPriceText
        starLabel.text = viewModel.hotelStars
        freeCancelationLabel.text = viewModel.freeCancelingText
        hotelAmenitiesLabel.attributedText = viewModel.hotelAmenitiesText
        
        /// Configure `ScrollView` and `PageControl`.
        imagePageControl.numberOfPages = viewModel.hotelsImage.count
        imagePageControl.currentPage = 0
        
        for imageURL in viewModel.hotelsImage {
            let url = URL(string: imageURL)
            let imageView = UIImageView()
            imageView.kf.setImage(with: url)
            imageView.contentMode = .scaleAspectFill
            imageView.setDimensions(width: Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING.width,
                                    height: 180)
            imageView.clipsToBounds = true
            if imageView.image == nil {
                imageView.image = UIImage(systemName: "xmark.rectangle.fill")
                imageView.tintColor = .darkGray
            }
            imageStackView.addArrangedSubview(imageView)
        }
        
        imageScrollView.contentSize = CGSize(width: imageScrollView.contentSize.width * CGFloat(viewModel.hotelsImage.count),
                                             height: imageScrollView.contentSize.height)
        
        /// Configure `FreeCancelationLabel`.
        if freeCancelationLabel.text == "" {
            freeCancelationLabel.isHidden = true
        }
    }
}

extension HomeCollectionViewCell: CodeView {
    func buildViewHierarchy() {
        addSubview(imageScrollView)
        imageScrollView.addSubview(imageStackView)
        addSubview(imagePageControl)
        addSubview(hotelDescriptionLabel)
        addSubview(finalPriceLabel)
        addSubview(starLabel)
        addSubview(hotelAmenitiesLabel)
        addSubview(freeCancelationLabel)
    }
    
    func setupConstraints() {
        imageScrollView.anchor(top: topAnchor,
                               leading: leadingAnchor,
                               trailling: trailingAnchor)
        
        imagePageControl.centerX(inView: imageScrollView,
                                 topAnchor: imageScrollView.bottomAnchor,
                                 paddingTop: -16)
        
        hotelDescriptionLabel.anchor(top: imageScrollView.bottomAnchor,
                                     leading: leadingAnchor,
                                     trailling: trailingAnchor,
                                     paddingTop: 8)
        
        finalPriceLabel.anchor(top: hotelDescriptionLabel.bottomAnchor,
                               leading: leadingAnchor,
                               trailling: trailingAnchor,
                               paddingTop: 8)
        
        starLabel.anchor(top: finalPriceLabel.bottomAnchor,
                                   leading: leadingAnchor,
                                   trailling: trailingAnchor,
                                   paddingTop: 8)
        
        hotelAmenitiesLabel.anchor(top: starLabel.bottomAnchor,
                                   leading: leadingAnchor,
                                   trailling: trailingAnchor,
                                   paddingTop: 8)
        
        freeCancelationLabel.anchor(top: hotelAmenitiesLabel.bottomAnchor,
                                    leading: leadingAnchor,
                                    bottom: bottomAnchor,
                                    trailling: trailingAnchor,
                                    paddingTop: 8)
        
        imageStackView.addConstraintsToFillView(imageScrollView)
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = .white
        clipsToBounds = true
    }
}

extension HomeCollectionViewCell: UIScrollViewDelegate {
    /// Set the current page of `PageControl` based on the `ScrollView` lenth.
    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        imagePageControl.currentPage = Int(scrollView.contentOffset.x / (scrollView.frame.width))
    }
}
