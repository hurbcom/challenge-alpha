//
//  UserDefaultManager.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import Foundation

final class UserDefaultsManager {
    
    public static let shared: UserDefaultsManager = .init()
    private let logger = LoggerFactory.createLogger(class: UserDefaultsManager.self)
    
    private init() {}
    
    func saveLastSearchedHotelQuery(_ query: String) {
        UserDefaults.standard.set(query, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTEL)
        
        guard self.hotelQueryNotSaved(query: query) else { return }
        
        var queries = self.getLastSearchedHotelQueries()
        queries.insert(query, at: 0)
        UserDefaults.standard.set(queries, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTELS)
        
        logger.info("Saved last searched hotel query - \(query)")
    }
    
    func saveLastSearchedPackageQuery(_ query: String) {
        UserDefaults.standard.set(query, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGE)
        
        guard self.packageQueryNotSaved(query: query) else { return }
        
        var queries = self.getLastSearchedPackageQueries()
        queries.insert(query, at: 0)
        UserDefaults.standard.set(queries, forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGES)
        
        logger.info("Saved last searched package query - \(query)")
    }
    
    func getLastSearchedHotelQuery() -> String {
        UserDefaults.standard.string(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTEL) ?? Constants.DEFAULT_DESTINATION
    }
    
    func getLastSearchedPackageQuery() -> String {
        UserDefaults.standard.string(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGE) ?? Constants.DEFAULT_DESTINATION
    }
    
    func getLastSearchedHotelQueries() -> [String] {
        guard let queries = UserDefaults.standard.object(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_HOTELS) as? [String] else {
            logger.debug("Failed to get last searched hotel queries")
            return []
        }
        
        return queries
    }
    
    func getLastSearchedPackageQueries() -> [String] {
        guard let queries = UserDefaults.standard.object(forKey: Constants.USER_DEFAULTS.LAST_SEARCHED_PACKAGES) as? [String] else {
            logger.debug("Failed to get last searched package queries")
            return []
        }
        
        return queries
    }
    
    private func hotelQueryNotSaved(query: String) -> Bool {
        return self.getLastSearchedHotelQueries().first(where: { $0 == query }) == nil
    }
    
    private func packageQueryNotSaved(query: String) -> Bool {
        return self.getLastSearchedPackageQueries().first(where: { $0 == query }) == nil
    }
}
