import UIKit

final class ProductListCell: UITableViewCell {
    let cardView = UIView()
    let titleLabel = UILabel()
    let priceLabel = UILabel()
    let categoryLabel = UILabel()
    let descriptionLabel = UILabel()
    
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
        categoryLabel.text = nil
        descriptionLabel.text = nil
    }
    
    // MARK: Private
    
    private func setupUI() {
        backgroundColor = .clear
        
        titleLabel.numberOfLines = 0
        descriptionLabel.numberOfLines = 0
        
        cardView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        categoryLabel.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false

        cardView.layer.shadowColor = UIColor.white.cgColor
        cardView.layer.shadowOffset = .zero
        cardView.layer.shadowOpacity = 1
        cardView.layer.shadowRadius = 4

        cardView.layer.cornerRadius = 10
        cardView.backgroundColor = .white
        
        cardView.addSubview(titleLabel)
        cardView.addSubview(priceLabel)
        cardView.addSubview(categoryLabel)
        cardView.addSubview(descriptionLabel)
        contentView.addSubview(cardView)

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: cardView.topAnchor, constant: 20),
            titleLabel.leftAnchor.constraint(equalTo: cardView.leftAnchor, constant: 20),
            titleLabel.rightAnchor.constraint(equalTo: cardView.rightAnchor, constant: -20),
            
            categoryLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            categoryLabel.leftAnchor.constraint(equalTo: titleLabel.leftAnchor),

            priceLabel.topAnchor.constraint(equalTo: categoryLabel.bottomAnchor, constant: 10),
            priceLabel.leftAnchor.constraint(equalTo: categoryLabel.leftAnchor),
            priceLabel.heightAnchor.constraint(equalToConstant: 45),
            
            descriptionLabel.topAnchor.constraint(equalTo: priceLabel.bottomAnchor, constant: 10),
            descriptionLabel.leftAnchor.constraint(equalTo: cardView.leftAnchor, constant: 10),
            descriptionLabel.rightAnchor.constraint(equalTo: cardView.rightAnchor, constant: -10),
            descriptionLabel.bottomAnchor.constraint(lessThanOrEqualTo: cardView.bottomAnchor, constant: -20),
            
            cardView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10),
            cardView.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 10),
            cardView.rightAnchor.constraint(equalTo: contentView.rightAnchor, constant: -10),
            cardView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -10),
        ])
    }
    
    func setupCell(product: Product) {
        titleLabel.text = product.name
        categoryLabel.text = product.category.capitalized
        descriptionLabel.text = product.smallDescription
        
        let nf = NumberFormatter()
        nf.locale = Locale(identifier: "pt-BR")
        nf.numberStyle = .currency
        priceLabel.text = nf.string(from: NSNumber(value: product.price))
    }
}
