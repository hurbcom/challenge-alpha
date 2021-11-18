import XCTest
import SnapshotTesting
@testable import AppHotel

class DetailsVCTests: XCTestCase {
    var sut: DetailsVC!
    let result = SearchResult(
        name: "Hotel teste",
        description: "descrição",
        cover: "",
        price: "R$ 100,00",
        address: "Rua de teste",
        category: .hotel,
        gallery: [])

    override func setUp() {
        sut = DetailsVC(result: result)
    }

    override func tearDown() {
        sut = nil
    }

    func test_setupUI_addsAllViews() {
        let subviews = sut.view.subviews
        XCTAssert(subviews.contains(sut.tableView))
    }

    func test_configure_setsRightValues() {
        XCTAssertEqual(sut.title, result.name)
        assertSnapshot(matching: sut, as: .image(on: .iPhone8))
    }
}
