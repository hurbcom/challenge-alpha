@@testable import iTravel_iOS_Challenge

final class TravelServiceSpy:TravelService(){
    
    var searchPackageCount:Int = 0
    var searchPackageCalled:Bool = false
    var searchPackageDataToBeReturned:Result<HUGraphQL.SearchPackageQuery.Data, Error>
    override func searchPackage(local:String = "Rio de Janeiro", completion: @escaping (Result<HUGraphQL.SearchPackageQuery.Data, Error>) -> Void){
        searchPackageCount += 1
        searchPackageCalled = true
        completion(searchPackageDataToBeReturned)
    }
}
