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
                        .font(.system(size: 18, weight: .semibold))
                        .foregroundColor(.black)
                }
            }.padding()
            
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.NORMAL) {
                TextField("", text: $viewModel.searchText, onCommit: {
                    viewModel.onTextfieldSubmit()
                })
                .placeholder(when: viewModel.searchText.isEmpty) {
                    Text("Quero ir para...")
                        .font(.system(size: 18))
                        .foregroundColor(UIConstants.COLOR.hurbGray)
                }.padding(.horizontal)
                
                Rectangle()
                    .frame(maxWidth: .infinity, maxHeight: 1)
                    .foregroundColor(UIConstants.COLOR.hurbGray.opacity(0.5))
            }
            
            ScrollView(.vertical, showsIndicators: false) {
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
            
//            Spacer()
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
    var destinationSuggestionsView: some View {
        if viewModel.destinationSuggestions.isEmpty {
            EmptyView()
        } else {
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.LARGE) {
                Text("Destinos")
                    .font(.system(size: 22, weight: .semibold))
                    .foregroundColor(.black)
                
                ForEach(0..<viewModel.destinationSuggestions.count, id: \.self) { index in
                    HStack(alignment: .top) {
                        Image(systemName: "mappin.circle")
                            .font(.system(size: 18, weight: .semibold))
                            .foregroundColor(UIConstants.COLOR.hurbBlue)
                        
                        Text(viewModel.destinationSuggestions[index].text ?? "")
                            .font(.system(size: 16, weight: .semibold))
                            .foregroundColor(.black)
                        
                        Spacer()
                    }
                    .contentShape(Rectangle())
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
                    .font(.system(size: 22, weight: .semibold))
                    .foregroundColor(.black)
                
                ForEach(0..<viewModel.otherSuggestions.count, id: \.self) { index in
                    HStack(alignment: .top) {
                        Image(systemName: "bag.fill")
                            .font(.system(size: 18, weight: .semibold))
                            .foregroundColor(UIConstants.COLOR.hurbBlue)
                        
                        Text(viewModel.otherSuggestions[index].text ?? "")
                            .font(.system(size: 16, weight: .semibold))
                            .foregroundColor(.black)
                        
                        Spacer()
                    }
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
