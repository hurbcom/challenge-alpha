//
//  JSONMockedEnums.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

/// Hotel Enums

// MARK: - AmenityCategoryEnum
enum AmenityCategoryEnum: String, Codable {
    case acomodação = "Acomodação"
    case atividades = "Atividades"
    case comidaBebida = "Comida / Bebida"
    case comodidadesInstalaçõesParaNegócios = "Comodidades / Instalações para negócios"
    case diversos = "Diversos"
    case entretenimentoEServiçosParaFamílias = "Entretenimento e serviços para famílias"
    case lojas = "Lojas"
    case opçõesDeTransporte = "Opções de transporte"
    case piscinaEComodidadesDeBemEstar = "Piscina e comodidades de bem-estar"
    case serviçosDeLimpezaLavanderia = "Serviços de limpeza / Lavanderia"
    case serviçosDeRecepção = "Serviços de recepção"
    case áreasComuns = "Áreas comuns"
}

// MARK: - CurrencyEnum
enum CurrencyEnum: String, Codable {
    case brl = "BRL"
}

// MARK: - CityEnum
enum CityEnum: String, Codable {
    case gramado = "Gramado"
}

// MARK: - CountryEnum
enum CountryEnum: String, Codable {
    case brasil = "Brasil"
}

// MARK: - StateEnum
enum StateEnum: String, Codable {
    case rioGrandeDoSul = "Rio Grande do Sul"
}

// MARK: - ResultCategoryEnum
enum ResultCategoryEnum: String, Codable {
    case hotel = "hotel"
}
