// LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Button } from 'react-native';
import Modal from 'react-native-modal'
import Nav from './nav';

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [checkpassword, setcheckPassword] = useState('');
  const [userName, setuserName] = useState('');
  const [nickName, setnickName] = useState('');
  const [isModalVisible, setModalVisible] = useState(false);

  const handleSignup = () => {
    // 여기에 로그인 로직을 구현합니다.
    // 로그인이 성공하면 다음 화면으로 이동할 수 있습니다.
    if(password == checkpassword)
    {
        navigation.navigate('Home'); // Home은 다음 화면의 이름 (설정에 따라 다를 수 있음)
    }
    else//password != checkpassword
    {
        setModalVisible(true);
        navigation.navigate('Signup');
    }
    
  };
  const closeModal = () => {
    setModalVisible(false);
  };
  return (
    <View style ={styles.SignContainer}>
      <View style ={styles.menu}>
        <Nav/>
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <View style={styles.container}>
      <Text style={styles.header}>회원가입</Text>
      <TextInput
        style={styles.input}
        placeholder="이름"
        onChangeText={setuserName}
        value={userName}
      />
      <TextInput
        style={styles.input}
        placeholder="닉네임"
        onChangeText={setnickName}
        value={nickName}
      />
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
      <TextInput
        style={styles.input}
        placeholder="비밀번호 확인"
        secureTextEntry
        onChangeText={setcheckPassword}
        value={checkpassword}
      />
      <TouchableOpacity style={styles.button} onPress={handleSignup}>
        <Text style={styles.buttonText}>회원가입</Text>
      </TouchableOpacity>
      <Modal isVisible={isModalVisible}>
        <View>
            <Text>알림 : 비밀번호가 다릅니다. 다시 확인해주세요.</Text>
            <Button title="확인" onPress={closeModal}></Button>
        </View>
      </Modal>
    </View>
    </View>
  );
};

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  SignContainer : {
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
  Title:{
    fontSize: 40,
    color : '#00B292',//진한 민트색
    fontWeight: 'bold',
    marginLeft : 20,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
  },
});

export default LoginScreen;
