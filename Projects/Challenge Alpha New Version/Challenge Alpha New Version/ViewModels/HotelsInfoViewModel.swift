//
//  HotelsInfoViewModel.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import Foundation
import HUGraphQL

struct HotelsInfoViewModel {
    var result: HUGraphQL.SearchQuery.Data.Search.Result
    init(_ result:HUGraphQL.SearchQuery.Data.Search.Result){
        self.result = result
    }
    var name:String {
        result.name
    }
    var smallDescription:String {
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

}
//MARK: - VM
struct HotelsInfoListViewModel {
    var resultList: [HUGraphQL.SearchQuery.Data.Search.Result]
    var numberOfSections: Int {return 1}
    func numberOfRowsInSection(_ section:Int) -> Int {
            return resultList.count
        }
    func resultAt( index: Int) -> HotelsInfoViewModel {
            let info = resultList[index]
        return HotelsInfoViewModel(info)
        }
}
