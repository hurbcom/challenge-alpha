import React, { Component } from 'react'
import {View, ActivityIndicator} from 'react-native'
import { rosa } from '../paleta/colors'

export default class Loading extends Component {
    render() {
        return (
            <View style={{  backgroundColor: this.props.transparent ? 'transparent' : '#a6a6a690', justifyContent: 'center', flex: 1, alignItems: 'center',}}>
                <ActivityIndicator size="large" color={rosa}/>
            </View>
        )
    }
}