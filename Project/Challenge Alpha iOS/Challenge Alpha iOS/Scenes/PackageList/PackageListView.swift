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
                    PackageCardView(
                        city: viewModel.packages[index].address?.city ?? "",
                        country: viewModel.packages[index].address?.country ?? "",
                        name: viewModel.packages[index].name ?? "",
                        duration: viewModel.packages[index].quantityDescriptors?.duration ?? 0,
                        maxPeople: viewModel.packages[index].quantityDescriptors?.maxPeople ?? 0,
                        amount: viewModel.packages[index].price?.amount ?? 0.0,
                        originalAmount: viewModel.packages[index].price?.originalAmount ?? 0.0,
                        tags: viewModel.getTagsForPackage(at: index),
                        imagesURL: viewModel.getImagesURLForPackage(at: index),
                        action: {}
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
}
