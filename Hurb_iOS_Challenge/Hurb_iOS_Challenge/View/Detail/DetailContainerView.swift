//
//  DetailContainerView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 29/05/22.
//

import UIKit

class DetailContainerView: UIView {
    // MARK: - Properties
    var hotelsResult: HotelResult? {
        didSet {
            configureContainter()
        }
    }
    
    /// State Control - Boolean variables.
    private var isFullDescriptionShown = false
    
    /// Views.
    
    private var imageStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.clipsToBounds = true
        return stackView
    }()
    
    lazy var imageScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.setDimensions(width: Constants.DetailContainerViewConstraints.DETAIL_IMAGES_DIMENTIONS_WITH_PADDING.width,
                                 height: Constants.DetailContainerViewConstraints.DETAIL_IMAGES_DIMENTIONS_WITH_PADDING.height)
        scrollView.isPagingEnabled = true
        scrollView.showsHorizontalScrollIndicator = false
        scrollView.delegate = self
        scrollView.backgroundColor = .clear
        scrollView.bounces = false
        return scrollView
    }()
    
    private var imagePageControl: UIPageControl = {
        let pageControl = UIPageControl()
        pageControl.pageIndicatorTintColor = .lightGray
        pageControl.currentPageIndicatorTintColor = .white
        pageControl.setDimensions(height: 6)
        return pageControl
    }()
    
    private var hotelNameLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 2
        label.textAlignment = .left
        label.font = UIFont.systemFont(ofSize: 15, weight: .semibold)
        label.textColor = .black
        return label
    }()
    
    private var hotelLocationLabel: UILabel = {
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
        label.numberOfLines = .max
        label.textAlignment = .left
        label.textColor = .black
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
    
    lazy var smallDescriptionLabel: UILabel = {
        let label = UILabel()
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(showMore(tapGesture:)))
        label.numberOfLines = .max
        label.textAlignment = .left
        label.lineBreakMode = .byWordWrapping
        label.textColor = .black
        label.isUserInteractionEnabled = true
        label.addGestureRecognizer(tapGesture)
        return label
    }()
    
    private var fullDescriptionLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = .max
        label.textAlignment = .left
        label.textColor = .gray
        label.font = UIFont.systemFont(ofSize: 13, weight: .light)
        label.isHidden = true
        return label
    }()
    
    lazy var showHideFullDescriptionButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle(" ‣ Ver mais ", for: .normal)
        button.titleLabel?.tintColor = .hurbMainBlue
        button.titleLabel?.font = UIFont.systemFont(ofSize: 13, weight: .semibold)
        button.addTarget(self, action: #selector(showMore), for: .touchUpInside)
        return button
    }()
    
    private var lineSpacing = LineView()
    private var lineSpacing2 = LineView()
    private var lineSpacing3 = LineView()
    
    /// Stacks.
    lazy var hotelDescriptionsStackView: UIStackView = {
        let stack = UIStackView(arrangedSubviews: [smallDescriptionLabel, showHideFullDescriptionButton, fullDescriptionLabel])
        stack.axis = .vertical
        stack.alignment = .leading
        stack.distribution = .fillProportionally
        stack.spacing = 16
        return stack
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
    private func configureContainter() {
        guard let hotelsResult = hotelsResult else { return }
        let viewModel = HomeViewModel()
        viewModel.hotelResult = hotelsResult
        
        hotelNameLabel.text = viewModel.hotelDetailName
        hotelLocationLabel.attributedText = viewModel.hotelDetailLocation
        finalPriceLabel.attributedText = viewModel.hotelFinalPriceText
        starLabel.text = viewModel.hotelStars
        freeCancelationLabel.text = viewModel.freeCancelingText
        hotelAmenitiesLabel.attributedText = viewModel.hotelDetailAmenitiesText
        smallDescriptionLabel.attributedText = viewModel.hotelDetailSmallDescription
        fullDescriptionLabel.text = viewModel.hotelDetailFullDescription
        
        /// Configure `ScrollView` and `PageControl`.
        imagePageControl.numberOfPages = viewModel.hotelsImage.count
        imagePageControl.currentPage = 0
        
        for imageURL in viewModel.hotelsImage {
            let url = URL(string: imageURL)
            let imageView = UIImageView()
            imageView.kf.setImage(with: url)
            imageView.contentMode = .scaleAspectFill
            imageView.setDimensions(width: Constants.DetailContainerViewConstraints.DETAIL_IMAGES_DIMENTIONS_WITH_PADDING.width,
                                    height: Constants.DetailContainerViewConstraints.DETAIL_IMAGES_DIMENTIONS_WITH_PADDING.height)
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
    
    // MARK: - Selectors
    @objc func showMore(tapGesture: UITapGestureRecognizer) {
        isFullDescriptionShown.toggle()
        fullDescriptionLabel.isHidden = isFullDescriptionShown ? false : true
        showHideFullDescriptionButton.setTitle(isFullDescriptionShown ? " ▴ Ver menos " : " ▾ Ver mais ", for: .normal)
    }
}

extension DetailContainerView: CodeView {
    func buildViewHierarchy() {
        addSubview(imageScrollView)
        imageScrollView.addSubview(imageStackView)
        addSubview(imagePageControl)
        addSubview(hotelNameLabel)
        addSubview(starLabel)
        addSubview(hotelLocationLabel)
        addSubview(finalPriceLabel)
        addSubview(hotelAmenitiesLabel)
        addSubview(freeCancelationLabel)
        addSubview(hotelDescriptionsStackView)
        addSubview(lineSpacing)
        addSubview(lineSpacing2)
        addSubview(lineSpacing3)
    }
    
    func setupConstraints() {
        imageScrollView.anchor(top: topAnchor,
                               leading: leadingAnchor,
                               trailling: trailingAnchor)
        
        imagePageControl.centerX(inView: imageScrollView,
                                 topAnchor: imageScrollView.bottomAnchor,
                                 paddingTop: -16)
        
        hotelNameLabel.anchor(top: imageScrollView.bottomAnchor,
                              leading: leadingAnchor,
                              trailling: trailingAnchor,
                              paddingTop: 16,
                              paddingLeading: 16,
                              paddingTrailling: 16)
        
        starLabel.anchor(top: hotelNameLabel.bottomAnchor,
                         leading: leadingAnchor,
                         paddingTop: 8,
                         paddingLeading: 16, width: 45)
        
        hotelLocationLabel.anchor(top: starLabel.topAnchor,
                                  leading: starLabel.trailingAnchor,
                                  trailling: trailingAnchor,
                                  paddingTrailling: 16)
        
        lineSpacing.anchor(top: hotelLocationLabel.bottomAnchor,
                           leading: leadingAnchor,
                           trailling: trailingAnchor,
                           paddingTop: 16,
                           paddingLeading: 16,
                           paddingTrailling: 16)
        
        hotelAmenitiesLabel.anchor(top: lineSpacing.bottomAnchor,
                                   leading: leadingAnchor,
                                   trailling: trailingAnchor,
                                   paddingTop: 16,
                                   paddingLeading: 16,
                                   paddingTrailling: 16)
        
        lineSpacing2.anchor(top: hotelAmenitiesLabel.bottomAnchor,
                           leading: leadingAnchor,
                           trailling: trailingAnchor,
                           paddingTop: 16,
                           paddingLeading: 16,
                           paddingTrailling: 16)
        
        finalPriceLabel.anchor(top: lineSpacing2.bottomAnchor,
                               leading: leadingAnchor,
                               trailling: trailingAnchor,
                               paddingTop: 16,
                               paddingLeading: 16,
                               paddingTrailling: 16)
        
        freeCancelationLabel.anchor(top: finalPriceLabel.bottomAnchor,
                                    leading: leadingAnchor,
                                    trailling: trailingAnchor,
                                    paddingTop: 8,
                                    paddingLeading: 16,
                                    paddingTrailling: 16)
        
        lineSpacing3.anchor(top: freeCancelationLabel.bottomAnchor,
                           leading: leadingAnchor,
                           trailling: trailingAnchor,
                           paddingTop: 16,
                           paddingLeading: 16,
                           paddingTrailling: 16)

        
        hotelDescriptionsStackView.anchor(top: lineSpacing3.bottomAnchor,
                                          leading: leadingAnchor,
                                          bottom: bottomAnchor,
                                          trailling: trailingAnchor,
                                          paddingTop: 16,
                                          paddingLeading: 16,
                                          paddingBottom: 16,
                                          paddingTrailling: 16)
        
        imageStackView.addConstraintsToFillView(imageScrollView)
    }
}

extension DetailContainerView: UIScrollViewDelegate {
    /// Set the current page of `PageControl` based on the `ScrollView` lenth.
    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        imagePageControl.currentPage = Int(scrollView.contentOffset.x / (scrollView.frame.width))
    }
}
