import UIKit
import MapKit

final class ProductDetailView: UIView {
    let collectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.minimumLineSpacing = 0
        layout.minimumInteritemSpacing = 0
        let collection = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collection.isPagingEnabled = true
        return collection
    }()

    let titleLabel = UILabel()
    let descriptionLabel = UILabel()
    let smallAddressLabel = UILabel()
    let priceLabel = UILabel()
    let mapView = MKMapView()
    let suggestionButton = UIButton()
    
    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        addSubviews()
        disableAutoresizingMaskIntoConstraints()
        setupUI()
        setupConstraints()
    }
    
    private func addSubviews() {

        
        addSubview(mapView)
        addSubview(titleLabel)
        addSubview(priceLabel)
        addSubview(collectionView)
        addSubview(descriptionLabel)
        addSubview(smallAddressLabel)
        addSubview(suggestionButton)
    }
    
    private func disableAutoresizingMaskIntoConstraints() {
        mapView.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        smallAddressLabel.translatesAutoresizingMaskIntoConstraints = false
        suggestionButton.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setupUI() {
        collectionView.backgroundColor = .darkGray

        titleLabel.numberOfLines = 0
        descriptionLabel.numberOfLines = 0
        
        suggestionButton.backgroundColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)
        suggestionButton.tintColor = .white
        suggestionButton.clipsToBounds = true
        suggestionButton.layer.cornerRadius = 7
        suggestionButton.setTitle("Sugestões para você", for: .normal)

        titleLabel.font = .boldSystemFont(ofSize: 17.0)
        descriptionLabel.font = .systemFont(ofSize: 12.0)
        smallAddressLabel.font = .systemFont(ofSize: 14.0)

        smallAddressLabel.textColor = .darkGray
    }
    
    
    required init?(coder: NSCoder) { nil }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: topAnchor),
            collectionView.leftAnchor.constraint(equalTo: leftAnchor),
            collectionView.rightAnchor.constraint(equalTo: rightAnchor),
            collectionView.heightAnchor.constraint(equalToConstant: 300),
            
            titleLabel.topAnchor.constraint(equalTo: collectionView.bottomAnchor, constant: 10),
            titleLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            titleLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            
            smallAddressLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            smallAddressLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            
            priceLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            priceLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            
            mapView.topAnchor.constraint(equalTo: smallAddressLabel.bottomAnchor, constant: 20),
            mapView.leftAnchor.constraint(equalTo: leftAnchor, constant: 20),
            mapView.rightAnchor.constraint(equalTo: rightAnchor, constant: -20),
            mapView.heightAnchor.constraint(equalToConstant: 250),
            
            descriptionLabel.topAnchor.constraint(equalTo: mapView.bottomAnchor,constant: 10),
            descriptionLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            descriptionLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            
            suggestionButton.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor,constant: 10),
            suggestionButton.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            suggestionButton.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            suggestionButton.heightAnchor.constraint(equalToConstant: 45),
            suggestionButton.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor, constant: -10)
        ])
    }
}
