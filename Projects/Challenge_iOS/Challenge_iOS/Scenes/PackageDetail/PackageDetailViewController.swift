//
//  PackageDetailViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 15/11/22.
//

import UIKit
import MapKit

final class PackageDetailViewController: UIViewController {
    // MARK: Properties
    private let viewModel: PackageDetailViewModel
    
    // MARK: Outlets
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var collectionViewGallery: UICollectionView!
    @IBOutlet weak var pageControl: UIPageControl!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelPeriod: UILabel!
    @IBOutlet weak var labelValue: UILabel!
    @IBOutlet weak var labelAddress: UILabel!
    @IBOutlet weak var viewMapkit: UIView!
    @IBOutlet weak var mapkitAddress: MKMapView!
    @IBOutlet weak var collectionViewAmenities: UICollectionView!
    @IBOutlet weak var buttonShowDetail: UIButton!
    @IBOutlet weak var buttonHelp: UIButton!
    @IBOutlet weak var viewHelp: UIView!
    @IBOutlet weak var viewAlertFor1Person: UIView!
    @IBOutlet weak var constraintHeightCollectionViewAmenities: NSLayoutConstraint!
    
    // MARK: Inicialization
    init(model: SearchResultModel) {
        viewModel = PackageDetailViewModel(model: model)
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
    }
    
    // MARK: Actions
    
    // MARK: BindEvents
    
    // MARK: Methods
    
    // MARK: Updates
    
    // MARK: Setup
    private func setupUI() {
        setupNavigateController()
        labelName.text = viewModel.getName()
        labelValue.text = viewModel.getValue()
        labelAddress.text = viewModel.getAddress()
        setupMapkit()
        setupButtons()
        setupCollectionViewGAllery()
        setupCollectionViewAmenities()
        scrollView.contentInsetAdjustmentBehavior = .never
        scrollView.delegate = self
        pageControl.numberOfPages = viewModel.getImagesURL().count
        viewHelp.layer.cornerRadius = 8
        viewAlertFor1Person.layer.cornerRadius = 3
    }
    
    private func setupMapkit() {
        if let coordinate = viewModel.getCoordinate() {
            let span = MKCoordinateSpan.init(latitudeDelta: 0.01, longitudeDelta: 0.01)
            let coordinate2D = CLLocationCoordinate2D(latitude: coordinate.lat, longitude: coordinate.lon)
            let point = MKPointAnnotation()
            point.coordinate = coordinate2D
            let region = MKCoordinateRegion(center: coordinate2D, span: span)
            mapkitAddress.addAnnotation(point)
            mapkitAddress.setRegion(region, animated: true)
        } else {
            viewMapkit.isHidden = true
        }
    }
    
    private func setupButtons() {
        buttonHelp.layer.cornerRadius = 5
        buttonHelp.layer.borderWidth = 1
        buttonHelp.layer.borderColor = UIColor.blue.cgColor
        
        buttonShowDetail.layer.cornerRadius = 5
        buttonShowDetail.layer.borderWidth = 1
        buttonShowDetail.layer.borderColor = UIColor.blue.cgColor
    }
    
    private func setupCollectionViewGAllery() {
        collectionViewGallery.register(
            UINib(nibName: ImageCollectionViewCell.identifier,
                  bundle: nil), forCellWithReuseIdentifier: ImageCollectionViewCell.identifier)
        
        let height = collectionViewGallery.bounds.height
        let cellSize = CGSize(width: UIScreen.main.bounds.width, height: height)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        layout.minimumLineSpacing = 0
        layout.minimumInteritemSpacing = 0
        self.collectionViewGallery.setCollectionViewLayout(layout, animated: false)
        
        collectionViewGallery.dataSource = self
        collectionViewGallery.delegate = self
    }
    
    private func setupCollectionViewAmenities() {
        collectionViewAmenities.register(
            UINib(nibName: PackageAmenitieCollectionViewCell.identifier,
                  bundle: nil),
            forCellWithReuseIdentifier: PackageAmenitieCollectionViewCell.identifier)
        
        let width = UIScreen.main.bounds.width / 2
        let cellSize = CGSize(width: width, height: 44)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        layout.minimumLineSpacing = 0
        layout.minimumInteritemSpacing = 0
        self.collectionViewAmenities.setCollectionViewLayout(layout, animated: false)
        
        collectionViewAmenities.dataSource = self
        collectionViewAmenities.reloadData()
        let height = CGFloat(44 + (viewModel.getAmenities().count % 2) * 44)
        constraintHeightCollectionViewAmenities.constant = height
    }
    
    private func setupNavigateController() {
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        navigationController?.navigationBar.shadowImage = UIImage()
        UINavigationBar.appearance().isTranslucent = true
        navigationController?.navigationBar.prefersLargeTitles = false
    }
}

// MARK: Extensions
extension PackageDetailViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        if collectionView === collectionViewGallery {
            return viewModel.getImagesURL().count
        } else {
            return viewModel.getAmenities().count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        if collectionView === collectionViewGallery {
            let imageUrl = viewModel.getImagesURL()[indexPath.item]
            
            if let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: ImageCollectionViewCell.identifier,
                for: indexPath) as? ImageCollectionViewCell {
                
                cell.setup(with: imageUrl)
                return cell
            }
        }
        else {
            let ameniti = viewModel.getAmenities()[indexPath.item]
            
            if let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: PackageAmenitieCollectionViewCell.identifier,
                for: indexPath) as? PackageAmenitieCollectionViewCell {
                
                cell.setup(with: ameniti)
                return cell
            }
        }
        return UICollectionViewCell()
    }
}

extension PackageDetailViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        pageControl.currentPage = indexPath.item
    }
}

extension PackageDetailViewController: UIScrollViewDelegate {
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offset = scrollView.contentOffset
        
        if offset.y > 300 {
            let shadowImage = UINavigationController().navigationBar.shadowImage
            navigationController?.navigationBar.setBackgroundImage(shadowImage, for: UIBarMetrics.default)
            navigationController?.navigationBar.shadowImage = shadowImage
            UINavigationBar.appearance().isTranslucent = false
        } else {
            navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
            navigationController?.navigationBar.shadowImage = UIImage()
            UINavigationBar.appearance().isTranslucent = true
        }
    }
}
