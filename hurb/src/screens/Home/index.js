import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SectionList, View, StyleSheet } from 'react-native';
import { Container, Header, Body, Title, Content } from 'native-base';

import { fetchHotels, setHotels } from '../../store/actions/hotelActions';

import HotelListItem from './HotelListItem';
import SectionHeader from '../../components/SectionHeader/SectionHeader';

export class HomeScreen extends Component {
  

  render() {
    return (
      <Container>
        <Header style={styles.header}>
          <Body>
              <Title>Sticky Headers</Title>
          </Body>
        </Header>
        <Content>
          <SectionList
            renderItem={({ item, index }) => <HotelListItem hotel={item} index={index} />}
            renderSectionHeader={({ section }) => <SectionHeader title={section.title} />}
            sections={[ // homogeneous rendering between sections
              {data: this.props.hotels.filter(hotel => hotel.stars === 5), title: '5 Estrelas'},
              {data: this.props.hotels.filter(hotel => hotel.stars === 4), title: '4 Estrelas'},
              {data: this.props.hotels.filter(hotel => hotel.stars === 3), title: '3 Estrelas'},
              {data: this.props.hotels.filter(hotel => hotel.stars === 2), title: '2 Estrelas'},
              {data: this.props.hotels.filter(hotel => hotel.stars === 1), title: '1 Estrelas'},
            ]}
            keyExtractor={(item) => item.name}
          />
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
  },

  header: {
    backgroundColor: '#cecece',
  }
});

const mapStateToProps = state => {
  return {
    hotels: state.hotels.hotels,
    isLoading: state.ui.isLoading,
  }
}

const mapDispatchToProps = dispatch => {
  return {
    fetchHotels: () => dispatch(fetchHotels()),
    setHotels: (hotels) => dispatch(setHotels(hotels)),
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(HomeScreen);