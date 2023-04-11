//
//  FullDescricaoModalViewController.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 10/04/23.
//

import UIKit

class FullDescricaoModalViewController: UIViewController {

    @IBOutlet weak var text: UILabel!
    
    var descricao:String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        // Do any additional setup after loading the view.
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

extension FullDescricaoModalViewController{
    
    func setupUI(){
        self.text.text = self.descricao
    }
}
