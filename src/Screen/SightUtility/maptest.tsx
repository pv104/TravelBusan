import React, { useEffect, useState } from 'react';
import { View, StyleSheet, SafeAreaView, TouchableOpacity, Text, Modal } from 'react-native';
import axios from 'axios';
import MapView, { PROVIDER_GOOGLE, Marker } from 'react-native-maps';
import { getCookie } from '../UserUtility/Cookie';
import { useNavigation } from '@react-navigation/native';

const Map = () => {
  const [sightData, setSightData] = useState([]);
  const navigation = useNavigation();
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isActive, setIsActive] = useState(false);

  useEffect(() => {
    fetchData();
  }, []);

  const city = async(text)=>{
    try{
      const response = await axios.get(`http://192.168.123.145:8080/sights/search?city=${text}`,{
        headers:{
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
      toggleModal();
    }catch(error)
    {
      console.log(error);
    }
  };
  const fetchData = async () => {
    try {
      const response = await axios.get('http://192.168.123.145:8080/sights', {
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
  const toggleModal = () => {
    setIsModalVisible(!isModalVisible);
    setIsActive(false);
  };
  const InfoModal =()=>{
    toggleModal();
  }
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
      <TouchableOpacity style={styles.button} onPress={() => InfoModal()}>
          <Text style={styles.buttonText}>지역 찾기</Text>
        </TouchableOpacity>
        <Modal
          visible={isModalVisible}
          animationType="slide"
          transparent={true}
        >
          <View style={styles.modalContainer}>
          <TouchableOpacity style={{marginBottom:30}}onPress={()=>city('중구')} >
            <Text style={styles.modalText}>중구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}} onPress={()=>city('서구')}>
            <Text style={styles.modalText}>서구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}} onPress={()=>city('동구')}>
            <Text style={styles.modalText}>동구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('영도구')}>
            <Text style={styles.modalText}>영도구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('해운대구')}>
            <Text style={styles.modalText}>해운대구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('사하구')}>
            <Text style={styles.modalText}>사하구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('금정구')}>
            <Text style={styles.modalText}>금정구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('강서구')}>
            <Text style={styles.modalText}>강서구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('연제구')}>
            <Text style={styles.modalText}>연제구 </Text>
          </TouchableOpacity>
          <TouchableOpacity style={{marginBottom:30}}  onPress={()=>city('수영구')}>
            <Text style={styles.modalText}>수영구 </Text>
          </TouchableOpacity>
            <TouchableOpacity onPress={() => toggleModal()}>
              <Text style={styles.closeButton}>Close</Text>
            </TouchableOpacity>
          </View>
        </Modal>
      <MapView
        style={{ flex: 1 }}
        provider={PROVIDER_GOOGLE}
        initialRegion={{
          latitude: 35.1152703,
          longitude: 129.0422268,
          latitudeDelta: 0.1,
          longitudeDelta: 0.1,
        }}//기준은 부산역
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
  buttonText: {
    color: 'white',
    fontSize : 20,
    textAlign : 'center',
    fontWeight: 'bold',
  },
  webview: {
    flex: 1,
  },
  button: {
    backgroundColor: '#00B292', // 버튼 배경색
    paddingVertical: 8, // 세로 방향 padding
    paddingHorizontal: 5, // 가로 방향 padding
    width : '23.8%',
    borderRadius: 5,
    marginRight : 5,
  },
  modalText: {
    color: 'white',
    fontWeight: 'bold',
    fontSize: 25, // 글씨 크기 조절
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
  },
  closeButton: {
    color: 'white',
    fontWeight: 'bold',
    marginTop: 60,
  },
});

export default Map;
