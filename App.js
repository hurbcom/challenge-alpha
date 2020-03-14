import React from 'react';
import { StyleSheet, Text, View } from 'react-native'
import { api } from './src/conexao/api'

var page = 1

export default class extends React.Component {
  constructor(){
    super()

    this.state = {
      hotels: []
    }
  }

  componentDidMount = () => {api.get(`?q=buzios&page=${page}`).then(dados=>console.log(dados))}

  render(){
    return (
      <View style={styles.container}>
        <Text>Open up App.js to start working on your app!</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
}); 
