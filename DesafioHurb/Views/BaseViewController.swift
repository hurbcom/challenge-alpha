//
//  BaseViewController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class BaseViewController: UIViewController {
    let disposeBag = DisposeBag()
    let error: PublishSubject<Error> = PublishSubject()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        prepare()
        bindViewModel()
    }
    
    func prepare() {}
    
    func bindViewModel() {
        error.asDriver(onErrorDriveWith: Driver.empty())
            .drive(onNext: { [weak self] error in
                self?.handleError(error)
            })
            .disposed(by: disposeBag)
    }
    
    func handleError(_ error: Error, onConfirm: (() -> Void)? = nil) {
        dump(error)
        
        if let netWorkError = error as? NetWorkError {
            Alert.show(
                in: self,
                title: "Ops...",
                message: netWorkError.localizedDescription
            )
            return
        }
        if let parseError = error as? ParseError {
            Alert.show(
                in: self,
                title: "Ops...",
                message: parseError.localizedDescription
            )
            return
        }
        Alert.show(
            in: self,
            title: "Ops...",
            message: NetWorkError(rawValue: 0)?.localizedDescription
        )
    }
}

enum Alert {
    static func show(
        in viewController: UIViewController,
        title: String?,
        message: String?,
        onConfirm: (() -> Void)? = nil
    ) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(
            UIAlertAction(
                title: "OK", style: .cancel,
                handler: { _ in
                    onConfirm?()
            }
        ))
        DispatchQueue.main.async {
            viewController.present(alert, animated: true)
        }
    }
}
