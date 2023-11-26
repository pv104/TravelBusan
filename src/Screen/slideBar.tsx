import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, FlatList, Image } from 'react-native';
import SearchBar from './SearchBar';
import Mine from './Blog/Blog_mydata';
import { useNavigation } from '@react-navigation/native';



const Separator = () => (
  <View style={styles.separator}></View>
);

const renderItem = ({ item }) => {
  return (
    <View style={styles.item}>
      {item.component}
    </View>
  );
};

const FlatListComponent = () => {
  const navigation = useNavigation();
  const gotoMap=()=>{
    navigation.navigate('Maps');
  }
  const data = [
    { key: '1', component:
     <View style={{flexDirection: 'row',justifyContent:"center"}}>
      <TouchableOpacity onPress={()=>gotoMap()}>
      <Image source={require('../pics/식당.png')} 
       style={{
        aspectRatio: 1 / 2, // 이미지 비율 (가로:세로)
        width: '100%', // 이미지의 가로 크기를 부모 요소에 맞춤
        resizeMode: 'contain', // 이미지 크기 조절 방식 (비율 유지)
        marginTop : -70,marginBottom : -50,marginRight : "10%"
      }}
      />
      <Text style={{fontWeight:'bold',justifyContent:"center"}}>맛집</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={()=>gotoMap()}>
        <Image source={require('../pics/여행.png')}
        style={{
          aspectRatio: 1 / 2, // 이미지 비율 (가로:세로)
          width: '100%', // 이미지의 가로 크기를 부모 요소에 맞춤
          resizeMode: 'contain', // 이미지 크기 조절 방식 (비율 유지)
          marginTop : -70,marginBottom : -50
        }}
         />
        <Text style={{fontWeight:'bold',justifyContent:"center"}}>추천 여행지</Text>
        </TouchableOpacity>
     </View>
    },
    { key: '2', component: <Mine /> },
    // 추가 아이템
  ];
  return (
    <View style={styles.container}>
      {/* 슬라이드 레이어 내용 */}
      <View style={styles.slideLayer}>
        <View style={{ marginTop: 20 }}>
          <SearchBar />
        </View>
        <FlatList
          data={data}
          keyExtractor={(item) => item.key}
          renderItem={renderItem}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  ico:{
    flexDirection: 'row',
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  slideLayer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 470,
    backgroundColor: 'white',
    borderTopLeftRadius: 30,
    borderTopRightRadius: 30,
    elevation: 5,
    alignItems: 'center',
    justifyContent: 'center',
  },
  item: {
    padding: 16,
    backgroundColor: 'white',
  },
  separator: {
    height: 3,
    backgroundColor: 'black',
  },
});

export default FlatListComponent;
