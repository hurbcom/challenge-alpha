import React from 'react';
import { StyleSheet, View, SafeAreaView, Modal, SectionList, Text, TouchableOpacity, TextInput, Button} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'
import { rosa, azul } from '../../paleta/colors'
import { MaterialIcons } from '@expo/vector-icons'
import Alert from "react-native-modal"

class Hotels extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showLoading:true, 
      loadingFlat: true, 
      page: 1,
      showBusca: false,
      textBusca: '',
      busca: 'buzios'
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
      api.get(`?q=${this.state.busca}&page=${this.state.page}&sort=stars`).then(dados=>{
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
    if (!this.state.loadingFlat || this.state.showLoading) return null
    return (<Loading transparent/>)
  }

  //setar campo de busca
  atualizaCampoBusca = (texto) => {this.setState({textBusca: texto})}

  //pesquisa pelo parâmetro do usuário
  buscar = () => {
    this.props.onSetHotels([])
    this.setState({page: 1, showBusca: false}, ()=>{this.loadHotels()})
  }

  render(){
    return (
      <View style={styles.container}>
            <Modal animationType="fade" transparent={true} visible={this.state.showLoading}><Loading /></Modal> 
            
          <Alert onSwipeComplete={this.close}  style={styles.modalBusca} isVisible={this.state.showBusca}>
                <View>
                  <TextInput onChangeText={text => this.atualizaCampoBusca(text)} value={this.state.textBusca} />
                  <Button title="Buscar" onPress={() => Alert.alert('Button with adjusted color pressed')}></Button>
                </View>
          </Alert>

          <SafeAreaView style={styles.container}>
                <SectionList
                  ListFooterComponent={this.renderFooter}
                  onEndReached={this.loadMoreHotels}
                  sections={this.groupStarsPackages()}
                  keyExtractor={(item, index) => item + index}
                  renderItem={({ item }) => <HotelItem navigation={this.props.navigation} item={item}/>}
                  renderSectionHeader={({ section: {titulo} }) => ( <Text style={styles.badge}>{titulo}</Text>)}/>
          </SafeAreaView>

          <TouchableOpacity activeOpacity={.7}  style={styles.fab} onPress={()=>this.setState({showBusca: true})}>
              <MaterialIcons name={'search'} size={28} color={'#fff'} />
          </TouchableOpacity>
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
    textAlign: 'center',
    padding: 10,
    fontSize: 22
  }, 
  fab:{
    position: "absolute",
    backgroundColor: rosa,
    width:50, 
    height: 50,
    borderRadius: 50,
    bottom: 10, 
    right: 10, 
    alignItems: 'center', 
    justifyContent: 'center'
  },
  modalBusca: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#fff',
    justifyContent: 'flex-end',
    margin: 0,
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