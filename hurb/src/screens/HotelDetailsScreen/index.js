import React, { Component } from 'react';
import { View, StyleSheet, Image, TouchableWithoutFeedback } from 'react-native';
import { Text, Container, Left, Header, Body, Title, Content, Icon } from 'native-base';

import { colors, metrics } from '../../styles'
import { formatPrice } from '../../service/numeral'

import AmenitiesBox from './AmenitiesBox'

export class HotelDetailsScreen extends Component {
  static navigationOptions = {
    title: 'Detalhes da Hospedagem',
    headerStyle: {
      backgroundColor: colors.primary,
    },
    headerTintColor: colors.white,
  }
  
  constructor(props) {
    super(props);
    this.state = {
      hotel: this.props.navigation.getParam('hotel', {}),
    }
  }

  render() {
    return (
      <Container>
        {/* <Header style={styles.header}>
          <Left>
            <TouchableWithoutFeedback onPress={() => this.props.navigation.goBack()}>
              <Icon name="arrow-back" style={{ color: 'white'}} />
            </TouchableWithoutFeedback>
          </Left>
          <Body>
            <Title>{this.state.hotel.name}</Title>
          </Body>
        </Header> */}
        <Content>
          <Image source={{ uri: this.state.hotel.image}} style={styles.image} resizeMode="cover" />
          <View style={styles.content}>
            <Text style={styles.titleText}>{this.state.hotel.name}</Text>

            <View style={styles.textBox}>
              <Text style={styles.labelText}>Cidade:</Text>
              <Text style={styles.subText}>{this.state.hotel.city}</Text>
            </View>

            <View style={styles.textBox}>
              <Text style={styles.labelText}>Estado:</Text>
              <Text style={styles.subText}>{this.state.hotel.state}</Text>
            </View>

            <AmenitiesBox amenities={this.state.hotel.amenities} />

            <Text style={styles.priceText}>{formatPrice(this.state.hotel.price)}</Text>
          </View>
        </Content>
      </Container>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryLight,
  },

  content: {
    paddingVertical: 10,
    paddingHorizontal: 16,
  },

  image: {
    width: metrics.screenWidth,
    height: metrics.screenWidth * 0.75,
  },

  textBox: {
    marginVertical: 5,
  },

  labelText: {
    fontSize: 16,
    fontWeight: 'bold',
  },

  titleText: {
    fontSize: 25,
    fontWeight: 'bold',
    textAlign: 'center',
    marginVertical: 10,
  },

  subText: {
    fontSize: 20,
  },

  priceText: {
    fontSize: 30,
    textAlign: 'right',
    fontWeight: 'bold',
    marginVertical: 10,    
  },
});

export default HotelDetailsScreen;