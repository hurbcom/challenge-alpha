import React, { Component } from 'react';
import { View, StyleSheet, Image } from 'react-native';
import { Text, Container, Header, Body, Title, Content } from 'native-base';

import { colors, metrics } from '../../styles'

export class HotelDetailsScreen extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      hotel: this.props.navigation.getParam('hotel', {}),
    }
  }

  render() {
    return (
      <Container>
        <Header style={styles.header}>
          <Body>
            <Title>{this.state.hotel.name}</Title>
          </Body>
        </Header>
        <Content style={styles.content}>
          <Image source={{ uri: this.state.hotel.image}} style={styles.image} resizeMode="cover" />
          <Text>{this.state.hotel.title}</Text>
          <Text>{this.state.hotel.city}</Text>
          <Text>{this.state.hotel.state}</Text>

          {/* acomodities */}

          <Text>{this.state.hotel.price}</Text>
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

  image: {
    width: metrics.screenWidth,
    height: metrics.screenWidth * 0.75,
  }
});

export default HotelDetailsScreen;