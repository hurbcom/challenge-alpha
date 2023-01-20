//
//  UserDefaultManager.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import Foundation

final class UserDefaultsManager {
    
    public static let shared: UserDefaultsManager = .init()
    
    private init() {}
    
    func saveLastSearchedHotelQuery(_ query: String) {
        UserDefaults.standard.set(query, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTEL)
    }
    
    func saveLastSearchedPackageQuery(_ query: String) {
        UserDefaults.standard.set(query, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGE)
    }
    
    func getLastSearchedHotelQuery() -> String {
        UserDefaults.standard.string(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTEL) ?? Constants.DEFAULT_DESTINATION
    }
    
    func getLastSearchedPackageQuery() -> String {
        UserDefaults.standard.string(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGE) ?? Constants.DEFAULT_DESTINATION
    }
}
