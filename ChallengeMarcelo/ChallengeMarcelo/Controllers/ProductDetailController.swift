import UIKit
import MapKit
import CoreLocation
import ScrollViewController

final class ProductDetailViewController: UIViewController {
    // MARK: UI

    lazy private var screenView = ProductDetailView()
    lazy private var scrollViewController = ScrollViewController()
    
    // MARK: Properties
    
    private let viewModel: ProductDetailViewModel

    // MARK: Init
    
    init(model: Product) {
        self.viewModel = ProductDetailViewModel(model: model)
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) { nil }
    
    // MARK: Override
    
    override func viewDidLoad() {
        super.viewDidLoad()
        embedScrollViewController()
        configView()
        
        viewModel.performSuggestions()
        screenView.collectionView.delegate = self
        screenView.collectionView.dataSource = self
        screenView.collectionView.register(PhotoCollectionCell.self, forCellWithReuseIdentifier: "PhotoCollectionCell")
        screenView.suggestionButton.addTarget(self, action: #selector(didTapSuggestion), for: .touchUpInside)
    }
    
    @objc func didTapSuggestion() {
        let nextController = SuggestionsViewController(model: viewModel.suggestions)
        navigationController?.present(nextController, animated: true)
        
    }
    
    private func embedScrollViewController() {
        addChild(scrollViewController)
        view.addSubview(scrollViewController.view)
        scrollViewController.scrollView.backgroundColor = .white
        scrollViewController.view.translatesAutoresizingMaskIntoConstraints = false
        scrollViewController.view.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        scrollViewController.view.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        scrollViewController.view.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        scrollViewController.view.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        scrollViewController.didMove(toParent: self)
        scrollViewController.contentView = screenView
    }
    
    private func configView() {
        navigationItem.title = viewModel.model.category.capitalized
        navigationItem.rightBarButtonItem = UIBarButtonItem(image: UIImage(systemName: "square.and.arrow.up"), style: .done, target: nil, action: nil)
        screenView.titleLabel.text = viewModel.model.name
        screenView.descriptionLabel.text = viewModel.model._description
        screenView.smallAddressLabel.text =
        "\(viewModel.model.address.city), \(viewModel.model.address.state) - \(viewModel.model.address.country)"
        
        let nf = NumberFormatter()
        nf.locale = Locale(identifier: "pt-BR")
        nf.numberStyle = .currency
        screenView.priceLabel.text = nf.string(from: NSNumber(value: viewModel.model.price))
        
        let latitude = viewModel.model.address.geoLocation.lat
        let longitude = viewModel.model.address.geoLocation.lon

        var region = MKCoordinateRegion()
        region.center.latitude = latitude
        region.center.longitude = longitude
        region.span.latitudeDelta = 0.05
        region.span.longitudeDelta = 0.05
    
        screenView.mapView.setRegion(
            screenView.mapView.regionThatFits(region),
            animated: true
        )

        screenView.mapView.addAnnotation(MKPlacemark(
            coordinate: CLLocationCoordinate2DMake(latitude, longitude)
        ))
    }
}

extension ProductDetailViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.model.gallery.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "PhotoCollectionCell", for: indexPath) as?
            PhotoCollectionCell {
            cell.setupCell(url: viewModel.model.gallery[indexPath.item])
            return cell
        }
        
        return UICollectionViewCell()
    }
}

extension ProductDetailViewController: UICollectionViewDelegate {
    // TODO
}

extension ProductDetailViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(
        _ collectionView: UICollectionView,
        layout collectionViewLayout: UICollectionViewLayout,
        sizeForItemAt indexPath: IndexPath
    ) -> CGSize {
        CGSize(width: collectionView.frame.width, height: collectionView.frame.height)
    }
}

