//
//  DetailsViewController.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import UIKit

// MARK: - Declaration

class DetailsViewController: UIViewController {
    
    // - MARK: Outlets
    
    @IBOutlet weak var photoImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var star1Image: UIImageView!
    @IBOutlet weak var star2Image: UIImageView!
    @IBOutlet weak var star3Image: UIImageView!
    @IBOutlet weak var star4Image: UIImageView!
    @IBOutlet weak var star5Image: UIImageView!
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var amenitiesTextView: UITextView!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var cityStateLabel: UILabel!
    @IBOutlet weak var favoriteButton: UIButton!
    
    
    
    // - MARK: The hotel selected from table view
    var currentHotel:Result?
    
    // The bool to check if the hotel is favorited
    var isFlagged: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        setUpView()
    }
    
    // The images manager instance
    let imageManager = ImagesManager.instance
    
    var dao:DAO {
        return DAO.instance
    }
    
    /**
     Set up all view elements.
     */
    func setUpView() {
        guard let myHotel = currentHotel else {
            self.dismiss(animated: true, completion: nil)
            return
        }
        
        if dao.favorites.contains(where: { $0.id == myHotel.id }) {
            self.favoriteButton.isSelected = true
        }
        self.photoImageView.layer.cornerRadius = 10
        self.photoImageView.clipsToBounds = true
        self.nameLabel.text = myHotel.name
        self.nameLabel.textColor = .baseBlue
        self.descriptionTextView.text = myHotel.smallDescription ?? "Sem descrição cadastrada."
        self.descriptionTextView.layer.cornerRadius = 10
        self.descriptionTextView.clipsToBounds = true
        let currency = String(myHotel.price.currency ?? "BRL") + " "
        var cents = String(myHotel.price.amount).split(separator: ".")[1]
        let value = String(myHotel.price.amount).split(separator: ".")[0]
        if cents.count == 1 {
            cents = cents + "0"
        }
        self.priceLabel.text = currency + value + "," + cents
        self.cityStateLabel.text = myHotel.address.city + " | " + myHotel.address.state
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
        
        let stars = [self.star1Image, self.star2Image, self.star3Image, self.star4Image, self.star5Image]
        if myHotel.stars != nil {
            for i in 0...myHotel.stars! - 1 {
                stars[i]!.isHidden = false
            }
        }
        
        // Favorite Button image
        let imageSelected = UIImage(named: "favoritoSelected")
        favoriteButton.setImage(imageSelected, for: .selected)
        
        let imageNormal = UIImage(named: "favoriteFlag")
        favoriteButton.setImage(imageNormal, for: .normal)
    }
    
    
    /**
     Convert image URL into data and tries to convert data into UIImage.
     - Parameters:
        - imageURL: The URL image to convert.
     */
    func loadImage(from imageURL: String) {
        imageManager.onImageView = self.photoImageView
        imageManager.tryConvertionFromURL(from: imageURL)
    }
    

    /**
     Handles adding/removing hotel from favorite.
     */
    @IBAction func addToFavorite(_ sender: Any) {
        guard let myHotel = currentHotel else {
            self.dismiss(animated: true, completion: nil)
            return
        }
        dao.manageFavorite(at: myHotel)
        
        if dao.favorites.contains(where: { $0.id == myHotel.id }) {
            self.favoriteButton.isSelected = true
        } else {
            self.favoriteButton.isSelected = false
        }
    }
    
    /**
     Opens on browser the url from the current hotel.
     */
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
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destinationVC = segue.destination as? FeedViewController {
            destinationVC.feedTableView.reloadData()
        } else if let destinationVC = segue.destination as? FavoritesViewController {
            destinationVC.favoritesTableView.reloadData()
        }
    }
}

