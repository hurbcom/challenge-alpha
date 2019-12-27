//
//  HotelDetailView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

struct HotelDetailView: View {
    
    let targetSize = CGSize(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.width/2)
    
    @ObservedObject var hotelDetailVM: HotelDetailViewModel
    
    init(hotel: Accommodation) {
        hotelDetailVM = HotelDetailViewModel(hotel: hotel)
    }
    
    var body: some View {
        VStack {
            ZStack(alignment: .bottom) {
                CustomImageView(url: self.hotelDetailVM.urlImage, size: targetSize)
                Rectangle().frame(height: 80)
                    .opacity(0.25)
                    .blur(radius: 10)
                HStack {
                    VStack(alignment: .leading, spacing: 8) {

                        Text(self.hotelDetailVM.name)
                            .font(.title).foregroundColor(Color.white)
                    }.padding(.leading)
                        .padding(.bottom)
                    Spacer()
                }
            }.frame(width: targetSize.width, height: targetSize.height)
            VStack(alignment: .leading) {
                    Text(self.hotelDetailVM.smallDescription)
                        .font(.body)
                        .foregroundColor(.primary)
                        .lineLimit(nil)
                        .lineSpacing(12).padding()
            }
            HStack {
                Text("Local").font(.title)
                    .foregroundColor(.secondary)
                    .multilineTextAlignment(.leading).padding()
                Spacer()
            }
            VStack {
                Text(self.hotelDetailVM.street)
                    .multilineTextAlignment(.center)
                Text(self.hotelDetailVM.city)
                Text(self.hotelDetailVM.state)
                
            }
            
            HStack {
                Text("Comodidades").font(.title)
                    .foregroundColor(.secondary)
                    .multilineTextAlignment(.leading).padding()
                Spacer()
            }
            ScrollView(.horizontal, content: {
                HStack(spacing: 10) {
                    ForEach(self.hotelDetailVM.amenities) { item in
                        AmenitiesCellView(name: item.name)
                    }
                }
            })
            
            Spacer()
        }.edgesIgnoringSafeArea(.top)
            .navigationBarHidden(true)
            .navigationBarBackButtonHidden(false)
    }
}

struct HotelDetailView_Previews: PreviewProvider {
    
    @State static var  hotelTest = DataContants.sharedInstance.hotelModelTest
    
    static var previews: some View {
        HotelDetailView(hotel: hotelTest)
    }
}
