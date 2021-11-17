import UIKit

class ResultCell: UITableViewCell {
    let cardView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.setCodable()

        view.layer.cornerRadius = 15
        view.setShadow()
        return view
    }()

    let cardContentView: UIView = {
        let view = UIView()
        view.backgroundColor = .clear
        view.setCodable()

        view.layer.cornerRadius = 15
        view.clipsToBounds = true
        return view
    }()

    let imgPhoto: UIImageView = {
        let img = UIImageView()
        img.setCodable()
        img.contentMode = .scaleAspectFill
        img.clipsToBounds = true
        img.tintColor = .black
        return img
    }()

    let infoView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.setCodable()
        return view
    }()

    let lblName: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.boldSystemFont(ofSize: 18)
        lbl.textColor = .black
        lbl.setCodable()
        return lbl
    }()

    let imgPrice: UIImageView = {
        let img = UIImageView()
        img.image = UIImage(systemName: "dollarsign.square.fill")
        img.tintColor = .black
        img.setCodable()
        img.contentMode = .scaleAspectFit
        return img
    }()

    let lblPrice: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.systemFont(ofSize: 14)
        lbl.textColor = .lightGray
        lbl.setCodable()
        return lbl
    }()

    let lblAddress: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.systemFont(ofSize: 12)
        lbl.textColor = .lightGray
        lbl.setCodable()
        return lbl
    }()

    func setupUI() {
        selectionStyle = .none

        contentView.addSubview(cardView)
        cardView.addSubview(cardContentView)

        cardContentView.addSubview(imgPhoto)
        cardContentView.addSubview(infoView)

        infoView.addSubview(lblName)
        infoView.addSubview(imgPrice)
        infoView.addSubview(lblPrice)
        infoView.addSubview(lblAddress)

        setupConstraints()
    }

    func setupConstraints() {
        // Card View
        cardView.setEdgesToSuperview(constant: 10)
        cardContentView.setEdgesToSuperview()

        // Cover Image View
        imgPhoto.setHeight(100)
        imgPhoto.setEdgesToSuperview(excluding: [.bottom])

        // Info
        infoView.setTop(to: imgPhoto.bottomAnchor)
        infoView.setEdgesToSuperview(excluding: [.top])

        // - Name
        lblName.setEdgesToSuperview(excluding: [.bottom], constant: 10)

        // - Address
        lblAddress.setTop(to: lblName.bottomAnchor, constant: 5)
        lblAddress.setEdgesToSuperview(excluding: [.bottom, .top], constant: 10)

        // Price View
        // - Icon
        imgPrice.setTop(to: lblAddress.bottomAnchor, constant: 5)
        imgPrice.setEdgesToSuperview(excluding: [.trailing, .top], constant: 10)
        // - Price
        lblPrice.setLeading(to: imgPrice.trailingAnchor, constant: 5)
        lblPrice.setCenterY(to: imgPrice.centerYAnchor)
    }

    func configure(result: SearchResult) {
        imgPhoto.loadImage(url: result.cover)
        lblName.text = result.name
        lblAddress.text = result.address
        lblPrice.text = "\(result.price)"
    }

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupUI()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        cardView.setShadow(color: selected ? .systemBlue : .black, animated: animated)
        if selected { setSelected(false, animated: true) }
    }
}
