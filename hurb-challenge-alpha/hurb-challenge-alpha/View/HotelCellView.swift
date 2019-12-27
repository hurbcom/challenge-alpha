//
//  HotelCellView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

/// class responsible for display information hotel or package in list
struct HotelCellView: View {
    
    @ObservedObject var cellVM: HotelCellViewModel
    
    init(hotel: Accommodation) {
        cellVM = HotelCellViewModel(hotel: hotel)
    }
    
    var body: some View {
        
        ZStack {
            RoundedRectangle(cornerRadius: 10, style: .continuous)
                .fill(Color.white).shadow(color: .gray, radius: 1, x: 0, y: 0).padding(2)
            
            VStack(alignment: .leading, spacing: 16.0) {
                VStack {
                    CustomImageView(url: self.cellVM.urlImage) .overlay(
                        DiscountView(text: self.cellVM.discount),
                        alignment: .topTrailing)
                }.cornerRadius(10).padding(EdgeInsets(top: 0, leading: 1, bottom: 0, trailing: 1))
                
                VStack(alignment: .leading, spacing: 5.0) {
                    Text(self.cellVM.name)
                        .font(.headline)
                        .fontWeight(.black)
                        .foregroundColor(.primary)
                        .lineLimit(2)
                    Text(self.cellVM.city)
                        .font(.headline)
                        .lineLimit(1)
                        .foregroundColor(.secondary)
                        .frame(height: 30, alignment: .top)
                    CancellationView(isCancellation: self.cellVM.huFreeCancellation)
                    PriceView(price: self.cellVM.price)
                }.frame(height: 130)
                    .padding(10)
                
            }
        }
    }
    
    /// View responsible for displaying prices
    struct PriceView: View {
        var price: String
        var body: some View {
            VStack {
                if !self.price.isEmpty {
                    
                    VStack(alignment: .leading, spacing: 10) {
                        Text(price)
                            .foregroundColor(Color.init(hex: "#F2732C")) .font(.headline).fontWeight(.black)
                            .foregroundColor(.primary)
                            .frame(maxWidth: .infinity, alignment: .trailing)
                        
                    }
                    
                }
                
            }
        }
    }
    /// View responsible for displaying cancelation information
     struct CancellationView: View {
            var isCancellation: Bool
            var body: some View {
                VStack {
                    if self.isCancellation {
                     Text("Cancelamento Grátis")
                                          .font(.body)
                                          .foregroundColor(.green)
                                          .lineLimit(1)
                        
                    }
                }
                
            }
        }
    }

    /// View responsible for displaying discount
    struct DiscountView: View {
        var text: String
        var body: some View {
            VStack {
                if !self.text.isEmpty {
                    VStack {
                        Text(text)
                            .foregroundColor(.white)
                            .font(.headline)
                    }.padding(8)
                        .background(Color.init(hex: "#F2732C"))
                    
                }
            }
            
        }
    }
struct HotelCellView_Previews: PreviewProvider {
    
    @State static var  hotelTest = DataContants.sharedInstance.hotelModelTest
    
    static var previews: some View {
        HotelCellView(hotel: hotelTest)
    }
}
