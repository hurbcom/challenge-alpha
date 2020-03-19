import React from 'react'
import { Card, Title, Paragraph } from 'react-native-paper'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { maskMoney } from '../../components/MaskMoney'
import { rosa } from '../../paleta/colors' 
import { MaterialIcons } from '@expo/vector-icons'
import { connect } from 'react-redux'
import { set_favorites } from '../../redux/action/favoriteAction'

class HotelItem extends React.Component {
  constructor(props){
    super(props)
  }

  //verifica se é favorito
  isFavorite = (item) => {
    let favorites = this.props.Favorites.filter(favorite => favorite.id === item.id)
    if(favorites.length > 0) return true
    else return false
  }

  //adiciona ou remove nos favoritos
  addFavorite = (item) => {
    if(this.isFavorite(item) === false) this.props.OnSetFavorites(this.props.Favorites.concat(item)) 
    else {
      let favorites = this.props.Favorites.filter(favorite => favorite.id !== item.id)
      this.props.OnSetFavorites(favorites) 
    }    
  }

  render(){
    const { item, navigation } = this.props

    return (
      <TouchableOpacity key={item.id} onPress={()=>navigation.navigate('Detalhes', {item:item})}>
        <Card style={styles.container}>    
            <Card.Cover source={{uri: item.image}}/>
            <Card.Content>
                <Title>{item.name}</Title>
                <Paragraph>{item.address ? `${item.address.city}, ${item.address.state} - ${item.address.country}` : '-'}</Paragraph>
                <View style={styles.viewRow}>
                  <TouchableOpacity onPress={()=>this.addFavorite(item)}><MaterialIcons name={this.isFavorite(item) ? 'favorite' : 'favorite-border'} size={28} color={rosa} /></TouchableOpacity>
                  <Paragraph style={styles.price}>{item.price ? maskMoney(item.price.amount) : '-'}</Paragraph>
                </View>
            </Card.Content>
        </Card>
      </TouchableOpacity>
    )
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      marginBottom: 20
    },
    viewRow: {
      flex: 1,
      flexDirection: 'row-reverse',
      justifyContent: 'space-between'
    },
    price: {
      fontSize: 21,
      marginTop:10,
      color: rosa,
      fontWeight: 'bold',
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

export default connect(mapStateToProps, mapDispatchToProps)(HotelItem)