import React, { useState } from 'react';
import { View, Text, Button, StyleSheet} from 'react-native';
import {getCookie} from './Cookie';
import Nav from './nav';
import axios from 'axios';
import Login from './Login';

const UserProfile = async () => {
  const onAccountDeletion =() =>{

  };
  const onPasswordChange =() =>{
  
  };
  const [username, setUsername]= useState('');
  const [nickname, setNickname]= useState('');
  const accessToken = getCookie('is_Login');
  try{
    const response = await axios.get('http://172.21.48.1:8080/users/Login.username',
    {
      headers: {
        'Authorization': `Bearer ${accessToken}`
      },
    }
    )
    if(response.status == 200)
    {
      console.log(response.data);
      setUsername(response.data.username);
      setNickname(response.data.nickname);  
    }
  }
  catch (error) {
    console.log(error);
  }
  return (
    <View style={styles.container}>
        <View style={styles.container2}> 
            <Nav/>
            <Text style={styles.Title}>Travel Busan</Text>
        </View>
        <View style={styles.content}>
            <Text style={styles.label}>이름: {username}</Text>
            <Text style={styles.label}>닉네임: {nickname}</Text>
            <View style={styles.buttonContainer}>
                <Button title="비밀번호 변경" onPress={onPasswordChange} />
                <Button title="회원 탈퇴" onPress={onAccountDeletion} />
            </View>
        </View>
    </View>
  );
};

const styles = StyleSheet.create({
    container2: {
        flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
      },
      Title:{
        fontSize: 40,
        color : '#00B292',//진한 민트색
        fontWeight: 'bold',
        marginLeft : 20,
      },
  container: {
    backgroundColor : 'white',
    padding: 20,
  },
  label: {
    fontSize: 18,
    marginBottom: 10,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 20,
  },
  content:{
    marginTop: 40,
    borderStyle: 'solid', // 테두리 선 스타일
    borderWidth: 1, // 테두리 두께
    borderColor: 'gray', // 테두리 선 색상
    padding: 20, // 컨텐츠와 테두리 간의 간격
  }
});

export default UserProfile;
