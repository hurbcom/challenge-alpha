import React from 'react';
import { StyleSheet, View, SafeAreaView, Modal, FlatList} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'

var page = 1

class Hotels extends React.Component {
  constructor() {
    super()
    this.state = {
      showLoading:true
    }
  }

  componentDidMount = () => {
    //carrega lista de hoteis
    api.get(`?q=Rio de Janeiro&page=${page}`).then(dados=>{
        this.props.onSetHotels(dados.results)
        this.setState({showLoading:false})
      }
    )
  }

  render(){
    return (
      <View style={styles.container}>
          <Modal animationType="fade" transparent={true} visible={this.state.showLoading}>
            <Loading />
          </Modal>

          <SafeAreaView style={styles.container}>
            <FlatList
              data={this.props.Hotels}
              renderItem={({ item }) => (<HotelItem item={item}/>)}
              keyExtractor={item => item.id}/>
          </SafeAreaView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
}); 

const mapStateToProps = ({ hotelRdc }) => {
  return {
      Hotels: hotelRdc.hotels,
  }
}

const mapDispatchToProps = dispatch => {
  return {
    onSetHotels: hotels => dispatch(set_hotels(hotels))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Hotels)