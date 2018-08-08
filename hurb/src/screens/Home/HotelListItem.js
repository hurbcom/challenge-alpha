import React, { Component } from 'react'
import { View, StyleSheet, Image } from 'react-native'
import { Text, Row } from 'native-base'

import { colors, metrics } from '../../styles'

export class HotelListItem extends Component {
  render() {
    return (
      <Row style={styles.container}>
        <Image uri={{ uri: this.props.hotel.image}} style={styles.image} resizeMode="contain" />
        <View>
          <Text>{this.props.hotel.name}</Text>

        </View>
      </Row>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    height: 100,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 2,
    borderRadius: metrics.baseRadius,
    marginHorizontal: 10,
    marginVertical: 5,
    overflow: 'hidden',
  },

  image: {
    height: 100,
    width: 100,
  }
});

export default HotelListItem;