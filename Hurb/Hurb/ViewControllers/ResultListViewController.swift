//
//  ResultListViewController.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit
import RxSwift
import Lottie

//Enum para definir as seções,
enum TableSection: Int {
    case CincoEstrelas = 0, QuatroEstrelas = 1, TresEstrelas = 2 , DuasEstrelas = 3, UmaEstrela = 4, ZeroEstrelas = 5, Pacotes = 6
}

protocol ResultListDelegate {
    func updateResultList(newPlace: SuggestionViewModel)
}

class ResultListViewController: UIViewController {

    //MARK: - Properties
    private let resultCell = "resultCell"
    private let suggestionCell = "suggestionCell"
    private let disposeBag = DisposeBag()
    
    //O Default Place está como Rio de Janeiro. No desafio não estava claro se o lugar default era Rio de Janeiro ou Búzios. E no exemplo ainda está usando a cidade de Gramado.
    private var searchText: String = Defines.DEFAULT_PLACE
    
    private var resultListViewModel: ResultListViewModel?
    private var results: [TableSection: [Hotel]] = [:]
    
    
    fileprivate var animationView: AnimationView?
    
    private var suggestions: [Suggestion] = []
    
    //MARK: - Outlets
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var loadingView: UIView!
    @IBOutlet weak var animationLoadingView: UIView!
    @IBOutlet weak var noResultsView: UIView!
    @IBOutlet weak var resultsView: UIView!
    @IBOutlet weak var noInternetConnectionView: UIView!
    
    
    //MARK: - IB Actions
    //Verific
    @IBAction func reconnect(_ sender: UIButton) {
        resultListViewModel = ResultListViewModel(place: Defines.DEFAULT_PLACE)
        loading()
    }
    
    //MARK: - ViewController life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.tableFooterView = UIView(frame: .zero)
        self.navigationItem.title = "Busca: \(searchText)"
        
        resultListViewModel = ResultListViewModel(place: searchText)
        loading()
        
    }
    
    //MARK: - Functions
    func loading() {
        
        //verifica se existe conectividade com a internet
        if !Reachability.isConnectedToNetwork(){
            print("Internet Connection not Available!")
            loadingView.isHidden = true
            noResultsView.isHidden = true
            noInternetConnectionView.isHidden = false
            FirebaseAnalyticsHelper.isNotConnectedEventLogger()
        }else{
            print("Internet Connection Available!")
            loadingView.isHidden = false
            noInternetConnectionView.isHidden = true
            
            animationView = AnimationView(name: "aroundTheWorld")
            animationView!.frame = CGRect(x: 0, y: 0, width: animationLoadingView.frame.size.width, height: animationLoadingView.frame.size.height)
            animationView!.contentMode = .scaleAspectFit
            animationView!.loopMode = .loop
            animationLoadingView.addSubview(animationView!)
            animationView!.play()
            
            NotificationCenter.default.addObserver(self, selector: #selector(continueAnimation), name: UIApplication.willEnterForegroundNotification, object: nil)
            
            setupSearchHotelsViewModelObserver()
        }
    }
    
    @objc func continueAnimation() {
        animationView!.play()
    }
    
    //MARK: - Rx Setup
    private func setupSearchHotelsViewModelObserver() {
        if Reachability.isConnectedToNetwork(){
            resultListViewModel?.hotelsObservable
                .subscribe(onNext: { hotels in
                    
                    self.results[.CincoEstrelas] = hotels.filter({$0.stars == 5})
                    self.results[.QuatroEstrelas] = hotels.filter({$0.stars == 4})
                    self.results[.TresEstrelas] = hotels.filter({$0.stars == 3})
                    self.results[.DuasEstrelas] = hotels.filter({$0.stars == 2})
                    self.results[.UmaEstrela] = hotels.filter({$0.stars == 1})
                    self.results[.ZeroEstrelas] = hotels.filter({$0.stars == 0})
                    self.results[.Pacotes] = hotels.filter({$0.stars == nil})
                    
                    print(hotels.count)
                    self.tableView.reloadData()
                    
                    if hotels.count > 0 {
                        self.loadingView.isHidden = true
                    } else {
                        self.loadingView.isHidden = false
                    }
                    
                    if self.resultListViewModel?.count == 0 {
                        self.noResultsView.isHidden = false
                    } else {
                        self.noResultsView.isHidden = true
                    }
                })
                .disposed(by: disposeBag)
        } else {
            self.noResultsView.isHidden = false
            FirebaseAnalyticsHelper.isNotConnectedEventLogger()
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetails" {
            if let vc = segue.destination as? HotelDetailViewController {
                if let hotelViewModel = sender as? HotelViewModel {
                    vc.hotelViewModel = hotelViewModel
                }
            }
        }
        
        if segue.identifier == "showSearchPlace"{
            if let vc = segue.destination as? SuggestionsViewController {
                vc.resultListDelegate = self
            }
        }
    }
    
}

extension ResultListViewController: ResultListDelegate {
    
    //Atualizar página com novo local escolhido
    func updateResultList(newPlace: SuggestionViewModel) {
        
        self.loadingView.isHidden = false
        self.animationView?.play()
        
        self.navigationItem.title = "Busca: \(newPlace.name)"
        self.results = [:]
        self.resultListViewModel?.removeAll()
        resultListViewModel = ResultListViewModel(place: newPlace.name)
        self.setupSearchHotelsViewModelObserver()
    }
}

extension ResultListViewController: UITableViewDelegate, UITableViewDataSource {
    //MARK: - UITableView extension
    func numberOfSections(in tableView: UITableView) -> Int {
        return 7 // De 0 a 5 estrelas e Pacotes
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        //Buscar título do Header de acordo com o número de estrelas.
        if let tableSection = TableSection(rawValue: section), let hotelData = results[tableSection] {
            return hotelData.count
        }
        
        return 0
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return Defines.LIST_HEADER[section]
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        // If we wanted to always show a section header regardless of whether or not there were rows in it,
        // then uncomment this line below:
        //return SectionHeaderHeight
        // First check if there is a valid section of table.
        // Then we check that for the section there is more than 1 row.
        if let tableSection = TableSection(rawValue: section), let hotelData = results[tableSection], hotelData.count > 0 {
            return 25
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
    
        let cell = tableView.dequeueReusableCell(withIdentifier: resultCell, for: indexPath) as! ResultCell
        
        var rowNumber = indexPath.row
        for i in 0..<indexPath.section {
            rowNumber += self.tableView.numberOfRows(inSection: i)
        }
        
        _ = resultListViewModel?[rowNumber]
        
        if let tableSection = TableSection(rawValue: indexPath.section), let hotel = results[tableSection]?[indexPath.row] {
            cell.hotel = HotelViewModel(hotel)
        }
        
        return cell
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    
        if let tableSection = TableSection(rawValue: indexPath.section), let hotel = results[tableSection]?[indexPath.row] {
            performSegue(withIdentifier: "showDetails", sender: HotelViewModel(hotel))
        }
    
    }
    
}

//MARK: - UIScrollView extension
extension ResultListViewController: UISearchControllerDelegate, UISearchBarDelegate {
    
    //Ao clicar no SearchBar, redirecionar para a View SuggestionsViewController
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        self.performSegue(withIdentifier: "showSearchPlace", sender: nil)
        self.searchBar.resignFirstResponder()
        self.dismissKeyboard()
    }
}
