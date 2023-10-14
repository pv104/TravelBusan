import React, { useState } from 'react';
import { View, TouchableOpacity, Text, StyleSheet, Modal } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

const NavigationBarButton = () => {
  const [isActive, setIsActive] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);

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
  };

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
          <Text style={styles.modalText}>모달창 메뉴 확장 추가 중</Text>
          <TouchableOpacity onPress={handleLogin}>
            <Text style={styles.loginButton}>Login</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={toggleModal}>
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
