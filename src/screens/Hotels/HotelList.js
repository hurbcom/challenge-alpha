import React from 'react';
import { StyleSheet, View, SafeAreaView, Modal, FlatList, Text} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'

class Hotels extends React.Component {
  constructor() {
    super()
    this.state = {
      showLoading:true, 
      page: 1
    }
  }

  componentDidMount = () => {
    //carrega lista de hoteis
    api.get(`?q=buzios&page=${this.state.page}&sort=stars`).then(dados=>{
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
            <Text style={styles.title}>Hotéis</Text>
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
  },
  title: {
    marginTop: 20, 
    marginLeft: 10,
    marginBottom: 20, 
    fontSize: 40,
    fontWeight: 'bold',
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