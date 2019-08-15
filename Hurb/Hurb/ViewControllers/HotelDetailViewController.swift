//
//  HotelDetailViewController.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit
import ImageSlideshow
import GoogleMaps

class HotelDetailViewController: UIViewController {

    //MARK: - Properties
    var hotelViewModel: HotelViewModel!
    var amenities: [Amenity] = []
    var imageArray: [UIImage] = []
    
    let googleMapsService = GoogleMapsService()
    var marker = GMSMarker()
    var mapAddress: String = ""
    
    //MARK: - IB Outlets
    @IBOutlet weak var slideshow: ImageSlideshow!
    @IBOutlet weak var lbCity: UILabel!
    @IBOutlet weak var lbName: UILabel!
    @IBOutlet weak var imgStar1: UIImageView!
    @IBOutlet weak var imgStar2: UIImageView!
    @IBOutlet weak var imgStar3: UIImageView!
    @IBOutlet weak var imgStar4: UIImageView!
    @IBOutlet weak var imgStar5: UIImageView!
    
    @IBOutlet weak var vwFreeCancellation: UIView!
    @IBOutlet weak var heightFreeCancellationView: NSLayoutConstraint!
    @IBOutlet weak var lbPriceDescription: UILabel!
    @IBOutlet weak var lbPrice: UILabel!
    
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var vwAmenities: UIView!
    @IBOutlet weak var lbAmenitiesTotal: UILabel!
    
    @IBOutlet weak var tableViewAmenities: UITableView!
    @IBOutlet weak var heightTableViewAmenities: NSLayoutConstraint!
    
    @IBOutlet weak var lbAddress: UILabel!
    @IBOutlet weak var mapView: GMSMapView!
    @IBOutlet weak var heightMapView: NSLayoutConstraint!
    
    //MARK: - IB Actions
    @IBAction func reservarHotel(_ sender: UIButton) {
        self.present(self.showAlert(mensagem: "Sistema de reservas estará disponível na próxima versão"), animated: true, completion: nil)
    }
    
