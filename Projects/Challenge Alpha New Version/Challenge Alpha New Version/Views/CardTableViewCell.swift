//
//  CardInfoTableViewCell.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import UIKit
import HUGraphQL

class CardTableViewCell: UITableViewCell {
    static let cellID = "CardTableViewCell"
    let cardImageView = UIImageView()
    let titleLabel = UILabel()
    let subtitleLabel = UILabel()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureUI()
        configureConstraints()
        
    }
    
    private func configureUI() {
        // Configure cardImageView
        cardImageView.contentMode = .scaleAspectFill
        cardImageView.clipsToBounds = true
        contentView.addSubview(cardImageView)
        
        // Configure titleLabel
        titleLabel.font = UIFont.systemFont(ofSize: 16, weight: .semibold)
        contentView.addSubview(titleLabel)
        
        // Configure subtitleLabel
        subtitleLabel.font = UIFont.systemFont(ofSize: 14)
        subtitleLabel.textColor = .gray
        contentView.addSubview(subtitleLabel)
    }
    
    private func configureConstraints(){
        // Add constraints
        cardImageView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        subtitleLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.numberOfLines = 0
        
        NSLayoutConstraint.activate([
            cardImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            cardImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            cardImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            cardImageView.heightAnchor.constraint(equalToConstant: 200),
            
            titleLabel.topAnchor.constraint(equalTo: cardImageView.bottomAnchor, constant: 8),
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            
            subtitleLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 4),
            subtitleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            subtitleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            subtitleLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -8)
        ])
    }
    
    func setCellData(selectedHotel:HotelsInfoViewModel) {
        titleLabel.text = selectedHotel.name
        subtitleLabel.text = selectedHotel.place
        getImageFrom(url: selectedHotel.imgGallery)
    }
    
    func setCellDataPackage(package:PackageInfoViewModel) {
        titleLabel.text = package.name
        subtitleLabel.text = package.prodtype
        getImageFrom(url: package.imgGallery)
    }
    
    func displayFavorite(fav:FavoriteViewModel) {
        titleLabel.text = fav.name
        subtitleLabel.text = fav.category
        getImageFrom(url: fav.urlImage)
    }
    
     private func getImageFrom(url:String) {
        let url = URL(string:url)
        if let data = try? Data(contentsOf: url!) {
            cardImageView.image  = UIImage(data: data)
        }
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
