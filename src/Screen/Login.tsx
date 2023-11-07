// LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import Nav from './nav'
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
import { setCookie,getCookie } from './Cookie';


const LoginScreen = () => {
  const [username, setusername] = useState('');
  const [password, setPassword] = useState('');
  const navigation  = useNavigation();
  const handleLogin = async () => {
    // 여기에 로그인 로직을 구현합니다.
    // 로그인이 성공하면 다음 화면으로 이동할 수 있습니다.
    try {
      const response = await axios.post(
        'http://172.21.48.1:8080/api/login',
        {
          username: username,
          password: password,
        },
        {
          headers: {
            'Content-Type': 'application/json', // 서버가 JSON 형식을 원하는 경우
          },
        }
      );
      if(response.status == 200)
      {
        try{
          const response2 = await axios.post(
            'http://172.21.48.1:8080/api/authenticate',
            {
              username : username,
              password : password,
            },
            {
              headers: {
                'Content-Type': 'application/json', // 서버가 JSON 형식을 원하는 경우
              },
            }
          )
          const accessToken = response2.data.token;
          setCookie("is_login", `${accessToken}`,''); 
          setCookie("userId", `${username}`,''); 
          console.log("쿠키 토큰" + getCookie("is_login"));
          console.log("로그인된 아이디 : "+ getCookie("userId"));
          console.log("로그인성공");
        }
        catch (error) {
          console.log(error);
        }
      }
      /*const accessToken = response.data.token;
      setCookie("is_login", `${accessToken}`,''); 
      console.log("쿠키 토큰" + getCookie("is_login"));
      console.log("로그인성공");*/

      navigation.navigate("Home");
    } 
    catch (error) {
      console.log(error);
    }
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
        placeholder="아이디"
        onChangeText={setusername}
        value={username}
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
