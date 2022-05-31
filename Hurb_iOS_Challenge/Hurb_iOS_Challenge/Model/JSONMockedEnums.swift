//
//  JSONMockedEnums.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

/// Hotel Enums

// MARK: - AmenityCategory
enum AmenityCategory: String, Codable {
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

// MARK: - Currency
enum Currency: String, Codable {
    case brl = "BRL"
}

// MARK: - City
enum City: String, Codable {
    case gramado = "Gramado"
}

// MARK: - Country
enum Country: String, Codable {
    case brasil = "Brasil"
}

// MARK: - State
enum State: String, Codable {
    case rioGrandeDoSul = "Rio Grande do Sul"
}

// MARK: - Result Category
enum ResultCategory: String, Codable {
    case hotel = "hotel"
}
