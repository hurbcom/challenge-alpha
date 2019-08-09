//
//  HotelDetailViewController.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit
import ImageSlideshow

class HotelDetailViewController: UIViewController {

    var hotelViewModel: HotelViewModel!
    var amenities: [Amenity] = []
    var imageArray: [UIImage] = []
    
    @IBOutlet weak var slideshow: ImageSlideshow!
//    @IBOutlet weak var imgPhoto: UIImageView!
    @IBOutlet weak var lbCity: UILabel!
    @IBOutlet weak var lbName: UILabel!
    @IBOutlet weak var imgStar1: UIImageView!
    @IBOutlet weak var imgStar2: UIImageView!
    @IBOutlet weak var imgStar3: UIImageView!
    @IBOutlet weak var imgStar4: UIImageView!
    @IBOutlet weak var imgStar5: UIImageView!
    
    @IBOutlet weak var vwFreeCancellation: UIView!
    @IBOutlet weak var lbPriceDescription: UILabel!
    @IBOutlet weak var lbPrice: UILabel!
    
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var vwAmenities: UIView!
    @IBOutlet weak var lbAmenitiesTotal: UILabel!
    
    @IBOutlet weak var tableViewAmenities: UITableView!
    @IBOutlet weak var heightTableViewAmenities: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateUI()
        
        self.navigationController?.title = "Detalhe do Hotel"
    }
    
    func updateUI() {
        
        lbCity.text = hotelViewModel.cityState
        lbName.text = hotelViewModel.name
//        imgPhoto.sd_setImage(with: hotelViewModel.imageUrl, placeholderImage: UIImage(named: "placeholderImage"), options: [.continueInBackground], completed: nil)
        
        setImageGallery()
        setStars()
        vwFreeCancellation.isHidden = !hotelViewModel.isFreeCancellation
        
        lbPriceDescription.text = hotelViewModel.priceDescription
        lbPrice.text = hotelViewModel.price
        
        lbDescription.text = hotelViewModel.description
        
        amenities = hotelViewModel.amenities
        lbAmenitiesTotal.text = "\(amenities.count) Amenidades"

        tableViewAmenities.reloadData()
        heightTableViewAmenities.constant = tableViewAmenities.contentSize.height
    }
    
    
    
    fileprivate func setStars(){
        let imgStars = [imgStar1, imgStar2, imgStar3, imgStar4, imgStar5]
        
        if let stars = hotelViewModel.stars {
            for imgStar in imgStars {
                if imgStar!.tag < stars {
                    imgStar?.image = UIImage(named: "star")
                } else {
                    imgStar?.image = UIImage()
                }
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.setNeedsStatusBarAppearanceUpdate()
    }
    override var preferredStatusBarStyle : UIStatusBarStyle {
        return .lightContent
    }
    
}

extension HotelDetailViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return amenities.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "amenityCell", for: indexPath)
        
        cell.textLabel?.text = "◆ \(amenities[indexPath.row].name!)"
        cell.textLabel?.font = UIFont(name: "HelveticaNeue", size: 10)
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
        return 18
    }
}

extension HotelDetailViewController: ImageSlideshowDelegate {
    fileprivate func setImageGallery(){
        var sources: [SDWebImageSource] = []
        
        for image in hotelViewModel.gallery {
            let sdWebImageSource = SDWebImageSource(urlString: image.url!, placeholder: UIImage(named: "placeholderImage"))!
            sources.append(sdWebImageSource)
        }
        
        slideshow.pageIndicatorPosition = .init(horizontal: .center, vertical: .under)
        slideshow.contentScaleMode = UIViewContentMode.scaleAspectFill
        
        let pageControl = UIPageControl()
        pageControl.currentPageIndicatorTintColor = UIColor.lightGray
        pageControl.pageIndicatorTintColor = UIColor.black
        slideshow.pageIndicator = pageControl
        
        // optional way to show activity indicator during image load (skipping the line will show no activity indicator)
        slideshow.activityIndicator = DefaultActivityIndicator()
        slideshow.delegate = self
        
        // can be used with other sample sources as `afNetworkingSource`, `alamofireSource` or `sdWebImageSource` or `kingfisherSource`
        slideshow.setImageInputs(sources)
        
        let recognizer = UITapGestureRecognizer(target: self, action: #selector(didTap))
        slideshow.addGestureRecognizer(recognizer)
        
        //        for image in hotelViewModel.gallery {
        //            let imageView = UIImageView(frame: imgPhoto.frame)
        //            imageView.sd_setImage(with: URL(string: image.url!), placeholderImage: UIImage(named: "placeholderImage"), options: [.continueInBackground], completed: nil)
        //        }
        
    }
    
    @objc func didTap() {
        let fullScreenController = slideshow.presentFullScreenController(from: self)
        // set the activity indicator for full screen controller (skipping the line will show no activity indicator)
        fullScreenController.slideshow.activityIndicator = DefaultActivityIndicator(style: .white, color: nil)
    }

    func imageSlideshow(_ imageSlideshow: ImageSlideshow, didChangeCurrentPageTo page: Int) {
        print("current page:", page)
    }
}
