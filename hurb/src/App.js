import React, { Component } from 'react';

import NavigatorActions from './service/navigationActions';
import { LauncherNavigator } from './navigation/navigation';


class App extends Component {
  componentDidMount() {
    NavigatorActions.setTopLevelNavigator(this._launcher);
  }

  render() {
    return (
      <LauncherNavigator ref={ref => this._launcher = ref} />
    );
  }
}

export default App;