//
//  DetailViewController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxCocoa
import RxSwift
import UIKit
import IGListKit
import Kingfisher

final class DetailViewController: BaseViewController {
    
    @IBOutlet private var collectionView: UICollectionView!
    @IBOutlet private var cityLabel: UILabel!
    @IBOutlet private var nameLabel: UILabel!
    @IBOutlet private var starImage: UIImageView!
    @IBOutlet private var descriptionLabel: UILabel!
    @IBOutlet private var showMoreLabel: UILabel!
    @IBOutlet private var originalAmountPerDayLabel: UILabel!
    @IBOutlet private var amountPerDayLabel: UILabel!
    @IBOutlet private var paymentCondition: UILabel!
    @IBOutlet private var dateLabel: UILabel!
    @IBOutlet private var periodLabel: UILabel!
    
    private let viewModel: DetailViewModelType
    private let router: DetailRouting
    
    private lazy var adapter: ListAdapter = {
        let listAdpater = ListAdapter(
            updater: ListAdapterUpdater(),
            viewController: self
        )
        listAdpater.collectionView = collectionView
        listAdpater.dataSource = self
        return listAdpater
    }()
    
    private var gallery = [DiffableBox<Gallery>]()
    
    init(withViewModel viewModel: DetailViewModelType, router: DetailRouting) {
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
        showMoreLabel.text = "Ver mais"
        dateLabel.text = "Horário:"
        periodLabel.text = "Entrada 14h| Saída 12h"
    }
    
    private func prepareCollectionView() {
        collectionView.showsHorizontalScrollIndicator = false
        if let layout = collectionView.collectionViewLayout as? UICollectionViewFlowLayout {
            layout.scrollDirection = .horizontal
            layout.minimumLineSpacing = 2.0
            layout.minimumInteritemSpacing = 2.0
        }
    }
    
    override func bindViewModel() {
        super.bindViewModel()
        
        viewModel.output.hotelDisplay
            .drive(onNext: { [weak self] hotelDisplay in
                self?.cityLabel.text = hotelDisplay.city
                self?.nameLabel.text = hotelDisplay.name
                self?.starImage.image = hotelDisplay.ratingImage
                self?.descriptionLabel.text = hotelDisplay.resultDescription
                self?.originalAmountPerDayLabel.attributedText = hotelDisplay.originalAmountPerDay
                self?.amountPerDayLabel.text = hotelDisplay.amountPerDay
                self?.paymentCondition.text = hotelDisplay.condition
                self?.gallery = hotelDisplay.gallery.map { DiffableBox(value: $0, identifier: $0.url as NSObjectProtocol) }
                self?.adapter.performUpdates(animated: true)
            })
            .disposed(by: disposeBag)
        
        viewModel.output.selectedImage
            .drive(onNext: { [weak self] imgeUrl in
                self?.showImageFullScreen(imgeUrl: imgeUrl)
            })
            .disposed(by: disposeBag)
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.fetchHotel.onNext(())
    }
    
    private func showImageFullScreen(imgeUrl: URL?) {

        let newImageView = UIImageView()
        newImageView.kf.setImage(with: imgeUrl)
        newImageView.frame = UIScreen.main.bounds
        newImageView.backgroundColor = .black
        newImageView.contentMode = .scaleAspectFit
        newImageView.isUserInteractionEnabled = true
        let tap = UITapGestureRecognizer(target: self, action: #selector(dismissFullscreenImage))
        newImageView.addGestureRecognizer(tap)
        self.view.addSubview(newImageView)
        self.navigationController?.isNavigationBarHidden = true
        self.tabBarController?.tabBar.isHidden = true
    }

    @objc func dismissFullscreenImage(_ sender: UITapGestureRecognizer) {
        self.navigationController?.isNavigationBarHidden = false
        self.tabBarController?.tabBar.isHidden = false
        sender.view?.removeFromSuperview()
    }
}

extension DetailViewController: ListAdapterDataSource {
    
    func objects(for listAdapter: ListAdapter) -> [ListDiffable] {
        return gallery
    }
    
    func listAdapter(_ listAdapter: ListAdapter, sectionControllerFor object: Any) -> ListSectionController {
        return HotelImageSectionController(didSelectItem: { index in
            self.viewModel.input.selectImage.onNext(index)
        })
    }
    
    func emptyView(for listAdapter: ListAdapter) -> UIView? {
        return nil
    }
}
