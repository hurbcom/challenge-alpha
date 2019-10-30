//___FILEHEADER___

import XCTest
import Moya
@testable import Challange_HURB___Alpha

class NetworkAdapterTests: XCTestCase {

    var networkAdapter: NetworkAdapter?
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        networkAdapter = NetworkAdapter()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        let expectation = XCTestExpectation(description: "Foi bom")
        
        
        networkAdapter?.getAPIRespose(completion: { (apiResponse, error) in
            if let apiResponse = apiResponse {
                dump(apiResponse)
                expectation.fulfill()
            } else {
                print(error)
            }
        })
        wait(for: [expectation], timeout: 10.0)
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
