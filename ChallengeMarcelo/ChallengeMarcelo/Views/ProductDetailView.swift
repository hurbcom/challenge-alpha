import UIKit
import MapKit

final class ProductDetailView: UIView {
    var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        let collectionView = UICollectionView(frame: CGRect.zero, collectionViewLayout: layout)
        layout.scrollDirection = .horizontal
        return collectionView
    }()
    
    let nameLabel = UILabel()
    let descriptionLabel = UILabel()
    let stateLabel = UILabel()
    let countryLabel = UILabel()
    let cityLabel = UILabel()
    let priceLabel = UILabel()
    let mapView = MKMapView()
    
    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        addSubviews()
        disableAutoresizingMaskIntoConstraints()
        setupConstraints()

        nameLabel.backgroundColor = .red
    }
    
    func addSubviews() {
        addSubview(collectionView)
        addSubview(nameLabel)
        addSubview(descriptionLabel)
        addSubview(stateLabel)
        addSubview(countryLabel)
        addSubview(cityLabel)
        addSubview(priceLabel)
        addSubview(mapView)
    }
    
    func disableAutoresizingMaskIntoConstraints() {
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        nameLabel.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        stateLabel.translatesAutoresizingMaskIntoConstraints = false
        countryLabel.translatesAutoresizingMaskIntoConstraints = false
        cityLabel.translatesAutoresizingMaskIntoConstraints = false
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        mapView.translatesAutoresizingMaskIntoConstraints = false
    }
    
    required init?(coder: NSCoder) { nil }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: topAnchor),
            collectionView.leftAnchor.constraint(equalTo: leftAnchor),
            collectionView.rightAnchor.constraint(equalTo: rightAnchor),
            collectionView.heightAnchor.constraint(equalToConstant: 400),
            
            nameLabel.topAnchor.constraint(equalTo: collectionView.bottomAnchor, constant: 10),
            nameLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
            nameLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
            
//            stateLabel.topAnchor.constraint(equalTo: collectionView.bottomAnchor, constant: 10),
//            stateLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
//            stateLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
        ])
    }
}
