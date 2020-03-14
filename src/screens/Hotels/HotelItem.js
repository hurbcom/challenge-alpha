import React from 'react';
import { Button, Card, Title, Paragraph } from 'react-native-paper'
import { StyleSheet } from 'react-native'
import { maskMoney } from '../../components/MaskMoney'

export default class HotelItem extends React.Component {
  render(){

    const { item } = this.props

    return (
        <Card style={styles.container}>
            <Card.Content>
                <Title>{item.name}</Title>
                <Paragraph>{`${item.address.city}, ${item.address.state} - ${item.address.country}`}</Paragraph>
            </Card.Content>
            <Card.Cover source={{ uri: item.gallery[0].url }} />
            <Card.Content>
                <Paragraph>{item.price.amount}</Paragraph>
            </Card.Content>
            <Card.Actions>
                <Button>Ok</Button>
            </Card.Actions>
        </Card>
    )
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      margin: 10,
    },
  }); 