import React, { Component } from 'react'
import { View, StyleSheet } from 'react-native'
import { Text } from 'native-base'


import { colors, metrics } from '../../styles'

export class AmenitiesBox extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Possui:</Text>
        {this.props.amenities.map(amenity => (
          <Text style={styles.text} key={amenity.name}>{amenity.name}</Text>
        ))}
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    marginVertical: 10,
    justifyContent: 'center',
    borderWidth: 1,
    borderColor: colors.primary,
    borderRadius: metrics.baseRadius,
  },

  title: {
    fontSize: 18,
    fontWeight: 'bold',
    color: colors.primary,
    marginBottom: 5,
  },

  text: {
    fontSize: 16,
    color: colors.primary,
    marginVertical: 5,
  }
});

export default AmenitiesBox