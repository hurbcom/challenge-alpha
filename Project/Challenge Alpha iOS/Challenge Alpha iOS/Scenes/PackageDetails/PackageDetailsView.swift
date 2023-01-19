//
//  PackageDetailsView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import SwiftUI
import MapKit

struct PackageDetailsView: View {
    
    @ObservedObject var viewModel: PackageDetailsViewModel
    @State var mapRegion = MKCoordinateRegion(
        center: CLLocationCoordinate2D(latitude: 0,longitude: 0),
        latitudinalMeters: 750,
        longitudinalMeters: 750
    ) // Start location
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: true) {
            VStack(alignment: .leading, spacing: 16) {
                // Images Carousel
                self.headerCarouselView
                
                VStack(alignment: .leading) {
                    // Package title
                    self.titleView
                    
                    // Tags
                    self.tagsScrollView
                    
                    // Date + Price
                    HStack {
                        self.dateInfoView
                        Spacer()
                        self.priceInfoView()
                    }
                    .padding(.top)
                    
                    // Amenities
                    self.amenitiesView
                        .padding(.top)
                    
                    // Location
                    self.mapView
                }
                .padding(.horizontal, 12)
            }
            .background(
                Color.white
                    .edgesIgnoringSafeArea(.all)
            )
        }
        .edgesIgnoringSafeArea(.top)
        .onAppear {
            // Update location with right coordinates
            self.mapRegion = MKCoordinateRegion(
                center: CLLocationCoordinate2D(latitude: viewModel.package.getLatitude(),
                                               longitude: viewModel.package.getLongitude()),
                latitudinalMeters: 750,
                longitudinalMeters: 750
            )
        }
    }
    
    // MARK: - View Components
    var headerCarouselView: some View {
        HUImageCarousel(
            imagesURL: viewModel.package.getImagesURL(),
            hasBlackOverlay: true,
            maxHeight: UIScreen.main.bounds.height / 3
        )
    }
    
    var tagsScrollView: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            ForEach(viewModel.package.getTags(), id: \.self) { tag in
                HUTagView(title: tag, textColor: .white, backgroundColor: UIConstants.COLOR.hurbBlue)
            }
        }
    }
    
    var titleView: some View {
        Text(viewModel.package.getName())
            .font(.system(size: 22, weight: .semibold))
    }
    
    var dateInfoView: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text("Viaje entre")
                .font(.system(size: 14))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            Text("\(viewModel.package.getStartDateMonthAndYear()) e \(viewModel.package.getEndDateMonthAndYear())")
                .font(.system(size: 14, weight: .medium))
                .foregroundColor(.black)
            
            Text("\(viewModel.package.getDuration()) diárias")
                .font(.system(size: 14))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
        }
    }
    
    func priceInfoView() -> some View {
        let amount = viewModel.package.getFormattedAmount()
        let originalAmount = viewModel.package.getFormattedOriginalAmount()
        
        return AnyView(
            VStack(alignment: .trailing, spacing: 2) {
                HStack(spacing: 4) {
                    Text("A partir de ")
                        .font(.system(size: 14))
                        .foregroundColor(UIConstants.COLOR.hurbGray)
                    +
                    Text("\(originalAmount != amount ? amount : "")")
                        .strikethrough()
                        .font(.system(size: 14))
                        .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                    
                    if !viewModel.package.getDiscountString().isEmpty {
                        HUTagView(
                            title: viewModel.package.getDiscountString(),
                            textColor: Color.white,
                            backgroundColor: UIConstants.COLOR.hurbOrange
                        )
                    }
                }
                
                Text(originalAmount)
                    .font(.system(size: 22, weight: .bold))
                    .foregroundColor(originalAmount != amount ? UIConstants.COLOR.hurbOrange : .black)
                
                Text("+ Taxas, até 12x no cartão")
                    .font(.system(size: 14))
                    .foregroundColor(UIConstants.COLOR.hurbGray)
                    .padding(.top, 2)
            }
        )
    }
    
    var amenitiesView: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("O que está incluso?")
                .font(.system(size: 18, weight: .semibold))
            
            LazyVGrid(columns: [.init(.flexible(), spacing: 16, alignment: .leading), .init(.flexible(), alignment: .leading)], spacing: 12) {
                ForEach(viewModel.package.getAmenitiesName(), id: \.self) { amenity in
                    HULabel(imageName: "checkmark.circle", title: amenity, fillColor: UIConstants.COLOR.hurbDarkGray)
                }
            }
            
            if #available(iOS 15, *) {
                Text(viewModel.package.description ?? "") {
                    $0.font = Font.system(size: 14, weight: .regular, design: .default)
                }.lineLimit(3)
                
                HUButton(title: "Ler mais", color: UIConstants.COLOR.hurbBlue, style: .outline, verticalPadding: 8, action: {})
            } else {
                HUButton(title: "Mostrar detalhes", color: UIConstants.COLOR.hurbBlue, style: .outline, verticalPadding: 8, action: {})
            }
        }
    }
    
    var mapView: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text("Local")
                .font(.system(size: 16))
                .foregroundColor(.black)
            
            Text("\(viewModel.package.getCity())")
                .font(.system(size: 22, weight: .bold))
                .foregroundColor(UIConstants.COLOR.hurbPink)
            
            Map(coordinateRegion: $mapRegion, annotationItems: [viewModel.place]) { place in
                MapMarker(coordinate: place.location,
                          tint: UIConstants.COLOR.hurbBlue)
            }
            .allowsHitTesting(false)
            .frame(maxWidth: .infinity, minHeight: 200, maxHeight: 200)
        }
        .padding(.vertical)
    }
}
