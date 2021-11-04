import Foundation
import ApolloCodegenLib
import ArgumentParser

// An outer structure to hold all commands and sub-commands handled by this script.
struct SwiftScript: ParsableCommand {

    static var projectFolderName = "HotelUrbano"
    static var generatedFolder = "GraphQL"
    static let endpoint = "http://localhost:8010/graphql"
    
    static var configuration = CommandConfiguration(
            abstract: """
        A swift-based utility for performing Apollo-related tasks.
        
        NOTE: If running from a compiled binary, prefix subcommands with `swift-script`. Otherwise use `swift run ApolloCodegen [subcommand]`.
        """,
            subcommands: [DownloadSchema.self, GenerateCode.self])
    
    /// The sub-command to download a schema from a provided endpoint.
    struct DownloadSchema: ParsableCommand {
        static var configuration = CommandConfiguration(
            commandName: "downloadSchema",
            abstract: "Downloads the schema with the settings you've set up in the `DownloadSchema` command in `main.swift`.")
        
        mutating func run() throws {
            let fileStructure = try FileStructure()
            CodegenLogger.log("File structure: \(fileStructure)")
            
            // Set up the URL you want to use to download the project
            let endpoint = URL(string: SwiftScript.endpoint)!
            
            
            // Calculate where you want to create the folder where the schema will be downloaded by the ApolloCodegenLib framework.
            let folderForDownloadedSchema = fileStructure
                .huGraphQLRootURL
            
            // Make sure the folder is created before trying to download something to it.
            try FileManager.default.apollo.createFolderIfNeeded(at: folderForDownloadedSchema)
            
            // Create an options object for downloading the schema. Provided code will download the schema via an introspection query to the provided URL as JSON to a file called "schema.json". For full options check out https://www.apollographql.com/docs/ios/api/ApolloCodegenLib/structs/ApolloSchemaOptions/
            let schemaConfiguration = ApolloSchemaDownloadConfiguration(using: .introspection(endpointURL: endpoint), timeout: 20, headers: [], outputFolderURL: folderForDownloadedSchema, schemaFilename: "schema")
            
            // Actually attempt to download the schema.
            try ApolloSchemaDownloader.fetch(with: schemaConfiguration)
        }
    }
    
    /// The sub-command to actually generate code.
    struct GenerateCode: ParsableCommand {
        static var configuration = CommandConfiguration(
            commandName: "generate",
            abstract: "Generates swift code from your schema + your operations based on information set up in the `GenerateCode` command.")
        
        mutating func run() throws {
            let fileStructure = try FileStructure()
            CodegenLogger.log("File structure: \(fileStructure)")
            
            // Get the root of the target for which you want to generate code.
            let targetRootURL = fileStructure.huGraphQLRootURL
            
            let schemeURL = targetRootURL.appendingPathComponent("schema.graphqls")
            
            let pathToGenerate = fileStructure.huGraphQLRootURL.appendingPathComponent("Generated")
            
            // Make sure the folder exists before trying to generate code.
            try FileManager.default.apollo.createFolderIfNeeded(at: pathToGenerate)

            // Create the Codegen options object. This default setup assumes `schema.json` is in the target root folder, all queries are in some kind of subfolder of the target folder and will output as a single file to `API.swift` in the target folder. For alternate setup options, check out https://www.apollographql.com/docs/ios/api/ApolloCodegenLib/structs/ApolloCodegenOptions/
            let codegenOptions = ApolloCodegenOptions(
                modifier: .internal,
                namespace: "HUGraphQL",
                outputFormat: .multipleFiles(inFolderAtURL: pathToGenerate),
                customScalarFormat: .passthroughWithPrefix("HUGScalar"),
                urlToSchemaFile: schemeURL)
            
            // Actually attempt to generate code.
            try ApolloCodegen.run(from: targetRootURL,
                                  with: fileStructure.cliFolderURL,
                                  options: codegenOptions)
        }
    }
}

// This will set up the command and parse the arguments when this executable is run.
SwiftScript.main()
