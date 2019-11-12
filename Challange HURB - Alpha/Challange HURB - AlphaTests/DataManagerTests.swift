//___FILEHEADER___

import XCTest
import Moya
@testable import Challange_HURB___Alpha

class DataManagerTests: XCTestCase {

    var dataManager: DataManager?
    
    override func setUp() {
        self.dataManager = DataManager(state: .test)
    }

    override func tearDown() { }
    
    /// Checks if the DataManager receives the hotels and returns then in order
    func testCheckHotelsOrder() {
        let hotels = dataManager?.hotels ?? []
        if hotels.isEmpty {
            assertionFailure()
        }
        for index in 0 ..< hotels.count-1 {
            if hotels[index].stars! < hotels[index+1].stars! {
                assertionFailure()
            }
        }
    }
    
    /// Checks if the DataManager receives the Package existent in the HurbSample
    func testPackages() {
        if dataManager?.packages.count != 1 {
            assertionFailure()
        }
    }

}
