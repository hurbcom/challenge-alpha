import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Image, StyleSheet, Text, View, ListView, ActivityIndicator } from 'react-native';
import * as hotelsActions from '../actions/hotelsActions';

class HomeScreen extends React.Component {
  
  static navigationOptions = {
    title: 'Hotéis',
  };

  constructor(props){
    super(props);
    this.state = {
      dataSource: null,
      isLoading: true,
      _data: null,
    }

    this.renderRow = this.renderRow.bind(this);
  }

  componentWillMount(){
   this.props.actions.getHotelsByLocation('Rio de Janeiro');
  }

  componentWillReceiveProps(nextProps){
    let ds = new ListView.DataSource({
      rowHasChanged: (r1, r2) => r1 !== r2,
    })

    //Filter packages
    let hotels = nextProps.hotels.filter(item => item.isHotel)
    let sortedByStarHotels = hotels.sort((a, b) => {
      if (a.stars < b.stars)
        return 1;
      if (a.stars > b.stars)
        return 1;
      return 0;
    });

    this.setState({ 
      dataSource: ds.cloneWithRows(sortedByStarHotels), 
      _data: sortedByStarHotels, 
      isLoading: false 
    });
  }

  renderRow(rowData){
    let { amenities } = rowData;
    var amenitiesBoard = (<View/>)
    if(amenities.length > 0){
      amenitiesBoard = (
        <View>
          <Text style={styles.amenity}>
            {amenities[0].name == null ? '' : amenities[0].name}
          </Text>
          <Text style={styles.amenity}>
            {amenities[1].name == null ? '' : amenities[1].name}
          </Text>
          <Text style={styles.amenity}>
            {amenities[2].name == null ? '' : amenities[2].name}
          </Text>
        </View>
      )
    }

    return (
      <View style={styles.listItem}>
        <View style={styles.imageWrapper}>
          <Image
            style={{ width: 110, height: 110 }}
            source={{
              uri: rowData.image === '' ||
                rowData.image === null
                ? 'https://via.placeholder.com/70x70.jpg'
                : rowData.image,
            }}
          />
        </View>
        <View style={{ flex: 1 }}>
          <Text style={styles.title}>
            {rowData.name}
          </Text>
          <Text style={styles.subtitle}>
            {rowData.stars}
          </Text>
          { amenitiesBoard } 
        </View>
      </View>
    );
  }

  render() {
    if (this.state.isLoading > 0) {
      return (
        <View style={styles.container}>
          <ActivityIndicator size="large" />
        </View>
      );
    } else {
      return (
        <ListView
          dataSource={this.state.dataSource}
          renderRow={this.renderRow}
          onEndReached={() => {/* do nothing */}}
          renderFooter={() => {
            return (
              this.state.isLoadingMore &&
              <View style={{ flex: 1, padding: 10 }}>
                <ActivityIndicator size="small" />
              </View>
            );
          }}
        />
      );
    }
  }
}


const mapStateToProps = (state, ownProps) => 
  ({ hotels: state.hotels });

const mapDispatchToProps = (dispatch) =>
  ({ actions: bindActionCreators(hotelsActions, dispatch) });

export default connect(mapStateToProps , mapDispatchToProps)(HomeScreen);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 15,
    backgroundColor: '#fff',
  },
  listItem: {
    flex: 1,
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderBottomColor: '#d6d7da',
    padding: 5,
  },
  imageWrapper: {
    padding: 4,
  },
  title: {
    fontSize: 16,
    textAlign: 'left',
    margin: 5
  },
  subtitle: {
    fontSize: 12,
    textAlign: 'left',
    margin: 5,
  },
  amenity: {
    fontSize: 12,
    textAlign: 'left',
    marginLeft: 5,
    marginRight: 5
  }
});
