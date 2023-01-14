import UIKit

final class ProductListView: UIView {
    let welcomeLabel = UILabel()
    let tableView = UITableView()
    let stackView = UIStackView()
    let searchButton = UIButton()
    let inputField = UITextField()

    init() {
        super.init(frame: .zero)
        setupTexts()
        setupColors()
        setupStackView()
        setupLayerProperties()
        disableAutoresizingMaskIntoConstraints()

        stackView.clipsToBounds = true
        searchButton.clipsToBounds = true

        addSubviews()
        setupConstraints()
    }

    required init?(coder: NSCoder) { nil }
    
    // MARK: - Private
    
    private func addSubviews() {
        addSubview(stackView)
        addSubview(tableView)
        addSubview(welcomeLabel)
    }
    
    private func setupTexts() {
        welcomeLabel.numberOfLines = 0
        welcomeLabel.textAlignment = .center
        welcomeLabel.font = .boldSystemFont(ofSize: 20)

        inputField.textAlignment = .center
        searchButton.setTitle("Search", for: .normal)
        welcomeLabel.text = "Seja bem vindo, escolha seu destino."
    }
    
    private func setupStackView() {
        stackView.spacing = 20
        stackView.addArrangedSubview(inputField)
        stackView.addArrangedSubview(searchButton)
    }
    
    private func setupColors() {
        tableView.backgroundColor = .clear

        inputField.textColor = .white
        inputField.font = UIFont.boldSystemFont(ofSize: 15.0)
        inputField.attributedPlaceholder = NSMutableAttributedString(
            string: "e.g: Rio de Janeiro",
            attributes: [
                .foregroundColor: UIColor.white.withAlphaComponent(0.8),
                .font: UIFont.italicSystemFont(ofSize: 15.0)
            ]
        )
        
        welcomeLabel.textColor = .white
        searchButton.backgroundColor = .white
        searchButton.setTitleColor(.black, for: .normal)
        backgroundColor = UIColor(red: 39/255, green: 14/255, blue: 245/255, alpha: 1)
    }
    
    private func setupLayerProperties() {
        inputField.layer.borderWidth = 1
        inputField.layer.cornerRadius = 8
        searchButton.layer.cornerRadius = 5
        inputField.layer.borderColor = UIColor.white.cgColor
    }
    
    private func disableAutoresizingMaskIntoConstraints() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
        stackView.translatesAutoresizingMaskIntoConstraints = false
        inputField.translatesAutoresizingMaskIntoConstraints = false
        welcomeLabel.translatesAutoresizingMaskIntoConstraints = false
        searchButton.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate([
            welcomeLabel.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            welcomeLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
            welcomeLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
            welcomeLabel.heightAnchor.constraint(equalToConstant: 50),

            stackView.topAnchor.constraint(equalTo: welcomeLabel.bottomAnchor, constant: 10),
            stackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            stackView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
            stackView.heightAnchor.constraint(equalToConstant: 45),

            searchButton.widthAnchor.constraint(equalToConstant: 100),

            tableView.topAnchor.constraint(equalTo: stackView.bottomAnchor, constant: 10),
            tableView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
            tableView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
            tableView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -10)
        ])
    }
}
