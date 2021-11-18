import XCTest
import SnapshotTesting
@testable import AppHotel

class ResultCellTests: XCTestCase {
    var sut: ResultCell!
    
    override func setUp() {
        sut = ResultCell()
    }

    override func tearDown() {
        sut = nil
    }

    func test_setupUI_addsAllViews() {
        let subviews = sut.contentView.subviews
        XCTAssert(subviews.contains(sut.cardView))

        let cardSubviews = sut.cardView.subviews
        XCTAssert(cardSubviews.contains(sut.cardContentView))

        let contentSubviews = sut.cardContentView.subviews
        XCTAssert(contentSubviews.contains(sut.infoView))
        XCTAssert(contentSubviews.contains(sut.imgPhoto))
        
        let infoSubviews = sut.infoView.subviews
        XCTAssert(infoSubviews.contains(sut.imgType))
        XCTAssert(infoSubviews.contains(sut.imgPrice))
        XCTAssert(infoSubviews.contains(sut.lblType))
        XCTAssert(infoSubviews.contains(sut.lblPrice))
        XCTAssert(infoSubviews.contains(sut.lblAddress))
        XCTAssert(infoSubviews.contains(sut.lblName))
    }
    
    func test_configure_setsRightValues() {
        let result = SearchResult(
            name: "Hotel teste",
            description: "descrição",
            cover: "",
            price: "R$ 100,00",
            address: "Rua de teste",
            category: .hotel,
            gallery: [])
        
        sut.configure(result: result)

        let tableView = MockTableViewController(cell: sut)
        assertSnapshot(matching: tableView, as: .image(on: .iPhone8))
    }
}
