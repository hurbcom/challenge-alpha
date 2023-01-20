//
//  SuggestionView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI

struct SuggestionView: View {
    
    @ObservedObject var viewModel: SuggestionViewModel
    
    var body: some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG) {
            HStack {
                Spacer()
                Button(action: viewModel.onCloseTap) {
                    Image(systemName: "xmark")
                        .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
                        .foregroundColor(.black)
                }
            }.padding()
            
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
                TextField("", text: $viewModel.searchText, onCommit: {
                    viewModel.onTextfieldSubmit()
                })
                .placeholder(when: viewModel.searchText.isEmpty) {
                    Text("Quero ir para...")
                        .font(.system(size: UIConstants.FONT_SIZE.TITLE))
                        .foregroundColor(UIConstants.COLOR.hurbGray)
                }.padding(.horizontal)
                
                Rectangle()
                    .frame(maxWidth: .infinity, maxHeight: 1)
                    .foregroundColor(UIConstants.COLOR.hurbGray.opacity(0.5))
            }
            
            ScrollView(.vertical, showsIndicators: false) {
                self.lastSearchedQueriesView()
                    .padding(.horizontal)
                
                self.destinationSuggestionsView
                    .padding(.horizontal)
                
                Rectangle()
                    .frame(maxWidth: .infinity, maxHeight: 1)
                    .foregroundColor(UIConstants.COLOR.hurbGray.opacity(0.5))
                    .padding(.horizontal)
                    .opacity(viewModel.destinationSuggestions.isEmpty ? 0 : 1)
                
                self.otherSuggestionsView
                    .padding(.horizontal)
            }
        }
        .background(
            Color.white
                .edgesIgnoringSafeArea(.all)
        )
        .onChange(of: viewModel.searchText) { newValue in
            viewModel.onChangeOfSearchText()
        }
    }
    
    @ViewBuilder
    func lastSearchedQueriesView() -> some View {
        let queries = viewModel.getLastSearchedQueries()
        
        if queries.isEmpty {
            EmptyView()
        } else {
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.SMALL) {
                Text("Últimas buscas")
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                    .foregroundColor(.black)
                    .padding(.vertical, UIConstants.PADDING_VALUES.NORMAL)
                
                ForEach(queries, id: \.self) { query in
                    HStack(alignment: .top) {
                        Image(systemName: "ticket")
                            .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
                            .foregroundColor(UIConstants.COLOR.hurbBlue)
                        
                        Text(query)
                            .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                            .foregroundColor(.black)
                        
                        Spacer()
                    }
                    .contentShape(Rectangle())
                    .padding(.vertical, UIConstants.PADDING_VALUES.NORMAL)
                    .onTapGesture {
                        viewModel.onDestinationTap(name: query)
                    }
                    
                    Rectangle()
                        .frame(maxWidth: .infinity, maxHeight: 1)
                        .padding(.horizontal)
                }
            }
        }
    }
    
    @ViewBuilder
    var destinationSuggestionsView: some View {
        if viewModel.destinationSuggestions.isEmpty {
            EmptyView()
        } else {
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.LARGE) {
                Text("Destinos")
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                    .foregroundColor(.black)
                
                ForEach(0..<viewModel.destinationSuggestions.count, id: \.self) { index in
                    HStack(alignment: .top) {
                        Image(systemName: "mappin.circle")
                            .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
                            .foregroundColor(UIConstants.COLOR.hurbBlue)
                        
                        Text(viewModel.destinationSuggestions[index].text ?? "")
                            .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                            .foregroundColor(.black)
                        
                        Spacer()
                    }
                    .contentShape(Rectangle())
                    .padding(.vertical, UIConstants.PADDING_VALUES.NORMAL)
                    .onTapGesture {
                        let searchTerm = viewModel.destinationSuggestions[index].text ?? ""
                        viewModel.onDestinationTap(name: searchTerm)
                    }
                }
            }
        }
    }
    
    @ViewBuilder
    var otherSuggestionsView: some View {
        if viewModel.otherSuggestions.isEmpty {
            EmptyView()
        } else {
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.LARGE) {
                Text(viewModel.getOtherSuggestionsTitle())
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                    .foregroundColor(.black)
                
                ForEach(0..<viewModel.otherSuggestions.count, id: \.self) { index in
                    HStack(alignment: .top) {
                        Image(systemName: viewModel.getOtherSuggestionsImage())
                            .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
                            .foregroundColor(UIConstants.COLOR.hurbBlue)
                        
                        Text(viewModel.otherSuggestions[index].text ?? "")
                            .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                            .foregroundColor(.black)
                        
                        Spacer()
                    }
                    .contentShape(Rectangle())
                    .padding(.vertical, UIConstants.PADDING_VALUES.NORMAL)
                    .onTapGesture {
                        let searchTerm = viewModel.otherSuggestions[index].text ?? ""
                        viewModel.onDestinationTap(name: searchTerm)
                    }
                }
            }
        }
    }
}

extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {

        ZStack(alignment: alignment) {
            placeholder().opacity(shouldShow ? 1 : 0)
            self
        }
    }
}

struct SuggestionView_Previews: PreviewProvider {
    static var previews: some View {
        SuggestionView(viewModel: SuggestionViewModel(suggestionType: .package, interactor: SuggestionInteractor(), router: SuggestionRouter(), onSearchComplete: { _ in }))
    }
}