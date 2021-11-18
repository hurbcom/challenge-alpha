import XCTest
@testable import AppHotel

class ConstraintsTests: XCTestCase {
    var view1: UIView!
    var view2: UIView!
    var container: UIView!

    override func setUp() {
        view1 = UIView()
        view2 = UIView()
        container = UIView()

        view1.setCodable()
        view2.setCodable()
        container.setCodable()

        container.addSubview(view1)
        container.addSubview(view2)
    }

    func test_setCodable_changesFlag() {
        let testView = UIView()

        XCTAssertEqual(testView.translatesAutoresizingMaskIntoConstraints, true)

        testView.setCodable()

        XCTAssertEqual(testView.translatesAutoresizingMaskIntoConstraints, false)
    }

    func test_setHeight_addHeightConstraint() {
        view1.setHeight(10)

        XCTAssertEqual(view1.constraints.first?.constant, 10)
        XCTAssertEqual(view1.constraints.first?.isActive, true)
        XCTAssertEqual(view1.constraints.first?.firstAnchor, view1.heightAnchor)
    }

    func test_setWidth_addWidthConstraint() {
        view1.setWidth(10)

        XCTAssertEqual(view1.constraints.first?.constant, 10)
        XCTAssertEqual(view1.constraints.first?.isActive, true)
        XCTAssertEqual(view1.constraints.first?.firstAnchor, view1.widthAnchor)
    }

    func test_setSize_addsWidthAndHeightConstraints() {
        view1.setSize(height: 11, width: 12)

        let width = view1.constraints.first { (constraint) -> Bool in
            return constraint.firstAnchor == view1.widthAnchor
        }
        let height = view1.constraints.first { (constraint) -> Bool in
            return constraint.firstAnchor == view1.heightAnchor
        }
        XCTAssertEqual(view1.constraints.count, 2)
        XCTAssertEqual(width?.constant, 12)
        XCTAssertEqual(width?.isActive, true)
        XCTAssertEqual(width?.firstAnchor, view1.widthAnchor)

        XCTAssertEqual(height?.constant, 11)
        XCTAssertEqual(height?.isActive, true)
        XCTAssertEqual(height?.firstAnchor, view1.heightAnchor)
    }
}
