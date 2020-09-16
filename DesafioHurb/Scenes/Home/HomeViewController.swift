//
//  HomeViewController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxCocoa
import RxSwift
import UIKit
import IGListKit

final class HomeViewController: BaseViewController {
    
    enum Constants {
        static let loading = "LOADING"
        static let packages = "Pacotes"
        static let oneStar = "Uma estrela"
        static let twoStar = "Duas estrelas"
        static let threeStar = "Três estrelas"
        static let fourStar = "Quatro estrelas"
        static let fiveStar = "Cinco estrelas"
    }
    
    private let viewModel: HomeViewModelType
    private let router: HomeRouting
    
    @IBOutlet private var collectionView: UICollectionView!
    
    private lazy var adapter: ListAdapter = {
        let listAdpater = ListAdapter(
            updater: ListAdapterUpdater(),
            viewController: self
        )
        listAdpater.collectionView = collectionView
        listAdpater.dataSource = self
        listAdpater.collectionViewDelegate = self
        return listAdpater
    }()
    
    private lazy var refreshControl = UIRefreshControl()
    
    private var packages = [DiffableBox<HotelDisplay>]()
    private var hotelsOneStar = [DiffableBox<HotelDisplay>]()
    private var hotelsTwoStar = [DiffableBox<HotelDisplay>]()
    private var hotelsThreeStar = [DiffableBox<HotelDisplay>]()
    private var hotelsFourStar = [DiffableBox<HotelDisplay>]()
    private var hotelsFiveStar = [DiffableBox<HotelDisplay>]()
    
    private var itemsCount = 0
    private var isLoadingMore = false
    
    init(withViewModel viewModel: HomeViewModelType, router: HomeRouting) {
        self.viewModel = viewModel
        self.router = router
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder _: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func prepare() {
        super.prepare()
        title = "Hotéis e Pacotes"
        prepareCollectionView()
    }
    
    private func prepareCollectionView() {
        collectionView.showsVerticalScrollIndicator = false
        collectionView.refreshControl = refreshControl
        collectionView.backgroundColor = UIColor.black.withAlphaComponent(0.1)
        collectionView.collectionViewLayout = ListCollectionViewLayout(
            stickyHeaders: false,
            topContentInset: 0,
            stretchToEdge: true
        )
    }
    
    override func bindViewModel() {
        super.bindViewModel()
        
        refreshControl.rx.controlEvent(.valueChanged)
            .bind(to: viewModel.input.fetchData)
            .disposed(by: disposeBag)
        
        viewModel.output.error
            .drive(error)
            .disposed(by: disposeBag)
        
        viewModel.output.hotelsPackages
            .drive(onNext: { [weak self] hotelsPackages in
                guard let self = self else { return }
                self.refreshControl.endRefreshing()
                self.packages = hotelsPackages.packages.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.hotelsOneStar = hotelsPackages.hotelsOneStar.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.hotelsTwoStar = hotelsPackages.hotelsTwoStar.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.hotelsThreeStar = hotelsPackages.hotelsThreeStar.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.hotelsFourStar = hotelsPackages.hotelsFourStar.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.hotelsFiveStar = hotelsPackages.hotelsFiveStar.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.isLoadingMore = false
                self.itemsCount = hotelsPackages.count
                self.adapter.performUpdates(animated: true)
            })
            .disposed(by: disposeBag)
        
        viewModel.output.selectedHotel
            .drive(onNext: { [weak self] hotel in
                self?.router.navigateToDetail(hotel: hotel)
            })
            .disposed(by: disposeBag)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.fetchData.onNext(())
        refreshControl.beginRefreshing()
    }
    
}

extension HomeViewController: ListAdapterDataSource {
    
    func objects(for listAdapter: ListAdapter) -> [ListDiffable] {
        var items: [ListDiffable] = []
        if !packages.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.packages),
                identifier: Constants.packages as NSObjectProtocol)
            )
            items.append(contentsOf: packages)
        }
        if !hotelsFiveStar.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.fiveStar),
                identifier: Constants.fiveStar as NSObjectProtocol)
            )
            items.append(contentsOf: hotelsFiveStar)
        }
        if !hotelsFourStar.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.fourStar),
                identifier: Constants.fourStar as NSObjectProtocol)
            )
            items.append(contentsOf: hotelsFourStar)
        }
        if !hotelsThreeStar.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.threeStar),
                identifier: Constants.threeStar as NSObjectProtocol)
            )
            items.append(contentsOf: hotelsThreeStar)
        }
        if !hotelsTwoStar.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.twoStar),
                identifier: Constants.twoStar as NSObjectProtocol)
            )
            items.append(contentsOf: hotelsTwoStar)
        }
        if !hotelsOneStar.isEmpty {
            items.append(DiffableBox(
                value: HotelHeaderDisplay(title: Constants.oneStar),
                identifier: Constants.oneStar as NSObjectProtocol)
            )
            items.append(contentsOf: hotelsOneStar)
        }
        
        if isLoadingMore {
            items.append("" as ListDiffable)
        }
        
        return items
    }
    
    func listAdapter(_ listAdapter: ListAdapter, sectionControllerFor object: Any) -> ListSectionController {
        switch object {
        case is DiffableBox<HotelDisplay>:
            return HotelSectionController(didSelectItem: { index in
                self.viewModel.input.selectHotel.onNext(index)
            })
        case is DiffableBox<HotelHeaderDisplay>:
            return HotelHeaderSectionController()
        case is String:
            return LoadingSectionController(cellHeight: HotelViewCell.defaultHeight)
        default:
            fatalError()
        }
    }
    
    func emptyView(for listAdapter: ListAdapter) -> UIView? {
        return nil
    }
}

extension HomeViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if indexPath.section == itemsCount - 8 {
            viewModel.input.fetchNextPage.onNext(())
            isLoadingMore = true
        }
    }
}
