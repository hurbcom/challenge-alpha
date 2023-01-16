import UIKit

final class PhotoCollectionCell: UICollectionViewCell {
    let photoView = UIImageView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        photoView.translatesAutoresizingMaskIntoConstraints = false
        
        contentView.addSubview(photoView)
        
        photoView.contentMode = .scaleAspectFill
        
        NSLayoutConstraint.activate([
            photoView.topAnchor.constraint(equalTo: topAnchor),
            photoView.leftAnchor.constraint(equalTo: leftAnchor),
            photoView.rightAnchor.constraint(equalTo: rightAnchor),
            photoView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    
    override func prepareForReuse() {
        photoView.image = nil
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setupCell(url: String?) {
        if let url {
            photoView.from(url: url)
        } else {
            photoView.image = UIImage(named: "Hurb")
        }
    }
}
