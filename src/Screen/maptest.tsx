import React, { useEffect, useState } from 'react';
import { View, StyleSheet, SafeAreaView } from 'react-native';
import axios from 'axios';
import MapView, { PROVIDER_GOOGLE, Marker } from 'react-native-maps';
import { getCookie } from './UserUtility/Cookie';
import { useNavigation } from '@react-navigation/native';

const Map = () => {
  const [sightData, setSightData] = useState([]);
  const navigation = useNavigation();
  
  const fetchData = async () => {
    try {
      const response = await axios.get('http://172.18.112.1:8080/sights', {
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      const updatedSightData = response.data.data.map((item) => ({
        id: item.id,
        name: item.name,
        title: item.title,
        info: item.info,
        addr: item.addr,
        mapx: item.mapx,
        mapy: item.mapy,
        img: item.img,
        city: item.city,
        homepage: item.homepage,
        tel: item.number,
        openData: item.openData,
        trafficReport: item.trafficReport,
        like: item.like,
        rate: item.rate,
      }));
      setSightData(updatedSightData);
    } catch (error) {
      console.error(error);
    }
  };
  
  useEffect(() => {
    fetchData();
  }, []);
  const handleMarkerPress = (item) =>{
    console.log(item);
    navigation.navigate('Sight', {
      id: item.id,
      name: item.name,
      title: item.title,
      info: item.info,
      addr: item.addr,
      mapx: item.mapx,
      mapy: item.mapy,
      img: item.img,
      city: item.city,
      homepage: item.homepage,
      tel: item.number,
      openData: item.openData,
      trafficReport: item.trafficReport,
      like: item.like,
      rate: item.rate,
    });
  }
  return (
    <SafeAreaView style={styles.container}>
      <MapView
        style={{ flex: 1 }}
        provider={PROVIDER_GOOGLE}
        initialRegion={{
          latitude: 35.1152703,
          longitude: 129.0422268,
          latitudeDelta: 0.009,
          longitudeDelta: 0.004,
        }}//기준은 부산역
      >
        {sightData.map((item) => (
          <Marker
            key={item.id.toString()}
            coordinate={{
              latitude: parseFloat(item.mapx), // Assuming mapx and mapy are strings, convert to float
              longitude: parseFloat(item.mapy),
            }}
            title={item.name}
            description={item.info}
            onPress={() => handleMarkerPress(item)}
          />
        ))}
      </MapView>
    </SafeAreaView>
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

export default Map;
