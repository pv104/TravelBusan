import React, { useState } from 'react';
import { View, TouchableOpacity, Text, StyleSheet, Modal } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import { useNavigation } from '@react-navigation/native';
import {getCookie, removeCookie} from './Cookie';


const NavigationBarButton = () => {
  const [isActive, setIsActive] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const navigation  = useNavigation();
  
  
  const toggleButton = () => {
    setIsModalVisible(!isModalVisible);
    setIsActive(!isActive);
  };

  const toggleModal = () => {
    setIsModalVisible(!isModalVisible);
    setIsActive(false);
  };

  const handleLogin = () => {
    toggleModal(); // 모달 닫기
    navigation.navigate('Login');
  };
  const handleLogout =() =>{
    removeCookie('userId');
    removeCookie('token');
    toggleModal();
  }
  const handleBlog = () => {
    toggleModal(); // 모달 닫기
    navigation.navigate('Blog');
  };
  const handleCommunity = () => {
    toggleModal(); // 모달 닫기
    navigation.navigate('Community');
  };
  const handleMaps =() =>{
    toggleModal();
    navigation.navigate('Maps');
  };
  const handleInfo2 =() =>{
    toggleModal();
  };
  const handleInfo =() =>{
    toggleModal();
    navigation.navigate('UserInfo');
  };
  const IsLogin = getCookie("userId");
  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={[styles.button, isActive ? styles.activeButton : styles.inactiveButton]}
        onPress={toggleButton}
      >
        <Text style={styles.buttonText}>
          {isActive ? '+' : <Icon name="bars" size={30} color="black" />}
        </Text>
      </TouchableOpacity>
      <Modal
        visible={isModalVisible}
        animationType="slide"
        transparent={true}
      >
        <View style={styles.modalContainer}>
          <Text style={styles.modalText}>We can go EveryWhere</Text>
          <View>
            {IsLogin ? 
            <View>
            <TouchableOpacity onPress={() => handleLogout()}>
              <Text style={styles.loginButton}>로그아웃</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() =>handleInfo()}>
              <Text style={styles.loginButton}>내 정보</Text>
            </TouchableOpacity>
           <TouchableOpacity onPress={() =>handleBlog()}>
             <Text style={styles.loginButton}>블로그</Text>
           </TouchableOpacity>
           <TouchableOpacity onPress={() =>handleCommunity()}>
              <Text style={styles.loginButton}>커뮤니티</Text>
           </TouchableOpacity>
            <View>
           </View>
            </View>
          :
          <View>
            <TouchableOpacity onPress={() =>handleLogin()}>
              <Text style={styles.loginButton}>Login</Text>
            </TouchableOpacity>
            </View>
            }
              
          </View>
          <TouchableOpacity onPress={() =>toggleModal()}>
            <Text style={styles.closeButton}>Close</Text>
          </TouchableOpacity>
        </View>
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'flex-start',
  },
  button: {
    padding: 10,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: 'gray',
  },
  activeButton: {
    backgroundColor: 'white',
  },
  inactiveButton: {
    backgroundColor: 'white',
  },
  buttonText: {
    color: 'black',
    fontWeight: 'bold',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
  },
  modalText: {
    color: 'white',
    fontWeight: 'bold',
    fontSize: 18, // 글씨 크기 조절
  },
  closeButton: {
    color: 'white',
    fontWeight: 'bold',
    marginTop: 60,
  },
  loginButton: {
    color: 'white',
    fontWeight: 'bold',
    fontSize: 18,
    marginTop: 20, // 버튼 간의 간격 조절
  },
});

export default NavigationBarButton;
