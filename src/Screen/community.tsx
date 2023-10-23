import React from 'react';
import { View,Image, Text, StyleSheet, FlatList, SafeAreaView,TouchableOpacity } from 'react-native';
import Nav from "./nav";
import Mine from "./Blog_mydata";
import { useNavigation } from '@react-navigation/native';
const CommunityData =[
  {Title:"부산 여행 추천 쫌", Writer : "여행자", Date : "15:39"},
  {Title:"어디가 좋을까", Writer : "웨어", Date : "15:39"},
  {Title:"서면 추천", Writer : "서면", Date : "15:39"},
  {Title:"부산 여행 추천 쫌", Writer : "방랑자", Date : "15:39"},
  {Title:"아무리 생각해도 이건 아님", Writer : "ㅇㅇ1", Date : "13:30"},
  {Title:"커뮤니티 글 테스트", Writer : "ㅇㅇ2", Date : "12:30"},
  {Title:"백에서 받을 예정", Writer : "ㅇㅇ3", Date : "11:30"},
  {Title:"테스트 중", Writer : "ㅇㅇ4", Date : "10:30"},
];

const Community =()=>{
  const navigation  = useNavigation();
  const handleSign =() =>{
    navigation.navigate('Editor_CM')
  };
  const IntoContent =()=>{
  
  };
    return(
        <View style = {styles.container}>
            <View style={styles.menu}>
              <Nav/>
              <Text style={styles.Title}>Travel Busan</Text>
            </View>
            <Mine/>
            <View style={styles.separator} />
            <TouchableOpacity style={styles.button} onPress ={handleSign}>
              <Text style={styles.buttonText}>글쓰기</Text>
            </TouchableOpacity>
            <View style={styles.separator} />
            <FlatList
                data={CommunityData}
                keyExtractor={(item) => item.Writer.toString()}
                renderItem={({ item }) => (
                <View style={styles.blogItem}>
                <TouchableOpacity onPress={IntoContent}>
                  <Text>{item.Title}</Text>
                  <Text>{item.Writer}</Text>
                  <Text style={styles.UnderInfo_date}>{item.Date}</Text>
                  <View style={styles.separator} />
                </TouchableOpacity>
                </View>
        )}
      />
        </View>
    );
};
const styles = StyleSheet.create({
    Title:{
        fontSize: 40,
        color : '#00B292',//진한 민트색
        fontWeight: 'bold',
        marginLeft : 20,
      },
      UnderInfo_date:{
        textAlign : 'right',
      },
      
      menu:{
        flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
        marginBottom : 20,
      },
      container: {
        flex: 1,
        paddingHorizontal: 20,
        paddingTop: 20,
      },
      separator: {
        height: 1,
        backgroundColor: 'gray',
        marginBottom: 10,
      },
      blogItem: {
        marginBottom: 0,
        marginTop : 0,
      },
      button: {
        backgroundColor: '#00B292', // 버튼 배경색
        paddingVertical: 8, // 세로 방향 padding
        paddingHorizontal: 5, // 가로 방향 padding
        width : '23.8%',
        borderRadius: 5,
        marginRight : 5,
        marginBottom : 10,
      },
      buttonText: {
        color: 'white',
        fontSize : 12,
        textAlign : 'center',
        fontWeight: 'bold',
      },
});
export default Community;