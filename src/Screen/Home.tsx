import React from 'react';  ``
import { View, StyleSheet,Text} from 'react-native';
import Sliding from './slide';
import Nav from './nav';
import SlideBar from './slideBar'


//가로 확장가능
function Home() {
  const handleButtonPress = () => {
    
  };
  return (
    <View>
      <View style={styles.container}> 
        <Nav/>
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <View>
        <Sliding/>
        <SlideBar/>
    </View>
  </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
  },
  Title:{
    fontSize: 40,
    color : '#00B292',//진한 민트색
    fontWeight: 'bold',
    marginLeft : 20,
  },
});
export default Home;
