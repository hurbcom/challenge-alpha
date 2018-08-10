import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SectionList, View, StyleSheet } from 'react-native';
import { Container, Header, Body, Title, Content } from 'native-base';
import Image from 'react-native-remote-svg'

import { fetchHotels, setHotels } from '../../store/actions/hotelActions';

import { colors } from '../../styles'
import HotelListItem from './HotelListItem';
import SectionHeader from './SectionHeader';

export class HomeScreen extends Component {
  static navigationOptions = {
    title: 'Hotel Urbano',
    headerLeft: <Image
      source={require('../../assets/icons/icon-hu-white.svg')}
      style={{
        justifyContent: 'center',
        alignItems: 'center',
        marginHorizontal: 16,
      }}
    />,
    headerStyle: {
      backgroundColor: colors.primary,
    },
    headerTintColor: colors.white,
  }

  _setSectionContent = () => {
    let sectionContent = [];

    for(let i=5; i>=1; i--) {
      let data = this.props.hotels.filter(hotel => hotel.stars === i);
      if(data.length > 0) sectionContent.push({ data, title: i});
    }

    let packages = this.props.hotels.filter(hotel => hotel.stars === null);
    if(packages.length > 0) sectionContent.push({ data: packages, title: 'Pacotes'});

    return sectionContent;
  }

  
  render() {

    return (
      <Container>
        <Content style={styles.content}>
          <SectionList
            renderItem={({ item }) => <HotelListItem hotel={item} onHotelSelected={() => this.props.navigation.push('HotelDetailsScreen', {'hotel': item})}/>}
            renderSectionHeader={({ section }) => <SectionHeader title={section.title} />}
            sections={this._setSectionContent()}
            keyExtractor={(item) => item.name}
            stickySectionHeadersEnabled={true}
            enableEmptySections={true}
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