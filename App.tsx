import React from 'react';  ``
import { View } from 'react-native';
import Button from './src/Screen/button'; // 컴포넌트 파일의 상대 경로
import Sliding from './src/Screen/slide';

function App() {
  const handleButtonPress = () => {
    
  };

  return (
    <View>
      <Button onPress={handleButtonPress} title="Click Me" />
      <Sliding/>
      {/* 다른 컴포넌트를 추가하여 화면을 구성할 수 있습니다. */}
    </View>
  );
}

export default App;
