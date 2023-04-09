//
//  HomeViewModel.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import HUGraphQL
import Apollo
import RxSwift

class HomeViewModel{
    
    var viewDidDisappear = DelegateView<Void>()
    let packagesSubject = PublishSubject<[PackageResult]>()

    
    func fetchPackages(){

        TravelService().searchPackage { result in
            switch result {
            case .success(let user):
                let jsonData = try? JSONSerialization.data(withJSONObject: user.jsonObject as Any, options: [])
                let packageItem = try? JSONDecoder().decode(PackageItem.self, from: jsonData ?? Data())
                if let results = packageItem?.searchPackage.results{
                    self.packagesSubject.onNext(results)
                    self.packagesSubject.onCompleted()
                }
            case .failure(let error):
                print("Failed to fetch user data: \(error)")
            }
            
        }
    }
    
    func parseData(json:String){
            
    }

}


fileprivate func prettyJSON(data: Data?) -> String? {
    guard let data = data else { return nil }
    guard let object = try? JSONSerialization.jsonObject(with: data, options: []),
          let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
          let prettyPrintedString = String(data: data, encoding:.utf8) else { return nil }

    return prettyPrintedString
}
