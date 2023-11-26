import React, { useEffect, useState } from 'react';
import { View,Image, Text, StyleSheet, FlatList, SafeAreaView,TouchableOpacity } from 'react-native';
import Nav from "../UserUtility/nav";
import BlogSearch from "./BlogSearch"
import Mine from "./Blog_mydata";
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
import { getCookie } from '../UserUtility/Cookie';


const blogData = [
  { 
    id: 1,//https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMTVfMjI4%2FMDAxNjk5OTc5MTc1NTI3.SQrOvisLy02F6ffumh4GhM2vlsghj436xGr2RCnrYzYg.uhDJDmc8-gFb031XSuuR0Cy-OMKvrLsbKY39VNvd3sIg.JPEG.restinbed%2F20231112%25A3%25DF165356.jpg
     title: '고척 스카이돔 다녀왔습니다.',
     imageUri: 'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200512_262%2F1589288911579EayCb_JPEG%2FS6KIQJennJusJ5nHyymqwarx.jpg',
     content: '야구보는 게 인생의 큰 낙인 것 같은 짝꿍과 함께 진짜 몇 년 만에 프로야구 직관을 하러 갔다. 나는 야구알못이지만.. 직관은 현장감 속에서 함께 응원하고, 이것저것 사먹는 재미가 쏠쏠한 것 같다. 서울에서 프로야구 경기가 열리는 야구장은 두 곳 !LG와 두산의 홈구장인 잠실야구장과 키움 홈구장인 고척야구장(aka 고척돔/고척스카이돔).우린 삼성팬이라서 주말 중 서울 원정경기를 찾다가 삼성:키움전을 보러 고척돔에 가게 됐다.', 
     date: '2023.10.13'
     },
     { 
       id: 2,
       title: '롤드컵 경기 직관 후기',
       imageUri: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMTVfMjI4%2FMDAxNjk5OTc5MTc1NTI3.SQrOvisLy02F6ffumh4GhM2vlsghj436xGr2RCnrYzYg.uhDJDmc8-gFb031XSuuR0Cy-OMKvrLsbKY39VNvd3sIg.JPEG.restinbed%2F20231112%25A3%25DF165356.jpg',
       content: '리닝전 너무 잘해서 징동전도 속으로는 이길거야 했지만 또 징동이 너무 강한상대라 걱정도했는데 진짜 너무 잘해서 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ오프닝영상도 너무 뽕찼다', 
       date: '2023.11.15'
       }
  // 다른 블로그 내용들 추가
];
const BlogScreen = () => {
const [CommunityData, setCommunityData] = useState([]);
const SetData = async () => {
    try {
      const response = await axios.get('http://192.168.123.172:8080/users/my-blog', {
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      console.log(response);
      const updatedCommunityData = response.data.data.map((item) => ({
        id: item.id,
        title: item.title,
        imageUri: require('../../pics/광안대교.jpg'),
        content: item.content, 
        date: item.date
      }));

      setCommunityData(updatedCommunityData);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    SetData();
  }, []);
  const navigation  = useNavigation();
  const IntoBlog = (item) => {
    navigation.navigate('BlogData', { 
      id : item.id,
      title : item.title,
      imageUri : item.imageUri,
      content : item.content,
      date : item.date,
     });
  };
  const userData = {
    username: 'login',
  };
  
  const explain ={
    ex : '나만의 작은 블로그. 언제나 여행가기 좋아.'
  }  

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
            <TouchableOpacity onPress={() => IntoBlog(item)}>
              <Image source={{uri: item.imageUri}} style={styles.blogImage} />
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
