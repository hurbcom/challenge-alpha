import React from 'react'
import { StyleSheet, View, SafeAreaView, SectionList, Text, TouchableOpacity } from 'react-native'
import { connect } from 'react-redux'
import { rosa, azul } from '../../paleta/colors'
import HotelItem from '../Hotels/HotelItem'
import { MaterialCommunityIcons } from '@expo/vector-icons'
import { set_favorites } from '../../redux/action/favoriteAction'

class Favorites extends React.Component {
  constructor(props) {
    super(props)
  }

  //agrupa por estrelas ou pacotes
  groupStarsPackages = () => {
    let grupos = []

    for(let i=5; i>=1; i--) {
      let favoritesHotels = this.props.Favorites.filter(hotel => hotel.stars === i)
      if(favoritesHotels.length > 0) grupos.push({ data: favoritesHotels, titulo: `${i} estrelas`})
    }

    let favoritesPackages = this.props.Favorites.filter(pack => pack.stars === null)
    if(favoritesPackages.length > 0) grupos.push({ data: favoritesPackages, titulo: 'Pacotes'})

    return grupos
  }

  render(){
    return (
      <View style={styles.container}>
          <SafeAreaView style={styles.container}>
              <SectionList
                ListFooterComponent={this.renderFooter}
                onEndReached={this.loadMoreHotels}
                sections={this.groupStarsPackages()}
                keyExtractor={(item, index) => item + index}
                renderItem={({ item }) => <HotelItem navigation={this.props.navigation} item={item}/>}
                renderSectionHeader={({ section: {titulo} }) => ( <Text style={styles.badge}>{titulo}</Text>)}/>
          </SafeAreaView>
          <TouchableOpacity activeOpacity={.7}  style={styles.fab} onPress={()=>this.props.OnSetFavorites([])}>
              <MaterialCommunityIcons name={'delete-empty'} size={28} color={'#fff'} />
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
}); 

const mapStateToProps = ({ favoriteRdc }) => {
  return {
      Favorites: favoriteRdc.favorites,
  }
}

const mapDispatchToProps = dispatch => {
  return {
    OnSetFavorites: favorites => dispatch(set_favorites(favorites))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Favorites)