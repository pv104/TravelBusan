import React, { useEffect, useState } from 'react';
import { View, StyleSheet, SafeAreaView, Text } from 'react-native';
import axios from 'axios';
import MapView, { PROVIDER_GOOGLE, Marker } from 'react-native-maps';
import { getCookie } from '../UserUtility/Cookie';
import { useNavigation } from '@react-navigation/native';

const Map2 = ({route}) => {
  const [sightData, setSightData] = useState([]);
  const navigation = useNavigation();

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get(`http://192.168.123.145:8080/sights/search?city=${route.params.gu}`, {
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
        mapx: parseFloat(item.mapx),
        mapy: parseFloat(item.mapy),
        img: item.img,
        city: item.city,
        homepage: item.homepage,
        tel: item.number,
        openData: item.openData,
        trafficReport: item.trafficReport,
        like: item.like,
        rate: item.rate,
        type : item.type
      }));
      setSightData(updatedSightData);
    } catch (error) {
      console.error(error);
    }
  };
  
  const handleMarkerPress = (item) =>{
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
      <Text style={{fontSize:25,
         fontWeight : 'bold',
         color : 'black'
         }}>
          {route.params.gu}의 명소입니다.
          </Text>
      <MapView
        style={{ flex: 1 }}
        provider={PROVIDER_GOOGLE}
        initialRegion={{
          latitude: route.params.lat,
          longitude: route.params.lng,
          latitudeDelta: 0.1,
          longitudeDelta: 0.1,
        }}//검색한 장소 기반
      >
      {sightData.map(item => (
        <Marker
            key={item.id.toString()}
            coordinate={{
              latitude: item.mapy, 
              longitude: item.mapx
            }}
            title={item.name}
            description={item.addr}
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

export default Map2;
