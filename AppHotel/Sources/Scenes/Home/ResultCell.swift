import UIKit

class ResultCell: UITableViewCell {
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

    func setupUI() {
        contentView.addSubview(imgPhoto)

        contentView.addSubview(infoView)
        infoView.addSubview(lblName)
        infoView.addSubview(imgPrice)
        infoView.addSubview(lblPrice)

        setupConstraints()
    }

    func setupConstraints() {
        // Cover Image View
        imgPhoto.setEdgesToSuperview(excluding: [.bottom], constant: 5)
        imgPhoto.setHeight(100)

        // Info
        infoView.setTop(to: imgPhoto.bottomAnchor)
        infoView.setEdgesToSuperview(excluding: [.top], constant: 5)

        // - Name
        lblName.setEdgesToSuperview(excluding: [.bottom], constant: 10)

        // Price View
        // - Icon
        imgPrice.setTop(to: lblName.bottomAnchor, constant: 10)
        imgPrice.setLeading(to: infoView.leadingAnchor, constant: 10)
        imgPrice.setBottom(to: infoView.bottomAnchor, constant: 10)
        // - Price
        lblPrice.setLeading(to: imgPrice.trailingAnchor, constant: 5)
        lblPrice.setTop(to: lblName.bottomAnchor, constant: 10)
        lblPrice.setBottom(to: infoView.bottomAnchor, constant: 10)
    }

    func configure(result: SearchResult) {
        lblName.text = result.name
        imgPhoto.loadImage(url: result.cover)
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
}
