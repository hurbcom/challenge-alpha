import React from 'react';
import { StyleSheet, View, SafeAreaView, Modal, SectionList, Text} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'
import { azul } from '../../paleta/colors'

class Hotels extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showLoading:true, 
      loadingFlat: true, 
      page: 1,
    }
  }

  componentDidMount = () => {
    this.loadHotels()
  }

  //agrupa por estrelas ou pacotes
  groupStarsPackages = () => {
    let grupos = []

    for(let i=5; i>=1; i--) {
      let hotels = this.props.Hotels.filter(hotel => hotel.stars === i)
      if(hotels.length > 0) grupos.push({ data: hotels, titulo: `${i} estrelas`})
    }

    let packages = this.props.Hotels.filter(pack => pack.stars === null)
    if(packages.length > 0) grupos.push({ data: packages, titulo: 'Pacotes'})

    return grupos
  }

  //carrega lista de hoteis
  loadHotels = () => {
    this.setState({loadingFlat:true}, ()=>{
      api.get(`?q=buzios&page=${this.state.page}&sort=stars`).then(dados=>{
          this.props.onSetHotels(this.props.Hotels.concat(dados.results))
          this.setState({showLoading:false, loadingFlat: false})
        }
      )
    })
  }

  //seta proxima página
  loadMoreHotels = () => {this.setState({page: (this.state.page+1)}, ()=>{this.loadHotels()})}

  //loading do scroll infinito
  renderFooter = () => {
    if (!this.state.loadingFlat) return null
    return (<Loading transparent/>)
  }

  render(){
    return (
      <View style={styles.container}>
          <Modal animationType="fade" transparent={true} visible={this.state.showLoading}>
            <Loading />
          </Modal>

          <SafeAreaView style={styles.container}>
                <SectionList
                  ListFooterComponent={this.renderFooter}
                //  onEndReachedThreshold={0.01}
                  onEndReached={this.loadMoreHotels}
                  sections={this.groupStarsPackages()}
                  keyExtractor={(item, index) => item + index}
                  renderItem={({ item }) => <HotelItem navigation={this.props.navigation} item={item}/>}
                  renderSectionHeader={({ section: {titulo} }) => ( <Text style={styles.badge}>{titulo}</Text>)}
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
  },
  badge: {
    backgroundColor: azul,
    color: '#fff',
    borderRadius: 20,
    textAlign: 'center',
    padding: 10,
    fontSize: 22
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