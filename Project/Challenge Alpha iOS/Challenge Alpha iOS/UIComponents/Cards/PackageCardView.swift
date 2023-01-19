//
//  PackageCardView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import SwiftUI

struct PackageCardView: View {
    
    // MARK: - Properties
    var city: String
    var country: String
    var name: String
    var duration: Int
    var maxPeople: Int
    var amount: String
    var originalAmount: String
    var tags: [String]
    var imagesURL: [String]
    var action: (() -> Void)
    
    var body: some View {
        self.content
            .onTapGesture {
                action()
            }
    }
    
    var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            // Image Carousel with Tags
            self.imageHeader
            
            HStack {
                VStack(alignment: .leading, spacing: 12) {
                    // Pacakge Location + Name
                    self.packageGeneralInfo
                    
                    // Quantity description
                    self.packageQuantityInfo
                    
                    // Price
                    self.packagePriceInfo
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
        ZStack(alignment: .topLeading) {
            HUImageCarousel(imagesURL: imagesURL)
                .cornerRadius(8, corners: .topLeft)
                .cornerRadius(8, corners: .topRight)
            
            HStack(spacing: 8) {
                ForEach(tags, id: \.self) { tag in
                    HUTagView(title: tag, textColor: Color.white, backgroundColor: UIConstants.COLOR.hurbOrange)
                }
            }
            .padding()
        }
    }
    
    /// Package Location and Name
    var packageGeneralInfo: some View {
        VStack(alignment: .leading, spacing: 8) {
            // Location
            Text("\(city), \(country)")
                .font(.system(size: 14))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            // Package name
            Text("\(name)")
                .font(.system(size: 16, weight: .semibold))
                .foregroundColor(.black)
                .lineLimit(2)
        }
    }
    
    /// Quantity Descriptors
    var packageQuantityInfo: some View {
        HStack(spacing: 16) {
            HULabel(imageName: "sun.max", title: "\(duration) diárias", fillColor: UIConstants.COLOR.hurbDarkGray)
            HULabel(imageName: "person", title: "\(maxPeople) \(maxPeople > 1 ? "pessoas" : "pessoa")", fillColor: UIConstants.COLOR.hurbDarkGray)
        }
    }
    
    /// Price information (amount + general amount)
    var packagePriceInfo: some View {
        VStack(alignment: .leading, spacing: 2) {
            // Original amount
            Text("A partir de ")
                .font(.system(size: 14))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            +
            Text("\(originalAmount != amount ? amount : "")")
                .strikethrough()
                .font(.system(size: 14))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            // Amount
            Text(originalAmount)
                .font(.system(size: 18, weight: .bold))
                .foregroundColor(originalAmount != amount ? UIConstants.COLOR.hurbOrange : .black)
            +
            Text(" até 12x no cartão")
                .font(.system(size: 14))
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
