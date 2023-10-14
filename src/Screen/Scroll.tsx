import React from 'react';
import { FlatList, Text, View, StyleSheet, TouchableOpacity } from 'react-native';
import Button from './button';
import Mine from './Blog_mydata';

const data = [
  { key: '1', component: <TouchableOpacity><Text>맛집 아이콘 자리</Text></TouchableOpacity> },
  { key: '2', component: <TouchableOpacity><Text>추천 여행지 아이콘 자리</Text></TouchableOpacity> },
  { key: '3', component : <Mine/>},
  { key: '4', component : <Button/>},
  { key: '5', component : <Button/>},
  // 더 많은 아이템 추가
];
const Separator = () => (
  <View style={styles.separator}></View>
);
const MyComponent = () => {
  const renderItem = ({ item }) => {
    return (
      <View style={styles.item}>
        {item.component}
      </View>
    );
  };

  return (
    <FlatList
      data={data}
      keyExtractor={(item) => item.key}
      renderItem={renderItem}
      ItemSeparatorComponent={Separator}
    />
  );
};
const styles = StyleSheet.create({
  item: {
    padding: 16,
    backgroundColor: 'white',
  },
  separator: {
    height: 3, // 간격의 높이 조절
    backgroundColor: 'black', // 간격의 색상 조절
  },
});

export default MyComponent;
