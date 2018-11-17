import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Image, StyleSheet, Text, View, ListView, ActivityIndicator } from 'react-native';
import * as hotelsActions from '../actions/hotelsActions';

class HomeScreen extends React.Component {
  
  static navigationOptions = {
    title: 'Home',
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
    this.setState({ 
      dataSource: ds.cloneWithRows(nextProps.hotels), 
      _data: nextProps.hotels, 
      isLoading: false 
    });
  }

  renderRow(rowData){
    return (
      <View style={styles.listItem}>
        <Text>{rowData.name}</Text>
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
    padding: 6,
  },
  imageWrapper: {
    padding: 5,
  },
  title: {
    fontSize: 20,
    textAlign: 'left',
    margin: 6
  }
});
