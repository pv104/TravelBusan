import React, { useState } from 'react';
import Nav from "../nav";
import { View, Text, StyleSheet, TextInput, Button, FlatList,Image } from 'react-native';

const BlogData = ({ route }) => {
  const [comment, setComment] = useState(''); // 댓글 내용 상태
  const [comments, setComments] = useState([]); // 댓글 목록
    // 댓글 작성 핸들러
    const handleComment = () => {
      if (comment) {
        // 현재 시간을 생성
        const now = new Date();
        // 시간과 댓글 내용을 함께 저장
        const commentWithTime = {
          time: now,
          text: comment,
        };
        // 새 댓글을 댓글 목록에 추가
        setComments([...comments, commentWithTime]);
        // 댓글 입력 필드 초기화
        setComment('');
      }
    };
    const formatDate = (date) => {
      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1
      const day = date.getDate().toString().padStart(2, '0');
      const hours = date.getHours().toString().padStart(2, '0');
      const minutes = date.getMinutes().toString().padStart(2, '0');
    
      return `${year}.${month}.${day} ${hours}:${minutes}`;
    };
  return (
    <View style={styles.container}>
        <View style={styles.menu}> 
            <Nav/>
            <Text style={styles.Title}>Travel Busan</Text>
        </View>
      <Text style={styles.title}>{route.params.title}</Text>
      <View style={styles.separator}/>
      <FlatList
  data={[
    {
      key: 'date',
      text: `작성일 : ${route.params.date}`,
    },
    {
      key: 'image',
      imageUri: route.params.imageUri,
    },
    {
      key: 'content',
      text: route.params.content,
    },
    {
      key: 'commentsTitle',
      text: '댓글',
    },
    {
      key: 'commentInput',
      input: comment,
    },
    {
      key: 'commentButton',
      buttonTitle: '댓글 작성',
    },
    ...comments, // 댓글 목록
  ]}
  keyExtractor={(item, index) => index.toString()}
  renderItem={({ item }) => (
    <View>
      {item.key === 'date' && (
        <Text style={styles.date}>{item.text}</Text>
      )}
      {item.key === 'image' && (
        <Image source={item.imageUri} style={styles.blogImage} />
      )}
      {item.key === 'content' && (
        <Text style={styles.content}>{item.text}</Text>
      )}
        
      {item.key === 'commentsTitle' && (
        <Text style={styles.commentsTitle}>{item.text}</Text>
      )}
      {item.key === 'commentInput' && (
        <TextInput
          style={styles.commentInput}
          placeholder="댓글을 작성하세요"
          value={item.input}
          onChangeText={setComment}
        />
      )}
      {item.key === 'commentButton' && (
        <Button title={item.buttonTitle} onPress={handleComment} />
      )}
      {item.key !== 'date' &&
        item.key !== 'image' &&
        item.key !== 'content' &&
        item.key !== 'commentsTitle' &&
        item.key !== 'commentInput' &&
        item.key !== 'commentButton' && (
          <View>
            <Text style={styles.commentItem}>{item.text}</Text>
            <Text style={styles.commentTime}>
              작성 시간: {formatDate(item.time)}
            </Text>
            <View style={styles.separator} />
          </View>
        )}
    </View>
  )}
/>

    </View>
  );
};

const styles = StyleSheet.create({
  commentTime: {
    fontSize: 12,
    color: 'gray',
  },
    menu: {
        flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
      },
      Title:{
        fontSize: 40,
        color : '#00B292',//진한 민트색
        fontWeight: 'bold',
        marginLeft : 20,
      },
      container3: {
        textAlign: 'left',
        borderWidth: 2,
        height: 100,
        width: 372,
      },
  container: {
    backgroundColor : "white",
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  blogImage:{
    width: 370,
    height: 200,
    resizeMode: 'cover',
  },
  title: {
    fontSize: 40,
    fontWeight: 'bold',
    marginTop : 30,
  },
  separator: {
    height: 1,
    backgroundColor: 'gray',
  },
  date : {
    fontSize:16,
    marginBottom : 50,
  },
  content: {
    fontSize: 16,
    color: 'black',
    fontWeight: 'bold',
  },
  commentsTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    marginTop: 20,
  },
  commentInput: {
    height: 40,
    borderWidth: 1,
    marginTop: 10,
    marginBottom: 10,
    padding: 5,
  },
  commentItem: {
    fontSize: 16,
    color: 'black',
    marginVertical: 5,
  },
  // 다른 스타일 추가
});

export default BlogData;
