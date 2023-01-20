//
//  HotelQueryParamsView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import SwiftUI

struct HotelQueryParamsView: View {
    
    @Environment(\.presentationMode) var presentationMode
    @Binding var startDate: Date
    @Binding var endDate: Date
    @Binding var adults: Int
    
    var body: some View {
        NavigationView{
            VStack(alignment: .leading, spacing: UIConstants.PADDING_VALUES.LARGE) {
                Text("Datas e hóspedes")
                    .font(.system(size: UIConstants.FONT_SIZE.HEADER, weight: .semibold))
                    .foregroundColor(.black)
                
                DatePicker("Data de entrada", selection: $startDate, in: Date()..., displayedComponents: [.date])
                
                DatePicker("Data de entrada", selection: $endDate, in: Date()..., displayedComponents: [.date])
                
                HUPicker(title: "Hóspedes", rooms: $adults, minLimit: 1, maxLimit: 10)
                
                Spacer()
                
                HUButton(title: "Aplicar", color: UIConstants.COLOR.hurbBlue, style: .fill) {
                    self.presentationMode.wrappedValue.dismiss()
                }
            }
            .padding()
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Text("Cancelar")
                        .foregroundColor(UIConstants.COLOR.hurbBlue)
                        .onTapGesture {
                            self.presentationMode.wrappedValue.dismiss()
                        }
                }
            }
        }
    }
}
