import React, { useEffect, useState } from 'react';
import { View, Image, Text, StyleSheet, FlatList, SafeAreaView, TouchableOpacity } from 'react-native';
import Nav from "../UserUtility/nav";
import Mine from "../Blog/Blog_mydata";
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
import { getCookie } from '../UserUtility/Cookie';

const Community = () => {
  const [CommunityData, setCommunityData] = useState([]);
  const navigation = useNavigation();
  const handleSign = () => {
    navigation.navigate('Editor_CM');
  };

  const SetData = async () => {
    try {
      const response = await axios.get('http://172.18.112.1:8080/boards', {
        headers: {
          'Authorization': `Bearer ${getCookie('token')}`
        }
      });
      const updatedCommunityData = response.data.data.map((item) => ({
        Title: item.title,
        id : item.id,
        Content: item.content,
        Writer: item.nickname,
        Date: item.creDate,
      }));

      setCommunityData(updatedCommunityData);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    SetData();
  }, []);

  const IntoContent = (item) => {
    navigation.navigate('CommunityContent', {
      Writer: item.Writer,
      title: item.Title,
      content: item.Content,
      date: item.Date,
    });
  };

  return (
    <View style={styles.container}>
      <View style={styles.menu}>
        <Nav />
        <Text style={styles.Title}>Travel Busan</Text>
      </View>
      <Mine />
      <View style={styles.separator} />
      <TouchableOpacity style={styles.button} onPress={handleSign}>
        <Text style={styles.buttonText}>글쓰기</Text>
      </TouchableOpacity>
      <View style={styles.separator} />
      <FlatList
        data={CommunityData}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.blogItem}>
            <TouchableOpacity onPress={() => IntoContent(item)}>
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
  Title: {
    fontSize: 40,
    color: '#00B292', // 진한 민트색
    fontWeight: 'bold',
    marginLeft: 20,
  },
  UnderInfo_date: {
    textAlign: 'right',
  },
  menu: {
    flexDirection: 'row', // 자식 컴포넌트를 가로로 나열
    marginBottom: 20,
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
    marginTop: 0,
  },
  button: {
    backgroundColor: '#00B292', // 버튼 배경색
    paddingVertical: 8, // 세로 방향 padding
    paddingHorizontal: 5, // 가로 방향 padding
    width: '23.8%',
    borderRadius: 5,
    marginRight: 5,
    marginBottom: 10,
  },
  buttonText: {
    color: 'white',
    fontSize: 12,
    textAlign: 'center',
    fontWeight: 'bold',
  },
});

export default Community;