    @IBAction func tracarRota(_ sender: UIButton) {
        let alert = UIAlertController(title: "Traçar Rota",
                                      message: "Selecione uma opção:",
                                      preferredStyle: UIAlertController.Style.alert)
        
        let googleMapsAction = UIAlertAction(title: "Google Maps",
                                             style: .default, handler: { action in MapasUtil.openGoogleMaps(endereco: self.mapAddress)})
        let appleMapsAction = UIAlertAction(title: "Apple Maps",
                                            style: .default, handler: { action in MapasUtil.openAppleMaps(endereco: self.mapAddress)})
        let wazeAction = UIAlertAction(title: "Waze",
                                       style: .default, handler: { action in MapasUtil.openWaze(endereco: self.mapAddress)})
        let cancelAction = UIAlertAction(title: "Cancelar",
                                         style: .cancel, handler: nil)
        
        alert.addAction(googleMapsAction)
        alert.addAction(appleMapsAction)
        alert.addAction(wazeAction)
        alert.addAction(cancelAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    //MARK: - ViewController life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        updateUI()
        self.navigationItem.title = hotelViewModel.isHotel ? "Detalhe do Hotel" : "Detalhe do Pacote"
        
    }
    
    //ao abrir a view controller, zerar os marcadores e carregar o mapa
    override func viewDidAppear(_ animated: Bool) {
        marker.map = nil
        if hotelViewModel.isHotel {
            self.heightMapView.constant = 300
            setMap()
        } else {
            self.mapView.isHidden = true
            self.heightMapView.constant = 0
        }
        
    }
    
    //MARK: - Functions
    
    //atualizar a UI com o viewModel
    fileprivate func updateUI() {
        
        lbCity.text = hotelViewModel.cityState
        lbName.text = hotelViewModel.name
        
        setImageGallery()
        setStars()
        vwFreeCancellation.isHidden = !hotelViewModel.isFreeCancellation
        heightFreeCancellationView.constant = hotelViewModel.isFreeCancellation ? 25 : 0
        
        lbPriceDescription.text = hotelViewModel.priceDescription
        lbPrice.text = hotelViewModel.price
        
        lbDescription.text = hotelViewModel.description
        
        amenities = hotelViewModel.amenities
        lbAmenitiesTotal.text = "\(amenities.count) Amenidades"

        tableViewAmenities.reloadData()
        heightTableViewAmenities.constant = tableViewAmenities.contentSize.height
    }
    
    //configurar o mapa
    fileprivate func setMap() {
        
        self.mapView.clear()
        self.mapView.settings.compassButton = true
        self.mapView.settings.zoomGestures = true
        self.mapView.isIndoorEnabled = false
        
        //pegar as coordenadas de acordo com o endereço (geocoding)
        googleMapsService.getLatitudeLongitude(endereco: hotelViewModel.address, successCompletion: { location, address in
            //mover câmera do mapa para o marcador
            let target = CLLocationCoordinate2D(latitude: location.lat, longitude: location.lng)
            self.mapView.animate(toLocation: target)
            self.mapView.animate(toZoom: 14)
            
            // Criar marcador no mapa
            self.marker.position = CLLocationCoordinate2D(latitude: location.lat, longitude: location.lng)
            self.marker.title = self.hotelViewModel.name
            self.marker.snippet = self.hotelViewModel.address
            self.marker.map = self.mapView
            
            //atualizar endereço com o endereço formatado do Google Maps
            self.lbAddress.text = "Endereço: \(address)"
            self.mapAddress = address
        
        }, errorCompletion: { erro in
            print(erro)
        })
    }
    
    //verificar quantidade de estrelas que serão exibidas
    fileprivate func setStars(){
        let imgStars = [imgStar1, imgStar2, imgStar3, imgStar4, imgStar5]
        
        if let stars = hotelViewModel.stars {
            for imgStar in imgStars {
                if imgStar!.tag < stars {
                    imgStar?.image = UIImage(named: "star")
                } else {
                    imgStar?.image = UIImage()
                }
            }
        }
    }

}

//MARK: - UITableView extension - Lista de Amenidades
extension HotelDetailViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return amenities.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "amenityCell", for: indexPath)
        
        cell.textLabel?.text = "◆ \(amenities[indexPath.row].name!)"
        cell.textLabel?.font = UIFont(name: "HelveticaNeue", size: 10)
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
        return 18
    }
}

//MARK: - ImageSlideshow extension
extension HotelDetailViewController: ImageSlideshowDelegate {
    fileprivate func setImageGallery(){
        var sources: [SDWebImageSource] = []
        
        //criando o source de imagens da galeria para o slideshow
        for image in hotelViewModel.gallery {
            let sdWebImageSource = SDWebImageSource(urlString: image.url!.convertStringToUrlString, placeholder: UIImage(named: "placeholderImage"))!
            sources.append(sdWebImageSource)
        }
        
        slideshow.pageIndicatorPosition = .init(horizontal: .center, vertical: .under)
        slideshow.contentScaleMode = UIViewContentMode.scaleAspectFill
        slideshow.circular = false
        
        let pageControl = UIPageControl()
        pageControl.currentPageIndicatorTintColor = UIColor.black
        pageControl.pageIndicatorTintColor = UIColor.lightGray
        slideshow.pageIndicator = pageControl
        
        slideshow.activityIndicator = DefaultActivityIndicator()
        slideshow.delegate = self
        
        //adicionando o source de imagens ao slideshow
        slideshow.setImageInputs(sources)
        
        let recognizer = UITapGestureRecognizer(target: self, action: #selector(didTap))
        slideshow.addGestureRecognizer(recognizer)
        
    }
    
    @objc func didTap() {
        //ao dar um tap em uma imagem, abrir em full screen
        let fullScreenController = slideshow.presentFullScreenController(from: self)
        fullScreenController.slideshow.activityIndicator = DefaultActivityIndicator(style: .white, color: nil)
    }

    func imageSlideshow(_ imageSlideshow: ImageSlideshow, didChangeCurrentPageTo page: Int) {
        print("current page:", page)
    }
}
