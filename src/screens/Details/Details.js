import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, Linking, TouchableOpacity } from 'react-native'
import Carousel from 'react-native-snap-carousel'
import { Card, Title,Paragraph } from 'react-native-paper'
import { Ionicons, Feather } from '@expo/vector-icons'
import { azul, rosa } from '../../paleta/colors'
import ReadMore from 'react-native-read-more-text'
import { maskMoney } from '../../components/MaskMoney'

export default class Packages extends React.Component {
  constructor(props){
    super(props)
  }

  //seta imagem da galeria
  renderImagem ({item, index}) {
    return (
        <View key={index}> 
            <Image  style={{width: 400, height: 280}} source={{uri: item.url}}/>
        </View>
    )
  }

  //comodidades
  renderComodidades ({item, index}) {
    return (
        <View  style={styles.carousel} key={index}>
            <Card style={styles.container}>    
              <Card.Content>
                <View style={styles.viewIcon}>
                  <Ionicons name="ios-checkmark-circle" size={32} color={azul} />
                </View>
                <Title>{item.name}</Title>
              </Card.Content>
            </Card>
        </View>
    )
  }

  // ler mais descrição
  readMore = (handlePress) => {return (<Text style={styles.read} onPress={handlePress}>Ler mais</Text>)}

  // ler menos descrição
  readLess = (handlePress) => {return (<Text style={styles.read} onPress={handlePress}>Ler menos</Text>)}

  //compartilhar hotel/pacote via rede social
  shareSocial = (url) => {
    Linking.openURL(`whatsapp://send?text=${url}`);
  }

  render(){

    const { item } = this.props.route.params
    return (
      <View>
        <ScrollView>
            <Carousel ref={(c) => { this._carousel = c; }} data={item.gallery} layout={'stack'} renderItem={this.renderImagem} sliderWidth={400} itemWidth={400}/> 
            
            <View style={styles.textLocal}>
                <Text >{item.address.city}, {item.address.state}</Text>
            </View>

            <TouchableOpacity style={{position: 'absolute', right: 5, top: 5}} onPress={()=> this.shareSocial(item.url)}>
                <Feather name={'share'} size={36} color={'#fff'} />
            </TouchableOpacity>

            <View style={styles.viewRow}>
              <Text style={styles.title}>{item.name}</Text> 
              <View style={styles.viewPrice}><Paragraph style={styles.price}>{item.price ? maskMoney(item.price.amount) : '-'}</Paragraph></View>
            </View>
           
            <Carousel ref={(c) => { this._carousel = c; }} data={item.amenities} layout={'default'} renderItem={this.renderComodidades} sliderWidth={400} itemWidth={160}/> 

            <View style={styles.viewDescription}> 
              <ReadMore numberOfLines={3} renderTruncatedFooter={this.readMore} renderRevealedFooter={this.readLess} onReady={()=>{}}>
                  <Text style={styles.textDescription}>{item.description}</Text>
              </ReadMore>
            </View>
            
        </ScrollView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    height: 140
  },
  viewPrice: {
    flex: 1, 
    flexDirection: 'row-reverse'
  },
  price: {
    fontSize: 21,
    marginTop:10,
    color: rosa,
    fontWeight: 'bold',
  },
  title: {
    fontSize: 30,
    fontWeight: 'bold',
  },
  textDescription: {
    textAlign: 'justify'
  },
  viewDescription: {
    margin:10, 
    backgroundColor: '#fff', 
    borderRadius: 20, 
    padding: 10
  },
  textLocal: {
    margin:10, 
    textAlign: 'justify'
  },
  viewIcon:{
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
  },
  carousel: {
    marginTop: 15,
    marginBottom: 15
  },
  viewRow:{
    flex: 1, 
    margin:10, 
    flexDirection: 'row'
  },
  read:{
    color: rosa, 
    fontSize: 18,
    marginTop: 15,
    fontWeight: 'bold'
  }
}); 