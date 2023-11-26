import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import axios from 'axios';
import { useNavigation } from '@react-navigation/native';
import StarTest from './StarRate';
import { getCookie } from '../UserUtility/Cookie';


const ReviewComponent = ({route}) => {
  const [reviewTitle, setReviewTitle] = useState('');
  const [reviewText, setReviewText] = useState('');
  const [rate, setRate] = useState(0);
  const navigation = useNavigation();
  const sights_id = route.params;
  console.log(route.params);
  const handleSetRate = (selectedRate: number) => {
    setRate(selectedRate); // StarTest 컴포넌트에서 받아온 rate 값을 상태에 설정
  };

  const submitReview = async () => {
    console.log(reviewTitle);
    console.log(reviewText);
    console.log(rate);
    try {
      const response = await axios.post(
        `http://192.168.123.145:8080/sights/${sights_id}/review`,
        {
          title: reviewTitle,
          comment: reviewText,
          rating : rate,
        },
        {
          headers: {
            Authorization: `Bearer ${getCookie('token')}`,
            'Content-Type': 'application/json',
          },
        }
      );
      if (response.status === 200) {
        console.log('success');
        console.log(response.data);
        navigation.goBack();
      }
    } catch (error) {
      console.log(error);

    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.bubble}>
        <TextInput
          placeholder="제목을 작성해주세요"
          onChangeText={(text) => setReviewTitle(text)}
          value={reviewTitle}
          multiline
          style={{ fontSize: 28,fontWeight:'bold' }}
        />
        <View style={styles.separator} />
        <TextInput
          placeholder="리뷰를 작성해주세요"
          onChangeText={(text) => setReviewText(text)}
          value={reviewText}
          multiline
          style={{ marginBottom: 250, fontSize: 20, }}
        />
        <Text style={{ fontSize: 18, fontWeight: 'bold', marginLeft : "21%", marginBottom:1 }}>별점을 선택해주세요</Text>
        {/* Add star image or component to select star rating */}
        {/* When selecting a star rating, update the rating state by calling setRating(selectedRating) */}
        <View style={{justifyContent : 'center',marginLeft: -12}}>
          <StarTest setRate={handleSetRate} />
        </View>
        <TouchableOpacity onPress={submitReview}>
          <Text style={{
            marginLeft:'8%',
            backgroundColor:"#00B292",
            color:"white", 
            width:"27%", 
            fontSize : 20,
            borderTopLeftRadius: 5,
            borderTopRightRadius: 5,
            borderBottomLeftRadius: 5,
            borderBottomRightRadius: 5,
            }}>리뷰쓰기</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  container2: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    width: '50%',
    height: '50%',
  },
  separator: {
    height: 1,
    backgroundColor: 'gray',
    marginBottom: 10,
  },
  bubble: {
    backgroundColor: '#fff',
    padding: 60,
    borderRadius: 20,
    borderWidth: 1,
    borderColor: '#000',
    maxWidth: '97%',
  },
});

export default ReviewComponent;
