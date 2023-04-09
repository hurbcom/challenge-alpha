//
//  HomeViewController.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import UIKit
import RxSwift
import RxRelay
import RxCocoa
import ImageSlideshow


class HomeViewController: UIViewController, UIScrollViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    var viewmodel:HomeViewModel = HomeViewModel()
    private let bag = DisposeBag()
    var packages:Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "HomeTableViewCell")
        setupUI()
        setupTableView()
        
    }

    
    
}

extension HomeViewController {
    
    func setupUI(){
        navigationItem.title = "iTravel"
        navigationController?.navigationBar.prefersLargeTitles = true
        
        let coloredAppearance = UINavigationBarAppearance()
        coloredAppearance.configureWithTransparentBackground()
        coloredAppearance.backgroundColor = UIColor(named: "AccentColor") // The key is here. Change the actual bar to clear.
        coloredAppearance.titleTextAttributes = [NSAttributedString.Key.foregroundColor:UIColor(named: "TextColor") ?? .black]
        coloredAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor(named: "TextColor") ?? .white]
        coloredAppearance.shadowColor = .clear
        
        UINavigationBar.appearance().standardAppearance = coloredAppearance
        UINavigationBar.appearance().compactAppearance = coloredAppearance
        UINavigationBar.appearance().scrollEdgeAppearance = coloredAppearance
        UINavigationBar.appearance().tintColor = UIColor(named: "TextColor")
        

    }
    

}


extension HomeViewController:UITableViewDelegate{
    
    func setupTableView() {
        tableView.rx.setDelegate(self).disposed(by: bag)
        let homeViewCell = UINib(nibName: "HomeTableViewCell",
                                      bundle: nil)
        self.tableView.register(homeViewCell,
                                    forCellReuseIdentifier: "HomeTableViewCell")
        bindTableView()
    }
    
    
    
    private func bindTableView() {
        tableView.register(UINib(nibName: "HomeTableViewCell", bundle: nil), forCellReuseIdentifier: "HomeTableViewCell")
        
        viewmodel.packagesSubject.bind(to: tableView.rx.items(cellIdentifier: "HomeTableViewCell", cellType: HomeTableViewCell.self)) { (row,item,cell) in
            // setup price
            let formatter = NumberFormatter()
            formatter.numberStyle = .decimal
            formatter.locale = Locale(identifier: "pt_BR") // or any other locale that uses comma as decimal separator

            let number: Double = Double(item.price.originalAmount ?? 000000)
            let formattedString = formatter.string(from: NSNumber(value: number/100)) ?? ""

            cell.preco.text = "R$ " + formattedString + ",00"
            
            var imgArray:[AlamofireSource] = []
            
            for img in item.gallery {
                if let alamoImg = AlamofireSource(urlString: img.url){
                    imgArray.append(alamoImg)
                }
            }
    
            cell.setupUI()
            cell.slideShow.setImageInputs(imgArray)

            
            //
            print(item)
            cell.titulo.text = item.name
            cell.local.text = item.address.city
            cell.diferencial.text = item.amenities.first?.name
            cell.data.text = "\(item.quantityDescriptors.duration) dias"
        }.disposed(by: bag)
        
        
        
        tableView.rx.modelSelected(PackageResult.self).subscribe(onNext: { item in
            print("SelectedItem: \(item.name)")
        }).disposed(by: bag)
        
        viewmodel.fetchPackages()
    }
   
    

    
    

    
    
    
}
