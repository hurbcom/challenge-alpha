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
        return listAdpater
    }()
    
    private var hotels = [DiffableBox<HotelDisplay>]()
    
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
        prepareCollectionView()
    }
    
    private func prepareCollectionView() {
        collectionView.backgroundColor = UIColor.black.withAlphaComponent(0.1)
        collectionView.collectionViewLayout = ListCollectionViewLayout(
            stickyHeaders: false,
            topContentInset: 0,
            stretchToEdge: true
        )
    }
    
    override func bindViewModel() {
        super.bindViewModel()
        viewModel.output.error
            .drive(onNext: { [weak self] error in
                guard let self = self else { return }
                print(error)
            })
            .disposed(by: disposeBag)
        
        viewModel.output.hotels
            .drive(onNext: { [weak self] hotels in
                guard let self = self else { return }
                self.hotels = hotels.map { DiffableBox(value: $0, identifier: $0.id as NSObjectProtocol) }
                self.adapter.performUpdates(animated: true)
            })
            .disposed(by: disposeBag)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.fetchData.onNext(())
    }
}

extension HomeViewController: ListAdapterDataSource {
    
    func objects(for listAdapter: ListAdapter) -> [ListDiffable] {
        return hotels
    }
    
    func listAdapter(_ listAdapter: ListAdapter, sectionControllerFor object: Any) -> ListSectionController {
        return HotelSectionController(didSelectItem: { index in
            self.viewModel.input.selectHotel.onNext(index)
        })
    }
    
    func emptyView(for listAdapter: ListAdapter) -> UIView? {
        return nil
    }
}
