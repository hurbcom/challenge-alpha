//
//  SearchView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI

struct SearchView: View {
    
    @ObservedObject var viewModel: SearchViewModel
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: true) {
            VStack(alignment: .leading) {
                // TODO: Add HUPicker
                
                HUSearchButton(searchText: $viewModel.searchText) {
                    // TODO: Handle search tap
                }
                Spacer()
                
                ForEach(0..<viewModel.searchResults.count, id: \.self) { index in
                    switch viewModel.searchResults[index].category {
                    case .hotel:
                        
                        self.searchHotelCard(viewModel.searchResults[index].toHotel())
                        
                    case .pacote:
                        
                        self.searchPackageCard(viewModel.searchResults[index].toPackage())
                        
                    case .atividade:
                        
                        EmptyView()
                    
                    default:
                        EmptyView()
                    }
                }
            }
            .padding(.horizontal)
        }
        .loading($viewModel.showLoading)
        .onAppear {
            viewModel.onViewAppear()
        }
    }
    
    @ViewBuilder
    func searchHotelCard(_ hotel: HotelResult) -> some View {
        HotelCardView(
            name: hotel.getName(),
            city: hotel.getCity(),
            country: hotel.getCountry(),
            stars: hotel.getStars(),
            amenities: hotel.getAmenitiesName(),
            amount: hotel.getFormattedAmount(),
            imagesURL: hotel.getImagesURL(),
            action: {
                viewModel.onHotelTap(hotel)
            }
        )
    }
    
    @ViewBuilder
    func searchPackageCard(_ package: PackageResult) -> some View {
        PackageCardView(
            city: package.getCity(),
            country: package.getCountry(),
            name: package.getName(),
            duration: package.getDuration(),
            maxPeople: package.getMaxPeople(),
            amount: package.getFormattedAmount(),
            originalAmount: package.getFormattedOriginalAmount(),
            tags: package.getTagsWithDiscount(),
            imagesURL: package.getImagesURL(),
            action: {
                viewModel.onPackageTap(package)
            }
        )
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView(viewModel: SearchViewModel(interactor: SearchInteractor(), router: SearchRouter()))
    }
}
