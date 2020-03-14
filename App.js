import React, { Component } from 'react'
import { Provider } from 'react-redux'
import Router from './src/Route'
import { store, persistor } from './src/redux/store'
import { PersistGate } from 'redux-persist/integration/react'

export default class App extends Component {
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