//
//  SearchProductViewController.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 10/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit
import HUGraphQL
import SkeletonView

protocol SearchProductDisplayLogic: AnyObject {
    
    func displayNewProducts(viewModel: SearchProduct.Query.ViewModel)
    func displayFirstPageSkeletonView()
    func displayErrorAlert()
}

private struct Section: Identifiable {

    enum Identifier: String, CaseIterable {
        
        case all = "All"
    }
    
    var id: Identifier

    // Conform to CustomStringConvertible protocol to use
//    var description: String {
//
//        switch self {
//            case .main: return ""
//        }
//    }
}

class CollectionViewSkeletonDiffableDataSource<Section: Hashable, Item: Hashable>: UICollectionViewDiffableDataSource<Section, Item>, SkeletonCollectionViewDataSource {

    func collectionSkeletonView(_ skeletonView: UICollectionView, cellIdentifierForItemAt indexPath: IndexPath) -> ReusableCellIdentifier {
        return ProductsCollectionViewCell.cellIdentifier
    }

    func collectionSkeletonView(_ skeletonView: UICollectionView, prepareCellForSkeleton cell: UICollectionViewCell, at indexPath: IndexPath) {

    }

    func collectionSkeletonView(_ skeletonView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 50
    }
}

class SearchProductViewController: UIViewController {
    
    var interactor: SearchProductBusinessLogic?
    var router: (NSObjectProtocol & SearchProductRoutingLogic & SearchProductDataPassing)?
    
    private lazy var dataSource: UICollectionViewDiffableDataSource<Section.ID, Product.ID>! = nil
    private lazy var sectionsStore: AnyModelStore<Section> = AnyModelStore([Section(id: .all)])
    private lazy var itemsStore: AnyModelStore<Product> = AnyModelStore([])
    
    private lazy var searchController = UISearchController(searchResultsController: nil)
    private lazy var page: Int = 1
    private lazy var limit: Int = 15
    private lazy var hasNext: Bool = true
    
    @IBOutlet private weak var collectionView: UICollectionView!
    
    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.setupSearchController()
        self.configureHierarchy()
        self.configureDataSource()
        self.load()
    }
    
    // MARK: Routing
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender)
        
    }
    
    private func setupSearchController() {
        
        self.navigationItem.searchController = self.searchController
        self.searchController.obscuresBackgroundDuringPresentation = false
        self.searchController.searchBar.placeholder = "Quero ir para..."
        self.searchController.searchBar.delegate = self
    }
    
    // MARK: Configure Hierarchy
    
    private func configureHierarchy() {
        
        self.collectionView.collectionViewLayout = self.createLayout()
        
        self.collectionView.register(
            ProductsCollectionViewCell.nib,
            forCellWithReuseIdentifier: ProductsCollectionViewCell.cellIdentifier
        )
    }
    
    func createLayout() -> UICollectionViewLayout {
        
        let sectionProvider = { (sectionIndex: Int, layoutEnvironment: NSCollectionLayoutEnvironment) -> NSCollectionLayoutSection? in
            
            let itemSize: NSCollectionLayoutSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .estimated(334))
            let item: NSCollectionLayoutItem = NSCollectionLayoutItem(layoutSize: itemSize)
            item.contentInsets = NSDirectionalEdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0)

            let groupSize: NSCollectionLayoutSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .estimated(334))
            let group: NSCollectionLayoutGroup = NSCollectionLayoutGroup.vertical(layoutSize: groupSize,subitems: [item])
            let sectionLayout: NSCollectionLayoutSection = NSCollectionLayoutSection(group: group)
            sectionLayout.interGroupSpacing = 0
            sectionLayout.contentInsets = NSDirectionalEdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0)

            return sectionLayout
        }
        
        return UICollectionViewCompositionalLayout(sectionProvider: sectionProvider)
    }
    
    private func configureDataSource() {
        
        self.dataSource = CollectionViewSkeletonDiffableDataSource<Section.ID, Product.ID>(collectionView: collectionView) { (collectionView: UICollectionView, indexPath: IndexPath, id: Product.ID) -> UICollectionViewCell? in
            
            let item: Product = self.itemsStore.fetchByID(id)
            
            guard let cell: ProductsCollectionViewCell = collectionView.dequeueReusableCell(
                withReuseIdentifier: ProductsCollectionViewCell.cellIdentifier,
                for: indexPath
            ) as? ProductsCollectionViewCell else { return UICollectionViewCell() }
            
            cell.product = item
            
            return cell
        }
        
        var snapshot = NSDiffableDataSourceSnapshot<Section.ID, Product.ID>()
        snapshot.appendSections(Section.Identifier.allCases)
        self.dataSource.apply(snapshot, animatingDifferences: false)
    }
    
    // MARK: Do something
    
    func load() {
        
        let request = SearchProduct.Query.Request(term: "punta cana", page: self.page, limit: self.limit)
        self.interactor?.searchProducts(request: request)
    }
}

extension SearchProductViewController: SearchProductDisplayLogic {

    func displayNewProducts(viewModel: SearchProduct.Query.ViewModel) {
        
        self.hasNext = viewModel.pagination.hasNext
        var snapshot = self.dataSource.snapshot()
        self.itemsStore.append(viewModel.products)
        snapshot.appendItems(viewModel.products.map({ $0.id }), toSection: .all)

        self.collectionView.hideSkeleton()
        self.dataSource.apply(snapshot, animatingDifferences: false)
    }
    
    func displayFirstPageSkeletonView() {
        
        self.collectionView.showAnimatedGradientSkeleton(transition: .none)
    }
    
    func displayErrorAlert() {
        
    }
}

extension SearchProductViewController: UISearchBarDelegate {

}

extension SearchProductViewController: UICollectionViewDelegate {

    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        
        if indexPath.row > self.page * (self.limit/2) && self.hasNext {
            
            self.page += 1
            let request = SearchProduct.Query.Request(term: "punta cana", page: self.page, limit: self.limit)
            self.interactor?.searchProducts(request: request)
        }
    }
}
