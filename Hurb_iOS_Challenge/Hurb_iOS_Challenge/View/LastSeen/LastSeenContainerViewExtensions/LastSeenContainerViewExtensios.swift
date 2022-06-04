//
//  LastSeenContainerViewExtensios.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import UIKit

// MARK: - UITableViewDataSource
extension LastSeenContainerView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return lastSeenHotels?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: tableViewCellReuseIdentifier, for: indexPath) as? LastSeenTableViewCell else {
            return UITableViewCell()
        }
        let viewModel = LastSeenViewModel()
        viewModel.hotelResult = lastSeenHotels?[indexPath.row]
        cell.hotelResult = lastSeenHotels?[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        switch editingStyle {
        case .delete:
            DataPersistenceManager.shared.deleteTitleWith(model: (lastSeenHotels?[indexPath.row])!) { [weak self] result in
                switch result {
                case .success():
                    print("DEBUG: - ITEM DELETADO DA LISTA DE VISTOS POR ÚLTIMO COM SUCESSO.")
                    AlertUtils.showAlert(message: "Item deletado da lista de vistos por último.")
                    self?.lastSeenHotels?.remove(at: indexPath.row)
                    tableView.deleteRows(at: [indexPath], with: .fade)
                case .failure(let error):
                    AlertUtils.showAlert(message: "Falha ao deletar item, por favor tente novamente.")
                    print("DEBUG: - \(error.localizedDescription)")
                }
            }
        default:
            break
        }
    }
}

extension LastSeenContainerView: UITableViewDelegate {
    
}
