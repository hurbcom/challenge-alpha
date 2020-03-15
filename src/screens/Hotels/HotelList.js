import React from 'react';
import { StyleSheet, View, SafeAreaView, Modal, FlatList, Text} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'

class Hotels extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showLoading:true, 
      laodingFlat: true, 
      page: 1
    }
  }

  componentDidMount = () => {
    this.setState({laodingFlat:true}, ()=>{
      //carrega lista de hoteis
      api.get(`?q=buzios&page=${this.state.page}&sort=stars`).then(dados=>{
         // var array = this.props.Hotels.concat(dados.results)
          this.props.onSetHotels(dados.results)
          this.setState({showLoading:false, laodingFlat: false})
        }
      )
    })
  }

  loadMoreHotels = () => {
    this.setState({page: (this.state.page+1)}, ()=>{
        this.componentDidMount()
    })
  }

  renderFooter = () => {
    if (!this.state.laodingFlat) return null
    return (<Loading/>);
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
              keyExtractor={(index) => String(index)}
              renderItem={({ item }) => (<HotelItem navigation={this.props.navigation} item={item}/>)}
              keyExtractor={item => item.id}
              ListFooterComponent={this.renderFooter()}
            //  onEndReached={this.loadMoreHotels.bind(this)}
            //  onEndReachedThreshold={0.5}
            //  initialNumToRender={20}
              />
          </SafeAreaView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  }
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