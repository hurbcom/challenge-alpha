//
//  PackageDetailViewController.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import UIKit
import ImageSlideshow
import Toast_Swift

class PackageDetailViewController: UIViewController {

    
    @IBOutlet weak var slideShow: ImageSlideshow!
    @IBOutlet weak var descricao: UILabel!
    @IBOutlet weak var titulo: UILabel!
    
    var package:PackageResult?
    var viewmodel:PackageDetailViewModel = PackageDetailViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.addBlurToView()
        self.view.makeToastActivity(.center)



        setupUI()
        setupInfos()
        setupTextObserver()
        // Do any additional setup after loading the view.
    }

    @IBAction func lerMaisTap(_ sender: Any) {
        presentFullDescricao()
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


extension PackageDetailViewController{
    
    func setupUI(){
        navigationItem.title = "Details"
        let backBarButtonItem = UIBarButtonItem(title: "You back button title here", style: .plain, target: nil, action: nil)
        navigationItem.backBarButtonItem = backBarButtonItem
        slideShow.contentScaleMode = .scaleAspectFill
    }
    
    func setupInfos(){
        titulo.text = package?.name
        viewmodel.parseHtmlToString(text: package?.description ?? "")
        descricao.font = UIFont.systemFont(ofSize: 17) // 17 é o tamanho padrão de fonte da iOS
        slideShow.setImageInputs(viewmodel.fetchGalleryImages(imgs: package?.gallery ?? []))
    }
    
    func setupTextObserver(){
        viewmodel.descricao.subscribe { texto in
            self.descricao.text = texto
            self.view.hideToastActivity()
            self.view.removeBlurFromView()
        }
    }
    
    func presentFullDescricao(){
        let con = FullDescricaoModalViewController()
        con.descricao = self.descricao.text
        self.navigationController?.present(con, animated: true)
    }
    

}
