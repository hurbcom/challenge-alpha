//
//  HomeViewContainerExtensions.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 23/05/22.
//

import UIKit

// MARK: - UICollectionViewDataSource
extension HomeContainerView: UICollectionViewDataSource {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        hotelsResult?.count ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! HomeCollectionViewCell
        let hotel = hotelsResult?[indexPath.row]
        let viewModel = HomeViewModel()
        viewModel.hotelResult = hotel
        cell.hotelResult = hotel
        return cell
    }
}

extension HomeContainerView: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        DispatchQueue.main.async { [weak self] in
            let viewController = DetailViewController()
            guard let hotelsResult = self?.hotelsResult else { return }
            viewController.detailContainerView.hotelsResult  = hotelsResult[indexPath.row]
            self?.parentViewController?.navigationController?.pushViewController(viewController, animated: true)
        }
    }
}

extension HomeContainerView: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING
    }
}
