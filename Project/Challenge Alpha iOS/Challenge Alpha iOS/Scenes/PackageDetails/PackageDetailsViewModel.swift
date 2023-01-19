//
//  PackageDetailsViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import Foundation
import Combine
import MapKit

final class PackageDetailsViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var mapRegion: MKCoordinateRegion
    
    // MARK: - Map properties
    /// A place to me marked on the Map
    var place: IdentifiablePlace
    /// Identify a Geolocation place
    struct IdentifiablePlace: Identifiable {
        let id: UUID
        let location: CLLocationCoordinate2D
        init(id: UUID = UUID(), lat: Double, long: Double) {
            self.id = id
            self.location = CLLocationCoordinate2D(
                latitude: lat,
                longitude: long)
        }
    }
    
    // MARK: - Dependencies
    var package: PackageResult
    var router: PackageDetailsRouter
    
    // MARK: - Initializer
    init(package: PackageResult, router: PackageDetailsRouter) {
        self.package = package
        self.router = router
        self.mapRegion = MKCoordinateRegion(
            center: CLLocationCoordinate2D(latitude: package.getLatitude(),
                                           longitude: package.getLongitude()),
            latitudinalMeters: 750,
            longitudinalMeters: 750
        )
        self.place = IdentifiablePlace(lat: package.getLatitude(), long: package.getLongitude())
    }
}
