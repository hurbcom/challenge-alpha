import React from 'react'
import { StyleSheet, View, SafeAreaView, SectionList, Text, TouchableOpacity} from 'react-native'
import { api } from '../../connection/fetch'
import { connect } from 'react-redux'
import { set_hotels } from '../../redux/action/hotelAction'
import Loading from '../../components/Loading'
import HotelItem from './HotelItem'
import { rosa, azul } from '../../paleta/colors'
import { MaterialIcons, Ionicons } from '@expo/vector-icons'
import Modal from 'react-native-modal'
import { Button } from 'react-native-elements'
import Autocomplete from 'react-native-autocomplete-input'
import {groupStarsPackages} from '../../components/GroupByStars'

class Hotels extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showLoading:true, 
      loadingFlat: true, 
      page: 1,
      showBusca: false,
      textBusca: '',
      busca: 'buzios', 
      listaSuggestion: [],
      hideResult: false,
    }
  }

  componentDidMount = () => {
    this.loadHotels()
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
    this.setState({showLoading: true, busca: this.state.textBusca, textBusca: ''}, ()=>{
      this.props.onSetHotels([])
      this.setState({page: 1, showBusca: false}, ()=>{this.loadHotels()})
    })
  }

  //carrega suggestions de acordo com busca do usuário
  carregaSuggestions = (suggestion) => {
    api.get(`/suggestion?q=${suggestion}`).then(dados=>{
        let array = []
        dados.suggestions.forEach((e)=> {
            array.push(e.text)
        })
        console.log(array)
        this.setState({listaSuggestion: array, hideResult: false})
      })
  }

  render(){
    const { listaSuggestion, showBusca, textBusca, showLoading, hideResult } = this.state

    return (
      <View style={styles.container}>

          {showLoading && <Loading />}
          
          <Modal isVisible={showBusca}>
            <View style={styles.viewBusca}>
                <TouchableOpacity onPress={()=>this.setState({showBusca: false, textBusca: ''})} style={{ flexDirection: 'row-reverse'}}><Ionicons name={'md-close'} size={28} color={'gray'} /></TouchableOpacity>
                
                <View style={{padding:10, flex: 1, flexDirection: 'column', justifyContent: 'space-between'}}>
                    <Autocomplete hideResults={hideResult} data={listaSuggestion} defaultValue={textBusca} onChangeText={text => this.carregaSuggestions(text)} renderItem={({ item, i }) => (<TouchableOpacity onPress={() => this.setState({ textBusca: item, hideResult: true })}><Text style={{padding: 15}}>{item}</Text></TouchableOpacity>)}/>
                    <Button buttonStyle={{backgroundColor: rosa}} onPress={this.buscar.bind(this)} title="Buscar" />
                </View>
            </View>
          </Modal>

          <SafeAreaView style={styles.container}>
                <SectionList
                  ListFooterComponent={this.renderFooter}
                  onEndReached={this.loadMoreHotels}
                  sections={groupStarsPackages(this.props.Hotels)}
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
  viewBusca: {
    padding: 10, 
    borderRadius: 5, 
    alignSelf: 'center', 
    backgroundColor: 'white', 
    width: '90%', 
    height: '30%'
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