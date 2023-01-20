//
//  HotelCardView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI

struct HotelCardView: View {
    
    // MARK: - Properties
    var name: String
    var city: String
    var country: String
    var stars: Int
    var amenities: [String]
    var amount: String
    var imagesURL: [String]
    var action: (() -> Void)
    
    var body: some View {
        self.content
            .onTapGesture {
                action()
            }
    }
    
    var content: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NONE) {
            // Image Carousel with Tags
            self.imageHeader
            
            HStack {
                VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
                    // Pacakge Location + Name
                    self.hotelGeneralInfo
                    
                    // Amenities
                    self.hotelAmenitiesInfo
                    
                    // Price
                    self.hotelPriceInfo
                }
                
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(
                self.cardBackground
            )
        }
    }
    
    // MARK: - View Components
    /// Image Carousel with tags on top
    var imageHeader: some View {
        HUImageCarousel(imagesURL: imagesURL)
            .cornerRadius(8, corners: .topLeft)
            .cornerRadius(8, corners: .topRight)
    }
    
    /// Hotel Location and Name
    var hotelGeneralInfo: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_SMALL) {
            if stars > 0 {
                HStack(spacing: UIConstants.PADDING_VALUES.SMALL) {
                    Image(systemName: "star.fill")
                        .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                        .foregroundColor(Color.yellow)
                    
                    Text("Hotel \(stars) estrelas")
                        .font(.system(size: UIConstants.FONT_SIZE.SUPER_SMALL, weight: .semibold))
                        .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                    
                    Spacer()
                }
            }
            
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.SUPER_SMALL) {
                // Hotel name
                Text("\(name)")
                    .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                    .foregroundColor(.black)
                    .lineLimit(2)
                
                // Location
                Text("\(city), \(country)")
                    .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                    .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            }
        }
    }
    
    /// Hotel amenities
    var hotelAmenitiesInfo: some View {
        AmenitiesGrid(amenities: self.amenities.sorted(by: { $0.count < $1.count }), limit: 4)
    }
    
    /// Price information (amount + general amount)
    var hotelPriceInfo: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.SUPER_SMALL) {
            // Label
            Text("A partir de ")
                .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            // Amount
            Text(amount)
                .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .bold))
                .foregroundColor(.black)
            
            Text("+ Taxas | até 12x no cartão")
                .font(.system(size: UIConstants.FONT_SIZE.SMALL))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
        }
    }
    
    /// White card background with shadow
    var cardBackground: some View {
        Color.white
            .cornerRadius(8, corners: .bottomLeft)
            .cornerRadius(8, corners: .bottomRight)
            .shadow(color: UIConstants.COLOR.hurbGray.opacity(0.4), radius: 5.0)
    }
}
