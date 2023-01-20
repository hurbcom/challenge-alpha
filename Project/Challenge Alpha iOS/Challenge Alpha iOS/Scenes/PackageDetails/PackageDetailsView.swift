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
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG) {
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
                .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
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
            .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
    }
    
    var dateInfoView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.SMALL) {
            Text("Viaje entre")
                .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            Text("\(viewModel.package.getStartDateMonthAndYear()) e \(viewModel.package.getEndDateMonthAndYear())")
                .font(.system(size: UIConstants.FONT_SIZE.SMALL, weight: .medium))
                .foregroundColor(.black)
            
            Text("\(viewModel.package.getDuration()) diárias")
                .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
        }
    }
    
    func priceInfoView() -> some View {
        let amount = viewModel.package.getFormattedAmount()
        let originalAmount = viewModel.package.getFormattedOriginalAmount()
        
        return AnyView(
            VStack(alignment: .trailing, spacing: UIConstants.PADDING_VALUES.SUPER_SMALL) {
                HStack(spacing: UIConstants.PADDING_VALUES.SMALL) {
                    Text("A partir de ")
                        .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                        .foregroundColor(UIConstants.COLOR.hurbGray)
                    +
                    Text("\(originalAmount != amount ? amount : "")")
                        .strikethrough()
                        .font(.system(size: UIConstants.FONT_SIZE.SMALL))
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
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .bold))
                    .foregroundColor(originalAmount != amount ? UIConstants.COLOR.hurbOrange : .black)
                
                Text("+ Taxas, até 12x no cartão")
                    .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                    .foregroundColor(UIConstants.COLOR.hurbGray)
                    .padding(.top, UIConstants.PADDING_VALUES.SUPER_SMALL)
            }
        )
    }
    
    var amenitiesView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG) {
            Text("O que está incluso?")
                .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
            
            AmenitiesGrid(amenities: viewModel.package.getAmenitiesName())
            
            if #available(iOS 15, *) {
                Text((viewModel.package.description ?? "").addFontToHTMLString().htmlToAttributedString())
                    .lineLimit(3)
                
                HUButton(title: "Ler mais", color: UIConstants.COLOR.hurbBlue, style: .outline, verticalPadding: 8, action: {
                    viewModel.onDescriptionButtonTap()
                })
            } else {
                HUButton(title: "Mostrar detalhes", color: UIConstants.COLOR.hurbBlue, style: .outline, verticalPadding: 8, action: {})
            }
        }
    }
    
    var mapView: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
            Text("Local")
                .font(.system(size: UIConstants.FONT_SIZE.DEFAULT))
                .foregroundColor(.black)
            
            Text("\(viewModel.package.getCity())")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .bold))
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

@available(iOS 15, *)
struct DescriptionView: View {
    
    var description: String
    var amenities: [String]
    var closeAction: (() -> Void)
    
    var body: some View {
        VStack(spacing: UIConstants.PADDING_VALUES.NONE) {
            AmenitiesGrid(amenities: amenities)
                .padding()
                .background(
                    Color.white
                )
            
            ScrollView(.vertical, showsIndicators: false) {
                Text(description.addFontToHTMLString().htmlToAttributedString())
            }.padding()
            
            HUButton(title: "Fechar", color: UIConstants.COLOR.hurbBlue, style: .fill, verticalPadding: 12) {
                self.closeAction()
            }
            .padding()
            .background(
                Color.white
            )
        }
        .frame(width: UIScreen.main.bounds.width - 32, height: UIScreen.main.bounds.height * 0.8)
        .background(
            Color.white
        )
        .cornerRadius(4)
    }
}
