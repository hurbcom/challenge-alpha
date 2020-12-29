//
//  HotelListCell.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/29/20.
//

import SwiftUI
import KingfisherSwiftUI

struct HotelListCell: View {
    
    var hotel: Hotel
    
    var body: some View {
        VStack(alignment: .center) {
            VStack(alignment: .leading, spacing: 0) {
                
                KFImage(URL(string: hotel.image ?? ""))
                    .resizable()
                    .placeholder {
                        ZStack {
                            Color.blueHurb
                            
                            Image("Placeholder")
                                .scaledToFill()
                                .frame(
                                    minWidth: 0,
                                    maxWidth: .infinity,
                                    minHeight: 0,
                                    maxHeight: 200,
                                    alignment: .center
                                )
                                .clipped()
                        }
                    }
                    .scaledToFill()
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: 200,
                        alignment: .center
                    )
                    .clipped()
                
                VStack(alignment: .leading, spacing: 5) {
                    
                    Spacer().frame(width: 0, height: 10)
                    
                    Text(hotel.name)
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundColor(.darkGray)
                    
                    Spacer().frame(width: 0, height: 10)
                    
                    Text(hotel.address?.city ?? "Cidade")
                        .foregroundColor(.darkGray)
                    
                    Text(hotel.address?.state ?? "Estado")
                        .foregroundColor(.darkGray)
                    
                    Text(hotel.formattedAmenities.joined(separator: ", "))
                        .font(.footnote)
                        .foregroundColor(.darkGray)
                    
                    Spacer().frame(width: 0, height: 10)
                    
                    Text(hotel.price?.formattedAmount ?? "")
                        .font(.title)
                        .fontWeight(.bold)
                        .foregroundColor(.orange)
                }
                .padding(EdgeInsets(top: 0, leading: 10, bottom: 10, trailing: 10))
            }
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            .background(Color.white)
            .cornerRadius(10)
            .shadow(color: Color.gray.opacity(0.6), radius: 2, x: 0.5, y: 0.0)
            
            Spacer(minLength: 10)
        }
        .padding(10)
        .frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .leading
        )
        
    }
}

struct HotelListCell_Previews: PreviewProvider {
    static var previews: some View {
        
        let imageUrl = "https://thumbcdn-0.hotelurbano.net/ulwbHJbu6YyaypSQwTz9eN6MHIU=/trim:bottom-right:80/bottom/filters:quality(50)/https%3A//novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/527706/samoa-beach-resort-ipojuca-001_20200213175602.jpg"
        
        let hotel = Hotel(
            name: "Nome do Hotel",
            price: ResultPrice(amount: 200.2),
            address: nil,
            image: imageUrl,
            amenities: [ResultAmenity(name: "TV", category: ""), ResultAmenity(name: "Banheira", category: "")])

        HotelListCell(hotel: hotel)
            .background(Color.white)
    }
}
