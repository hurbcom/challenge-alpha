import UIKit
import ScrollViewController

final class ProductDetailViewController: UIViewController {
    // MARK: UI

    lazy private var screenView = ProductDetailView()
    lazy private var scrollViewController = ScrollViewController()
    
    // MARK: Properties
    
    private let viewModel: ProductDetailViewModel

    // MARK: Init
    
    init(model: Product) {
        self.viewModel = ProductDetailViewModel(model: model)
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) { nil }
    
    // MARK: Override
    
    override func viewDidLoad() {
        super.viewDidLoad()
        embedScrollViewController()
    }
    
    private func embedScrollViewController() {
        addChild(scrollViewController)
        view.addSubview(scrollViewController.view)
        scrollViewController.scrollView.backgroundColor = .white
        scrollViewController.view.translatesAutoresizingMaskIntoConstraints = false
        scrollViewController.view.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        scrollViewController.view.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        scrollViewController.view.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        scrollViewController.view.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        scrollViewController.didMove(toParent: self)
        scrollViewController.contentView = screenView
    }
}
