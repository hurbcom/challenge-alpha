import UIKit

final class HistoryView: UIView {
    let welcomeLabel = UILabel()
    let tableView = UITableView()
    
    // MARK: Init
    
    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        
        welcomeLabel.backgroundColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)
        welcomeLabel.textColor = .white
        welcomeLabel.textAlignment = .center
        welcomeLabel.clipsToBounds = true
        welcomeLabel.layer.cornerRadius = 7
        welcomeLabel.text = "Seu histórico de busca"
        
        tableView.translatesAutoresizingMaskIntoConstraints = false
        welcomeLabel.translatesAutoresizingMaskIntoConstraints = false
        
        addSubview(welcomeLabel)
        addSubview(tableView)
        
        tableView.separatorStyle = .none
        NSLayoutConstraint.activate([
            welcomeLabel.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 10),
            welcomeLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            welcomeLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            welcomeLabel.heightAnchor.constraint(equalToConstant: 45),
            
            tableView.topAnchor.constraint(equalTo: welcomeLabel.bottomAnchor, constant: 10),
            tableView.leftAnchor.constraint(equalTo: leftAnchor),
            tableView.rightAnchor.constraint(equalTo: rightAnchor),
            tableView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
