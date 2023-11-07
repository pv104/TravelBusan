import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { WebView } from 'react-native-webview';
import Nav from './nav';
import { MapView, Marker, Callout } from 'react-native-maps'; // react-native-maps에서 필요한 컴포넌트들을 불러옵니다.
import axios from 'axios';

const KakaoMapWithMarkers = () => {
  const [markerData, setMarkerData] = useState([]);

  useEffect(() => {
    const fetchMarkerData = async () => {
      try {
        const response = await axios.get('서버API주소'); // 서버 API 주소를 적절히 변경하세요.
        setMarkerData(response.data);
      } catch (error) {
        console.error('데이터를 불러오는 중 에러 발생:', error);
      }
    };

    fetchMarkerData();
  }, []);

  const handleMarkerPress = (marker) => {
    // 클릭한 마커와 관련된 동작을 추가하세요.
    // 예를 들어, 정보 표시 또는 다른 사용자 정의 동작 수행
    console.log('마커 클릭: ', marker);
  };

  return (
    <View style={styles.container}>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <WebView
        source={{
          uri: 'https://map.kakao.com',
        }}
        style={styles.webview}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row',
  },
  container: {
    flex: 1,
  },
  Title: {
    fontSize: 40,
    color: '#00B292',
    fontWeight: 'bold',
    marginLeft: 20,
  },
  webview: {
    flex: 1,
  },
  map: {
    flex: 1,
  },
});

export default KakaoMapWithMarkers;
