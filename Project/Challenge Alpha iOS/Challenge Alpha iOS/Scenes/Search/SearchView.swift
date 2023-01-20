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
                self.searchHeader
                
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
        .error($viewModel.showError)
        .onChange(of: viewModel.selectedSegmentedControlIndex, perform: { _ in
            viewModel.onChangeOfSelectedSegment()
        })
        .onAppear {
            viewModel.onViewAppear()
        }
    }
    
    // MARK: - Visual Components
    var searchHeader: some View {
        VStack(alignment: .leading) {
            HStack {
                Text("Hotéis")
                    .frame(maxWidth: .infinity, minHeight: 42, maxHeight: 42)
                    .foregroundColor(viewModel.selectedSegmentedControlIndex == 0 ? UIConstants.COLOR.hurbBlue : UIConstants.COLOR.hurbDarkGray)
                    .background(
                        viewModel.selectedSegmentedControlIndex == 0 ? UIConstants.COLOR.hurbBlue.opacity(0.1) : Color.white
                    )
                    .contentShape(Rectangle())
                    .cornerRadius(4)
                    .onTapGesture {
                        viewModel.selectedSegmentedControlIndex = 0
                    }
                
                Text("Pacotes")
                    .frame(maxWidth: .infinity, minHeight: 42, maxHeight: 42)
                    .foregroundColor(viewModel.selectedSegmentedControlIndex == 1 ? UIConstants.COLOR.hurbBlue : UIConstants.COLOR.hurbDarkGray)
                    .background(
                        viewModel.selectedSegmentedControlIndex == 1 ? UIConstants.COLOR.hurbBlue.opacity(0.1) : Color.white
                    )
                    .contentShape(Rectangle())
                    .cornerRadius(4)
                    .onTapGesture {
                        viewModel.selectedSegmentedControlIndex = 1
                    }
            }
            
            HUSearchButton(searchText: $viewModel.searchText, placeholderText: viewModel.getPlaceholderText(), style: viewModel.selectedSegmentedControlIndex == 0 ? .plain : .sideButton) {
                viewModel.onSearchTap()
            } sideButtonAction: {
                viewModel.onSearchButtonTap()
            }
            
            if viewModel.selectedSegmentedControlIndex == 0 {
                HUButton(title: "Pesquisar", color: UIConstants.COLOR.hurbBlue, style: .fill) {
                    viewModel.onSearchButtonTap()
                }
            }
        }
        .padding(.bottom)
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
