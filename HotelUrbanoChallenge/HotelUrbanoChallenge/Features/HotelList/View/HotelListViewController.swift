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
        
        self.navigationController?.navigationBar.barTintColor = UIColor(hexadecimal: 0x143775)
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

    
    /*
     // Override to support conditional editing of the table view.
     override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
     // Return false if you do not want the specified item to be editable.
     return true
     }
     */
    
    /*
     // Override to support editing the table view.
     override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
     if editingStyle == .delete {
     // Delete the row from the data source
     tableView.deleteRows(at: [indexPath], with: .fade)
     } else if editingStyle == .insert {
     // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
     }
     }
     */
    
    /*
     // Override to support rearranging the table view.
     override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
     
     }
     */
    
    /*
     // Override to support conditional rearranging of the table view.
     override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
     // Return false if you do not want the item to be re-orderable.
     return true
     }
     */
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
//}

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
