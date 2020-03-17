import React, { Component } from 'react'
import { Provider } from 'react-redux'
import Router from './src/Route'
import { store, persistor } from './src/redux/store'
import { PersistGate } from 'redux-persist/integration/react'
import { StatusBar, Platform } from 'react-native'

export default class App extends Component {
  componentDidMount(){
    console.disableYellowBox = true // Oculta Warning na tela do app 😉
    Platform.OS === 'ios' ? StatusBar.setBarStyle('dark-content') : StatusBar.setBarStyle('light-content')
  }

  render() {
    return(
        <Provider store={store}>
          <PersistGate loading={null} persistor={persistor}>
              <Router />
          </PersistGate>
        </Provider>
    )
  }
}