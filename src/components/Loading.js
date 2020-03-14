import React, { Component } from 'react'
import {View, ActivityIndicator} from 'react-native'

export default class Loading extends Component {
    render() {
        return (
            <View style={{  backgroundColor: '#a6a6a645', justifyContent: 'center', flex: 1, alignItems: 'center',}}>
                <ActivityIndicator size="large" color="tomato"/>
            </View>
        )
    }
}