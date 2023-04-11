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
    
    @IBOutlet weak var searchBarBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var searchButton: UIButton!
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var textFieldVIew: UIView!
    @IBOutlet weak var tableView: UITableView!
    var viewmodel:HomeViewModel = HomeViewModel(service: TravelService())
    private let bag = DisposeBag()
    var packages:Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "HomeTableViewCell")
        setupUI()
        setupTableView()
        setupSearchUI()
        addKeyboardNotifications()
        setupErrorHandler()
    }
    
  

    @IBAction func searchTap(_ sender: Any) {
        if let search = textField.text{
            viewmodel.fetchPackages(search: search)
        }
    }
    
    
}

//Setups iniciais da tela

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
    
    func setupSearchUI(){
        textFieldVIew.layer.cornerRadius = 14
        textFieldVIew.layer.masksToBounds = true
        textField.layer.cornerRadius = 14
        textField.layer.masksToBounds = true
        textField.layer.shadowRadius = 4
        textFieldVIew.dropShadow()
    }
    
    func addKeyboardNotifications(){
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(self.keyboardWillShow),
            name: UIResponder.keyboardWillShowNotification,
            object: nil)

        NotificationCenter.default.addObserver(
            self,
            selector: #selector(self.keyboardWillHide),
            name: UIResponder.keyboardWillHideNotification,
            object: nil)
    }
    
    @objc func keyboardWillShow(_ notification: NSNotification) {
        // Move the view only when the usernameTextField or the passwordTextField are being edited
        textField.returnKeyType = .search
        if textField.isEditing{
            moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.searchBarBottomConstraint, keyboardWillShow: true)
        }
    }
    
    @objc func keyboardWillHide(_ notification: NSNotification) {
        moveViewWithKeyboard(notification: notification, viewBottomConstraint: self.searchBarBottomConstraint, keyboardWillShow: false)
    }
    
    func moveViewWithKeyboard(notification: NSNotification, viewBottomConstraint: NSLayoutConstraint, keyboardWillShow: Bool) {
        // Keyboard's size
        guard let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else { return }
        let keyboardHeight = keyboardSize.height
        
        // Keyboard's animation duration
        let keyboardDuration = notification.userInfo![UIResponder.keyboardAnimationDurationUserInfoKey] as! Double
        
        // Keyboard's animation curve
        let keyboardCurve = UIView.AnimationCurve(rawValue: notification.userInfo![UIResponder.keyboardAnimationCurveUserInfoKey] as! Int)!
        
        // Change the constant
        if keyboardWillShow {
            let safeAreaExists = (self.view?.window?.safeAreaInsets.bottom != 0) // Check if safe area exists
            let bottomConstant: CGFloat = 108
            viewBottomConstraint.constant = keyboardHeight + (safeAreaExists ? 0 : bottomConstant)
        }else {
            viewBottomConstraint.constant = 108
        }
        
        // Animate the view the same way the keyboard animates
        let animator = UIViewPropertyAnimator(duration: keyboardDuration, curve: keyboardCurve) { [weak self] in
            // Update Constraints
            self?.view.layoutIfNeeded()
        }
        
        // Perform the animation
        animator.startAnimation()
    }
    
    func setupErrorHandler(){
        viewmodel.error.subscribe { error in
            if error == "Error"{
                self.view.makeToast("Não foram encontrados pacotes",position: .top)
            }
        }
    }
    

}


// TableView Delegate
extension HomeViewController:UITableViewDelegate{
    
    func setupTableView() {
        tableView.delegate = nil
        tableView.dataSource = nil
        let homeViewCell = UINib(nibName: "HomeTableViewCell",
                                      bundle: nil)
        self.tableView.register(homeViewCell,
                                    forCellReuseIdentifier: "HomeTableViewCell")
        let backBarButtonItem = UIBarButtonItem(title: "Home", style: .plain, target: nil, action: nil)
        navigationItem.backBarButtonItem = backBarButtonItem
        tableView.keyboardDismissMode = .onDrag
        bindTableView()
    }
    

    
    
    
    private func bindTableView() {
        tableView.register(UINib(nibName: "HomeTableViewCell", bundle: nil), forCellReuseIdentifier: "HomeTableViewCell")
    
        viewmodel.packages.bind(to: tableView.rx.items(cellIdentifier: "HomeTableViewCell", cellType: HomeTableViewCell.self)) { (row,item,cell) in
            cell.preco.text = self.viewmodel.formatPrice(price: item.price.originalAmount)
            cell.setupUI()
            cell.slideShow.setImageInputs(self.viewmodel.fetchGalleryImages(imgs: item.gallery))
            cell.titulo.text = item.name
            cell.local.text = item.address.city
            cell.diferencial.text = item.amenities.first?.name
            cell.data.text = "\(item.quantityDescriptors.duration) dias"
            cell.package = item
            cell.delegate = self
        }.disposed(by: bag)
        
        
        
        tableView.rx.modelSelected(PackageResult.self).subscribe(onNext: { item in
            self.viewmodel.selectedItem.accept?(item)
        }).disposed(by: bag)
        
        viewmodel.fetchPackages()
    }
}

//notificao ao usuario clicar na imagem

extension HomeViewController:HomeTableViewCellDelegate{
    func tapImage(package: PackageResult) {
        self.viewmodel.selectedItem.accept?(package)
    }
}
