import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import Nav from './nav';


const onAccountDeletion =() =>{

};
const onPasswordChange =() =>{

};
const UserProfile = ({ name, email, nickname}) => {
  return (
    <View style={styles.container}>
        <View style={styles.container2}> 
            <Nav/>
            <Text style={styles.Title}>Travel Busan</Text>
        </View>
        <View style={styles.content}>
            <Text style={styles.label}>이름: {name}</Text>
            <Text style={styles.label}>닉네임: {nickname}</Text>
            <Text style={styles.label}>이메일: {email}</Text>
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
