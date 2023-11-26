import React, { useEffect, useState } from "react";
import {View, Text, Image, Button, Alert, StyleSheet, ImageURISource, TextInput, TouchableOpacity} from 'react-native'
import {launchCamera, launchImageLibrary, CameraOptions, ImagePickerResponse, ImageLibraryOptions, Asset} from 'react-native-image-picker';
import Nav from "../UserUtility/nav";
import axios from "axios";
import { getCookie } from "../UserUtility/Cookie";
import { useNavigation } from "@react-navigation/native";
      /*formData.append('title',JSON.stringify(title));
      formData.append('content',JSON.stringify(content));*/
// 이미지 파일 배열

// Blob 객체 생성

//함수형 컴포넌트
const BlogContent =() => {
  const navigation = useNavigation();
    //화면 갱신에 영향을 주는 특별한 변수 state 하지만 여긴 함수임 useState() 사용
    const [img, setImg] = useState<Asset[]>();//setState를 대신하는 넘이랑 변수랑 같이 하기
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const formData = new FormData();
    const showPhoto = async ()=> {
      //1. 옵션객체 만들기
      const option: ImageLibraryOptions = {
          mediaType : "photo",
          selectionLimit : 5,
      }

      //ES7의 새로운 문법 : async-await 문법 [callback 비동기 작업을 동기작업처럼 처리함]
      const response = await launchImageLibrary(option) //함수에 async가 붙어 있어야 함
      if(response.didCancel) Alert.alert('취소')
      else if(response.errorMessage) Alert.alert('Error : '+ response.errorMessage)
      else {
          const uris:Asset[] = []
         
          response.assets?.forEach((value)=>uris.push(value)) //선택한 사진 순서와 상관없이 들어옴
          //원래는 FlatList로 이미지 보여줘야하지만 
          //첫번째 이미지만 보여주기
          setImg( uris );
      }
  }

    const handleSubmit = async() => {
      const value ={
        title : title,
        content : content
      };
      const imgFile = {
        uri: img[0].uri,
        type: "image/jpeg", // 이미지 타입에 맞게 설정
        name: "image.jpg",
      };
      console.log(imgFile);
      formData.append("title",JSON.stringify(title));
      formData.append("content",JSON.stringify(content));
      formData.append("img", imgFile);
      console.log(formData);
      try {
        const response = await axios.post(
          'http://192.168.123.145:8080/blogs',
          formData,
          {
            headers: {
              Authorization: `Bearer ${getCookie('token')}`,
              'Content-Type' : 'multipart/form-data',
            },
            transformRequest: (data, headers) => {
              return data;
            },
          });
          console.log("성공");
          navigation.goBack();
      } catch (error) {
        console.log(error);
      }
    };
    return (
      <View style={styles.container}>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <Text style={styles.label}>제목</Text>
      <TextInput
        style={styles.input}
        value={title}
        onChangeText={(text) => setTitle(text)}
        placeholder="제목을 입력하세요"
      />

      <Text style={styles.label}>내용</Text>
      <TextInput
        style={[styles.input, styles.contentInput]}
        value={content}
        onChangeText={(text) => setContent(text)}
        placeholder="블로그 내용을 입력하세요"
        multiline={true}
      />
      {img && 
      <View style={styles.root}>
       <Image source={{uri : img[0].uri}} style={styles.img}></Image>
    </View>        
    }
    <View>
     <TouchableOpacity style={styles.button} onPress={showPhoto}>
         <Text style={styles.buttonText}>이미지 첨부</Text>
     </TouchableOpacity>
    </View>
      <TouchableOpacity style={styles.button} onPress={handleSubmit}>
        <Text style={styles.buttonText}>제출</Text>
      </TouchableOpacity>
    </View>
    )

}

const styles = StyleSheet.create({
    root: {
        flex : 1,
        padding : 16,
    },
    text : {
        padding:8,
        color : 'black'
    },
    img : {
      marginTop : 8,
      flex : 1, //가로넓이는 alien-item이 관리하고 기본 스트래치로 되어 있음
    },
    container: {
      padding: 20,
    },
    label: {
      fontSize: 18,
      fontWeight: 'bold',
    },
    buttonText: {
      color: 'white',
      textAlign: 'center',
    },
    button: {
      marginBottom: 10,
      backgroundColor: '#00B292', // 버튼 배경색
      paddingVertical: 8, // 세로 방향 padding
      paddingHorizontal: 5, // 가로 방향 padding
      width: '23.8%',
      borderRadius: 5,
      marginRight: 5,
    },
    input: {
      borderWidth: 1,
      borderColor: 'gray',
      borderRadius: 5,
      padding: 10,
      marginVertical: 10,
    },
    contentInput: {
      height: 150,
    },
    image: {
      width: 200,
      height: 200,
      marginTop: 10,
    },
    menu: {
      flexDirection: 'row',
    },
    Title: {
      fontSize: 40,
      color: '#00B292',
      fontWeight: 'bold',
      marginLeft: 20,
    },
})

export default BlogContent;