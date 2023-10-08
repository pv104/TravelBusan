import React from 'react';
import { ScrollView, Text, Image, Button,FlatList } from 'react-native';

const data = [
    { key: '1', text: '아이템 1' },
    { key: '2', text: '아이템 2' },
    // 더 많은 아이템 추가
  ];
const slide = () => {
  return (
    <ScrollView>
      <Image source={require('../pics/KakaoTalk_20230927_092116999.jpg')} />
      <Button title="클릭하세요" onPress={() => console.log('버튼 클릭')} />
      {/* 다른 컴포넌트 추가 */}
      <FlatList
      data={data}
      renderItem={({ item }) => <Text>{item.text}</Text>}
    />
    </ScrollView>
  );
};

export default slide;