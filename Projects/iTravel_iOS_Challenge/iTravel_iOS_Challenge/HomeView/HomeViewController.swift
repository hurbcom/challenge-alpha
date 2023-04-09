//
//  HomeViewController.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import UIKit
import RxSwift
import RxRelay

class HomeViewController: UIViewController {
    
    @IBOutlet weak var count: UILabel!
    var viewmodel:HomeViewModel = HomeViewModel()
    var packages:Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        setupPackageObserver()
        setupUI()
        
    }

    @IBAction func testApi(_ sender: Any) {
        viewmodel.testApi()
    }
    
    
}

extension HomeViewController {
    
    func setupUI(){
        navigationItem.title = "iTravel"
        navigationController?.navigationBar.prefersLargeTitles = true
        
        let coloredAppearance = UINavigationBarAppearance()
        coloredAppearance.configureWithTransparentBackground()
        coloredAppearance.backgroundColor = .clear // The key is here. Change the actual bar to clear.
        coloredAppearance.titleTextAttributes = [NSAttributedString.Key.foregroundColor:UIColor(named: "TextColor") ?? .black]
        coloredAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor(named: "TextColor") ?? .white]
        coloredAppearance.shadowColor = .clear
        
        UINavigationBar.appearance().standardAppearance = coloredAppearance
        UINavigationBar.appearance().compactAppearance = coloredAppearance
        UINavigationBar.appearance().scrollEdgeAppearance = coloredAppearance
        UINavigationBar.appearance().tintColor = UIColor(named: "TextColor")
        

    }
    
    func setupPackageObserver(){
        viewmodel.packages.subscribe { [self] result in
            self.packages += 1
            self.count.text = "\(packages)"
        }
    }
}
