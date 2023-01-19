//
//  HUButton.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI

struct HUButton: View {
    
    enum Style {
        case fill
        case outline
    }
    
    var title: String
    var color: Color
    var style: Style
    var verticalPadding: CGFloat = 16
    var action: () -> Void
    
    var content: some View {
        Button(action: { action() }) {
            Text(title)
        }
    }
    
    var body: some View {
        Group {
            switch style {
            case .fill:
                content
                    .buttonStyle(FillButtonStyle(color: self.color, verticalPadding: self.verticalPadding))
            case .outline:
                content
                    .buttonStyle(OutlineButtonStyle(color: self.color, verticalPadding: self.verticalPadding))
            }
        }
    }
}

// MARK: - Button Styles
struct FillButtonStyle: ButtonStyle {
    
    var color: Color
    var verticalPadding: CGFloat
    
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.system(size: 16, weight: .semibold))
            .lineLimit(1)
            .minimumScaleFactor(0.5)
            .foregroundColor(Color.white)
            .padding(.vertical, verticalPadding)
            .padding(.horizontal)
            .frame(maxWidth: .infinity)
            .background(color)
            .cornerRadius(4)
    }
}

struct OutlineButtonStyle: ButtonStyle {
    
    var color: Color
    var verticalPadding: CGFloat
    
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.system(size: 16, weight: .semibold))
            .lineLimit(1)
            .minimumScaleFactor(0.5)
            .foregroundColor(color)
            .padding(.vertical, verticalPadding)
            .padding(.horizontal)
            .frame(maxWidth: .infinity)
            .background(Color.clear)
            .cornerRadius(4)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(color, lineWidth: 1)
            )
    }
}

// MARK: Preview
struct HUButton_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 8) {
            HUButton(title: "Mostrar detalhes", color: UIConstants.COLOR.hurbBlue, style: .outline, action: {})
                .padding(.horizontal)
                .previewLayout(.sizeThatFits)
            
            
            HUButton(title: "Pesquisar", color: UIConstants.COLOR.hurbBlue, style: .fill, action: {})
                .padding(.horizontal)
                .previewLayout(.sizeThatFits)
        }
    }
}
