import React, { useState } from 'react';
import { View, Text, TextInput, Button, Image, StyleSheet } from 'react-native';
import axios from 'axios';
import { useNavigation } from '@react-navigation/native';
import { getCookie } from '../Cookie';


const BlogEditor = () => {
  const navigation = useNavigation();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  // 제출 버튼을 클릭했을 때 실행
  const handleSubmit = async () => {
    // title, content, image 등을 사용하여 블로그 내용을 저장 또는 업로드하는 로직을 추가
    // 여기에 서버에 블로그 내용을 업로드하거나 저장하는 로직을 추가하세요.
    console.log(title);
    console.log(content);
    console.log(getCookie('token'));
    try {
      const response = await axios.post(
        'http://172.26.208.1:8080/boards',
        {
          title : title,
          content : content,
        },
        {
          headers: {
             Authorization: `Bearer ${getCookie('token')}`,
            'Content-Type': 'application/json',
          },
        }
      );
      if(response.status == 200)
      {
        console.log('성공');
        console.log(response.data);
        navigation.navigate("Community");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.label}>제목</Text>
      <TextInput
        style={styles.input}
        onChangeText={(text) => setTitle(text)}
        placeholder="제목을 입력하세요"
      />

      <Text style={styles.label}>내용</Text>
      <TextInput
        style={[styles.input, styles.contentInput]}
        onChangeText={(text) => setContent(text)}
        placeholder="게시글 내용을 입력하세요"
        multiline={true}
      />

      <Button title="제출" onPress={handleSubmit} />
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
  input: {
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 5,
    padding: 10,
    marginVertical: 10,
  },
  contentInput: {
    height: 150, // 여러 줄 텍스트 입력을 위한 높이 조절
  },
  image: {
    width: 200,
    height: 200,
    marginTop: 10,
  },
});

export default BlogEditor;
