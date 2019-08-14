//
//  SuggestionsViewController.swift
//  Hurb
//
//  Created by Alexandre Papanis on 12/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit
import Lottie

class SuggestionsViewController: UIViewController {

    //MARK: - Properties
    fileprivate let suggestionCell = "suggestionCell"
    fileprivate var animationView: AnimationView?
    fileprivate var timer: Timer?
    
    var resultListDelegate: ResultListDelegate!
    
    //O Default Place está como Rio de Janeiro. No desafio não estava claro se o lugar default era Rio de Janeiro ou Búzios. E no exemplo ainda está usando a cidade de Gramado.
    fileprivate var searchText: String = Defines.DEFAULT_PLACE
    fileprivate var suggestions: [Suggestion] = []
    
    //MARK: - Outlets
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var noResultsView: UIView!
    @IBOutlet weak var noInternetConnectionView: UIView!
    @IBOutlet weak var loadingView: UIView!
    
    @IBOutlet weak var animationLoadingView: UIView!
    
    
    @IBAction func reconnect(_ sender: UIButton) {
        searchPlace() 
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //esconder as views de Sem Resultados e Sem Conexão com a Internet.
        self.noResultsView.isHidden = true
        self.noInternetConnectionView.isHidden = true
        
        //carregar a animação do loading
        animationView = AnimationView(name: "aroundTheWorld")
        animationView!.frame = CGRect(x: 0, y: 0, width: animationLoadingView.frame.size.width, height: animationLoadingView.frame.size.height)
        animationView!.contentMode = .scaleAspectFit
        animationView!.loopMode = .loop
        animationLoadingView.addSubview(animationView!)
        animationView!.play()
        
        //Colocar o focus no searchBar e abrir o teclado
        self.searchBar.becomeFirstResponder()
        
        //Remover as linhas vazias da tableView
        self.tableView.tableFooterView = UIView(frame: .zero)
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
}

//MARK: UISearch extensions
extension SuggestionsViewController: UISearchControllerDelegate, UISearchBarDelegate {
 
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        self.loadingView.isHidden = true
        self.suggestions = []
        self.tableView.reloadData()
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        timer?.invalidate()
        
        self.searchText = searchText
        
        if self.searchText.count >= 3 {
            timer = Timer.scheduledTimer(timeInterval: 0.5, target: self, selector: #selector(getSuggestions), userInfo: nil, repeats: false)
        }
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchPlace()
    }
    
    func searchPlace() {
        self.dismissKeyboard()
        
        //fazer consulta com pelo menos 3 caracteres, senão exibe um alertView
        if self.searchText.count >= 3 {
            getSuggestions()
        } else {
            self.present(showAlert(mensagem: "Por favor, digite ao menos 3 caracteres"), animated: true, completion: nil)
        }
    }
    
    @objc func getSuggestions(){
        if !Reachability.isConnectedToNetwork(){
            print("Internet Connection not Available!")
            loadingView.isHidden = true
            noResultsView.isHidden = true
            noInternetConnectionView.isHidden = false
            FirebaseAnalyticsHelper.isNotConnectedEventLogger()
        }else{
            print("Internet Connection Available!")
            //exibir a tela de loading
            loadingView.isHidden = false
            noInternetConnectionView.isHidden = true
            
            //Continuar a animação depois que o app voltar para o Foreground
            NotificationCenter.default.addObserver(self, selector: #selector(continueAnimation), name: UIApplication.willEnterForegroundNotification, object: nil)
            
            
            searchBar.text = searchText
            
            //Fazer busca de sugestões a partir do que foi digitado no SearechBar
            APIClient.searchSuggestions(by: self.searchText, completion: { [unowned self] result in
                
                self.loadingView.isHidden = true
                switch result {
                case .success(let suggestionResults):
                    //se for sucesso, pegar a lista de sugestoes e atualizar tabela
                    self.suggestions = suggestionResults.suggestions ?? []
                    if suggestionResults.suggestions?.count == 0 {
                        self.noResultsView.isHidden = false
                    } else {
                        self.noResultsView.isHidden = true
                        self.tableView.reloadData()
                    }
                    
                case .failure(let error):
                    print(error.localizedDescription)
                }
            })
        }
    }
    
    @objc func continueAnimation() {
        animationView!.play()
    }
}

extension SuggestionsViewController: UITableViewDelegate, UITableViewDataSource {
    
    //MARK: - UITableView extension
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return suggestions.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: suggestionCell, for: indexPath)
        
        let suggestionViewModel = SuggestionViewModel(suggestions[indexPath.row])
        
        cell.textLabel!.text = suggestionViewModel.name
        cell.detailTextLabel!.text = suggestionViewModel.type
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        //ao selecionar uma sugestão, pegar o SuggestionViewModel e passar para o delegate (ResultListDelegate) da ViewController anterior (ResultViewController) atualizar a tableView e remover a viewcontroller da stack de navegação.
        let suggestionViewModel = SuggestionViewModel(suggestions[indexPath.row])
        resultListDelegate.updateResultList(newPlace: suggestionViewModel)
        
        FirebaseAnalyticsHelper.suggestionChosenEventoLoger(place: suggestionViewModel.name)
        self.navigationController?.popViewController(animated: true)
    }
    
    func scrollViewWillBeginDragging(_ scrollView: UIScrollView) {
        dismissKeyboard()
    }
}
