import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, StyleSheet, FlatList } from 'react-native';
import Nav from './nav';
import { useNavigation } from '@react-navigation/native';

const data = [
  {
    id: 123,
    Title: "동아대학교",
    Star: 1,
    Good: 32156,
    Content: "그냥 대학교",
    type : "메인",
  }
]

const Review = [
  { id: 11, Title: "그냥 동아대", Writer: "테스터1", Star: 3.0, Content: "동아대는 그냥 동아대입니다.", type : "리뷰",},
  { id: 12, Title: "테스트", Writer: "테스터2", Star: 3.0, Content: "테스트 중입니다." , type : "리뷰"},
  { id: 13, Title: "222222", Writer: "테스터3", Star: 3.0, Content: "123123123123123123123." , type : "리뷰"},
]

const blogData = [
  { id: 1, title: '예시 1 - 블로그 제목', imageUri: require('../pics/광안대교.jpg'), content: '블로그 내용 1', date: '2023.10.13' , type : "블로그"},
  { id: 2, title: '예시 2', imageUri: require('../pics/광안대교.jpg'), content: '블로그 내용 2', date: '2023.10.14'  , type : "블로그"},
  { id: 3, title: '예시 3', imageUri: require('../pics/광안대교.jpg'), content: '블로그 내용 3', date: '2023.10.15'  , type : "블로그"},
  { id: 4, title: '예시 4', imageUri: require('../pics/광안대교.jpg'), content: '블로그 내용 4', date: '2023.10.16'  , type : "블로그"},
  // 다른 블로그 내용들 추가
];

const Sight = () => {
  const navigation = useNavigation();

  const IntoBlog = (item) => {
    navigation.navigate('BlogData', {
      id: item.id,
      title: item.title,
      imageUri: item.imageUri,
      content: item.content,
      date: item.date,
    });
  };
  const [prevType, setPrevType] = useState('메인'); // 이전 항목의 타입을 저장
  const renderItem = (item) => {
    if (item.type === '블로그')
     {
      return (
        <View style={styles.blogItem}>
              <View>
               {prevType !== item.type ? (
               <View>
                <Text>{item.type}</Text>
               </View>
                ) : null}
              </View>
          <TouchableOpacity onPress={() => IntoBlog(item)}>
            <Image source={item.imageUri} style={styles.blogImage} />
            <View style={styles.overlay}>
              <Text style={styles.blogTitle}>{item.title}</Text>
              <Text style={styles.blogDate}>{item.date}</Text>
            </View>
          </TouchableOpacity>
        </View>
      );
      
    } else if (item.type == '메인') 
    {
      return (
        <View style={styles.blogItem}>
            <View>
               {prevType!== item.type ? (
               <View>
                <Text>{item.type}</Text>
               </View>
                ) : null}
              </View>
            <Text style={styles.MainTitle}>{item.Title}</Text>
            <View style={styles.separator} />
            <View style={styles.Reviewmenu}>
          <Text style={styles.Reviewmenu_in}>좋아요: {item.Good}</Text>
          <Text style={styles.Reviewmenu_in}>별점: {item.Star}</Text>
            </View>
            <Text>{item.Content}</Text>
            <View style={styles.separator} />
      </View>
      );
    }
    else{
        return(
          <View style={styles.blogItem}>
            <View>
               {prevType !== item.type ? (
               <View>
                <Text>{item.type}</Text>
               </View>
                ) : null}
              </View>
            <Text style={styles.ReviewTitle}>{item.Title}</Text>
            <View style={styles.Reviewmenu}>
              <Text style={styles.Reviewmenu_in}>작성자: {item.Writer}</Text>
              <Text style={styles.Reviewmenu_in}>별점: {item.Star}</Text>
            </View>
            <Text>{item.Content}</Text>
            <View style={styles.separator} />
          </View>
        );
    }
  };

  return (
    <View>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <FlatList
        data={[...data, ...Review, ...blogData]} // 모든 데이터를 병합
        keyExtractor={(item) => item.id.toString()} // 각 항목의 고유 키 지정
        renderItem={({ item }) => renderItem(item)}
      />
    </View>
    
  );
};

const styles = StyleSheet.create({
  overlay: {
    ...StyleSheet.absoluteFillObject, // 컨테이너 전체를 채우도록 스타일 적용
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // 불투명한 배경색
    height: '38%', // 세로 높이의 1/3 크기
    top: 125,
    padding: 10,
    elevation: 4, // 그림자 효과 (Android)
    textAlign: 'left',
  },
  menu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
    marginBottom: 30,
  },
  MainTitle: {
    fontSize: 30,
    color: "black",
    fontWeight: "bold",
  },
  Reviewmenu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
    marginBottom: 20,
  },
  Reviewmenu_in: {
    marginRight: 10,
  },
  ReviewTitle: {
    fontSize: 20,
    marginBottom: 5,
    fontWeight: "bold",
    color: "black",
  },
  SeparatorTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    backgroundColor: 'white', // 제목 배경색
    textAlign: 'center',
    paddingVertical: 5,
  },
  Title: {
    fontSize: 40,
    color: '#00B292', // 진한 민트색
    fontWeight: 'bold',
    marginLeft: 20,
  },
  separator: {
    height: 1,
    backgroundColor: 'gray',
  },
  blogItem: {
    marginBottom: 30,
    marginTop: 0,
  },
  blogImage: {
    width: 400,
    height: 200,
    resizeMode: 'cover',
  },
  blogTitle: {
    fontSize: 20,
    color: 'white',
    fontWeight: 'bold',
  },
  blogDate: {
    color: 'white',
  },
});

export default Sight;
