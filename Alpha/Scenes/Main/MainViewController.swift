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

class MainViewController: BaseViewController {
    let disposeBag = DisposeBag()
    let headerRefreshTrigger = PublishSubject<Void>()

    internal lazy var dataSource: RxTableViewSectionedReloadDataSource<FeedSection> = {
        let dataSource = RxTableViewSectionedReloadDataSource<FeedSection>(configureCell: { (_, tableView, indexPath, feedSection) -> UITableViewCell in
            switch feedSection {
            case .Star(let hotels):
                guard let cell = tableView.dequeueReusableCell(withIdentifier: feedSection.identifier)
                as? StarTableViewCell else { fatalError("Unknown identifier") }
                cell.currentDataSource = HotelsDataSource(with: hotels)
                return cell
            case .Package(let packages):
                guard let cell = tableView.dequeueReusableCell(withIdentifier: feedSection.identifier)
                as? StarTableViewCell else { fatalError("Unknown identifier") }
                return cell
            }
        })
        dataSource.titleForHeaderInSection = { dataSource, index in
            dataSource.sectionModels[index].header
        }
        return dataSource
    }()

    internal var feedTableView: UITableView = {
        let view = UITableView()
        view.backgroundColor = .clear
        view.register(StarTableViewCell.self, forCellReuseIdentifier: Identifiers.Star.rawValue)
        view.register(StarTableViewCell.self, forCellReuseIdentifier: Identifiers.Package.rawValue)
        view.tableFooterView = UIView(frame: .zero)
        view.isScrollEnabled = true
        view.showsVerticalScrollIndicator = false
        view.rowHeight = UITableView.automaticDimension
        view.sectionHeaderHeight = UITableView.automaticDimension
        view.estimatedRowHeight = 140.0
        view.estimatedSectionHeaderHeight = 55.0
        view.layoutMargins = .zero
        return view
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func setupUI() {
        self.view.backgroundColor = .red

        self.view.addSubview(feedTableView)
    }

    override func bindViewModel() {
        guard let viewModel = viewModel as? MainViewModel else { return }

        let refresh = Observable.of(Observable.just(()), headerRefreshTrigger).merge()
        let input = MainViewModel.Input(headerRefresh: refresh)

        let output = viewModel.transform(input: input)

        output.feed.asDriver(onErrorJustReturn: [])
            .drive(feedTableView.rx.items(dataSource: dataSource))
            .disposed(by: disposeBag)
    }

    override func setupConstraints() {
        super.setupConstraints()

        feedTableView.snp.makeConstraints { (make) in
            make.top.equalTo(view.safeAreaLayoutGuide.snp.top)
            make.leading.equalTo(view.safeAreaLayoutGuide.snp.leadingMargin)
            make.bottom.equalTo(view.safeAreaLayoutGuide.snp.bottom)
            make.trailing.equalTo(view.safeAreaLayoutGuide.snp.trailingMargin)
        }
    }
}
