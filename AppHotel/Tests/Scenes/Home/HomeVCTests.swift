import XCTest
@testable import AppHotel

class HomeVCTests: XCTestCase {
    var sut: HomeVC!
    var output: HomeVCOutputSpy!
    
    override func setUp() {
        sut = HomeVC()
        output = HomeVCOutputSpy()
        
        sut.output = output
    }

    override func tearDown() {
        sut = nil
    }

    func test_searchTerm_onSet_callsOutput() {
        sut.searchTerm = "Novo Termo"
        
        XCTAssertEqual(sut.title, "Novo Termo")
        XCTAssertEqual(output.$invokedAskForSearch.value, "Novo Termo")
    }
    
    func test_displayResults_updatesList() {
        let result1 = SearchResult(
            name: "Hotel teste",
            description: "descrição",
            cover: "",
            price: "R$ 100,00",
            address: "Rua de teste",
            category: .hotel,
            gallery: [])

        sut.items = [result1]

        let result2 = SearchResult(
            name: "Hotel teste 2",
            description: "descrição diferente",
            cover: "",
            price: "R$ 120,00",
            address: "Rua de homologação",
            category: .package,
            gallery: [])

        sut.displayResults([result2])

        XCTAssertEqual(sut.items.count, 1)
        XCTAssertEqual(sut.items[0].name, result2.name)
    }
}
