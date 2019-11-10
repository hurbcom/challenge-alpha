//___FILEHEADER___

import XCTest
import Moya
@testable import Challange_HURB___Alpha

class DataManagerTests: XCTestCase {

    var dataManager: DataManager?
    
    override func setUp() {
        let expectation = XCTestExpectation(description: "Get api response")
        self.dataManager = DataManager()
        wait(for: [expectation], timeout: 10.0)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        let hotels = dataManager?.hotels ?? []
        for i in 0 ..< hotels.count-1 {
            if hotels[i].stars! < hotels[i+1].stars! {
                assert(false)
            }
        }
    }

}
