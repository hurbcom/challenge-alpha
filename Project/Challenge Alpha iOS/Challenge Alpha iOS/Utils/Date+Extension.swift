//
//  Date+Extension.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import Foundation

extension Date {
    static func tomorrow() -> Date {
        let calendar = Calendar.current
        let today = Date()
        let midnight = calendar.startOfDay(for: today)
        let tomorrow = calendar.date(byAdding: .day, value: 1, to: midnight)!
        
        return tomorrow
    }
    
    func dayAndMonthString() -> String {
        let calendar = Calendar.current
        let components = calendar.dateComponents([.day, .month], from: self)
        
        guard let month = components.month,
              let monthString = Months(monthNumber: month)?.rawValue,
              let day = components.day else {
            return ""
        }
        
        let dayString = String(day)
        return "\(dayString) \(monthString)"
    }
}
