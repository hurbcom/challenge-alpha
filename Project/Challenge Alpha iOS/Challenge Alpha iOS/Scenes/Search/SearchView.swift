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
                self.searchButton
                Spacer()
                
                ForEach(0..<viewModel.serachResults.count, id: \.self) { index in
                    Text(viewModel.serachResults[index].name ?? "")
                }
            }
            .padding(.horizontal)
        }
        .onAppear {
            viewModel.onViewAppear()
        }
    }
    
    var searchButton: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .resizable()
                .foregroundColor(.black)
                .frame(width: 16, height: 16)
            
            Text(viewModel.searchText)
                .font(.system(size: 16, weight: .regular))
                .foregroundColor(.black)
            
            Spacer()
        }
        .padding(.horizontal) // internal padding
        .padding(.vertical, 14) // internal padding
        .frame(maxWidth: .infinity)
        .background {
            RoundedRectangle(cornerRadius: 4.0)
                .strokeBorder(Color.secondary, lineWidth: 1)
                .foregroundColor(.clear)
        }
        .contentShape(Rectangle())
        .onTapGesture {
            viewModel.onSearchTap()
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView(viewModel: SearchViewModel(interactor: SearchInteractor(), router: SearchRouter()))
    }
}
