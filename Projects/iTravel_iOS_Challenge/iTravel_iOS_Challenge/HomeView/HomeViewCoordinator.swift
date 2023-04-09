//
//  HomeViewCoordinator.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import UIKit

class MovieListCoordinator: BaseCoordinator {
    private var viewModel: HomeViewModel = HomeViewModel()
    
    override func start() {

        let homeViewController = HomeViewController()
        homeViewController.viewmodel = viewModel
        self.navigationController = UINavigationController(rootViewController: homeViewController)
        self.navigationController.viewControllers = [homeViewController]
    }
    
    private func setupBinding() {
        viewModel.viewDidDisappear.subscribe(to: self) { this, _ in
            this.parentCoordinator?.didFinish(coordinator: this)
        }
    }
    

//
//    private func navigateToMovie(movie: Movie) {
//        let movieCoordinator = MovieCoordinator(movie: movie)
//        movieCoordinator.navigationController = navigationController
//        start(coordinator: movieCoordinator)
//    }
//
//    private func navigateToLogin() {
//        let loginCoordinator = LoginCoordinator()
//        loginCoordinator.navigationController = navigationController
//        start(coordinator: loginCoordinator)
//    }
}
