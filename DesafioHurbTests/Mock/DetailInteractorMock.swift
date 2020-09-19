//
//  DetailInteractorMock.swift
//  DesafioHurbTests
//
//  Created by Edson Aparecido Guido on 19/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation
@testable import DesafioHurb
import RxSwift

class DetailInteractorMock: DetailInteractable {

    var requestReceivedPage: Int?
    var requestReturnValue: Any?
    var errorReturn: NSError?
    
}
