//
//  HomeListView.swift
//  Hurb
//
//  Created by Arthur Givigir on 1/2/21.
//

import SwiftUI

struct HomeListView: View {
    
    @ObservedObject var hotelListViewModel: HotelListViewModel
    
    var body: some View {
        ZStack(alignment: .top) {
            
            SearchBarView(hotelListViewModel: self.hotelListViewModel)
                .zIndex(1.0)
            
            VStack {
                Group {
                    Spacer()
                        .frame(height: 50)
                    
                    if let groupedHotels = self.hotelListViewModel.groupedHotels,
                       !groupedHotels.isEmpty {
                        
                        List {
                            ForEach(self.hotelListViewModel.groupedHotels ?? [], id: \.section) { groupedHotel in
                                
                                Section(header: HeaderSectionView(stars: groupedHotel.section)) {
                                    ForEach(groupedHotel.hotels ?? [], id: \.id) { hotel in
                                        HotelListCell(hotel: hotel)
                                    }
                                    .listRowInsets(EdgeInsets())
                                    .background(Color.white)
                                }
                            }
                        }
                        .introspectTableView { tableView in
                            tableView.backgroundColor = .clear
                        }
                    } else {
                        EmptyDataView()
                    }
                }
            }
        }
        
    }
}

struct HomeListView_Previews: PreviewProvider {
    static var previews: some View {
        HomeListView(hotelListViewModel: HotelListViewModel())
    }
}
