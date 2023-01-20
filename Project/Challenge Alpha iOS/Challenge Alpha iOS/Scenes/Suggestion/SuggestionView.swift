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
            suggestionTextView(
                title: "Últimas buscas",
                imageName: "ticket",
                suggestionsStrings: queries,
                showDivider: true
            )
        }
    }
    
    @ViewBuilder
    var destinationSuggestionsView: some View {
        if viewModel.destinationSuggestions.isEmpty {
            EmptyView()
        } else {
            suggestionTextView(
                title: "Destinos",
                imageName: "mappin.circle",
                suggestionsStrings: viewModel.destinationSuggestions.map({ $0.text ?? "" })
            )
        }
    }
    
    @ViewBuilder
    var otherSuggestionsView: some View {
        if viewModel.otherSuggestions.isEmpty {
            EmptyView()
        } else {
            suggestionTextView(
                title: viewModel.getOtherSuggestionsTitle(),
                imageName: viewModel.getOtherSuggestionsImage(),
                suggestionsStrings: viewModel.otherSuggestions.map({ $0.text ?? "" })
            )
        }
    }
    
    @ViewBuilder
    func suggestionTextView(title: String, imageName: String, suggestionsStrings: [String], showDivider: Bool = false) -> some View {
        VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.LARGE) {
            Text(title)
                .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                .foregroundColor(.black)
            
            ForEach(suggestionsStrings, id: \.self) { suggestion in
                HStack(alignment: .top) {
                    Image(systemName: imageName)
                        .font(.system(size: UIConstants.FONT_SIZE.TITLE, weight: .semibold))
                        .foregroundColor(UIConstants.COLOR.hurbBlue)
                    
                    Text(suggestion)
                        .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                        .foregroundColor(.black)
                    
                    Spacer()
                }
                .contentShape(Rectangle())
                .padding(.vertical, UIConstants.PADDING_VALUES.NORMAL)
                .onTapGesture {
                    viewModel.onDestinationTap(name: suggestion)
                }
            }
            
            if showDivider {
                Rectangle()
                    .frame(maxWidth: .infinity, maxHeight: 1)
                    .foregroundColor(UIConstants.COLOR.hurbGray.opacity(0.5))
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
