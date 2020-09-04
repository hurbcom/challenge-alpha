//
//  HomeViewModel.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxCocoa
import RxSwift

protocol HomeViewModelInput: AnyObject {}

protocol HomeViewModelOutput: AnyObject {}

protocol HomeViewModelType: AnyObject {
    var input: HomeViewModelInput { get }
    var output: HomeViewModelOutput { get }
}

final class HomeViewModel: HomeViewModelType, HomeViewModelInput, HomeViewModelOutput {
    
    init(interactor: HomeInteractable) {}

    var input: HomeViewModelInput { return self }
    var output: HomeViewModelOutput { return self }

}
