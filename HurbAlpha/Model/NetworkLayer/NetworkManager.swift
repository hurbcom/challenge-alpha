//
//  NetworkManager.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//
/**
 This class resolves all management that requires network conection
  OBS: This class is a singleton and can be acesses from wanywhere in the code
*/
class NetworkManager {
    static var shared = NetworkManager()
}
/// in order to simplify the access  to the NetworkManager capabilities
let netManager = NetworkManager.shared
