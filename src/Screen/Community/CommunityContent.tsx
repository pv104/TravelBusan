import React, { useState } from 'react';
import Nav from "../UserUtility/nav";
import { View, Text, StyleSheet, TextInput, Button, FlatList } from 'react-native';

const formatDate = (date) => {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1
    const day = date.getDate().toString().padStart(2, '0');
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
  
    return `${year}.${month}.${day} ${hours}:${minutes}`;
  };
const CommunityContent = ({ route }) => {
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

  return (
    <View style={styles.container}>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <Text style={styles.title}>{route.params.title}</Text>
      <View style={styles.separator} />
      <Text style={styles.date}>작성일 : {route.params.date}</Text>
      <Text style={styles.Writer}>작성자 : {route.params.Writer}</Text>
      <View style={styles.container2}>
        <Text style={styles.content}>{route.params.content}</Text>
      </View>
      <Text style={styles.commentsTitle}>댓글</Text>
      <TextInput
        style={styles.commentInput}
        placeholder="댓글을 작성하세요"
        value={comment}
        onChangeText={setComment}
      />
      <Button title="댓글 작성" onPress={handleComment} />
      <View style={styles.container3}>
        <FlatList
          data={comments}
          keyExtractor={(item, index) => index.toString()}
          renderItem={({ item }) => (
            <View>
                <View>
                    <Text style={styles.commentItem}>{item.text}</Text>
                    <Text style={styles.commentTime}>
                        작성 시간: {formatDate(item.time)}
                    </Text>
                </View>
              <View style={styles.separator} />
            </View>
          )}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
    container2: {
      textAlign: 'left',
      borderWidth: 2,
      height: 110,
      width: 372,
    },
    commentTime: {
        fontSize: 12,
        color: 'gray',
      },
    container3: {
      textAlign: 'left',
      borderWidth: 2,
      height: 250,
      width: 372,
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
    container: {
      backgroundColor: 'white',
      flex: 1,
      paddingHorizontal: 20,
      paddingTop: 20,
    },
    blogImage: {
      width: 370,
      height: 200,
      resizeMode: 'cover',
    },
    title: {
      fontSize: 40,
      fontWeight: 'bold',
      marginTop: 30,
    },
    separator: {
      height: 1,
      backgroundColor: 'gray',
    },
    Writer: {
      fontSize: 16,
      marginBottom: 30,
    },
    date: {
      fontSize: 16,
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

  export default CommunityContent;