//___FILEHEADER___

import XCTest
import Moya
import os.log
@testable import Challange_HURB___Alpha

class NetworkAdapterTests: XCTestCase {

    var networkAdapter: NetworkAdapter?
    
    override func setUp() {
        networkAdapter = NetworkAdapter(state: .test)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    /// Tests if the networkAdapter can handle the query and transform it in an APIResponse
    func testNetworkLayerAPIResponse() {
        var expectations: [XCTestExpectation] = []
        let expectation = XCTestExpectation(description: "Get api response \(page)")
        expectations.append(expectation)
        networkAdapter?.getAPIRespose(query: "buzios", page: page, completion: { (result) in
            switch result {
            case .success(let apiResponse):
                os_log(.info, "Data received from the api successfully")
                dump(apiResponse)
                expectations[page-1].fulfill()
            case .failure(let error):
                os_log(.error, "Error receiving Data from the API")
                dump(error)
            }
        })
        wait(for: expectations, timeout: 10.0)
    }
}
