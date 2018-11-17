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
  tabBarLabel: 'Hotéis',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={Platform.OS === 'ios' ? 'ios-link' : 'md-link'}
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
      name={Platform.OS === 'ios' ? 'ios-link' : 'md-link'}
    />
  ),
};

const SettingsStack = createStackNavigator({
  Settings: SettingsScreen,
});

SettingsStack.navigationOptions = {
  tabBarLabel: 'Configurações',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={Platform.OS === 'ios' ? 'ios-options' : 'md-options'}
    />
  ),
};

export default createBottomTabNavigator({
  HotelsStack,
  PackagesStack,
  SettingsStack,
});
