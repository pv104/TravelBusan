import React from 'react';
import { View, StyleSheet } from 'react-native';
import WebView from 'react-native-webview';


const KakaoMapView = () => {
  return (
    <View style={styles.container}>
      <WebView
        source={{ uri: 'https://map.kakao.com/' }}
        style={styles.webview}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  webview: {
    flex: 1,
  },
});

export default KakaoMapView;
