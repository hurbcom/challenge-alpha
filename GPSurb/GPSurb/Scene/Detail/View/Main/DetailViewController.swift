//
//  DetailViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 18/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Kingfisher
import Cosmos
import Lottie

class DetailViewController: UIViewController {
    
    // MARK: OUTLETS
    @IBOutlet weak var imgHeader: UIImageView!
    @IBOutlet weak var lblLocation: UILabel!
    @IBOutlet weak var lblShortDescription: UILabel!
    @IBOutlet weak var viewStar: CosmosView!
    @IBOutlet weak var txtViewDescription: UITextView!
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var viewFavorite: AnimationView!
    // MARK: CONSTANTS
    
    // MARK: VARIABLES
    private var presenter: DetailPresenter!
    public lazy var viewData = ResultViewData()
    private var isFavorite = false
    // MARK: IBACTIONS
    
    @objc private func addAndRemoveFavorite() {
        HapticAlert.hapticReturn(style: .medium)
        guard !self.viewFavorite.isAnimationPlaying else { return }
        self.presenter.addOrRemoveFavorite(viewData: self.viewData)
        if self.isFavorite {
            self.viewFavorite.stop()
        } else {
            self.viewFavorite.play()
        }
        self.isFavorite = !self.isFavorite
    }
}

// MARK: - LIFE CYCLE -
extension DetailViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = DetailPresenter()
        self.downloadImage(urlString: self.viewData.urlImageCard)
        self.registerNIB()
        self.setupView()
        self.addGesture()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.isFavoriteInDataBase()
    }
}

// MARK: - DELEGATE COLLECTIONVIEWDATASOURCE -
extension DetailViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.viewData.amenities.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell: AmenitiesCollectionViewCell = self.collectionView.dequeueReusableCell(for: indexPath)
        cell.setupCell(description: self.viewData.amenities[indexPath.row])
        return cell
    }
}

// MARK: - AUX METHODS -
extension DetailViewController {
    private func downloadImage(urlString: String) {
           if let url: URL = URL(string: urlString) {
               let resource = ImageResource(downloadURL: url, cacheKey: urlString)
               let processor = DownsamplingImageProcessor(size: self.imgHeader.bounds.size)
               self.imgHeader.kf.indicatorType = .activity
               self.imgHeader.kf.setImage(with: resource, placeholder: nil, options: [.transition(.fade(0.8)), .cacheOriginalImage, .processor(processor)], progressBlock: nil) { (result) in
                   switch result {
                   case .failure(_):
                        self.imgHeader.image = UIImage(named: "")
                   default: break
                   }
               }
           }
       }
    
    private func registerNIB() {
        self.collectionView.register(AmenitiesCollectionViewCell.self)
    }
    
    private func setupView() {
        self.lblLocation.text = self.viewData.destinationName
        self.lblShortDescription.text = self.viewData.offerName
        self.txtViewDescription.text = self.viewData.description.textWithoutHtml
        self.viewStar.isHidden = self.viewData.type == .package
        self.viewStar.rating = self.viewData.stars
        self.viewStar.text = "(\(self.viewData.stars))"
        self.setupFavoriteView()
    }
    
    private func setupFavoriteView() {
        let animation = Animation.named("favourite_app_icon")
        viewFavorite.animation = animation
    }
    
    private func isFavoriteInDataBase() {
        self.isFavorite = self.presenter.isExistFavorite(sku: self.viewData.sku)
        if !self.isFavorite {
            self.viewFavorite.stop()
        } else {
            self.viewFavorite.play()
        }
    }
    
    private func addGesture() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(self.addAndRemoveFavorite))
        self.viewFavorite.addGestureRecognizer(tap)
    }
}
