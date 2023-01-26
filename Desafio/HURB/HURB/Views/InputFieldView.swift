import UIKit

final class InputFieldView: UIView {
    let searchButton = UIButton()
    let stackView = UIStackView()
    let textField = UITextField()
    
    // MARK: Init
    
    init() {
        super.init(frame: .zero)
        disableAutoresizingMaskIntoConstraints()
        addSubViews()
        setupUI()
        setupConstraints()
    }

    required init?(coder: NSCoder) { nil }
    
    // MARK: Private
    
    private func setupUI() {
        textField.font = UIFont.boldSystemFont(ofSize: 15.0)
        textField.attributedPlaceholder = NSMutableAttributedString(
            string: "Onde você quer ir",
            attributes: [
                .foregroundColor: UIColor.black.withAlphaComponent(0.8),
                .font: UIFont.italicSystemFont(ofSize: 15.0)
            ]
        )
       
        searchButton.setImage(UIImage(systemName: "magnifyingglass"), for: .normal)
        searchButton.tintColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)
        
        layer.borderWidth = 1
        layer.cornerRadius = 8
        layer.borderColor = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1).cgColor

        stackView.spacing = 20

    }
    
    private func addSubViews() {
        stackView.addArrangedSubview(textField)
        stackView.addArrangedSubview(searchButton)
        addSubview(stackView)
    }
    
    private func disableAutoresizingMaskIntoConstraints() {
        stackView.translatesAutoresizingMaskIntoConstraints = false
        textField.translatesAutoresizingMaskIntoConstraints = false
        searchButton.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: topAnchor, constant: 10),
            stackView.leftAnchor.constraint(equalTo: leftAnchor, constant: 25),
            stackView.rightAnchor.constraint(equalTo: rightAnchor, constant: -25),
            stackView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -10)
        ])
    }
}
