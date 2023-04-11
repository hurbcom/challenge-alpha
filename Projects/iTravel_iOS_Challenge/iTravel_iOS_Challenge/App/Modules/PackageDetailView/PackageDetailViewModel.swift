//
//  PackageDetailViewModel.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import Foundation
import ImageSlideshow
import RxSwift

class PackageDetailViewModel{
    
    var viewDidDisappear = DelegateView<Void>()
    
    let descricao = PublishSubject<String>()

    func fetchGalleryImages(imgs:[Gallery]) -> [AlamofireSource]{
        
        var imgArray:[AlamofireSource] = []
        
        for img in imgs {
            if let alamoImg = AlamofireSource(urlString: img.url){
                imgArray.append(alamoImg)
            }
        }
        
        return imgArray
    }
    
    func parseHtmlToString(text:String){
        DispatchQueue.global(qos: .userInitiated).async {
            // Converter o texto HTML em uma NSAttributedString
            guard let data = text.data(using: .utf8) else { return }
            let options: [NSAttributedString.DocumentReadingOptionKey: Any] = [
                .documentType: NSAttributedString.DocumentType.html,
                .characterEncoding: String.Encoding.utf8.rawValue
            ]
            let attributedString = try? NSAttributedString(data: data, options: options, documentAttributes: nil)

            DispatchQueue.main.async {
                // Aqui você pode atualizar a interface do usuário com a string final
                let finalString = attributedString?.string ?? ""
                self.descricao.onNext(finalString)
            }
        }
    }
}
