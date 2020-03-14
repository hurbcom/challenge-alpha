import React from 'react';
import { Button, Card, Title, Paragraph } from 'react-native-paper'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { maskMoney } from '../../components/MaskMoney'
import {rosa} from '../../paleta/colors' 

export default class HotelItem extends React.Component {
  render(){

    const { item } = this.props

    return (
      <TouchableOpacity onPress={()=>this.props.navigation.navigate('IndicadorDetalhadoScreen')}>
        <Card style={styles.container}>    
            <Card.Cover source={{ uri: item.gallery[0].url }} />
            <Card.Content>
                <Title>{item.name}</Title>
                <Paragraph>{`${item.address.city}, ${item.address.state} - ${item.address.country}`}</Paragraph>
                <View style={styles.viewPrice}><Paragraph style={styles.price}>{maskMoney(item.price.amount)}</Paragraph></View>
            </Card.Content>
            {/* <Card.Actions>
                <Button>Ok</Button>
            </Card.Actions> */}
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