//
//  MainViewController.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import RxDataSources
import SnapKit

class MainViewController: BaseViewController {
    let disposeBag = DisposeBag()
    let headerRefreshTrigger = PublishSubject<Void>()

    internal lazy var dataSource: RxTableViewSectionedReloadDataSource<FeedSection> = {
        let dataSource = RxTableViewSectionedReloadDataSource<FeedSection>(configureCell: { (_, tableView, indexPath, target) -> UITableViewCell in

            let cell = UITableViewCell()
            return cell
        })
        dataSource.titleForHeaderInSection = { dataSource, index in
            dataSource.sectionModels[index].header
        }
        return dataSource
    }()

    internal var feedTableView: UITableView = {
        let view = UITableView()
        view.backgroundColor = .clear
        view.tableFooterView = UIView(frame: .zero)
        view.isScrollEnabled = false
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

//        output.feed.subscribe(
//            onNext: { [weak self] items in
//                dump(items)
//            }
//        ).disposed(by: disposeBag)

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
