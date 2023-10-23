import React from 'react';
import { View,Image, Text, StyleSheet, FlatList, SafeAreaView,TouchableOpacity } from 'react-native';
import { WebView } from 'react-native-webview';
import Nav from './nav';

const KakaoMap = () => {
  return (
    <View style={styles.container}>
    <View style={styles.menu}>
        <Nav/>
        <Text style={styles.Title}>Travel Busan</Text>
    </View>
      <WebView
        source={{
          uri: 'https://map.kakao.com', // 카카오 지도 URL을 여기에 입력
        }}
        style={styles.webview}
      />
    </View>
  );
};

const styles = StyleSheet.create({
    menu: {
        flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
      },
  container: {
    flex: 1,
  },
  Title:{
    fontSize: 40,
    color : '#00B292',//진한 민트색
    fontWeight: 'bold',
    marginLeft : 20,
  },
  webview: {
    flex: 1,
  },
});

export default KakaoMap;
