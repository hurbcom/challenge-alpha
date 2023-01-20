//
//  ErrorView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI

struct ErrorView: View {
    
    var body: some View {
        VStack {
            Image(systemName: "airplane.departure")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
            
            Text("Resultado não encontrado")
                .font(.system(size: UIConstants.FONT_SIZE.DEFAULT, weight: .semibold))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                .padding(.vertical, 24)
            
            Text("Que pena, não encontramos resultados para sua busca :(")
                .multilineTextAlignment(.center)
                .font(.system(size: UIConstants.FONT_SIZE.DEFAULT))
                .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                .padding(.horizontal, 32)
        }
        .frame(maxWidth: .infinity)
    }
}

struct ErrorView_Preview: PreviewProvider {
    
    static var previews: some View {
        ErrorView()
    }
}
