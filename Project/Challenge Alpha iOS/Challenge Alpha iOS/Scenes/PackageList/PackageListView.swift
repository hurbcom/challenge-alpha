//
//  PackageListView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI

struct PackageListView: View {
    
    @ObservedObject var viewModel: PackageListViewModel
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 12) {
                ForEach(0..<viewModel.packages.count, id: \.self) { index in
                    PackageView(
                        city: viewModel.packages[index].address?.city ?? "",
                        country: viewModel.packages[index].address?.country ?? "",
                        name: viewModel.packages[index].name ?? "",
                        duration: viewModel.packages[index].quantityDescriptors?.duration ?? 0,
                        maxPeople: viewModel.packages[index].quantityDescriptors?.maxPeople ?? 0,
                        amount: viewModel.packages[index].price?.amount ?? 0.0,
                        originalAmount: viewModel.packages[index].price?.originalAmount ?? 0.0,
                        tags: viewModel.getTagsForPackage(at: index),
                        imagesURL: viewModel.getImagesURLForPackage(at: index),
                        action: {print("Cliquei")}
                    )
                }
            }
            .padding()
        }
        .loading($viewModel.showLoading)
        .onAppear {
            self.viewModel.getPackages()
        }
    }
    
    @ViewBuilder
    func PackageView(
        city: String,
        country: String,
        name: String,
        duration: Int,
        maxPeople: Int,
        amount: Double,
        originalAmount: Double,
        tags: [String],
        imagesURL: [String],
        action: @escaping (() -> Void)
    ) -> some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack(alignment: .topLeading) {
                ImageCarousel(imagesURL: imagesURL)
                    .cornerRadius(8, corners: .topLeft)
                    .cornerRadius(8, corners: .topRight)
                
                HStack(spacing: 8) {
                    ForEach(tags, id: \.self) { tag in
                        HUTagView(title: tag, textColor: Color.white, backgroundColor: UIConstants.COLOR.hurbOrange)
                    }
                }
                .padding()
            }
            
            HStack {
                VStack(alignment: .leading, spacing: 12) {
                    VStack(alignment: .leading, spacing: 8) {
                        // Location
                        Text("\(city), \(country)")
                            .font(.system(size: 14))
                            .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                        
                        // Package name
                        Text("\(name)")
                            .font(.system(size: 16, weight: .semibold))
                            .foregroundColor(.black)
                            .lineLimit(2)
                    }
                    
                    // Quantity description
                    HStack(spacing: 16) {
                        HULabel(imageName: "sun.max", title: "\(duration) diárias", fillColor: UIConstants.COLOR.hurbDarkGray)
                        HULabel(imageName: "person", title: "\(maxPeople) \(maxPeople > 1 ? "pessoas" : "pessoa")", fillColor: UIConstants.COLOR.hurbDarkGray)
                    }
                    
                    VStack(alignment: .leading, spacing: 2) {
                        // Original amount
                        Text("A partir de ")
                            .font(.system(size: 14))
                            .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                        +
                        Text("\(originalAmount != amount ? "R$ \(String(format: "%.0f", amount / 100))" : "")")
                            .strikethrough()
                            .font(.system(size: 14))
                            .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                        
                        // Amount
                        Text("R$ \(String(format: "%.0f", originalAmount / 100))")
                            .font(.system(size: 18, weight: .bold))
                            .foregroundColor(originalAmount != amount ? UIConstants.COLOR.hurbOrange : .black)
                        +
                        Text(" até 12x no cartão")
                            .font(.system(size: 14))
                            .foregroundColor(UIConstants.COLOR.hurbDarkGray)
                    }
                }
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(
                Color.white
                    .cornerRadius(8, corners: .bottomLeft)
                    .cornerRadius(8, corners: .bottomRight)
                    .shadow(color: UIConstants.COLOR.hurbGray.opacity(0.4), radius: 5.0)
            )
        }
        .onTapGesture {
            action()
        }
    }
}
