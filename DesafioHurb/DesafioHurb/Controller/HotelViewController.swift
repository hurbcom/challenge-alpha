//
//  HotelViewController.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 24/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class HotelViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    private var tableView: UITableView!
    private var hotel: Hotel!
    private var imageView: UIImageView!
    private var imageStarsView : UIImageView!
    let font = UIFont.systemFont(ofSize: UIFont.systemFontSize)
    
    init(hotel: Hotel) {
        super.init(nibName: nil, bundle: nil)
        self.hotel = hotel
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.configView()
    }
    
    func configView() {
        self.view.backgroundColor = UIColor.white
        self.title = self.hotel.name
        
        let origin : CGFloat = 64
        self.tableView = UITableView(frame: CGRect(x: 0, y: origin, width: screenWidth, height: screenHeight - origin))
        self.tableView.backgroundColor = gray
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")
        self.tableView.separatorInset.left = 0
        self.tableView.allowsSelection = false
        self.view.addSubview(self.tableView)
        
        self.imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenWidth/1.5))
        self.imageView.contentMode = .scaleToFill
        if let url = URL.init(string: self.hotel.imageUrl) {
            self.imageView.load.request(with: url)
        }
        
        self.imageStarsView = UIImageView(frame: CGRect(x: screenWidth/2, y: 10, width: screenWidth/2, height: 30))
        self.imageStarsView.image = UIImage(named: "stars5")
        self.imageStarsView.contentMode = .scaleAspectFit
    }
    
    // MARK: Table view delegate and data source
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 6
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        let row = indexPath.row
        cell.backgroundColor = UIColor.white
        cell.textLabel?.textColor = UIColor.black
        cell.textLabel?.font = self.font
        
        switch row {
        case 0:
            cell.addSubview(self.imageView)
        case 1:
            cell.textLabel?.text = self.hotel.name
            cell.textLabel?.font = UIFont.boldSystemFont(ofSize: 20)
            cell.backgroundColor = red
            cell.textLabel?.textColor = UIColor.white
        case 2:
            cell.textLabel?.text = "\(self.hotel.city), \(self.hotel.state)"
        case 3:
            cell.textLabel?.text = "Hotel oferece: \(self.hotel.getAmenitiesString())"
            cell.textLabel?.numberOfLines = 0
        case 4:
            cell.textLabel?.text = "R$ \(hotel.price)"
            cell.textLabel?.font = UIFont.boldSystemFont(ofSize: 16)
            cell.addSubview(self.imageStarsView)
            self.configImageStars()
        case 5:
            cell.textLabel?.text = "Mais informações: \n\n\(self.hotel.description)"
            cell.textLabel?.numberOfLines = 0
            cell.backgroundColor = gray
            
        default:
            cell.textLabel?.text = "Teste"
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let row = indexPath.row
        switch row {
        case 0:
            return screenWidth/1.5
        case 1:
            return 40
        case 3:
            return self.heightForView(text: self.hotel.getAmenitiesString(), font: self.font, width: screenWidth - 20)
        case 5:
            return self.heightForView(text: self.hotel.description, font: self.font, width: screenWidth - 20)
        default:
            return 50
        }
    }
    
    // MARK: Auxiliary
    
    func heightForView(text: String, font: UIFont, width: CGFloat) -> CGFloat {
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: width, height: screenWidth))
        label.font = font
        label.text = "Mais informações: \n\n\(text)"
        label.numberOfLines = 0
        
        label.sizeToFit()
        return label.frame.height + 44
    }
    
    func configImageStars() {
        if hotel.isPackage {
            self.imageStarsView.image = nil
        } else {
            switch hotel.stars {
            case 1:
                self.imageStarsView.image = UIImage(named: "stars1")
            case 2:
                self.imageStarsView.image = UIImage(named: "stars2")
            case 3:
                self.imageStarsView.image = UIImage(named: "stars3")
            case 4:
                self.imageStarsView.image = UIImage(named: "stars4")
            case 5:
                self.imageStarsView.image = UIImage(named: "stars5")
            default:
                self.imageStarsView.image = UIImage(named: "stars1")
            }
        }
    }
    
    
}
