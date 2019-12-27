//
//  MapView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//
import MapKit

import SwiftUI

struct MapView: UIViewRepresentable {
    let geolocation: CLLocationCoordinate2D
    var name = String()
    init(latitude: Double, longitude: Double, name: String) {
        self.geolocation = CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
        self.name = name
    }
    func makeUIView(context: UIViewRepresentableContext<MapView>) -> MKMapView {
        MKMapView(frame: .zero)
    }
    
    func updateUIView(_ uiView: MKMapView, context: UIViewRepresentableContext<MapView>) {
        let span = MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01)
        let region = MKCoordinateRegion(center: self.geolocation, span: span)
        uiView.setRegion(region, animated: true)
        
        uiView.isZoomEnabled = false;
        uiView.isScrollEnabled = false;
        uiView.isUserInteractionEnabled = false;
        
        let annotation = MKPointAnnotation()
        annotation.coordinate = self.geolocation
        annotation.title = self.name
        uiView.addAnnotation(annotation)
    }
}
