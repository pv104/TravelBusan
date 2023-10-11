import React from 'react';  ``
import { View, StyleSheet } from 'react-native';
import Sliding from './src/Screen/slide';
import Nav from './src/Screen/nav';
import SlideBar from './src/Screen/slideBar'
import Scroll from './src/Screen/Scroll';
//가로 확장가능
function App() {
  const handleButtonPress = () => {
    
  };

  return (
    <View>
      <View style={styles.container}> 
        <Nav/>
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
});
export default App;
