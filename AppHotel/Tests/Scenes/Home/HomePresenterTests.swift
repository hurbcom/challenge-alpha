import XCTest
@testable import AppHotel

class HomePresenterTests: XCTestCase {
    var sut: HomePresenter!
    var output: HomePresenterOutputSpy!

    override func setUp() {
        sut = HomePresenter()
        output = HomePresenterOutputSpy()
        sut.output = output
    }
    
    func test_formatResults_callsShowReposOutput() {
        let results = [
            SearchResult(
                name: "Hotel teste",
                description: "descrição",
                cover: "",
                price: "R$ 100,00",
                address: "Rua de teste",
                category: .hotel,
                gallery: [])]
        
        sut.formatResults(results)

        XCTAssertEqual(output.$invokedShowResults.value?.count, 1)
        XCTAssertEqual(output.$invokedShowResults.value?[0].name, results[0].name)
        XCTAssertNil(output.$invokedShowAlert.value)
    }
    
    func test_formatError_callsShowAlertOutput() {
        let error = NetworkError.emptyData
        sut.formatError(error)

        XCTAssertEqual(output.$invokedShowAlert.value?.title, "Ops!")
        XCTAssertEqual(output.$invokedShowAlert.value?.message, error.message)
    }
}
