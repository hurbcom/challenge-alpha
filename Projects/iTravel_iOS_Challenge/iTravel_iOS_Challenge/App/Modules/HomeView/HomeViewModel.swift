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
import RxRelay
import ImageSlideshow

class HomeViewModel{
    
    let selectedItem = DelegateView<PackageResult>()
    
    let packages: BehaviorRelay<[PackageResult]> = BehaviorRelay(value: [])

    let error = PublishSubject<String>()
    
    var service:TravelService?
    
    init(service:TravelService) {
        self.service = service
    }
    
    func fetchPackages(search:String = "Rio de Janeiro"){

        service?.searchPackage(local:search) { result in
            switch result {
            case .success(let user):
                let jsonData = try? JSONSerialization.data(withJSONObject: user.jsonObject as Any, options: [])
                let packageItem = try? JSONDecoder().decode(PackageItem.self, from: jsonData ?? Data())
                if let results = packageItem?.searchPackage.results{
                    self.packages.accept(results)
                }else{
                    self.error.onNext("Error")
                }
            case .failure(let error):
                print("Failed to fetch user data: \(error)")
            }
            
        }
    }
    
    func formatPrice(price:Int) -> String{
        let formatter = NumberFormatter()
        formatter.numberStyle = .decimal
        formatter.locale = Locale(identifier: "pt_BR") // or any other locale that uses comma as decimal separator

        let number: Double = Double(price) 
        let formattedString = formatter.string(from: NSNumber(value: number/100)) ?? ""
        
        return ("R$ " + formattedString + ",00")
    }
    
    func fetchGalleryImages(imgs:[Gallery]) -> [AlamofireSource]{
        
        var imgArray:[AlamofireSource] = []
        
        for img in imgs {
            if let alamoImg = AlamofireSource(urlString: img.url){
                imgArray.append(alamoImg)
            }
        }
        
        return imgArray
    }

}


fileprivate func prettyJSON(data: Data?) -> String? {
    guard let data = data else { return nil }
    guard let object = try? JSONSerialization.jsonObject(with: data, options: []),
          let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
          let prettyPrintedString = String(data: data, encoding:.utf8) else { return nil }

    return prettyPrintedString
}
