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

enum TableSection: Int {
    case CincoEstrelas = 0, QuatroEstrelas = 1, TresEstrelas = 2 , DuasEstrelas = 3, UmaEstrela = 4, ZeroEstrelas = 5, Pacotes = 6
}

class ResultListViewController: UIViewController {

    //MARK: - Properties
    private let resultCell = "resultCell"
    private let suggestionCell = "suggestionCell"
    private let disposeBag = DisposeBag()
    private var searchText: String = Defines.DEFAULT_PLACE
    private var resultListViewModel = ResultListViewModel(place: Defines.DEFAULT_PLACE)
    private var results: [TableSection: [Hotel]] = [:]
    private var lastContentOffset: CGFloat = 0
    fileprivate let heightSectionHeader: CGFloat = 25
    fileprivate var animationView: AnimationView?
    
    private var suggestions: [Suggestion] = []
    
    //MARK: - Outlets
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableViewResults: UITableView!
    @IBOutlet weak var tableViewSuggestions: UITableView!
    
    @IBOutlet weak var widthCancelSearchButton: NSLayoutConstraint!
   
    @IBOutlet weak var loadingView: UIView!
    @IBOutlet weak var animationLoadingView: UIView!
    @IBOutlet weak var searchResultsView: UIView!
    @IBOutlet weak var noResultsView: UIView!
    
    @IBAction func cancelarBusca(_ sender: UIButton) {
        self.suggestions = []
        self.searchResultsView.isHidden = true
        self.searchBar.text = ""
        self.widthCancelSearchButton.constant = 0
    }
    
    //MARK: - ViewController life cycle
    override func viewDidLoad() {
        super.viewDidLoad()

        widthCancelSearchButton.constant = 0
        loading()
        
        self.tableViewResults.tableFooterView = UIView(frame: .zero)
        self.tableViewSuggestions.tableFooterView = UIView(frame: .zero)
        
        self.searchResultsView.isHidden = true
        self.navigationItem.title = "Busca: \(searchText)"
        setupSearchHotelsViewModelObserver()
        
    }
    
