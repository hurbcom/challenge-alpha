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
        ZStack(alignment: .bottom) {
            
            ScrollView {
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
                        MapView(latitude: self.hotelDetailVM.latitude,
                                longitude: self.hotelDetailVM.longitude,
                                name: self.hotelDetailVM.name).frame(width: targetSize.width, height: targetSize.height)
                    }
                    
                    VStack(alignment: .leading) {
                        Text(self.hotelDetailVM.adress)
                            .multilineTextAlignment(.leading).padding()
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
                    }).padding(.bottom, 100)
                    
                }
            }
            
            LoadBottomBar(viewModel: self.hotelDetailVM)
        }.edgesIgnoringSafeArea(.all)
            .navigationBarHidden(true)
            .navigationBarBackButtonHidden(false)
    }
}

struct LoadBottomBar: View {
    var viewModel: HotelDetailViewModel
    
    var body: some View {
        RoundedRectangle(cornerRadius: 8)
                    .fill(Color.white)
                    .frame(height: 90)
                    .shadow(color: Color.init(hex: "#143A7B"), radius: 10)
                    .overlay(
                        HStack {
                            HStack {
                                Text("R$").font(.headline).foregroundColor(Color.init(hex: "#F2732C"))
                                Text(self.viewModel.price).font(.title).foregroundColor(Color.init(hex: "#F2732C"))
                                Spacer()
                            }.padding()
                            Spacer()
                            Button(action: { self.viewModel.openURL()})
                            {
                                Text("Ver no site")
                                    .padding()
                                    .foregroundColor(Color.white)
                            }.background(Color.init(hex: "#143A7B")).padding()
                        }
                )
    }
}

/// View responsible for displaying mapView
struct LoadMapView: View {
    var latitude: Double
    var longitude: Double
    var name: String
    var body: some View {
        VStack {
            if (latitude != 0.0) && (longitude != 0.0) {
                MapView(latitude: latitude, longitude: longitude, name: name)
            }
        }
        
    }
}

struct HotelDetailView_Previews: PreviewProvider {
    
    @State static var  hotelTest = DataContants.sharedInstance.hotelModelTest
    
    static var previews: some View {
        HotelDetailView(hotel: hotelTest)
    }
}
