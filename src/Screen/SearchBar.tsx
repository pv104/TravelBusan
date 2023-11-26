import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, Modal, Alert } from 'react-native';
import Postcode from '@actbase/react-daum-postcode';
import Icon from 'react-native-vector-icons/Feather'; 
import Geocoder from 'react-native-geocoding';
import { useNavigation } from '@react-navigation/native';

function SearchBar() {
  Geocoder.init("AIzaSyAYjPhPSHxu9o3uPAdLSB2bGWLJX_cF13M");
  const navigation = useNavigation();
  const [isModal, setModal] = useState(false);
  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);
  const [address , setAddress] = useState('');

  const Geocoding = async()=>{
    try{
      await Geocoder.from(address)
      .then(json=>{
        const location = json.results[0].geometry.location;
        setLatitude(0);
        setLongitude(0);
        setLatitude(location.lat);
        setLongitude(location.lng);
        console.log(latitude);
        console.log(longitude);
        console.log(location.lat);
        console.log(location.lng);
      }).catch(error=>console.log(error));
    }
    catch(error){
      console.error('지오코딩 에러', error);
      Alert.alert('지오코딩에 문제가 생겼습니다.')
    }
  };
  return (
    <View>
      <Modal visible={isModal}>
      <TouchableOpacity 
      onPress={()=>setModal(false)}>
             <Icon name="x" size={30} color="red" />
        </TouchableOpacity>
      <Postcode
          style={{ width: '100%', height: '100%' }}
          jsOptions={{ animation: true, hideMapBtn: true }}
          onSelected={data => {
            console.log(JSON.stringify(data));
            setAddress('');
            setAddress(data.address);
            Geocoding();
            setModal(false);
            navigation.navigate("SearchMap",{
              gu : data.sigungu,
              lat : latitude,
              lng : longitude,
            })
          } } onError={function (error: unknown): void {
            throw new Error('Function not implemented.');
          } }/>

      </Modal>
      <TouchableOpacity
        style={{
          borderWidth: 3,
          borderColor: 'black',
          padding: 15,
          marginBottom: 10,
          borderTopLeftRadius: 15,
          borderTopRightRadius: 15,
          borderBottomRightRadius:15,
          borderBottomLeftRadius:15,
          width: 300, 
          height : 50
        }}
        onPress={()=>setModal(true)}
      >
      <Text style={{height : 50}}>어디로 가고싶나요?</Text>
      </TouchableOpacity>
      </View>
  );
}

export default SearchBar;
