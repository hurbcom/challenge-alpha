//
//  Services.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import Foundation
import HUGraphQL

class Services {
    let service = HUGService(enableLog: true)
    static let servicesNetwork = Services()
    func performSearchFetch(query:String, pagination:HUGraphQL.SearchInputPagination? = nil, completion:@escaping([HUGraphQL.SearchQuery.Data.Search.Result])->()){
        let q = HUGraphQL.SearchQuery(q: query, pagination: pagination)
        service.client.fetch(query: q ){ [self]  result in
            switch result {
            case .failure(let error):
                print(error.localizedDescription)
                print("DEU TUDO ERRADO")
            case .success(let Graphresult):
//                FUNCIONOU
                if let result = Graphresult.data?.search?.results {
//                    let jsonData = try? JSONSerialization.data(withJSONObject: Graphresult.data?.search?.jsonObject)
//                    let teste  = try? JSONDecoder().decode(ModelInfoList.self, from: jsonData!)
//                    if let array = teste {
//                        completion(array.info)
//                        print("Passou aqui")
//                        print(array.info)
//                    } else {print("NÃO PASSOU")}
//                }
//                if let result = Graphresult.data?.search?.results {

                    completion(result)
                    print("PASSOU")
                } else {print("NÃO PASSOU")}
//                if let results = Graphresult.data?.search?.results {
//                    self.resuModel = results
//                    for i in results {
//                        print("nome:" + i.name)
//                        print("descrição:\n" + i.smallDescription)
//                        print("categoria \(i.category)")
//                        for j in i.gallery {
//                            print("imagem: \(j)")
//                        }
//                        print("\n====== // ===== // ======== \n")
//                    }
//
//                }

                // using search
//                search = searchModel(search: (Graphresult.data)!, results: (Graphresult.data?.search?.results)!)
//                guard let search = search else {return}
//                for i in search.results {
//                    print("nome:" + i.name)
//                    print("descrição:\n" + i.description)
//                    print("\n====== // ===== // ======== \n")
//                }
                
            }
            
        }
    }
}
