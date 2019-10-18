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

class MainViewController: BaseViewController {
    let disposeBag = DisposeBag()
    let headerRefreshTrigger = PublishSubject<Void>()

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func setupUI() {
        self.view.backgroundColor = .red
    }

    override func bindViewModel() {
        guard let viewModel = viewModel as? MainViewModel else { return }

        let refresh = Observable.of(Observable.just(()), headerRefreshTrigger).merge()
        let input = MainViewModel.Input(headerRefresh: refresh)

        let output = viewModel.transform(input: input)

        output.feed.subscribe(
            onNext: { [weak self] items in
//                dump(items)
            }
        ).disposed(by: disposeBag)
    }
}
