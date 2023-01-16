//
//  HUSearchButton.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI

struct HUSearchButton: View {
    
    enum Style {
        case plain
        case sideButton
    }
    
    @Binding var searchText: String
    var style: Style = .plain
    var action: (() -> Void)
    var sideButtonAction: (() -> Void)?
    
    var body: some View {
        HStack {
            self.searchLabel
            
            Spacer()
            
            if self.style == . sideButton {
                self.sideButton
            }
        }
        .frame(maxWidth: .infinity, minHeight: 48, maxHeight: 48)
        .background {
            RoundedRectangle(cornerRadius: 4.0)
                .strokeBorder(Color.secondary, lineWidth: 1)
                .foregroundColor(.clear)
        }
        .contentShape(Rectangle())
        .onTapGesture {
            self.action()
        }
    }
    
    // MARK: - Small Components
    @ViewBuilder
    private func magnifyingglassImage(color: Color) -> some View {
        Image(systemName: "magnifyingglass")
            .foregroundColor(color)
            .font(.system(size: 16, weight: .medium))
    }
    
    var searchLabel: some View {
        HStack {
            if self.style == .plain {
                self.magnifyingglassImage(color: .black)
            }
            
            Text(self.searchText)
                .font(.system(size: 16, weight: .regular))
                .foregroundColor(.black)
        }
        .padding(.horizontal)
    }
    
    var sideButton: some View {
        ZStack {
            UIConstants.COLOR.hurbBlue
                .frame(width: 48, height: 48)
                .cornerRadius(4.0, corners: .topRight)
                .cornerRadius(4.0, corners: .bottomRight)
            
            self.magnifyingglassImage(color: .white)
        }
        .contentShape(Rectangle())
        .onTapGesture {
            self.sideButtonAction?()
        }
    }
}

struct HUSearchButton_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            HUSearchButton(
                searchText: .constant("Pesquisar por hotel ou destino"),
                style: .plain,
                action: {}
            )
            HUSearchButton(
                searchText: .constant("Pesquisar por hotel ou destino"),
                style: .sideButton,
                action: {},
                sideButtonAction: {}
            )
        }.padding(.horizontal)
    }
}
