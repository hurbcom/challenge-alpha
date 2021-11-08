//
//  ResultRenderView.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Theo Mendes on 04/11/21.
//

import SwiftUI
import Combine

enum ResultRenderViewType {
    case search
    case searchPackage
    case searchHotel
    case suggestion
}

struct ResultRenderView: View {
    private let type: ResultRenderViewType
    private var subscriptions = Set<AnyCancellable>()
    @ObservedObject var viewModel = ResultRenderViewModel()
    
    init(type: ResultRenderViewType) {
        self.type = type
    }
    
    var body: some View {
        Text(String(format:"%@", viewModel.resultText))
            .font(.headline)
            .lineLimit(nil)
            .padding()
            .onAppear {
                switch self.type {
                case .search: viewModel.performSearch()
                case .searchHotel: viewModel.performSearchHotel()
                case .searchPackage: viewModel.performSearchPackage()
                case .suggestion:
                    viewModel.performSuggestion()
                }
            }
            .navigationTitle("Result")
    }
}

struct ResultRenderView_Previews: PreviewProvider {
    static var previews: some View {
        ResultRenderView(type: .search)
    }
}
