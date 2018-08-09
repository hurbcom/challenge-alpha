import React, { Component } from 'react'
import { View, StyleSheet, Image, TouchableOpacity } from 'react-native'
import { Text, Row } from 'native-base'

import { colors, metrics } from '../../styles'
import { formatPrice } from '../../service/numeral'

export class HotelListItem extends Component {
  render() {
    return (
      <TouchableOpacity onPress={this.props.onHotelSelected}>
        <Row style={styles.container}>
          <Image source={{ uri: this.props.hotel.image}} style={styles.image} resizeMode="cover" />
          <View style={styles.textsRight}>
            <Text style={styles.titleText}>{this.props.hotel.name}</Text>
            <Text style={styles.subText}>{this.props.hotel.city}</Text>
            <Text style={styles.subText}>{this.props.hotel.state}</Text>
            <View style={styles.priceRow}>
              <Text style={styles.priceText}>{formatPrice(this.props.hotel.price)}</Text>
            </View>
          </View>
        </Row>
      </TouchableOpacity>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    height: 100,
    elevation: 2,
    borderRadius: metrics.baseRadius,
    marginHorizontal: 10,
    marginVertical: 8,
    overflow: 'hidden',
  },

  image: {
    height: 100,
    width: 100,
    marginRight: 10,
  },

  textsRight: {
    flex: 1,
    justifyContent: 'space-around',
  },
  
  priceRow: {
    alignItems: 'flex-end',
    marginRight: 10,
  },
  
  titleText: {
    fontSize: 18,
    fontWeight: 'bold',
  },

  subText: {
    fontSize: 16,
  },

  priceText: {
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default HotelListItem;