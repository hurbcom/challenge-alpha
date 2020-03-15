import React from 'react';
import { StyleSheet, Text, View, Image } from 'react-native'
import Carousel from 'react-native-snap-carousel'

export default class Packages extends React.Component {
  constructor(props){
    super(props)
  }
  
  // static defaultNavigationOptions = ({ navigation }) => {
  //   const edit = navigation.getParam('edit', false);
  //     return {
  //       headerTitle: 'Edit Page',
  //     }
  // }  

  _renderItem ({item, index}) {
    return (
        <View>
            <Image  style={{width: 400, height: 280}} source={{uri: item.url}}/>
        </View>
    )
  }

  render(){
    const { item } = this.props.route.params
    return (
      <View>
        <Carousel
          ref={(c) => { this._carousel = c; }}
          data={item.gallery}
          layout={'default'}
          renderItem={this._renderItem}
          sliderWidth={400}
          itemWidth={400}/> 
        
         <Text style={styles.text}>{item.address.city}, {item.address.state}</Text>
         <Text style={styles.title}>{item.name}</Text> 
         <Text style={styles.text}>{item.description}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  title: {
    marginLeft: 10,
    fontSize: 30,
    fontWeight: 'bold',
  },
  text: {
    margin: 10, 
    textAlign: 'justify'
  }
}); 