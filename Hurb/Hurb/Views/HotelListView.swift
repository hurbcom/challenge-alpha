//
//  HotelListView.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import SwiftUI
import Introspect

struct HotelListView: View {
    
    @ObservedObject var hotelListViewModel = HotelListViewModel()
    
    init() {
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor: UIColor.white]
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
    }
    
    var body: some View {
        
        ZStack {
            
            NavigationView {
                ZStack {
                    
                    Color.blue
                        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
                        .ignoresSafeArea(.container, edges: .top)
                    
                    VStack {
                        
                        HStack {
                            TextField("Buscar por cidade:", text: self.$hotelListViewModel.searchByCity)
                                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                        }
                        
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
                    }
                    .background(Color.white)
                }
                .navigationBarTitle("Hurb")
            }
            
        }
        
    }
}

struct HotelListView_Previews: PreviewProvider {
    static var previews: some View {
        HotelListView()
    }
}
