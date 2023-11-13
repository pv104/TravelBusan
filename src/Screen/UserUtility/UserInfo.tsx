import React, { useState,useEffect } from 'react';
import { View, Text, Button, StyleSheet,Alert} from 'react-native';
import {getCookie} from './Cookie';
import Nav from './nav';
import axios from 'axios';
import { useNavigation } from '@react-navigation/native';

const UserProfile = () => {
  const navigation = useNavigation();
  const onAccountDeletion =async() =>{
    try{
      const response = await axios.delete(`http://172.18.112.1:8080/users`,{
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      if(response.status == 200)
      {
        console.log("정상삭제");
        Alert.alert('알림', '계정이 정상적으로 삭제되었습니다.');
        navigation.navigate("home");
      }
    }
    catch (error) {
      console.log(error);
    }
  };
  const onPasswordChange = () =>{
  };
  const [username, setUsername]= useState('');
  const [nickname, setNickname]= useState('');
  const [email, setEmail] = useState('');
  const userId = getCookie('userId');
  console.log(userId);
  const setUser = async()=>{
    try{
      const response = await axios.get(`http://172.18.112.1:8080/users/user`, {
          headers: {
            'Authorization': `Bearer ${getCookie('token')}`
          }
        });
      if(response.status == 200)
      {
        console.log(response.data);
        setUsername(response.data.username);
        setNickname(response.data.nickname);  
        setEmail(response.data.email);
        console.log(username);
        console.log(nickname);
        console.log(email);
      }
    }
    catch (error) {
      console.log(error);
    }
  }
  useEffect(() => {
    setUser();
  }, []);
  return (
    <View style={styles.container}>
        <View style={styles.container2}> 
            <Nav/>
            <Text style={styles.Title}>Travel Busan</Text>
        </View>
        <View style={styles.content}>
            <Text style={styles.label}>ID: {username}</Text>
            <Text style={styles.label}>닉네임: {nickname}</Text>
            <Text style={styles.label}>이메일: {email}</Text>
            <View style={styles.buttonContainer}>
                <Button title="회원 정보 변경" onPress={onPasswordChange} />
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
