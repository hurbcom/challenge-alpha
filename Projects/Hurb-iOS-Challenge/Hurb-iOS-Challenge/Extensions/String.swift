//
//  String.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 19/01/23.
//

import Foundation

extension String {
    
    var stringReadingHtml: String? {
        
        let newString: String = self.replacingOccurrences(of: "\n", with: "<br>")
        guard let data = newString.data(using: .unicode, allowLossyConversion: false) else {
            
            return nil
        }
        
        return try? NSAttributedString(
            data: data,
            options: [.documentType: NSAttributedString.DocumentType.html],
            documentAttributes: nil
        ).string
    }
}
