import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SectionList, View, StyleSheet } from 'react-native';
import { Container, Header, Body, Title, Content } from 'native-base';

import { fetchHotels, setHotels } from '../../store/actions/hotelActions';

import { colors } from '../../styles'
import HotelListItem from './HotelListItem';
import SectionHeader from '../../components/SectionHeader/SectionHeader';

export class HomeScreen extends Component {

  
  render() {
    let noStars = this.props.hotels.filter(hotel => hotel.stars === null);

    noStars = noStars.map(hotel => {
      return { data: [hotel], title: hotel.name };
    });

    let sectionContent = [
      { data: this.props.hotels.filter(hotel => hotel.stars === 5), title: 5 },
      { data: this.props.hotels.filter(hotel => hotel.stars === 4), title: 4 },
      { data: this.props.hotels.filter(hotel => hotel.stars === 3), title: 3 },
      { data: this.props.hotels.filter(hotel => hotel.stars === 2), title: 2 },
      { data: this.props.hotels.filter(hotel => hotel.stars === 1), title: 1 },
      ...noStars,
    ];

    console.log("section content", sectionContent);

    return (
      <Container>
        <Header style={styles.header}>
          <Body>
              <Title>Hotel Urbano</Title>
          </Body>
        </Header>
        <Content style={styles.content}>
          <SectionList
            renderItem={({ item }) => <HotelListItem hotel={item} />}
            renderSectionHeader={({ section }) => <SectionHeader title={section.title} />}
            sections={sectionContent}
            keyExtractor={(item) => item.name}
            stickySectionHeadersEnabled={true}
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
    backgroundColor: colors.primaryLight,
  },

  content: {
    backgroundColor: colors.white,
  },

  header: {
    elevation: 4,
    backgroundColor: colors.primary,
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