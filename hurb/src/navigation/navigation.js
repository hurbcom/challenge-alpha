import { createStackNavigator, createSwitchNavigator } from 'react-navigation'

import HomeScreen from '../screens/Home'
import SplashScreen from '../screens/SplashScreen'

export const HomeStack = createStackNavigator(
  {
    HomeScreen: { screen: HomeScreen },
  }, 
  {
    headerMode: 'none'
  }
);

export const LauncherNavigator = createSwitchNavigator(
  {
    SplashScreen: { screen: SplashScreen },
    HomeStack: { screen: HomeStack },
  }, 
  {
    headerMode: 'none'
  }
);