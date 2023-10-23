import React from 'react';
import { View,Image, Text, StyleSheet, FlatList, SafeAreaView,TouchableOpacity } from 'react-native';
import Nav from "./nav";
import BlogSearch from "./BlogSearch"
import Mine from "./Blog_mydata";
import { useNavigation } from '@react-navigation/native';

const userData = {
  username: '작성자 아이디',
};

const explain ={
  ex : '블로그 테스팅 입니다~(블로그 설명란)'
}

const blogData = [
  { id: 1,
     title: '예시 1 - 블로그 제목',
     imageUri: require('../pics/광안대교.jpg'),
     content: '블로그 내용 1', 
     date: '2023.10.13' },
  { id: 2, title: '예시 2',imageUri: require('../pics/영도대교.png'),content:  '블로그 내용 2', date: '2023.10.14' },
  { id: 3, title: '예시 3',imageUri: require('../pics/광안대교.jpg'),content:  '블로그 내용 3', date: '2023.10.15' },
  { id: 4, title: '예시 4',imageUri: require('../pics/광안대교.jpg'),content:  '블로그 내용 4', date: '2023.10.16' },
  // 다른 블로그 내용들 추가
];

const IntoBlog =()=>{

};

const BlogScreen = () => {
  const navigation  = useNavigation();
  const IntoEditor=()=>{
    navigation.navigate('Editor');
  }
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.menu}> 
        <Nav/>
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <View style={styles.searchbar}>
        <BlogSearch/>
      </View>
      <Mine/>
      <View style={styles.button2}>
        <Text style={styles.explain}>{explain.ex}</Text>
      </View>
      <View style={styles.separator} />
      <View>
        <TouchableOpacity onPress={IntoEditor}>
          <Text style={styles.button}>글 작성하기</Text>
        </TouchableOpacity>
      </View>
      <FlatList
        data={blogData}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.blogItem}>
            <TouchableOpacity onPress={IntoBlog}>
              <Image source={item.imageUri} style={styles.blogImage} />
              <View style={styles.overlay}>
                <Text style={styles.blogTitle}>{item.title}</Text>
                <Text style={styles.blogDate}>{item.date}</Text>
                <Text style={styles.username}>{userData.username}</Text>
                </View>
                </TouchableOpacity>
                </View>
        )}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
  },
  Title:{
    fontSize: 40,
    color : '#00B292',//진한 민트색
    fontWeight: 'bold',
    marginLeft : 20,
  },
  container: {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  username: {
    fontSize: 13,
    color : 'white',
    fontWeight: 'bold',
    textAlign : "right",
    marginBottom: 10,
  },
  button: {
    color : 'white',
    backgroundColor: '#00B292', // 버튼 배경색
    paddingVertical: 8, // 세로 방향 padding
    paddingHorizontal: 5, // 가로 방향 padding
    width : '23.8%',
    borderRadius: 5,
    marginRight : 5,
  },
  overlay: {
    ...StyleSheet.absoluteFillObject, // 컨테이너 전체를 채우도록 스타일 적용
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // 불투명한 배경색
    height: '38%', // 세로 높이의 1/3 크기
    top: 125,
    padding: 10,
    elevation: 4, // 그림자 효과 (Android)
    textAlign: 'left',
    
  },
  explain:{
    fontSize: 15,
    color : 'black',
    marginTop: -30,
  },
  separator: {
    height: 1,
    backgroundColor: 'gray',
    marginBottom: 20,
  },
  searchbar: {
    transform: [{ scale: 0.9 }],
    justifyContent: 'center',  // 수평 중앙 정렬
    alignContent: 'center',   // 수직 중앙 정렬
    alignItems: 'center',     // 자식 요소들을 수직 중앙 정렬
  },
  blogItem: {
    marginBottom: 50,
    marginTop : 10,
  },
  blogImage:{
    width: 400,
    height: 200,
    resizeMode: 'cover',
  },
  blogTitle:{
    fontSize : 20,
    color : 'white',
    fontWeight: 'bold',
  },
  blogDate:{
    color:'white',
  },
  button2: {
    marginTop: 50,
  }
});

export default BlogScreen;
