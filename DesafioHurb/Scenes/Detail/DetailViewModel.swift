//
//  DetailViewModel.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxCocoa
import RxSwift

protocol DetailViewModelInput: AnyObject {
    var fetchHotel: PublishSubject<Void> { get }
    var selectImage: PublishSubject<Int> { get }
}

protocol DetailViewModelOutput: AnyObject {
    var hotelDisplay: Driver<HotelDetailDisplay> { get }
    var selectedImage: Driver<URL?> { get }
}

protocol DetailViewModelType: AnyObject {
    var input: DetailViewModelInput { get }
    var output: DetailViewModelOutput { get }
}

final class DetailViewModel: DetailViewModelType, DetailViewModelInput, DetailViewModelOutput {
    
    let hotelDisplay: Driver<HotelDetailDisplay>
    let selectedImage: Driver<URL?>
    
    init(interactor: DetailInteractable, hotel: Hotel) {
        
        hotelDisplay = fetchHotel.asDriver(onErrorDriveWith: Driver<Void>.empty())
            .map { _ in
                HotelDetailDisplay(hotel: hotel)
        }
        
        selectedImage = selectImage.asDriver(onErrorDriveWith: Driver<Int>.empty())
            .map { index in
                URL(string: hotel.gallery[index].url)
        }
        
    }
    
    let fetchHotel: PublishSubject<Void> = PublishSubject()
    let selectImage: PublishSubject<Int> = PublishSubject()

    var input: DetailViewModelInput { return self }
    var output: DetailViewModelOutput { return self }
}
