//
//  MainViewController.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxDataSources
import SnapKit
import os.log

class FeedViewController: BaseViewController {
    // MARK: - Properties

    let disposeBag = DisposeBag()
    let headerRefreshTrigger = PublishSubject<Void>()

    lazy var dataSource: RxTableViewSectionedReloadDataSource<FeedSection> = {
        let dataSource = RxTableViewSectionedReloadDataSource<FeedSection>(configureCell: { (_, tableView, indexPath, feedSection) -> UITableViewCell in
            switch feedSection {
            case .Star(let hotels):
                guard let cell = tableView.dequeueReusableCell(withIdentifier: feedSection.identifier)
                as? StarTableViewCell else {
                    os_log("❌ - Unknown cell identifier %@", log: Logger.appLog(), type: .fault, "\(self)")
                    fatalError("Unknown identifier")
                }
                cell.currentDataSource = HotelsDataSource(with: hotels)
                cell.collectionView.accessibilityIdentifier = "PackageCollectionView\(indexPath)"
                cell.accessibilityIdentifier = "Star\(indexPath)"
                return cell
            case .Package(let packages):
                guard let cell = tableView.dequeueReusableCell(withIdentifier: feedSection.identifier)
                as? PackageTableViewCell else {
                    os_log("❌ - Unknown cell identifier %@", log: Logger.appLog(), type: .fault, "\(self)")
                    fatalError("Unknown identifier")
                }
                cell.currentDataSource = PackageDataSource(with: packages)
                cell.collectionView.accessibilityIdentifier = "HotelCollectionView\(indexPath)"
                cell.accessibilityIdentifier = "PackageTable\(indexPath)"
                return cell
            }
        })

        return dataSource
    }()

    var feedTableView: UITableView = {
        let view = UITableView()
        view.backgroundColor = .clear
        view.register(StarTableViewCell.self, forCellReuseIdentifier: Identifiers.Star.rawValue)
        view.register(PackageTableViewCell.self, forCellReuseIdentifier: Identifiers.Package.rawValue)
        view.register(StarTableViewHeader.self, forHeaderFooterViewReuseIdentifier: Identifiers.FeedSection.rawValue)
        view.tableFooterView = UIView(frame: .zero)
        view.isScrollEnabled = true
        view.showsVerticalScrollIndicator = false
        view.rowHeight = UITableView.automaticDimension
        view.sectionHeaderHeight = UITableView.automaticDimension
        view.estimatedRowHeight = 140.0
        view.estimatedSectionHeaderHeight = 55.0
        view.layoutMargins = .zero
        view.accessibilityIdentifier = "feedTableView"
        return view
    }()

    var loadingView: LoadingUIView = {
        let view = LoadingUIView(frame: .zero)
        view.isHidden = false
        view.backgroundColor = .white
        return view
    }()

    var errorView: ErrorUIView = {
        let view = ErrorUIView(frame: .zero)
        view.isHidden = true
        view.backgroundColor = .white
        return view
    }()

    // MARK: - viewDidLoad

    override func viewDidLoad() {
        super.viewDidLoad()

    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setNavigationBarHidden(true, animated: animated)
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        navigationController?.setNavigationBarHidden(false, animated: animated)
    }

    // MARK: - View methods

    override func setupUI() {
        self.view.backgroundColor = .white

        feedTableView
            .rx.setDelegate(self)
            .disposed(by: disposeBag)

        self.view.addSubview(feedTableView)
        self.view.addSubview(errorView)
        self.view.addSubview(loadingView)
    }

    // MARK: - Binding

    override func bindViewModel() {
        guard let viewModel = viewModel as? FeedViewModel else { return }

        let refresh = Observable.of(Observable.just(()), headerRefreshTrigger).merge()
        let input = FeedViewModel.Input(refresh: refresh)

        let output = viewModel.transform(input: input)

        output.isLoading.subscribe(onNext: { [weak self] event in
            if !event {
                self?.loadingView.isHidden = true
            } else {
                self?.loadingView.isHidden = false
            }
        }, onError: { [weak self] _ in
            self?.errorView.isHidden = false
        }).disposed(by: disposeBag)

        output.feed.asDriver(onErrorJustReturn: [])
            .drive(feedTableView.rx.items(dataSource: dataSource))
            .disposed(by: disposeBag)
    }

    // MARK: - Constraints

    override func setupConstraints() {
        super.setupConstraints()

        feedTableView.snp.makeConstraints { (make) in
            make.top.equalTo(view.safeAreaLayoutGuide.snp.top).offset(20)
            make.leading.equalTo(view.safeAreaLayoutGuide.snp.leadingMargin)
            make.bottom.equalTo(view.safeAreaLayoutGuide.snp.bottom)
            make.trailing.equalTo(view.safeAreaLayoutGuide.snp.trailingMargin)
        }

        loadingView.snp.makeConstraints { (make) in
            make.edges.equalToSuperview()
        }

        errorView.snp.makeConstraints { (make) in
            make.edges.equalToSuperview()
        }
    }
}

// MARK: - Table View delegate

extension FeedViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView,
                   viewForHeaderInSection section: Int) -> UIView? {
        guard let view = tableView.dequeueReusableHeaderFooterView(withIdentifier: Identifiers.FeedSection.rawValue)
            as? StarTableViewHeader else { return nil }

        view.titleLabel.text = dataSource.sectionModels[section].header
        view.subTitle.text = dataSource.sectionModels[section].subTitle

        return view
    }
}
