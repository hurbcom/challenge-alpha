import React, { Component } from 'react';
import { Text, View, StyleSheet, ActivityIndicator } from 'react-native';
import { connect } from 'react-redux';

import { fetchHotels, setHotels } from '../../store/actions/hotelActions';


export class SplashScreen extends Component {

  async componentWillMount() {
    await Expo.Font.loadAsync({
      'Roboto': require('native-base/Fonts/Roboto.ttf'),
      'Roboto_medium': require('native-base/Fonts/Roboto_medium.ttf'),
    });

    let hotels = await this.props.fetchHotels();
    console.log(hotels);
    await this.props.setHotels(hotels);
    // this.props.storeHotels(hotels);
    this.props.navigation.navigate('HomeStack');
  }
  
  render() {
    return (
      <View style={styles.container}>
        <ActivityIndicator />
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  }
});

const mapDispatchToProps = dispatch => {
  return {
    fetchHotels: () => dispatch(fetchHotels()),
    setHotels: (hotels) => dispatch(setHotels(hotels)),
  }
}

export default connect(null, mapDispatchToProps)(SplashScreen);