import React from 'react';
import { Button, Card, Title, Paragraph } from 'react-native-paper'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { maskMoney } from '../../components/MaskMoney'
import {rosa} from '../../paleta/colors' 

export default class HotelItem extends React.Component {
  constructor(props){
    super(props)
  }

  render(){

    const { item, navigation } = this.props

    return (
      <TouchableOpacity key={item.id} onPress={()=>navigation.navigate('Detalhes', {
          item:item
      })}>
        <Card style={styles.container}>    
            <Card.Cover source={{ uri: item.image }} />
            <Card.Content>
                <Title>{item.name}</Title>
                <Paragraph>{item.address ? `${item.address.city}, ${item.address.state} - ${item.address.country}` : '-'}</Paragraph>
                <View style={styles.viewPrice}><Paragraph style={styles.price}>{item.price ? maskMoney(item.price.amount) : '-'}</Paragraph></View>
            </Card.Content>
        </Card>
      </TouchableOpacity>
    )
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      marginTop: 10,
      marginBottom: 10
    },
    price: {
      fontSize: 20,
      marginTop:10,
      color: rosa,
      fontWeight: 'bold',
    },
    viewPrice: {
      flex: 1, 
      flexDirection: 'row-reverse'
    }
  }); 