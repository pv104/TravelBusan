// LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Button, Alert } from 'react-native';
import Modal from 'react-native-modal';
import Nav from './nav';
import axios from 'axios'; // axios 모듈을 사용해야 합니다.
import { useNavigation } from '@react-navigation/native';

const LoginScreen = () => {
  const [isModalVisible, setModalVisible] = useState(false);
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    passwordCheck: '',
    nickname: '',
    email: '',
  });
  const navigation  = useNavigation();
  const handleInputChange = (name, value) => {
    setFormData({ ...formData, [name]: value });
  };
  const changeInt = (name, value) =>{
    const LongId = parseInt(value, 10);
    setFormData({ ...formData, [name]: LongId });
  } 
  const handleSignup = () => {
    // 여기에 로그인 로직을 구현합니다.
    console.log('console');
    console.log(formData);
    if(formData.password != formData.passwordCheck)
    {
      console.log("differnt");
      Alert.alert('경고', '비밀번호가 일치하지 않습니다.');
    }
    else{
      try {
        const response = axios.post(
          'http://172.26.208.1.1:8080/users/signup',
          formData,
          {
            headers: {
              'Content-Type': 'application/json', // 서버가 JSON 형식을 원하는 경우
            },
          }
        );
        console.log('성공');
        navigation.navigate("Home");
      }
      catch (error) {
        console.log(error);
      }
    }
  };

  const closeModal = () => {
    setModalVisible(false);
  };

  return (
    <View style={styles.SignContainer}>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <View style={styles.container}>
        <Text style={styles.header}>Styles</Text>
        <TextInput
          style={styles.input}
          placeholder="이름"
          onChangeText={(text) => handleInputChange('username', text)}
        />
        <TextInput
          style={styles.input}
          placeholder="비밀번호"
          secureTextEntry
          onChangeText={(text) => handleInputChange('password', text)}
        />
        <TextInput
          style={styles.input}
          placeholder="비밀번호 확인"
          secureTextEntry
          onChangeText={(text) => handleInputChange('passwordCheck', text)}
        />
        <TextInput
          style={styles.input}
          placeholder="닉네임"
          onChangeText={(text) => handleInputChange('nickname', text)}
        />
        <TextInput
          style={styles.input}
          placeholder="이메일"
          onChangeText={(text) => handleInputChange('email', text)}
        />
        <TouchableOpacity style={styles.button} onPress={handleSignup}>
          <Text style={styles.buttonText}>가입하기</Text>
        </TouchableOpacity>
        <Modal isVisible={isModalVisible}>
          <View>
            <Text>알림 : 회원가입에 실패했습니다. 다시 확인해주세요.</Text>
            <Button title="닫기" onPress={closeModal} />
          </View>
        </Modal>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row',
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  SignContainer: {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  header: {
    fontSize: 24,
    marginBottom: 20,
  },
  input: {
    width: '80%',
    padding: 10,
    borderWidth: 1,
    borderColor: 'gray',
    marginBottom: 10,
  },
  button: {
    width: '80%',
    padding: 15,
    backgroundColor: '#00B292',
    alignItems: 'center',
    borderRadius: 5,
  },
  Title: {
    fontSize: 40,
    color: '#00B292',
    fontWeight: 'bold',
    marginLeft: 20,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
  },
});

export default LoginScreen;
