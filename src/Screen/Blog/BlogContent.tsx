import React, { useState } from 'react';
import { View, Text, TextInput, Image, StyleSheet, TouchableOpacity } from 'react-native';
import Nav from '../nav';
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
const BlogEditor = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [image, setImage] = useState(null);
  const navigation = useNavigation();

  const handleImagePicker = () => {
    const option = {
      title: '이미지 선택',
      storageOptions: {
        skipBackup: true,
        path: 'images',
      },
    };
  
    /*ImagePicker.launchImageLibrary(option, (response) => {
      if (response.didCancel) {
        console.log('사용자가 이미지 선택을 취소했습니다.');
      }
    });*/
  };

  const handleSubmit = () => {
    console.log('제목:', title);
    console.log('내용:', content);
    console.log('이미지 URI:', image);
    try {
      const response = axios.post(
        'http://172.21.48.1:8080/api/blog',
        {
          title : title,
          content : content,
          image : image,
        },
        {
          headers: {
            'Content-Type': 'application/json', // 서버가 JSON 형식을 원하는 경우
          },
        }
      );
      console.log('성공');
      navigation.navigate("Blog");
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
      <TouchableOpacity style={styles.button} onPress={handleImagePicker}>
        <Text style={styles.buttonText}>이미지 첨부</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={handleSubmit}>
        <Text style={styles.buttonText}>제출</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
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
});

export default BlogEditor;
