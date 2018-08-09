import React, { Component } from 'react'
import { View, StyleSheet } from 'react-native'
import { Text, Row } from 'native-base'
import { Ionicons as Icon } from '@expo/vector-icons'

import { colors } from '../../styles'

export class SectionHeader extends Component {
  
  _renderStars = () => {
    let stars = [];
    for(let i=0; i<this.props.title; i++) {
      stars.push(<Icon name="md-star" color={colors.golden} size={28} style={styles.starIcons} key={i}/>);
    }
    for (let i = this.props.title; i < 5; i++) {
      stars.push(<Icon name="md-star-outline" color={colors.golden} size={28} style={styles.starIcons} key={i} />);
    }
    return (
      <Row style={styles.row}>
        {stars}
      </Row>
    );
  }
  
  _renderContent = () => {
    let content;
    if(typeof this.props.title === 'string') {
      return <Text style={styles.title} >{this.props.title}</Text>;
    }
    return this._renderStars();
  }

  render() {
    return (
      <View style={styles.container}>
        {this._renderContent()}
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    height: 50,
    paddingHorizontal: 10,
    justifyContent: 'center',
  },

  title: {
    color: colors.charcoal,
  },

  row: {
    alignItems: 'center',
  },

  starIcons: {
    marginHorizontal: 3,
  }
});

export default SectionHeader;