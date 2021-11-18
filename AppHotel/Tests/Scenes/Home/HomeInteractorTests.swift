import XCTest
@testable import AppHotel

class HomeInteractorTests: XCTestCase {
    var sut: HomeInteractor!
    var output: HomeInteractorOutputSpy!
    var worker: MockSearch!

    override func setUp() {
        sut = HomeInteractor()
        
        worker = MockSearch()
        sut.repository = worker

        output = HomeInteractorOutputSpy()
        sut.output = output
    }
    
    func test_fetchResults_callsRepository_withRightTerm() {
        sut.fetchResults(term: "Termo de teste")
        
        XCTAssertEqual(worker.$invokedSearch.value?.term, "Termo de teste")
        XCTAssertEqual(sut.currentTerm, "Termo de teste")
        XCTAssertEqual(sut.currentPagination.current, 1)
    }
    
    func test_fetchResults_callsPresenteResults_onSuccess() {
        worker.shouldFail = false
        worker.mockResponse = []
        
        sut.fetchResults(term: "Termo de teste")

        XCTAssertEqual(output.$invokedPresentResults.value?.count, 0)
        XCTAssertNil(output.$invokedPresentError.value)
    }
    
    func test_fetchResults_callsPresenteError_onFail() {
        let error = NetworkError.decodingError
        worker.shouldFail = true
        worker.mockError = error
        
        sut.fetchResults(term: "Termo de teste")

        XCTAssertNil(output.$invokedPresentResults.value)
        XCTAssertEqual(output.$invokedPresentError.value?.message, error.message)
    }
}
