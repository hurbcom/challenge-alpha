//
//  CardInfoTableViewCell.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import UIKit

class CardInfoTableViewCell: UITableViewCell {
    static let identifierCell = "CardInfoTableViewCell"
    lazy var cardImage: UIImageView = {
        let image = UIImageView()
        image.contentMode = .scaleToFill
        image.translatesAutoresizingMaskIntoConstraints = false
        image.layer.cornerRadius = 5
        image.layer.masksToBounds = true
        return image
    }()
    lazy var cardTitle: UILabel = {
       let label = UILabel()
        label.font = .systemFont(ofSize: 18, weight: .bold)
        label.textColor = .label
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    lazy var cardDetail: UILabel = {
       let label = UILabel()
        label.textColor = .secondaryLabel
        label.font = .systemFont(ofSize: 18, weight: .regular)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    lazy var VStack: UIStackView = {
        let vstack = UIStackView()
        vstack.axis = .vertical
        vstack.translatesAutoresizingMaskIntoConstraints = false
        vstack.distribution = .fill
        return vstack
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.backgroundColor = .clear
        
        self.contentView.addSubview(VStack)
        VStack.addArrangedSubview(cardImage)
        VStack.addArrangedSubview(cardTitle)
        VStack.addArrangedSubview(cardDetail)
        
        configureConstraints()
    }
    override func layoutSubviews() {
        super.layoutSubviews()

        contentView.frame = contentView.frame.inset(by: UIEdgeInsets(top: 20, left: 0, bottom: 20, right: 0))
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func configureConstraints(){
        NSLayoutConstraint.activate([
            VStack.topAnchor.constraint(equalTo: self.contentView.topAnchor),
            VStack.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor),
            VStack.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor),
            cardImage.heightAnchor.constraint(equalToConstant: 160),
            cardImage.widthAnchor.constraint(equalToConstant: 150),
        ])
    }
    
    func setCellData(selectedHotel:HotelsInfoViewModel) {
        cardTitle.text = selectedHotel.name
        cardDetail.text = selectedHotel.place
        getImageFrom(url: selectedHotel.imgGallery)
    }
    
    func getImageFrom(url:String) {
        let urlf = url
        
        print("URL CONCLUIDA: " + urlf)
        let url = URL(string:urlf)
        let data = try? Data(contentsOf: url!)
        cardImage.image  = UIImage(data: data!)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

}
