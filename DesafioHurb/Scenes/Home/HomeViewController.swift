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

final class HomeViewController: BaseViewController {
    
    private let viewModel: HomeViewModelType
    private let router: HomeRouting
    
    init(withViewModel viewModel: HomeViewModelType, router: HomeRouting) {
        self.viewModel = viewModel
        self.router = router
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder _: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.fetchData.onNext(())
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
                print(hotels)
            })
            .disposed(by: disposeBag)
    }
}
