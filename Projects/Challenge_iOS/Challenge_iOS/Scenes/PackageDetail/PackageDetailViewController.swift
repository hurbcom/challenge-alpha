//
//  PackageDetailViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 15/11/22.
//

import UIKit
import MapKit

class PackageDetailViewController: UIViewController {
    // MARK: Properties
    private let model: SearchResultModel
    
    // MARK: Outlets
    @IBOutlet weak var collectionViewGallery: UICollectionView!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelPeriod: UILabel!
    @IBOutlet weak var labelValue: UILabel!
    @IBOutlet weak var labelAddress: UILabel!
    @IBOutlet weak var mapviewGeoloacation: MKMapView!
    
    // MARK: Inicialization
    init(model: SearchResultModel) {
        self.model = model
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
        labelName.text = model.name
        labelValue.text = model.getAmountShort()
        labelAddress.text = model.getAddressShortFormatted()
        
        if let lat = model.address?.geoLocation?.lat, let lon = model.address?.geoLocation?.lon {
            let span = MKCoordinateSpan.init(latitudeDelta: 0.01, longitudeDelta: 0.01)
            let coordinate = CLLocationCoordinate2D(latitude: lat, longitude: lon)
            
            let point = MKPointAnnotation()
            point.coordinate = coordinate
            mapviewGeoloacation.addAnnotation(point)
            
            let region = MKCoordinateRegion(center: coordinate, span: span)
            mapviewGeoloacation.setRegion(region, animated: true)
        }
    }
}
