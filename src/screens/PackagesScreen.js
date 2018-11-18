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
    return (
      <TouchableNativeFeedback onPress={this.handleClick.bind(this, rowData)} >
        <View style={styles.listItem}>
          <View style={styles.imageWrapper}>
            <Image
              style={{ width: 70, height: 70 }}
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
              {rowData.smallDescription}
            </Text>
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
    borderRadius: 20
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
