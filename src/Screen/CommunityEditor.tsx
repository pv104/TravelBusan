import React, { useState } from 'react';
import { View, Text, TextInput, Button, Image, StyleSheet } from 'react-native';

const BlogEditor = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  // 제출 버튼을 클릭했을 때 실행
  const handleSubmit = () => {
    // title, content, image 등을 사용하여 블로그 내용을 저장 또는 업로드하는 로직을 추가
    console.log('제목:', title);
    console.log('내용:', content);
    // 여기에 서버에 블로그 내용을 업로드하거나 저장하는 로직을 추가하세요.
  };

  return (
    <View style={styles.container}>
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
