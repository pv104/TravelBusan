import React,{useState} from 'react';
import {View,Text, StyleSheet,TouchableOpacity } from 'react-native';
import { getCookie } from '../Cookie';
import { useNavigation } from '@react-navigation/native';

const myData =()=>{
    const navigation = useNavigation();
    const handlePress = () => {
        // 버튼을 누르면 이 함수가 실행됩니다.
        // 원하는 작업을 여기에 추가하세요.
        navigation.navigate("Blog");
      };
    const [mine, setMine] = useState('로그인 된 아이디가 없습니다.');
    const [signal, setSignal] = useState('');
    const handleSign =() =>{
        setSignal("새로운 소식이 없습니다.");
    };
    const handleActing =() =>{
        setSignal("새로운 활동이 없습니다.");
    }
    const cookie = getCookie('is_login');
    const userId = getCookie('userId');
    console.log(userId);
    if(cookie != undefined)//로그인 상태 확인
    {
        setMine("함수 작동 확인");
    }
    else if(userId != undefined)
    {
        setMine(userId);
    }
    return(
        <View>
            <Text style ={styles.my}>{mine}</Text>
            <View style ={styles.menuButton}>
                <TouchableOpacity style={styles.button} onPress={handlePress}>
                    <Text style={styles.buttonText}>내 블로그로</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.button}>
                    <Text style={styles.buttonText}>방명록</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.button} onPress ={handleSign}>
                    <Text style={styles.buttonText}>내 소식</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.button} onPress ={handleActing}>
                    <Text style={styles.buttonText}>내 활동</Text>
                </TouchableOpacity>
            </View>
            <View style={styles.container}>
                <Text style={styles.text}>{signal}</Text>
            </View>
        </View>
    );
};
const styles = StyleSheet.create({
    my:{
        backgroundColor : 'gray',
        fontSize : 20,
        color : 'white',
        fontWeight : 'bold',
    },
    container: {
      justifyContent: 'center',
      alignItems: 'center',
      borderWidth : 2,
      height : 110,
      width : 372,
    },
    button: {
      backgroundColor: '#00B292', // 버튼 배경색
      paddingVertical: 8, // 세로 방향 padding
      paddingHorizontal: 5, // 가로 방향 padding
      width : '23.8%',
      borderRadius: 5,
      marginRight : 5,
    },
    text: {
        fontSize: 24,
        fontWeight: 'bold',
        color: 'gray',
      },
    menuButton:{
        backgroundColor : '#00B292',
        flexDirection: 'row',
        marginBottom: 5,
    },
    menu:{
        flexDirection: 'row',
        borderBlockColor : 'black',
        borderWidth: 2,
    },
    buttonText: {
      color: 'white',
      fontSize : 12,
      textAlign : 'center',
      fontWeight: 'bold',
    },
  });
export default myData;