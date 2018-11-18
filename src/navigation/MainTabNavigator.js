import React from 'react';
import { Platform } from 'react-native';
import { createStackNavigator, createBottomTabNavigator } from 'react-navigation';

import TabBarIcon from '../components/TabBarIcon';
import HotelsScreen from '../screens/HotelsScreen';
import PackagesScreen from '../screens/PackagesScreen';
import SettingsScreen from '../screens/SettingsScreen';

const HotelsStack = createStackNavigator({
  Links: HotelsScreen,
});

HotelsStack.navigationOptions = {
  tabBarLabel: 'Hoteis',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={Platform.OS === 'ios' ? 'ios-bed' : 'md-bed'}
    />
  ),
};

const PackagesStack = createStackNavigator({
  Links: PackagesScreen,
});

PackagesStack.navigationOptions = {
  tabBarLabel: 'Pacotes',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={Platform.OS === 'ios' ? 'ios-gift' : 'md-gift'}
    />
  ),
};

const SettingsStack = createStackNavigator({
  Settings: SettingsScreen,
});

SettingsStack.navigationOptions = {
  tabBarLabel: 'Informações',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={
        Platform.OS === 'ios'
          ? `ios-information-circle${focused ? '' : '-outline'}`
          : 'md-information-circle'
      }
    />
  ),
};

export default createBottomTabNavigator({
  HotelsStack,
  PackagesStack,
  SettingsStack,
},
{
  tabBarOptions: {
    style: { 
      backgroundColor: '#000000',
      padding: 2
    }
  }
});
