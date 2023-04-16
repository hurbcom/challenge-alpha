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
    var Description:String {
        removeHtmlTagsFromText(text: result.description)
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
    
    func removeHtmlTagsFromText(text:String) -> String {
        let htmlString = text
        let attributedString = try? NSAttributedString(data: htmlString.data(using: .utf8)!, options: [.documentType: NSAttributedString.DocumentType.html], documentAttributes: nil)
        let plainString = attributedString?.string
        let decomposedString = plainString?.decomposedStringWithCanonicalMapping
        return decomposedString!
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
