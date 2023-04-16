//
//  PackageInfoViewModel.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 14/04/23.
//

import Foundation


import HUGraphQL

struct PackageInfoViewModel {
    var result: HUGraphQL.SearchPackageQuery.Data.SearchPackage.Result
    init(_ result:HUGraphQL.SearchPackageQuery.Data.SearchPackage.Result){
        self.result = result
    }
    var name:String {
        result.name
    }
    var Description:String {
        result.description
    }
    var imgGallery: String {
        result.gallery[0].url!
    }
    var prodtype:String {
        result.category
    }
    var place:String {
        result.address!.city!
    }
    var price:Double {
        result.price.amount
    }
    var url:String {
        result.url
    }

}
//MARK: - VM
struct PackageInfoListViewModel {
    var resultList: [HUGraphQL.SearchPackageQuery.Data.SearchPackage.Result]
    var numberOfSections: Int {return 1}
    func numberOfRowsInSection(_ section:Int) -> Int {
            return resultList.count
        }
    func resultAt( index: Int) -> PackageInfoViewModel {
            let info = resultList[index]
        return PackageInfoViewModel(info)
        }
}
