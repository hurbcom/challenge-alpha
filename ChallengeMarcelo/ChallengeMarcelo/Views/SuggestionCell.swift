import UIKit

final class SuggestionCell: UITableViewCell {
    let titleLabel = UILabel()
    let smallAddressLabel = UILabel()

    // MARK: Init
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        selectionStyle = .none
        addSubviews()
        setupUI()
        setupConstraints()
    }
    
    required init?(coder: NSCoder) { nil }
    
    // MARK: Override
    
    override func prepareForReuse() {
        titleLabel.text = nil
        smallAddressLabel.text = nil
    }
    
    // MARK: Private
    
    private func setupUI() {
        backgroundColor = .clear
        
        titleLabel.numberOfLines = 0

        titleLabel.font = .boldSystemFont(ofSize: 15.0)
        smallAddressLabel.font = .systemFont(ofSize: 13.0)
        
        smallAddressLabel.textColor = .darkGray
        
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        smallAddressLabel.translatesAutoresizingMaskIntoConstraints = false
        
    }
    
    private func addSubviews() {
        contentView.addSubview(titleLabel)
        contentView.addSubview(smallAddressLabel)
    }
    
    func setupCell(suggestion: Suggestion) {
        titleLabel.text = suggestion.text
        smallAddressLabel.text = suggestion.suggestionType.capitalized
    }

    private func setupConstraints() {
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10),
            titleLabel.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 20),
            titleLabel.rightAnchor.constraint(equalTo: contentView.rightAnchor, constant: -20),

            smallAddressLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),
            smallAddressLabel.leftAnchor.constraint(equalTo: titleLabel.leftAnchor),
            smallAddressLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -10)
        ])
    }
}
