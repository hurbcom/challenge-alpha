import React from 'react';
import { AppearanceProvider, useColorScheme } from 'react-native-appearance';
import {
    NavigationContainer,
    DefaultTheme,
    DarkTheme,
} from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import HotelSearchList from './pages/HotelSearchList';
import HotelDetails from './pages/HotelDetails';

const AppStack = createStackNavigator();

export default function Routes() {
    const scheme = useColorScheme(); // captura a preferencia do tema do sistema do usuário(Darkmode);
    return (
        <AppearanceProvider>
            <NavigationContainer
                theme={scheme === 'dark' ? DarkTheme : DefaultTheme}
            >
                <AppStack.Navigator screenOptions={{ headerShown: false }}>
                    <AppStack.Screen
                        name="HotelSearchList"
                        component={HotelSearchList}
                    />
                    <AppStack.Screen
                        name="HotelDetails"
                        component={HotelDetails}
                    />
                </AppStack.Navigator>
            </NavigationContainer>
        </AppearanceProvider>
    );
}
