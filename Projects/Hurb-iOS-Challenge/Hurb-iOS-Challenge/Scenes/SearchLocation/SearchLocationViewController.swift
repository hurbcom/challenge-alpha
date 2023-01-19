//
//  SearchLocationViewController.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 17/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit
import SkeletonView

protocol SearchLocationDisplayLogic: AnyObject {
    
    func displayLocations(viewModel: SearchLocation.Setup.ViewModel)
    func displaySkeleton()
    func displayErrorAlert()
}

protocol SuggestedSearch: AnyObject {

    // A location was selected; inform our delegate that a location was selected to view.
    func didSelectLocation(location: String)
}

private enum Section {
    case main
}

private class CollectionViewSkeletonDiffableDataSource<Section: Hashable, Item: Hashable>: UICollectionViewDiffableDataSource<Section, Item>, SkeletonCollectionViewDataSource {

    func collectionSkeletonView(_ skeletonView: UICollectionView, cellIdentifierForItemAt indexPath: IndexPath) -> ReusableCellIdentifier {
        return SearchLocationCollectionViewCell.cellIdentifier
    }

    func collectionSkeletonView(_ skeletonView: UICollectionView, prepareCellForSkeleton cell: UICollectionViewCell, at indexPath: IndexPath) {

    }

    func collectionSkeletonView(_ skeletonView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 20
    }
}

class SearchLocationViewController: UIViewController {
    
    var interactor: SearchLocationBusinessLogic?
    var router: (NSObjectProtocol & SearchLocationRoutingLogic & SearchLocationDataPassing)?
    
    private lazy var dataSource: UICollectionViewDiffableDataSource<Section, String>! = nil
    weak var delegate: SuggestedSearch?
    
    @IBOutlet private weak var collectionView: UICollectionView!

    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.configureHierarchy()
        self.configureDataSource()
    }
    
    // MARK: Routing
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender)
        
    }
    
    // MARK: Configure Hierarchy
    
    private func configureHierarchy() {
        
        self.collectionView.collectionViewLayout = self.createLayout()
        
        self.collectionView.register(
            SearchLocationCollectionViewCell.nib,
            forCellWithReuseIdentifier: SearchLocationCollectionViewCell.cellIdentifier
        )
    }
    
    func createLayout() -> UICollectionViewLayout {
        
        let sectionProvider = { (sectionIndex: Int, layoutEnvironment: NSCollectionLayoutEnvironment) -> NSCollectionLayoutSection? in
            
            let itemSize: NSCollectionLayoutSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .absolute(41))
            let item: NSCollectionLayoutItem = NSCollectionLayoutItem(layoutSize: itemSize)
            item.contentInsets = NSDirectionalEdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0)

            let groupSize: NSCollectionLayoutSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .absolute(41))
            let group: NSCollectionLayoutGroup = NSCollectionLayoutGroup.vertical(layoutSize: groupSize,subitems: [item])
            let sectionLayout: NSCollectionLayoutSection = NSCollectionLayoutSection(group: group)
            sectionLayout.interGroupSpacing = 0
            sectionLayout.contentInsets = NSDirectionalEdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0)

            return sectionLayout
        }
        
        return UICollectionViewCompositionalLayout(sectionProvider: sectionProvider)
    }
    
    private func configureDataSource() {
        
        self.dataSource = CollectionViewSkeletonDiffableDataSource<Section, String>(collectionView: collectionView) { (collectionView: UICollectionView, indexPath: IndexPath, location: String) -> UICollectionViewCell? in
            
            guard let cell: SearchLocationCollectionViewCell = collectionView.dequeueReusableCell(
                withReuseIdentifier: SearchLocationCollectionViewCell.cellIdentifier,
                for: indexPath
            ) as? SearchLocationCollectionViewCell else { return nil }
            
            cell.location = location
        
            return cell
        }
    }
    
    // MARK: Search Term
    
    func searchTerm(_ term: String) {
        
        self.interactor?.searchTerm(request: SearchLocation.Setup.Request(term: term, limit: 3))
    }
    
    func eraseDataSource() {
        
        var snapshot = NSDiffableDataSourceSnapshot<Section, String>()
        snapshot.deleteAllItems()
        
        self.collectionView.hideSkeleton()
        self.dataSource.apply(snapshot, animatingDifferences: false)
    }
}

extension SearchLocationViewController: SearchLocationDisplayLogic {
    
    func displayLocations(viewModel: SearchLocation.Setup.ViewModel) {

        var snapshot = NSDiffableDataSourceSnapshot<Section, String>()
        snapshot.appendSections([Section.main])
        snapshot.appendItems(viewModel.locations, toSection: .main)

        self.collectionView.hideSkeleton()
        self.dataSource.apply(snapshot, animatingDifferences: true)
    }
    
    func displaySkeleton() {
        
        self.eraseDataSource()
        self.collectionView.showAnimatedGradientSkeleton(transition: .none)
    }
    
    func displayErrorAlert() {
        
        let alert: UIAlertController = UIAlertController(title: "Erro", message: "Aconteceu um erro inesperado, tente novamente em alguns instantes.", preferredStyle: .alert)
        let okAction: UIAlertAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
        
        alert.addAction(okAction)
        
        self.present(alert, animated: true, completion: nil)
    }
}

extension SearchLocationViewController: UICollectionViewDelegate {

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        if let location: String = self.dataSource.itemIdentifier(for: indexPath) {
            
            self.delegate?.didSelectLocation(location: location)
        }
    }
}
