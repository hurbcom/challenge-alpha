// swift-tools-version:5.5
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "HUNetwork",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v13),
        .macOS(.v10_15),
    ],
    products: [
        .executable(name: "ApolloCodegen", targets: ["ApolloCodegen"]),
        .library(name: "HUGraphQL", targets: ["HUGraphQL"]),
    ],
    dependencies: [
        .package(name: "Apollo", url: "https://github.com/apollographql/apollo-ios.git", .upToNextMinor(from: "0.49.0")),
        .package(url: "https://github.com/apple/swift-argument-parser.git", .upToNextMinor(from: "0.5.0")),
    ],
    targets: [
        .target(
            name: "HUGraphQL",
            dependencies: [
                .product(name: "Apollo", package: "Apollo"),
                .product(name: "ApolloWebSocket", package: "Apollo"),
            ],
            exclude: ["Queries"]
        ),
        .executableTarget(
            name: "ApolloCodegen",
            dependencies: [
                .product(name: "ApolloCodegenLib", package: "Apollo"),
                .product(name: "ArgumentParser", package: "swift-argument-parser")
            ],
            exclude: ["ApolloCLI"])
    ],
    swiftLanguageVersions: [.v5]
)
