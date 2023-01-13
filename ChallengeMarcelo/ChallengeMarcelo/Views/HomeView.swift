import UIKit

final class HomeView: UIView {
    let inputField = UITextField()
    let searchButton = UIButton()
    let tableView = UITableView()

    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        
        searchButton.backgroundColor = .blue
        searchButton.setTitle("Search", for: .normal)
        searchButton.setTitleColor(.white, for: .normal)
        
        inputField.layer.borderWidth = 2.0
        inputField.layer.borderColor = UIColor.black.cgColor
        
        tableView.translatesAutoresizingMaskIntoConstraints = false
        inputField.translatesAutoresizingMaskIntoConstraints = false
        searchButton.translatesAutoresizingMaskIntoConstraints = false
        
        addSubview(inputField)
        addSubview(searchButton)
        addSubview(tableView)
        
        searchButton.centerXAnchor.constraint(
            equalTo: inputField.centerXAnchor
        ).isActive = true
        
        searchButton.topAnchor.constraint(
            equalTo: inputField.bottomAnchor,
            constant: 20
        ).isActive = true
        
        inputField.heightAnchor.constraint(
            equalToConstant: 50
        ).isActive = true

        inputField.topAnchor.constraint(
            equalTo: safeAreaLayoutGuide.topAnchor,
            constant: 50
        ).isActive = true
        
        inputField.leftAnchor.constraint(
            equalTo: leftAnchor,
            constant: 50
        ).isActive = true
        
        inputField.rightAnchor.constraint(
            equalTo: rightAnchor,
            constant: -50
        ).isActive = true
        
        tableView.topAnchor.constraint(
            equalTo: searchButton.bottomAnchor,
            constant: 20
        ).isActive = true
        
        tableView.leftAnchor.constraint(
            equalTo: leftAnchor
        ).isActive = true

        tableView.rightAnchor.constraint(
            equalTo: rightAnchor
        ).isActive = true

        tableView.bottomAnchor.constraint(
            equalTo: safeAreaLayoutGuide.bottomAnchor
        ).isActive = true
    }
    
    required init?(coder: NSCoder) { nil }
}
