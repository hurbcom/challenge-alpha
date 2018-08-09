import { createStackNavigator, createSwitchNavigator } from 'react-navigation'

import SplashScreen from '../screens/SplashScreen'
import HomeScreen from '../screens/HomeScreen'
import HotelDetailsScreen from '../screens/HotelDetailsScreen'

export const HomeStack = createStackNavigator(
  {
    HomeScreen: { screen: HomeScreen },
    HotelDetailsScreen: { screen: HotelDetailsScreen },
  },
);

export const LauncherNavigator = createSwitchNavigator(
  {
    SplashScreen: { screen: SplashScreen },
    HomeStack: { screen: HomeStack },
  }, 
);