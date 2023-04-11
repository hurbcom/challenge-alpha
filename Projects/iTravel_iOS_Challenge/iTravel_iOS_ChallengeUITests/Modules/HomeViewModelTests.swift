import XCTest

@testable import iTravel_iOS_Challenge

final class HomeViewModelTests: XCTestCase {
    private let serviceSpy = TravelServiceSpy()
    private lazy var sut = HomeViewModel(service: serviceSpy)
    
    
    
    func test_fetchPackage_should_call_service_searchPackage(){
        sut.fetchPackages()
        
        XCTAssertTrue(serviceSpy.searchPackageCalled)
    }
    
}
