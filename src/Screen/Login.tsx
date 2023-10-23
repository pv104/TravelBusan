// LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import Nav from './nav'
import { useNavigation } from '@react-navigation/native';


const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigation  = useNavigation();
  const handleLogin = () => {
    // 여기에 로그인 로직을 구현합니다.
    // 로그인이 성공하면 다음 화면으로 이동할 수 있습니다.
    navigation.navigate('Home'); // Home은 다음 화면의 이름 (설정에 따라 다를 수 있음)
  };
  const GotoSignUp =() =>{
    navigation.navigate('Signup');
  }

  return (
    <View style ={styles.SignContainer}>
      <View style = {styles.menu}>
        <Nav/>
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <View style={styles.container}>
      <Text style={styles.header}>로그인</Text>
      <TextInput
        style={styles.input}
        placeholder="이메일"
        onChangeText={setEmail}
        value={email}
      />
      <TextInput
        style={styles.input}
        placeholder="비밀번호"
        secureTextEntry
        onChangeText={setPassword}
        value={password}
      />
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>로그인</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={GotoSignUp}>
        <Text style={styles.buttonText}>회원가입</Text>
      </TouchableOpacity>
    </View>
    </View>
  );
};

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
  },
  SignContainer : {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop : -200,
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
    backgroundColor:  '#00B292',//진한 민트색
    alignItems: 'center',
    borderRadius: 5,
    marginBottom : 20,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
  },
  Title:{
    fontSize: 40,
    color : '#00B292',//진한 민트색
    fontWeight: 'bold',
    marginLeft : 20,
  },
});

export default LoginScreen;
