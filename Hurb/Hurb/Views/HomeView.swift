//
//  HotelListView.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import SwiftUI
import Introspect

struct HomeView: View {
    
    @ObservedObject var hotelListViewModel = HotelListViewModel()
    
    init() {
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor: UIColor.white]
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
    }
    
    var body: some View {
        
        ZStack {
            
            NavigationView {
                ZStack {
                    
                    Color.blueHurb
                        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
                        .ignoresSafeArea(.container, edges: .top)
                    
                    ZStack {
                        HomeListView(hotelListViewModel: self.hotelListViewModel)
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
        HomeView()
    }
}
