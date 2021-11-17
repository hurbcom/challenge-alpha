import UIKit

extension UIView {
    func setCodable() {
        translatesAutoresizingMaskIntoConstraints = false
    }

    func setSize(height: CGFloat, width: CGFloat) {
        setWidth(width)
        setHeight(height)
    }

    func setEdgesToSuperview(excluding: Set<AnchorSide> = [], constant: CGFloat = 0) {
        guard let parent = superview else { return }
        if !excluding.contains(.top) { setTop(to: parent.topAnchor, constant: constant) }
        if !excluding.contains(.leading) { setLeading(to: parent.leadingAnchor, constant: constant) }
        if !excluding.contains(.trailing) { setTrailing(to: parent.trailingAnchor, constant: constant) }
        if !excluding.contains(.bottom) { setBottom(to: parent.bottomAnchor, constant: constant) }
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

enum AnchorSide: CaseIterable {
    case top, bottom, leading, trailing
}
