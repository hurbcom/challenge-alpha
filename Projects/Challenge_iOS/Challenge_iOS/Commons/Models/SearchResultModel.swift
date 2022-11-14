//
//  SearchResultModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation

struct SearchResultModel: Decodable {
    let id: String?
    let name: String
    let description: String
    let smallDescription: String
    let category: String
    let isAvailable: Bool
    let huFreeCancellation: Bool?
    let gallery: [Gallery]
    let sku: String
    let price: Price?
    let address: Address?
    let amenities: [Amenities]
    
    struct Gallery: Decodable {
        let url: String
    }
    
    struct Price: Decodable {
        let amount: Double
        let currency: String
        let taxes: [Taxe]
    }
    
    struct Taxe: Decodable {
        let originalAmount: Double
        let originalCurrency: String
    }
    
    struct Address: Decodable {
        let state: String
        let country: String
        let city: String
        let geoLocation: Geolocation?
    }
    
    struct Geolocation: Decodable {
        let lat: Double
        let lon: Double
    }
    
      struct Amenities: Decodable {
          let name: String
          let category: String
      }
}

/*
 {
   "id" : "ATBZFI",
   "category" : "hotel",
   "description" : "Desfrute de uma hospedagem única em Copacabana, com as melhores vistas do Rio de Janeiro. O Hilton Rio de Janeiro Copacabana oferece 545 apartamentos, 36 salas de eventos flexíveis, duas piscinas, um spa completo, restaurantes e bares, e um serviço de praia exclusivo, na melhor localização de Copacabana. Próximo às atrações de lazer da cidade, diversas opções gastronômicas a poucos passos do hotel, escritórios corporativos e aeroporto internacional e doméstico. O hotel conta com a maior quantidade de quartos com vista mar de Copacabana.\n\nInformamos que o hotel é Pet Friendly, aceitando 1 animal até 34kg por apartamento, com a cobrança do valor adicional de R$ 250,00 por estada. O hotel disponibiliza cama pet, comedouro e bebedouro. Deverá ser verificada a disponibilidade da inclusão do pet através do e-mail reservations.copacabana@hilton.com\n\nTemos estacionamento com custo adicional, porém a vaga será concedida mediante disponibilidade no momento do check-in. Peso máximo do veículo deverá ser 2.000kg. Não aceitamos motocicletas, vans, caminhões, veículos com bagageiro e\/ou com altura superior a 1,97m.",
   "isAvailable" : true,
   "gallery" : [
     {
       "url" : "https:\/\/thumbcdn-z.hotelurbano.net\/qBceSVLyAN15vsgxSGMbZBrQ1ZA=\/origxorig\/center\/middle\/filters:quality(70)\/http:\/\/media.omnibees.com\/Images\/5784\/RoomTypes\/654202.jpg",
       "description" : "",
       "__typename" : "SearchResultItemGalleryImage"
     },
     {
       "url" : "https:\/\/thumbcdn-z.hotelurbano.net\/HEJiRfMzVHL46pMX07A7j4qOzac=\/origxorig\/center\/middle\/filters:quality(70)\/http:\/\/media.omnibees.com\/Images\/5784\/Property\/643043.jpg",
       "description" : "",
       "__typename" : "SearchResultItemGalleryImage"
     },
     {
       "url" : "https:\/\/thumbcdn-z.hotelurbano.net\/2g3c6S84VB76zN1Gj_HCNEOz4R4=\/origxorig\/center\/middle\/filters:quality(70)\/http:\/\/media.omnibees.com\/Images\/5784\/Property\/227328.png",
       "description" : "",
       "__typename" : "SearchResultItemGalleryImage"
     },
     {
       "url" : "https:\/\/thumbcdn-z.hotelurbano.net\/QSE0BZTS87tggo3OZ0kIfxcNZRI=\/origxorig\/center\/middle\/filters:quality(70)\/http:\/\/media.omnibees.com\/Images\/5784\/Property\/232637.png",
       "description" : "",
       "__typename" : "SearchResultItemGalleryImage"
     },
     {
       "url" : "https:\/\/thumbcdn-z.hotelurbano.net\/P7_n5uvQ_VhdQTmiY7L-EOeKCDg=\/origxorig\/center\/middle\/filters:quality(70)\/http:\/\/media.omnibees.com\/Images\/5784\/Property\/253314.jpg",
       "description" : "",
       "__typename" : "SearchResultItemGalleryImage"
     }
   ],
   "url" : "https:\/\/www.hurb.com\/br\/hotels\/rio-de-janeiro\/hilton-rio-de-janeiro-copacabana-HT-BZFI",
   "sku" : "HT-BZFI-0-0-0-0-0-0-0-0-0",
   "tags" : [

   ],
   "price" : {
     "amount" : 525.82000000000005,
     "__typename" : "ProductPrice",
     "currency" : "BRL",
     "originalAmount" : null,
     "taxes" : [
       {
         "originalAmount" : 10.73,
         "originalCurrency" : "BRL",
         "__typename" : "ProductPriceTaxes"
       }
     ]
   },
   "address" : {
     "__typename" : "SearchResultItemAddress",
     "state" : "Rio de Janeiro",
     "country" : "Brasil",
     "city" : "Rio De Janeiro",
     "geoLocation" : {
       "lat" : -22.964476300000001,
       "lon" : -43.175644200000001,
       "__typename" : "Coordinates"
     }
   },
   "__typename" : "SearchResultHotelItem",
   "smallDescription" : "Desfrute de uma hospedagem única em Copacabana, com as melhores vistas do Rio de Janeiro. O Hilton Rio de Janeiro Copacabana oferece 545 apartamentos, 36 salas de eventos flexíveis, duas piscinas, um spa completo, restaurantes e bares, e um serviço d",
   "name" : "Hilton Rio de Janeiro Copacabana",
   "amenities" : [
     {
       "name" : "Berço disponível a pedido",
       "category" : "Diversos",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Quartos para Deficientes",
       "category" : "Diversos",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Lavanderia\/Limpeza a seco",
       "category" : "Serviços de limpeza \/ Lavanderia",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Aceita os principais cartões de crédito",
       "category" : "Diversos",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Equipe Poliglota",
       "category" : "Diversos",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Recepção 24 horas",
       "category" : "Serviços de recepção",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Serviço de Quarto 24 horas",
       "category" : "Acomodação",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Bar de piscina",
       "category" : "Comida \/ Bebida",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Piscina",
       "category" : "Comodidades de bem-estar",
       "__typename" : "SearchResultItemAmenity"
     },
     {
       "name" : "Salas de reuniões",
       "category" : "Comodidades \/ Instalações para negócios",
       "__typename" : "SearchResultItemAmenity"
     }
   ]
 }
 */
