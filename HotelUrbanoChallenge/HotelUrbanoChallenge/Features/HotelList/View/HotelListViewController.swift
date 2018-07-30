//
//  HotelListViewController.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import UIKit
import SVProgressHUD

class HotelListViewController: UITableViewController {
    
    fileprivate var presenter: HotelListPresenter!
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "Hotel Urbano"
        self.navigationController?.navigationBar.barTintColor = UIColor(hexadecimal: 0x143775)
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor : UIColor.white, NSAttributedStringKey.font : UIFont.systemFont(ofSize: 18.0, weight: UIFont.Weight.semibold)]
        self.tableView.register(UINib(nibName: HotelItemViewCell.identifier, bundle: nil), forCellReuseIdentifier: HotelItemViewCell.identifier)
        self.presenter = HotelListPresenter(view: self)
        self.presenter.loadHotels()
    }
    
}
// MARK: - Table view data source
extension HotelListViewController {
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return self.presenter.hotelListViewModel.count
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return self.presenter.hotelListViewModel[section].hotelItems.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let hotel = self.presenter.hotelListViewModel[indexPath.section]
        let hotelList = hotel.hotelItems[indexPath.row]
        
        if let cell = tableView.dequeueReusableCell(withIdentifier:HotelItemViewCell.identifier, for: indexPath) as? HotelItemViewCell {
            cell.fillOutlets(hotel: hotelList)
            return cell
        }
        return UITableViewCell()
    }
    
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 50
    }
    
    override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = UIView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: 50))
        header.backgroundColor = .white
        
        let y: CGFloat = 5
        
        let title = UILabel(frame: CGRect(x: 20, y: y, width: view.frame.width, height: 50))
        title.text = "\(String(self.presenter.hotelListViewModel[section].groupDescription)) Estrelas"
        title.font = UIFont.boldSystemFont(ofSize: 22)
        title.textColor = UIColor(hexadecimal: 0x143775)
        header.addSubview(title)
        
        return header
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 154
    }
    
}

extension HotelListViewController: HotelListProtocol {
  
    func startLoading() {
        SVProgressHUD.setDefaultStyle(.custom)
        SVProgressHUD.setForegroundColor(.white)
        SVProgressHUD.setBackgroundColor(UIColor.lightGray)
        SVProgressHUD.setDefaultMaskType(.clear)
        SVProgressHUD.show()
    }
    
    func stopLoading() {
        SVProgressHUD.dismiss()
    }
    
    func reloadTableView() {
        UIView.transition(with: tableView!, duration: 0.35, options: .transitionCrossDissolve, animations: {
            self.tableView?.reloadData()
        })
    }
    
    func showAlertError(with title: String, message: String, buttonTitle: String) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: buttonTitle, style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}
