import * as React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import ListHotels from './screens/Hotels/HotelList'
import ListFavorites from './screens/Favorites/FavoriteList'
import { MaterialIcons } from '@expo/vector-icons'
import { rosa, azul } from './paleta/colors' 
import { createStackNavigator } from '@react-navigation/stack'
import Details from './screens/Details/Details'

const HomeStack = createStackNavigator();

function HotelstackScreen() {
  return (
    <HomeStack.Navigator>
      <HomeStack.Screen name="Hotéis" component={ListHotels} />
      <HomeStack.Screen name="Detalhes" component={Details}/>
    </HomeStack.Navigator>
  );
}

const SettingsStack = createStackNavigator();

function FavoritesStackScreen() {
  return (
    <SettingsStack.Navigator>
      <SettingsStack.Screen name="Favoritos" component={ListFavorites} />
      <SettingsStack.Screen name="Detalhes" component={Details} />
    </SettingsStack.Navigator>
  );
}

const Tab = createBottomTabNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Tab.Navigator screenOptions={({ route }) => ({
          tabBarIcon: ({ focused, color, size }) => {
            let iconName

            if (route.name === 'Hotéis') iconName = focused ? 'card-travel' : 'card-travel'
            else if (route.name === 'Favoritos') iconName = focused ? 'favorite' : 'favorite-border'
            
            color = focused ? rosa : azul

            // You can return any component that you like here!
            return <MaterialIcons name={iconName} size={size} color={color} />
          },
        })}
        tabBarOptions={{activeTintColor: rosa, inactiveTintColor: azul}}> 
        <Tab.Screen name="Hotéis" component={HotelstackScreen} />
        <Tab.Screen name="Favoritos" component={FavoritesStackScreen} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}