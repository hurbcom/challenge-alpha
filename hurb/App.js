import React, { Component } from 'react';
import { Provider } from 'react-redux';

import configureStore from './src/store/store';

import App from './src/App';

class Root extends Component {
  store = configureStore();

  render() {
    return (
      <Provider store={this.store}>
        <App />
      </Provider>
    );
  }
}

export default Root;
