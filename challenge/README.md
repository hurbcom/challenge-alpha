# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Desafio Alpha

<p align="center">
  <img src="../ca.jpg" alt="Challange accepted" />
</p>

[![Build Status](https://travis-ci.org/felipehjcosta/challenge-alpha.svg?branch=master)](https://travis-ci.org/felipehjcosta/challenge-alpha)
[![codebeat badge](https://codebeat.co/badges/1de44d75-71bc-498d-a52b-918fb56421b4)](https://codebeat.co/projects/github-com-felipehjcosta-challenge-alpha-master)

## How to run 

The project is in `/challenge/android` and you can run the apk module with 2 ways:
* Open with Android Studio
* Run the command `./gradlew build installDebug`

If you would like to run the instant app module, you can run the following commands:

1. Build instantapp module
```shell
/gradlew :instantapp:assembleDebug
```
2. Unzip bundle
```shell
unzip instantapp/build/outputs/apk/debug/instantapp-debug.zip -d instantapp/build/outputs/apk/debug/instantapp-debug/
```
3. Install apks
```shell
adb install-multiple -r -t --ephemeral instantapp/build/outputs/apk/debug/instantapp-debug/base-debug.apk instantapp/build/outputs/apk/debug/instantapp-debug/search-debug.apk
```
4. Open deeplink
```shell
adb shell am start -a android.intent.action.VIEW -d "https://felipehjcosta.github.com"
```
