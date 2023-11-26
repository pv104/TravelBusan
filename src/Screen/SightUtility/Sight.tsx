import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, StyleSheet, FlatList, Modal, SafeAreaView } from 'react-native';
import Nav from '../UserUtility/nav';
import { useNavigation } from '@react-navigation/native';
import MapView, { Marker, PROVIDER_GOOGLE } from 'react-native-maps';
import axios from 'axios';
import { getCookie } from '../UserUtility/Cookie';

const blogData = [
  { id: 1,
    title: '흰여울 마을 후기',
    imageUri: 'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20171228_197%2F1514451096257hdrvJ_JPEG%2FEUDU5oOqcz00HIt4wJD5lVp2.jpg',
    content: '흰여울 마을을 다녀왔어요~~~~~~', 
    date: '2023.10.13',
   type : "블로그" },
 { id: 2,
    title: '흰여울 마을 산책사진',
    imageUri: 'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20171228_103%2F1514450162821RHFxw_JPEG%2FtDyLFd8UKNKcICm84xseyXxR.jpg',
    content:  '블로그 내용 2', date: '2023.10.14',type : "블로그" },
 { id: 3, 
   title: '영도, 흰여울 마을을 다녀오다.',
   imageUri: 'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20171228_117%2F1514450162206uDTng_JPEG%2FsgnIY_DysRFctyLJN3z_Z4SD.jpg',
   content:  '블로그 내용 3', date: '2023.10.15',type : "블로그" },
 { id: 4, 
   title: '부산 여행(영도편)',
   imageUri: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA2MTNfNTEg%2FMDAxNjg2NjU3MzE5MjM3.8tXSPZVmj5GjPZOOYTq0_Z3DPRmmBLmpYm3ZvIiOlCYg.-4AQy2JCaukyfpTdP2boZLLB89cYTomW_etW6R0Qbu4g.JPEG.smulsal%2F%253F%259E%25A5%253F%2586%258C%25EC%25B6%2594%25EC%25B2%259C-001_%25282%2529.jpg',
   content:  '블로그 내용 4', date: '2023.10.16', type : "블로그" },
 // 다른 블로그 내용들 추가
];
const Sight = ({ route }) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isActive, setIsActive] = useState(false);
  const [review, setReview] = useState([]);
  const [blog, setblog] = useState([]);
  const [showAllReviews, setShowAllReviews] = useState(false);
  const sight_id = route.params.id;
  const upGood=()=>{

  };
  useEffect(() => {
    ReviewFetchData();
    BlogFetchData();
  }, []);

  const ReviewFetchData = async () => {
    try {
      const response = await axios.get(`http://192.168.123.145:8080/sights/${sight_id}`, {
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      const updatedSightData = response.data.data.comments.map((item) => ({
        id : item.reviewId,
        sightsId : item.sightsId,
        reviewId : item.reviewId,
        title : item.title,
        writer : item.writer,
        comment : item.comment,
        creData : item.creData,
        rating : item.rating,
        type : "리뷰"
      }));
      setReview(updatedSightData);
      
    } catch (error) {
      console.error(error);
    }
  };

  const BlogFetchData = async () =>{
    try {
      const response = await axios.get(`http://192.168.123.145:8080/blogs`, {
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      console.log(response.data.data);
      const updatedSightData = response.data.data.map((item) => ({
        id : item.id,
        title : item.title,
        content : item.content,
        nickname : item.nickname,
        visit : item.visit,
        likeCount : item.likeCount,
        imageUri : JSON.stringify(item.img),
        type : "블로그"
      }));
      setblog(updatedSightData);
    } catch (error) {
      console.error(error);
    }
  }
const gotoReview=(item)=>{
  navigation.navigate("Review", item);
}
  const toggleModal = () => {
    setIsModalVisible(!isModalVisible);
    setIsActive(false);
  };
  const InfoModal =()=>{
    toggleModal();
  }
  const IntoBlog = (item) => {
    navigation.navigate('BlogData', {
      id: item.id,
      title: item.title,
      imageUri: item.img,
      content: item.content,
      date: item.date,
    });
  };
  const navigation = useNavigation();
  const rendering = (item, index) => {
    if (item.type === '리뷰')
     {
      if(!showAllReviews&&index > 2)
      {
        return(
          <View>
          </View>
        );
      }
      return (
        <View style={styles.bubbleContainer}>
      <View style={styles.bubble}>
        <Text style={styles.title}>{item.title}</Text>
        <View style={styles.separator} />
        <Text style={styles.writer}>작성자: {item.writer}</Text>
        <Text style={styles.writer}>별점 : {item.rating}</Text>
        <Text style={styles.content}>{item.comment}</Text>
      </View>
      </View>
      );
    } 
    else if(item.type == '블로그')
    {
      return(
        <View style={styles.blogItem}>
        <TouchableOpacity onPress={() => IntoBlog(item)}>
        <Image source={require("C:\Users\vv545\Project\TravelBusan\src\main\resources\static\img\\광안대교.jpg")}
         style={styles.blogImage} />
        <View style={styles.overlay}>
          <Text style={styles.blogTitle}>{item.title}</Text>
          <Text style={styles.blogDate}>{item.date}</Text>
        </View>
      </TouchableOpacity>
    </View>
      )
    }
  }
  return (
   <FlatList
    data={[...review,...blog]}
    keyExtractor={(item) => item.title.toString()}
    ListHeaderComponent={() => (
      <View>
        <View style={styles.menu}>
          <Nav />
          <Text style={styles.Title}>Travel Busan</Text>
        </View>
        <View style={styles.blogItem}>
          <Text style={styles.MainTitle}>{route.params.name}</Text>
          <TouchableOpacity>
            <Text>세부 정보 보기</Text>
          </TouchableOpacity>
          <View style={styles.separator} />
          <View style={styles.Reviewmenu}>
            <TouchableOpacity onPress={()=>upGood()}>
            <Text style={styles.Reviewmenu_in}>좋아요: {route.params.like}</Text>
            </TouchableOpacity>
            <Text style={styles.Reviewmenu_in}>별점: {route.params.rate}</Text>
          </View>
          <Image source={{ uri: route.params.img }} style={styles.blogImage} />
          <Text numberOfLines={5} style={{ fontSize: 16 }}>{route.params.info}</Text>
          <TouchableOpacity onPress={() => InfoModal()}>
            <Text>더보기</Text>
          </TouchableOpacity>
          <Modal
            visible={isModalVisible}
            animationType="slide"
            transparent={true}
          >
            <View style={styles.modalContainer}>
              <Text style={{ height: '80%', textAlign: "center", color: "white" }}>{route.params.info}</Text>
              <TouchableOpacity onPress={() => toggleModal()}>
                <Text style={styles.closeButton}>Close</Text>
              </TouchableOpacity>
            </View>
          </Modal>
          <View style={styles.separator} />
          <SafeAreaView style={styles.container}>
              <View style={styles.mapContainer}>
                <MapView
                  style={styles.map} 
                  provider={PROVIDER_GOOGLE}
                  initialRegion={{
                    latitude: route.params.mapy,
                    longitude: route.params.mapx,
                    latitudeDelta: 0.009,
                    longitudeDelta: 0.004,
                  }}
                >
              <Marker
              coordinate={{
                latitude: route.params.mapy,
                longitude: route.params.mapx,
              }}
                title={route.params.name}
                description={route.params.addr}
            />
                </MapView>
              </View>
            </SafeAreaView>
            <View>
              <Text style={{fontSize : 20,color :"black",fontWeight:"bold"}}>
                {route.params.addr}
              </Text>
            </View>
            <View style={styles.separator} />
        </View>
        <View>
          <TouchableOpacity onPress={()=>gotoReview(route.params.id)}>
            <Text style={{
              marginLeft:'1%',
              backgroundColor:"#00B292",
              color:"white", 
              width:"20%", 
              fontSize : 20,
              borderTopLeftRadius: 5,
              borderTopRightRadius: 5,
              borderBottomLeftRadius: 5,
              borderBottomRightRadius: 5,
              marginBottom : 5,
              }}>리뷰 작성</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => setShowAllReviews(!showAllReviews)}>
              <Text style={{
              marginLeft:'1%',
              backgroundColor:"#00B292",
              color:"white", 
              width:"30%", 
              fontSize : 20,
              borderTopLeftRadius: 5,
              borderTopRightRadius: 5,
              borderBottomLeftRadius: 5,
              borderBottomRightRadius: 5,
              }}>모든 리뷰 보기</Text>
            </TouchableOpacity>
        </View>
      </View>
    )}
    renderItem={({ item, index }) => rendering(item, index)}
/>

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
  container: {
    flex: 1,
  },
  mapContainer: {
    width: '100%',
    height: 300, // 작은 창 크기 조절
    borderRadius: 10,
    overflow: 'hidden',
    marginBottom: 20,
  },
  map: {
    flex: 1,
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
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
    fontSize : 20,
  },
  ReviewTitle: {
    fontSize: 20,
    marginBottom: 5,
    fontWeight: "bold",
    color: "black",
  },
  closeButton: {
    color: 'white',
    fontWeight: 'bold',
    marginTop: 60,
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
    width: 500,
    height: 200,
    resizeMode: 'cover',
    marginBottom : 30,
  },
  blogTitle: {
    fontSize: 20,
    color: 'white',
    fontWeight: 'bold',
  },
  blogDate: {
    color: 'white',
  },
  bubbleContainer: {
    paddingHorizontal: 20,
    paddingVertical: 15,
    borderRadius: 10,
    backgroundColor: '#e3e3e3',
    marginHorizontal: 10,
    marginTop: 10,
    marginBottom: 20,
    alignSelf: 'flex-start',
    maxWidth: '80%',
  },
  bubble: {
    flexDirection: 'column',
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  writer: {
    fontSize: 15,
    fontWeight: 'bold',
    marginTop: 5,
  },
  content: {
    marginTop: 5,
    fontWeight : 'bold'
  },
});

export default Sight;
