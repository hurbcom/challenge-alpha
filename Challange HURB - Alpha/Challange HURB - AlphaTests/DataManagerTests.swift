//___FILEHEADER___

import XCTest
import Moya
@testable import Challange_HURB___Alpha

class DataManagerTests: XCTestCase {

    var dataManager: DataManager?
    
    override func setUp() {
        self.dataManager = DataManager()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        let hotels = dataManager?.hotels ?? []
        
        for index in 0 ..< hotels.count-1 {
            if hotels[index].stars! < hotels[index+1].stars! {
                assert(false)
            }
        }
    }

}
