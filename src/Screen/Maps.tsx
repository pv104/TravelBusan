import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { WebView } from 'react-native-webview';
import Nav from './nav';
import MapView from 'react-native-maps';
import axios from 'axios';

const KakaoMapWithMarkers = () => {
  const [markerData, setMarkerData] = useState([]); // 좌표 데이터를 저장할 상태

  useEffect(() => {
    // 서버에서 좌표 데이터를 가져오는 함수
    const fetchMarkerData = async () => {
      try {
        const response = await axios.get('서버API주소'); // 실제 서버 API 주소로 대체해야 합니다.
        setMarkerData(response.data); // 좌표 데이터를 상태에 저장
      } catch (error) {
        console.error('데이터를 불러오는 중 에러 발생:', error);
      }
    };

    // 컴포넌트가 마운트될 때 좌표 데이터를 가져옵니다.
    fetchMarkerData();
  }, []);

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
      <MapView style={styles.map} initialRegion={/* 초기 지도 설정 */}>
        {/* 좌표 데이터를 기반으로 마커를 표시 */}
        {markerData.map((marker, index) => (
          <MapView.Marker
            key={index}
            coordinate={{
              latitude: marker.latitude,
              longitude: marker.longitude,
            }}
            title={marker.title}
            description={marker.description}
          />
        ))}
      </MapView>
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
