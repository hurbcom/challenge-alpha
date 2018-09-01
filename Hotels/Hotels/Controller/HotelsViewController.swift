//
//  HotelsViewController.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import SDWebImage

class HotelsViewController: UIViewController {

    var hotels = [Hotel]()
    
    @IBOutlet var tableView: UITableView!
    @IBOutlet var searchCollectionView: UICollectionView!
    
    let indicator = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.white)
    
    let favoriteDestinies = Destinies()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //configurando activity indicator
        indicator.hidesWhenStopped = true
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: indicator)
        
        //preenchendo locais para pesquisa
        favoriteDestinies.commonDestinies()
        //carregando hotéis e pacotes do destino default: fortaleza
        loadHotels(destiny: favoriteDestinies.getSelected())
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //MARK: - load hotels
    /*
     > faz o download do json com as informações dos hotéis e pacotes
     > atualiza a tabela após o download concluído
     > printa as informações recebidas
     > anima o indicator
    */
    private func loadHotels(destiny: Destiny) {
        indicator.startAnimating()
        HUAPIHelper.loadHotels(query: destiny.name!, completionHandler: { (hotels, success) in
            if success {
                guard let _ = hotels else { return }
                print(hotels!)
                self.hotels = hotels!
                self.tableView.reloadData()
                self.indicator.stopAnimating()
            }
        })
    }

}


//MARK: - table view datasource
extension HotelsViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        var qtdSections = Hotel.getStarsThatContainHotels(hotels: self.hotels).count
        //se existirem pacotes de viagem, devemos adicionar mais uma section
        if Hotel.existsPackage(hotels: self.hotels) { qtdSections = qtdSections + 1 }
        return qtdSections
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //se existirem pacotes de viagem
        if Hotel.existsPackage(hotels: self.hotels) {
            return section == 0 ? 1 : Hotel.getHotelsWith(stars: Hotel.getStarsThatContainHotels(hotels: self.hotels)[section-1], hotels: self.hotels).count
        }
        //se não existirem pacotes de viagem
        return Hotel.getHotelsWith(stars: Hotel.getStarsThatContainHotels(hotels: self.hotels)[section], hotels: self.hotels).count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        //se existirem pacotes de viagem
        if Hotel.existsPackage(hotels: self.hotels) {
            if indexPath.section == 0 && indexPath.row == 0 {
                //primeira linha da primeira célula sempre reservada para exibir os pacotes de viagem
                let cell = tableView.dequeueReusableCell(withIdentifier: "PACKAGES_CELL_IDENTIFIER", for: indexPath) as? PackagesTableViewCell
                fillPackageCell(cell: cell!, packages: Hotel.getPackages(hotels: self.hotels))
                return cell!
            }
            //exibindo hotéis
            let cell = tableView.dequeueReusableCell(withIdentifier: "HOTEL_CELL_IDENTIFIER", for: indexPath) as? HotelTableViewCell
            let hotel = Hotel.getHotelsWith(stars: Hotel.getStarsThatContainHotels(hotels: self.hotels)[indexPath.section-1], hotels: self.hotels)[indexPath.row]
            fillHotelCell(cell: cell!, hotel: hotel)
            return cell!
        }
        //se não existirem pacotes de viagem
        let cell = tableView.dequeueReusableCell(withIdentifier: "HOTEL_CELL_IDENTIFIER", for: indexPath) as? HotelTableViewCell
        let hotel = Hotel.getHotelsWith(stars: Hotel.getStarsThatContainHotels(hotels: self.hotels)[indexPath.section], hotels: self.hotels)[indexPath.row]
        fillHotelCell(cell: cell!, hotel: hotel)
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        //se existirem pacotes
        if Hotel.existsPackage(hotels: self.hotels) {
            //altura da célula de pacotes deve ser maior do que altura da célula de hotel
            return indexPath.section == 0 && indexPath.row == 0 ? 280.0 : 158.0
        }
        //se não existirem pacotes
        return 158.0
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if Hotel.existsPackage(hotels: self.hotels) {
            //exibindo título dos pacotes
            if section == 0 {
                let packagesheader = tableView.dequeueReusableCell(withIdentifier: "PACKAGES_HEADER_IDENTIFIER")
                return packagesheader
            }
            //exibindo estrelas dos hoteis naquele grupo
            let starsHeader = tableView.dequeueReusableCell(withIdentifier: "STARS_HEADER_IDENTIFIER") as? StarsTableViewHeaderCell
            starsHeader?.starsRatingView.value = CGFloat(Hotel.getStarsThatContainHotels(hotels: self.hotels)[section-1])
            return starsHeader
        }
        //exibindo estrelas dos hoteis naquele grupo (sem pacotes)
        let starsHeader = tableView.dequeueReusableCell(withIdentifier: "STARS_HEADER_IDENTIFIER") as? StarsTableViewHeaderCell
        starsHeader?.starsRatingView.value = CGFloat(Hotel.getStarsThatContainHotels(hotels: self.hotels)[section])
        return starsHeader
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        //se existirem pacotes
        if Hotel.existsPackage(hotels: self.hotels) {
            //altura da header que mostra as estrelas deve ser maior que a altura da header de pacotes
            return section == 0 ? 44.0 : 55.0
        }
        //não existindo pacotes, todas as headers têm a mesma alturas (header de estrelas)
        return 55.0
    }
    
    //MARK: - Fill cells
    /*
     > faz a associação das informações de hotéis com a view
    */
    private func fillHotelCell(cell: HotelTableViewCell, hotel: Hotel) {
        cell.hotelName.text = hotel.name
        cell.hotelAddress.text = hotel.address?.addressResume
        cell.hotelPrice.text = hotel.price?.currentPrice?.doubleValue.getMoneyValue()
        cell.amenities = Array(hotel.amenities.prefix(4))
        cell.hotelImage.sd_setImage(with: URL(string: hotel.image ?? "Error"), placeholderImage: #imageLiteral(resourceName: "placeholder-image"), options: SDWebImageOptions.progressiveDownload) { (image, error, type, url) in
            print(error ?? "Nenhum erro")
        }
    }
    
    /*
     > passa os objetos hotéis que representam pacotes para a collection view correspondente
    */
    private func fillPackageCell(cell: PackagesTableViewCell, packages: [Hotel]) {
        cell.packages = packages
        cell.packagesCollectionView.reloadData()
    }
}


