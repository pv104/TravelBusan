import React from 'react';  ``
import { View } from 'react-native';
import Sliding from './src/Screen/slide';
import Nav from './src/Screen/nav';

function App() {
  const handleButtonPress = () => {
    
  };

  return (
    <View>
      <Nav/>
      <Sliding/>
      {/* 다른 컴포넌트를 추가하여 화면을 구성할 수 있습니다. */}
    </View>
  );
}

export default App;
