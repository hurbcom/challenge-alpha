import React from 'react'
import { StyleSheet, View, SafeAreaView, SectionList, Text, TouchableOpacity } from 'react-native'
import { connect } from 'react-redux'
import { rosa, azul } from '../../paleta/colors'
import HotelItem from '../Hotels/HotelItem'
import { MaterialCommunityIcons } from '@expo/vector-icons'
import { set_favorites } from '../../redux/action/favoriteAction'
import {groupStarsPackages} from '../../components/GroupByStars'

class Favorites extends React.Component {
  constructor(props) {
    super(props)
  }

  render(){
    return (
      <View style={styles.container}>
          <SafeAreaView style={styles.container}>
              <SectionList
                sections={groupStarsPackages(this.props.Favorites)}
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