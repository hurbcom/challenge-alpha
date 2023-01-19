//
//  String+Extension.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import Foundation

extension String {
    func monthAndYearString() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "pt-BR")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        
        guard let date = dateFormatter.date(from: self) else {
            return ""
        }
        
        let calendar = Calendar.current
        let components = calendar.dateComponents([.month, .year], from: date)
        
        guard let month = components.month,
              let monthString = Months(monthNumber: month)?.rawValue,
              let year = components.year else {
            return ""
        }
        
        let yearString = String(year).suffix(2)
        return "\(monthString)/\(yearString)"
    }
}

//extension StringProtocol {
//    func htmlToAttributedString() -> AttributedString {
//        guard let attString = try? AttributedString(
//            NSAttributedString(
//                data: .init(utf8),
//                options: [
//                    .documentType: NSAttributedString.DocumentType.html,
//                    .characterEncoding: String.Encoding.utf8.rawValue
//                ],
//                documentAttributes: nil
//            )
//        ) else {
//            return AttributedString(stringLiteral: "")
//        }
//        
//        return attString
//    }
//}
