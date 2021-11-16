import UIKit

extension UIView {
    func setCodable() {
        translatesAutoresizingMaskIntoConstraints = false
    }

    func setSize(height: CGFloat, width: CGFloat) {
        setWidth(width)
        setHeight(height)
    }

    func setEdgesToSuperview() {
        guard let parent = superview else { return }
        setTop(to: parent.topAnchor)
        setLeading(to: parent.leadingAnchor)
        setTrailing(to: parent.trailingAnchor)
        setBottom(to: parent.bottomAnchor)
    }

    func setHeight(_ height: CGFloat) {
        heightAnchor.constraint(equalToConstant: height).isActive = true
    }

    func setWidth(_ width: CGFloat) {
        widthAnchor.constraint(equalToConstant: width).isActive = true
    }

    func setEqualWidth(to view: UIView) {
        widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
    }

    func setCenterX(to view: NSLayoutXAxisAnchor, constant: CGFloat = 0) {
        centerXAnchor.constraint(equalTo: view, constant: constant).isActive = true
    }

    func setBottom(to view: NSLayoutYAxisAnchor, constant: CGFloat = 0) {
        bottomAnchor.constraint(equalTo: view, constant: constant * -1).isActive = true
    }

    func setTop(to view: NSLayoutYAxisAnchor, constant: CGFloat = 0) {
        topAnchor.constraint(equalTo: view, constant: constant).isActive = true
    }

    func setLeading(to view: NSLayoutXAxisAnchor, constant: CGFloat = 0) {
        leadingAnchor.constraint(equalTo: view, constant: constant).isActive = true
    }

    func setTrailing(to view: NSLayoutXAxisAnchor, constant: CGFloat = 0) {
        trailingAnchor.constraint(equalTo: view, constant: constant * -1).isActive = true
    }
}
