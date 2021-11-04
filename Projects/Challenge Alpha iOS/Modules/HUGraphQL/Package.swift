// swift-tools-version:5.5
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "HUGraphQL",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v13),
        .macOS(.v10_15),
    ],
    products: [
        .library(
            name: "HUGraphQL",
            targets: ["HUGraphQL"]),
    ],
    dependencies: [
        .package(name: "Apollo", url: "https://github.com/apollographql/apollo-ios.git", .upToNextMinor(from: "0.49.0")),
    ],
    targets: [
        .target(
            name: "HUGraphQL",
            dependencies: [
                .product(name: "Apollo", package: "Apollo"),
                .product(name: "ApolloWebSocket", package: "Apollo"),
            ])
    ],
    swiftLanguageVersions: [.v5]
)
