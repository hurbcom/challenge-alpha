import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { 
  Image, 
  StyleSheet, 
  Text, 
  View, 
  ListView, 
  ActivityIndicator, 
  TouchableNativeFeedback 
} from 'react-native';
import { WebBrowser } from 'expo';
import * as hotelsActions from '../actions/hotelsActions';

class PackagesScreen extends React.Component {
  
  static navigationOptions = {
    title: 'Pacotes',
    headerStyle: { backgroundColor: '#000000' },
    headerTitleStyle: { color: '#BBC933' }
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

    let packages = nextProps.hotels.filter(item => item.isPackage)

    this.setState({ 
      dataSource: ds.cloneWithRows(packages), 
      _data: packages, 
      isLoading: false 
    });
  }

  handleClick(rowData){
    WebBrowser.openBrowserAsync(rowData.url);
  }

  renderRow(rowData){

    let { amenities } = rowData;
    var amenitiesBoard = [
        <Text key={0} style={styles.noAmenity}>
          {'Sem mimos'}
        </Text>
    ]
    if(amenities.length > 0){
      amenitiesBoard = [
          <Text key={0} style={styles.amenity}>
            {amenities[0].name == null ? '' : amenities[0].name}
          </Text> ,
          <Text key={1} style={styles.amenity}>
            {amenities[1].name == null ? '' : amenities[1].name}
          </Text> ,
          <Text key={2}style={styles.amenity}>
            {amenities[2].name == null ? '' : amenities[2].name}
          </Text>
      ]      
    }

    return (
      <TouchableNativeFeedback onPress={this.handleClick.bind(this, rowData)} >
      <View style={styles.listItem}>
        <View style={styles.imageWrapper}>
          <Image
            style={{ width: 130, height: 130, borderRadius: 8 }}
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
          <View>
            { amenitiesBoard } 
          </View>
        </View>
      </View>
      </TouchableNativeFeedback>
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

export default connect(mapStateToProps , mapDispatchToProps)(PackagesScreen);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 15,
    backgroundColor: '#ffffff',
    textDecorationColor: '#202026'
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
    borderRadius: 20
  },
  title: {
    fontSize: 16,
    color: '#2D2034',
    textAlign: 'left',
    margin: 5
  },
  stars: {
    marginBottom: 6,
    width: 15,
    height: 15
  },
  starsContainer: {
    flex: 1
  },
  amenity: {
    fontSize: 12,
    textAlign: 'left',
    marginLeft: 5,
    marginRight: 5,
    color: '#4CBB91'
  },
  noAmenity: {
    fontSize: 12,
    textAlign: 'left',
    margin: 5,
    color: 'red'
  }
});
