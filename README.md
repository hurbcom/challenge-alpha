# Hurb Alpha

### Version

0.1

### Build and Runtime Requirements

Xcode 6.0 or later
iOS 10.0 or later

### Configuring the Project

Configure the Team for each target within the project.

Run the project or the unity tests directly from XCode

### About Hurb Alpha

Hurb Alpha is an app developed for the Alpha Challenge - Hurb.
When running the application, the sistem will fetch from the API (https://www.hurb.com/search/api?q=buzios&page=1) the hotels and packages results and will display it on the main page (Feed) sorted by stars category (packages will be at the end, if exists). When touching an item on the Feed, it will open a details page where you can see more information on the hotel/package chosen and the link for opening the hotel/package on the Hurb main website. You also have the option to favorite a hotel on the details page and your favorites hotels/packages will appear on the Favorites page (second item on tab bar). 
* The application does not include a local persistence for loading favorites from ended run sessions. 

#### Written in Objective-C and Swift

### Unit Tests and UITests

Hurb Alpha has unit tests written for the asyncronous functions on the application. These tests are in the HurbAlphaTests group. To run the unit tests, select the HurbAlphaTests scheme in the Scheme menu. Then hold the Run button down and select the "Test" option, or press Command+u to run the tests. The application does not count with UITests due to the inaccuracy of these tests on async content table views.

Last modified: 11/09/2019 - Júlia Affonso Figueiredo Rocha
