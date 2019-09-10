//
//  DetailsViewController.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController {
    
    // - MARK: Outlets
    
    @IBOutlet weak var photo: UIImageView!
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var star1: UIImageView!
    @IBOutlet weak var star2: UIImageView!
    @IBOutlet weak var star3: UIImageView!
    @IBOutlet weak var star4: UIImageView!
    @IBOutlet weak var star5: UIImageView!
    
    @IBOutlet weak var descriptionTextView: UITextView!
    
    @IBOutlet weak var amenitiesTextView: UITextView!
    
    @IBOutlet weak var vmax: UILabel!
    @IBOutlet weak var local: UILabel!
    
    var currentHotel:Result?
    
    enum LoadingState {
        case notLoading
        case loading
        case loaded(UIImage)
    }
    
    private let activityView = UIActivityIndicatorView(style: .gray)
    
    var loadingState: LoadingState = .notLoading {
        didSet {
            switch loadingState {
            case .notLoading:
                photo.image = nil
                activityView.stopAnimating()
            case .loading:
                photo.image = nil
                activityView.startAnimating()
            case let .loaded(img):
                photo.image = img
                activityView.stopAnimating()
            }
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        setUpView()
    }
    
    func setUpView() {
        guard let myHotel = currentHotel else {
            self.dismiss(animated: true, completion: nil)
            return
        }
        self.name.text = myHotel.name
        self.name.textColor = .baseBlue
        self.descriptionTextView.text = myHotel.resultDescription
        self.descriptionTextView.layer.cornerRadius = 10
        self.descriptionTextView.clipsToBounds = true
        let currency = String(myHotel.price.currency ?? "BRL") + " "
        var cents = String(myHotel.price.amount).split(separator: ".")[1]
        let value = String(myHotel.price.amount).split(separator: ".")[0]
        if cents.count == 1 {
            cents = cents + "0"
        }
        self.vmax.text = currency + value + "," + cents
        self.local.text = myHotel.address.city + " | " + myHotel.address.state
        var amenitiesText = ""
        for amenity in myHotel.amenities {
            if let amenityName = amenity.name {
                if amenitiesText == "" {
                    amenitiesText = amenityName
                } else {
                    amenitiesText = amenitiesText + " | " + amenityName
                }
            }
            amenitiesTextView.text = amenitiesText
            self.amenitiesTextView.layer.cornerRadius = 10
            self.amenitiesTextView.clipsToBounds = true
        }
        loadImage(from: myHotel.gallery[0].url)
        
        let stars = [self.star1, self.star2, self.star3, self.star4, self.star5]
        if myHotel.stars != nil {
            for i in 0...myHotel.stars! - 1 {
                stars[i]!.isHidden = false
            }
        }
    }
    
    func loadImage(from imageURL: String) {
        DispatchQueue.main.async {
            self.loadingState = .loading
            guard let url = URL(string: imageURL) else {
                debugPrint("error in image url", #function)
                return
            }
            guard let data = try? Data(contentsOf: url) else {
                //                debugPrint("error getting data", #function, url)
                return
            }
            guard let img = UIImage(data: data) else {
                debugPrint("error in uiimage", #function)
                self.loadingState = .notLoading
                return
            }
            self.loadingState = .loaded(img)
        }
        
    }
    

    @IBAction func addToFavorite(_ sender: Any) {
    }
    
    
    @IBAction func seeOnWebsite(_ sender: Any) {
        guard let myHotel = currentHotel else {
            self.dismiss(animated: true, completion: nil)
            return
        }
        guard let url = URL(string: myHotel.url) else { return }
        UIApplication.shared.open(url)
    }
    
    @IBAction func dismiss(_ sender: Any) {
        
        self.dismiss(animated: true, completion: nil)
    }
}

