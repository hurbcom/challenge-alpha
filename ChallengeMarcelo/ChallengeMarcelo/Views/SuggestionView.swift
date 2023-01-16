import UIKit

final class SuggestionView: UIView {
    let titleLabel = UILabel()
    let tableView = UITableView()
    
    
    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        addSubviews()
        disableAutoresizingMaskIntoConstraints()
        setupConstraints()
        
        titleLabel.backgroundColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)
        titleLabel.tintColor = .white
        titleLabel.clipsToBounds = true
        titleLabel.layer.cornerRadius = 7
        titleLabel.text = "Pensando em você"
        titleLabel.textColor = .white
        titleLabel.textAlignment = .center
        titleLabel.font = .boldSystemFont(ofSize: 17.0)
    }
    
    private func addSubviews() {
        addSubview(titleLabel)
        addSubview(tableView)
    }
    
    private func disableAutoresizingMaskIntoConstraints() {
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        tableView.translatesAutoresizingMaskIntoConstraints = false
    }
    
    
    required init?(coder: NSCoder) { nil }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: topAnchor, constant: 10),
            titleLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 10),
            titleLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -10),
            titleLabel.heightAnchor.constraint(equalToConstant: 45),
            
            tableView.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            tableView.leftAnchor.constraint(equalTo: leftAnchor),
            tableView.rightAnchor.constraint(equalTo: rightAnchor),
            tableView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
}
