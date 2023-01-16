import UIKit

final class ProductListCell: UITableViewCell {
    let cardView = UIView()
    let titleLabel = UILabel()
    let priceLabel = UILabel()
    let photoView = UIImageView()
    let descriptionLabel = UILabel()
    let smallAddressLabel = UILabel()

    // MARK: Init
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        selectionStyle = .none
        setupUI()
    }
    
    required init?(coder: NSCoder) { nil }
    
    // MARK: Override
    
    override func prepareForReuse() {
        titleLabel.text = nil
        priceLabel.text = nil
        photoView.image = nil
        descriptionLabel.text = nil
        smallAddressLabel.text = nil
    }
    
    // MARK: Private
    
    private func setupUI() {
        backgroundColor = .clear
        
        titleLabel.numberOfLines = 0
        descriptionLabel.numberOfLines = 0
        
        photoView.clipsToBounds = true
        photoView.contentMode = .scaleAspectFill

        titleLabel.font = .boldSystemFont(ofSize: 17.0)
        descriptionLabel.font = .systemFont(ofSize: 12.0)
        smallAddressLabel.font = .systemFont(ofSize: 14.0)
        
        smallAddressLabel.textColor = .darkGray
        
        cardView.translatesAutoresizingMaskIntoConstraints = false
        photoView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        smallAddressLabel.translatesAutoresizingMaskIntoConstraints = false

        cardView.layer.shadowColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1).cgColor
        cardView.layer.shadowOffset = .zero
        cardView.layer.shadowOpacity = 0.5
        cardView.layer.shadowRadius = 4

        cardView.layer.cornerRadius = 10
        cardView.backgroundColor = .white
        
        cardView.addSubview(photoView)
        cardView.addSubview(titleLabel)
        cardView.addSubview(priceLabel)
        cardView.addSubview(descriptionLabel)
        cardView.addSubview(smallAddressLabel)
        contentView.addSubview(cardView)

        NSLayoutConstraint.activate([
            photoView.topAnchor.constraint(equalTo: cardView.topAnchor),
            photoView.leftAnchor.constraint(equalTo: cardView.leftAnchor),
            photoView.rightAnchor.constraint(equalTo: cardView.rightAnchor),
            photoView.heightAnchor.constraint(equalToConstant: 120),
            
            titleLabel.topAnchor.constraint(equalTo: photoView.bottomAnchor, constant: 20),
            titleLabel.leftAnchor.constraint(equalTo: cardView.leftAnchor, constant: 20),
            titleLabel.rightAnchor.constraint(equalTo: cardView.rightAnchor, constant: -20),

            smallAddressLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            smallAddressLabel.leftAnchor.constraint(equalTo: titleLabel.leftAnchor),

            priceLabel.topAnchor.constraint(equalTo: smallAddressLabel.bottomAnchor, constant: 10),
            priceLabel.leftAnchor.constraint(equalTo: smallAddressLabel.leftAnchor),
            priceLabel.heightAnchor.constraint(equalToConstant: 45),
            
            descriptionLabel.topAnchor.constraint(equalTo: priceLabel.bottomAnchor, constant: 10),
            descriptionLabel.leftAnchor.constraint(equalTo: cardView.leftAnchor, constant: 10),
            descriptionLabel.rightAnchor.constraint(equalTo: cardView.rightAnchor, constant: -10),
            descriptionLabel.bottomAnchor.constraint(lessThanOrEqualTo: cardView.bottomAnchor, constant: -20),
            
            cardView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 20),
            cardView.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 20),
            cardView.rightAnchor.constraint(equalTo: contentView.rightAnchor, constant: -20),
            cardView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -20),
        ])
    }
    
    func setupCell(product: Product) {
        titleLabel.text = product.name
        descriptionLabel.text = product.smallDescription
        smallAddressLabel.text = "\(product.address.city), \(product.address.country)"
        
        if let firstImage = product.gallery.first {
            photoView.from(url: firstImage)
        } else {
            photoView.image = UIImage(named: "Hurb")
        }

        let nf = NumberFormatter()
        nf.locale = Locale(identifier: "pt-BR")
        nf.numberStyle = .currency
        priceLabel.text = nf.string(from: NSNumber(value: product.price))
    }
}
