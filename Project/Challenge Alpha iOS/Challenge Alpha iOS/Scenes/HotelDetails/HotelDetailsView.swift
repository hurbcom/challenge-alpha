//
//  HotelDetailsView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI
import MapKit

struct HotelDetailsView: View {
    
    @ObservedObject var viewModel: HotelDetailsViewModel
    @State var mapRegion = MKCoordinateRegion(
        center: CLLocationCoordinate2D(latitude: 0,longitude: 0),
        latitudinalMeters: 5000,
        longitudinalMeters: 5000
    ) // Start location
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: true) {
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG) {
                // Images Carousel
                self.headerCarouselView
                
                VStack(alignment: .leading) {
                    // Package title, location, description
                    self.hotelGeneralInfoView
                    
                    // Price
                    self.priceInfoView()
                    
                    // Location
                    self.mapView
                    
                    // Amenities
                    self.amenitiesView
                        .padding(.top)
                }
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
                center: CLLocationCoordinate2D(latitude: viewModel.hotel.getLatitude(),
                                               longitude: viewModel.hotel.getLongitude()),
                latitudinalMeters: 5000,
                longitudinalMeters: 5000
            )
        }
    }
    
    // MARK: - View Components
    var headerCarouselView: some View {
        HUImageCarousel(
            imagesURL: viewModel.hotel.getImagesURL(),
            hasBlackOverlay: true,
            maxHeight: UIScreen.main.bounds.height / 3
        )
    }
    
    var hotelGeneralInfoView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
            Text("\(viewModel.hotel.getCity())")
                .font(.system(size: UIConstants.FONT_SIZE.DEFAULT))
                .foregroundColor(UIConstants.COLOR.hurbGray)
            
            Text(viewModel.hotel.getName())
                .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                .foregroundColor(.black)
            
            if viewModel.hotel.getStars() > 0 {
                HStack(spacing: UIConstants.PADDING_VALUES.SMALL) {
                    Image(systemName: "star.fill")
                        .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                        .foregroundColor(Color.yellow)
                    
                    Text("Hotel \(viewModel.hotel.getStars()) estrelas")
                        .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                        .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                    
                    Spacer()
                }
            }
            
            if #available(iOS 15, *) {
                Text(viewModel.hotel.getDescription().addFontToHTMLString().htmlToAttributedString())
                    .lineLimit(2)
                
                Text("Ler mais")
                    .foregroundColor(Color.blue)
                    .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                    .contentShape(Rectangle())
                    .onTapGesture {
                        viewModel.onReadMoreTap()
                    }
            }
        }
        .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
    }
    
    func priceInfoView() -> some View {
        return AnyView(
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.SUPER_SMALL) {
                Text("A partir de ")
                    .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                    .foregroundColor(UIConstants.COLOR.hurbGray)
                
                Text("\(viewModel.hotel.getFormattedAmount())")
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .bold))
                    .foregroundColor(.black)
                
                Text("+ Taxas | até 12x no cartão")
                    .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                    .foregroundColor(UIConstants.COLOR.hurbGray)
                    .padding(.top, UIConstants.PADDING_VALUES.SUPER_SMALL)
            }.padding([.top, .horizontal], UIConstants.PADDING_VALUES.NORMAL)
        )
    }
    
    var amenitiesView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG) {
            Text("Comodidades")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                .foregroundColor(.black)
            
            ForEach(viewModel.hotel.getAmenitiesName(), id: \.self) { amenity in
                HULabel(imageName: "checkmark.circle", title: amenity, fillColor: UIConstants.COLOR.hurbDarkGray)
            }
        }
        .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
    }
    
    var mapView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
            Text("Local")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                .foregroundColor(.black)
                .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
            
            Map(coordinateRegion: $mapRegion)/*, annotationItems: [viewModel.place]) { place in
                MapMarker(coordinate: place.location,
                          tint: UIConstants.COLOR.hurbBlue)
            }*/
            .allowsHitTesting(false)
            .frame(maxWidth: .infinity, minHeight: 150, maxHeight: 150)
            
            Text("\(viewModel.hotel.getCity()), \(viewModel.hotel.getState())")
                .font(.system(size: UIConstants.FONT_SIZE.DEFAULT))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
            
            Rectangle()
                .foregroundColor(UIConstants.COLOR.hurbLightGray)
                .frame(maxWidth: .infinity, maxHeight: 1)
                .padding(.top, UIConstants.PADDING_VALUES.SMALL)
        }
        .padding(.top)
    }
}
