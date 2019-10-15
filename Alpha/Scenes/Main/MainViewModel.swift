//
//  MainViewModel.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import RxCocoa
import RxSwift
import os.log

class MainViewModel: BaseViewModel, ViewModelType {
    let disposeBag = DisposeBag()

    struct Input {
        let headerRefresh: Observable<Void>
    }
    struct Output {
        let feed: BehaviorRelay<[APIResult]>
    }

    func transform(input: Input) -> Output {
        let elements = BehaviorRelay<[APIResult]>(value: [])

        input.headerRefresh.flatMapLatest { [weak self] () -> Observable<[APIResult]> in
            return APIClient.RxGetFeed(forCity: "buzios", page: 1)
        }.subscribe(
            onNext: { [weak self] items in
                elements.accept(items)
            }
        ).disposed(by: disposeBag)

        return Output(feed: elements)
    }

}
