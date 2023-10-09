import React, { useState, useEffect } from 'react';
import { View, Text, Image, TouchableOpacity, Animated, Easing } from 'react-native';

const images = [
  {
    url: require('../pics/영도대교.png'), // 이미지 파일 경로
    //link: '/mainPage', // 링크 정보 (React Native에서는 다른 네비게이션 방식을 사용해야 함)
  },
];

function Slide() {
  const [index, setIndex] = useState(0);
  const translateX = new Animated.Value(0);

  useEffect(() => {
    const nextImage = () => {
      setIndex((index + 1) % images.length);
    };

    const interval = setInterval(nextImage, 5000);

    return () => clearInterval(interval);
  }, [index]);

  useEffect(() => {
    Animated.timing(translateX, {
      toValue: -index * 100, // 이미지 너비에 따라 조절 (100은 이미지의 수평 이동 거리입니다)
      duration: 500, // 애니메이션 지속 시간 (500ms로 설정)
      easing: Easing.inOut(Easing.ease), // 이징 함수 설정
      useNativeDriver: false, // 네이티브 드라이버 사용 안 함
    }).start();
  }, [index, translateX]);

  const nextImage = () => {
    setIndex((index + 1) % images.length);
  };

  const prevImage = () => {
    setIndex(index === 0 ? images.length - 1 : index - 1);
  };

  return (
    <View>
      <View style={{ flexDirection: 'row' }}>
        {images.map((image, i) => (
          <Animated.View
            key={i}
            style={{
              transform: [{ translateX }],
            }}
          >
            <TouchableOpacity onPress={() => {/* 링크 처리 */}}>
              <Image
                source={image.url}
                style={{
                  width: 417, // 이미지 가로 너비
                  height: 270, // 이미지 세로 높이
                }}
              />
            </TouchableOpacity>
          </Animated.View>
        ))}
      </View>
    </View>
  );
}

export default Slide;