//MARK: - collection view datasource and delegate
/*
 COLLECTION VIEW PRINCIPAIS DESTINOS
*/
extension HotelsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return favoriteDestinies.destinies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "SEARCH_NAME_CELL_IDENTIFIER", for: indexPath) as? FavoriteSearchCollectionViewCell
        cell?.searchName.text = favoriteDestinies.destinies[indexPath.row].name
        //definindo estilo para célula selecionada naquele momento
        if favoriteDestinies.destinies[indexPath.row].isSelected {
            cell?.layer.borderColor = #colorLiteral(red: 0.9216816425, green: 0.2687143087, blue: 0.5662311912, alpha: 1).cgColor
            cell?.layer.borderWidth = 5
            cell?.searchName.textColor = #colorLiteral(red: 0, green: 0, blue: 0, alpha: 1)
        } else {
            //demais células
            cell?.layer.borderWidth = 0
            cell?.searchName.textColor = #colorLiteral(red: 0.6666666865, green: 0.6666666865, blue: 0.6666666865, alpha: 1)
        }

        return cell!
    }
    
    /*
     > no momento que uma célula é selecionada, é feita uma requisição com o destino escolhido
     > collection view de destinos é atualizada para apresentar o atual selecionado
     > o destino escolhido deve ficar centralizado e em destaque
    */
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.favoriteDestinies.destinies[indexPath.row].select()
        self.loadHotels(destiny: favoriteDestinies.getSelected())
        self.searchCollectionView.reloadData()
        collectionView.scrollToItem(at: indexPath, at: .centeredHorizontally, animated: true)
    }
    
}




//MARk: - collection view flow layout
extension HotelsViewController: UICollectionViewDelegateFlowLayout {
    
    //define a distância entre as células de destinos
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0, left: 7.0, bottom: 0, right: 7.0)
    }
    
}
