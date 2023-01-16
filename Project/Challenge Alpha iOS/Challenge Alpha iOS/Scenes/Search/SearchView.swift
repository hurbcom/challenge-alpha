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
                HUSearchButton(searchText: $viewModel.searchText) {
                    // TODO: Handle search tap
                }
                Spacer()
                
                ForEach(0..<viewModel.serachResults.count, id: \.self) { index in
                    Text(viewModel.serachResults[index].name ?? "")
                }
            }
            .padding(.horizontal)
        }
        .loading($viewModel.showLoading)
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView(viewModel: SearchViewModel(interactor: SearchInteractor(), router: SearchRouter()))
    }
}