    func loading() {
        loadingView.isHidden = false
        animationView = AnimationView(name: "aroundTheWorld")
        animationView!.frame = CGRect(x: 0, y: 0, width: animationLoadingView.frame.size.width, height: animationLoadingView.frame.size.height)
        animationView!.contentMode = .scaleAspectFit
        animationView!.loopMode = .loop
        animationLoadingView.addSubview(animationView!)
        animationView!.play()
        
        NotificationCenter.default.addObserver(self, selector: #selector(continueAnimation), name: UIApplication.willEnterForegroundNotification, object: nil)
        
    }
    
    @objc func continueAnimation() {
        animationView!.play()
    }
    
    //MARK: - Rx Setup
    private func setupSearchHotelsViewModelObserver() {
        if Connectivity.isConnectedToInternet() {
            resultListViewModel.hotelsObservable
                .subscribe(onNext: { hotels in
                    
                    self.results[.CincoEstrelas] = hotels.filter({$0.stars == 5})
                    self.results[.QuatroEstrelas] = hotels.filter({$0.stars == 4})
                    self.results[.TresEstrelas] = hotels.filter({$0.stars == 3})
                    self.results[.DuasEstrelas] = hotels.filter({$0.stars == 2})
                    self.results[.UmaEstrela] = hotels.filter({$0.stars == 1})
                    self.results[.ZeroEstrelas] = hotels.filter({$0.stars == 0})
                    self.results[.Pacotes] = hotels.filter({$0.stars == nil})
                    
                    self.tableViewResults.reloadData()
//                    self.tableView.unlock()
                    
                    self.loadingView.isHidden = true
                    self.animationView!.stop()
                    
                    if self.resultListViewModel.count == 0 {
                        self.noResultsView.isHidden = false
                    } else {
                        self.noResultsView.isHidden = true
                    }
                })
                .disposed(by: disposeBag)
        } else {
//            self.noResultsView.isHidden = false
//            self.errorLabel.text = "No internet connection detected. Please, check it and try again."
        }
    }
    
    //MARK: - Functions
    @objc func dismissKeyboard() {
        searchBar.text = ""
        view.endEditing(true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetails" {
            if let vc = segue.destination as? HotelDetailViewController {
                if let hotelViewModel = sender as? HotelViewModel {
                    vc.hotelViewModel = hotelViewModel
                }
            }
        }
    }
    
}

extension ResultListViewController: UITableViewDelegate, UITableViewDataSource {
    //MARK: - UITableView extension
    func numberOfSections(in tableView: UITableView) -> Int {
        if tableView == tableViewResults {
            return 7 // De 0 a 5 estrelas e Pacotes
        }
        
        if tableView == tableViewSuggestions {
            return 1
        }
        
        return 0
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if tableView == tableViewResults {
            if let tableSection = TableSection(rawValue: section), let hotelData = results[tableSection] {
                return hotelData.count
            }
        }
        
        if tableView == tableViewSuggestions {
            return suggestions.count
        }
        
        return 0
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if tableView == tableViewResults {
            return Defines.LIST_HEADER[section]
        }
        
        return nil
        
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        // If we wanted to always show a section header regardless of whether or not there were rows in it,
        // then uncomment this line below:
        //return SectionHeaderHeight
        // First check if there is a valid section of table.
        // Then we check that for the section there is more than 1 row.
        if tableView == tableViewResults {
            if let tableSection = TableSection(rawValue: section), let hotelData = results[tableSection], hotelData.count > 0 {
                return heightSectionHeader
            }
        }
        
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if tableView == tableViewResults {
            let cell = tableView.dequeueReusableCell(withIdentifier: resultCell, for: indexPath) as! ResultCell
            
            var rowNumber = indexPath.row
            for i in 0..<indexPath.section {
                rowNumber += self.tableViewResults.numberOfRows(inSection: i)
            }
            print(rowNumber)
            _ = resultListViewModel[rowNumber]
            
            if let tableSection = TableSection(rawValue: indexPath.section), let hotel = results[tableSection]?[indexPath.row] {
                cell.hotel = HotelViewModel(hotel)
            }
            
            return cell
        }
        
        if tableView == tableViewSuggestions {
            let cell = tableView.dequeueReusableCell(withIdentifier: suggestionCell)!
            
            let suggestionViewModel = SuggestionViewModel(suggestions[indexPath.row])
            
            cell.textLabel!.text = suggestionViewModel.name
            cell.detailTextLabel!.text = suggestionViewModel.type
            
            return cell
        }
        
        return UITableViewCell()
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        if tableView == tableViewResults {
            if let tableSection = TableSection(rawValue: indexPath.section), let hotel = results[tableSection]?[indexPath.row] {
                performSegue(withIdentifier: "showDetails", sender: HotelViewModel(hotel))
            }
        }
        
        if tableView == tableViewSuggestions {
            self.searchResultsView.isHidden = true
            
            self.widthCancelSearchButton.constant = 0
            
            let suggestionViewModel = SuggestionViewModel(suggestions[indexPath.row])
            
            self.navigationItem.title = "Busca: \(suggestionViewModel.name)"
            self.results = [:]
            self.resultListViewModel.removeAll()
            resultListViewModel = ResultListViewModel(place: suggestionViewModel.name)
            self.setupSearchHotelsViewModelObserver()
            
        }
    }
    
}

//MARK: - UIScrollView extension
extension ResultListViewController: UIScrollViewDelegate {
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        dismissKeyboard()
        
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height
        
        // Exibir/ocultar a barra de navegação enquanto usa o scroll
        if (self.lastContentOffset > scrollView.contentOffset.y) {
            navigationController?.setNavigationBarHidden(false, animated: true)
        }
        else if (self.lastContentOffset < scrollView.contentOffset.y && self.lastContentOffset > 0) {
            navigationController?.setNavigationBarHidden(true, animated: true)
        }
        
        // Chegando ao final do conteúdo, carregar próxima página
        if offsetY > contentHeight - scrollView.frame.height * 1.1 {
//            if service.hasMorePages {
//                loadMorePages()
//            }
        }
        
    }
}

extension ResultListViewController: UISearchControllerDelegate, UISearchBarDelegate {
    
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        self.widthCancelSearchButton.constant = 70
        
        self.searchResultsView.isHidden = false
        self.suggestions = []
        self.tableViewSuggestions.reloadData()
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        self.searchText = searchText
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        if self.searchText.count >= 3 {
            self.dismissKeyboard()
            
            searchBar.text = searchText
            
            APIClient.searchSuggestions(by: self.searchText, completion: { [unowned self] result in
                switch result {
                case .success(let suggestionResults):
                    self.suggestions = suggestionResults.suggestions ?? []
                    self.tableViewSuggestions.reloadData()
                case .failure(let error):
                    print(error.localizedDescription)
                }
            })
            
        } else {
            self.present(showAlert(mensagem: "Por favor, digite ao menos 3 caracteres"), animated: true, completion: nil)
        }
    }
}
