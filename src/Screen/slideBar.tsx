import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Animated } from 'react-native';
import { GestureHandlerRootView, PanGestureHandler, State } from 'react-native-gesture-handler';
import Scroll from './Scroll';

const SlideLayer = () => {
  const [isOpen, setIsOpen] = useState(false);

  // 애니메이션 값을 초기화합니다.
  const translateY = new Animated.Value(0);

  // 레이어 열기/닫기 함수
  const toggleLayer = () => {
    if (isOpen) {
      // 레이어가 열려있으면 닫습니다.
      Animated.timing(translateY, {
        toValue: 0,
        duration: 300,
        useNativeDriver: true,
      }).start(() => {
        setIsOpen(false);
      });
    } else {
      // 레이어가 닫혀있으면 엽니다.
      Animated.timing(translateY, {
        toValue: -100, // 슬라이드 레이어의 높이
        duration: 300,
        useNativeDriver: true,
      }).start(() => {
        setIsOpen(true);
      });
    }
  };

  // 슬라이드 제스처 이벤트 핸들러
  const onSlideGestureEvent = Animated.event(
    [{ nativeEvent: { translationY: translateY } }],
    { useNativeDriver: true }
  );

  const onSlideStateChange = ({ nativeEvent }) => {
    if (nativeEvent.state === State.END) {
      // 슬라이드 제스처가 끝났을 때 레이어 열기/닫기 처리
      if (nativeEvent.translationY < -100) {
        toggleLayer(); // 슬라이드 열기
      } else {
        toggleLayer(); // 슬라이드 닫기
      }
    }
  };

  return (
   <GestureHandlerRootView>
     <View style={styles.container}>
      <TouchableOpacity onPress={toggleLayer}>
        <Text>Toggle Layer</Text>
      </TouchableOpacity>
      <PanGestureHandler
        onGestureEvent={onSlideGestureEvent}
        onHandlerStateChange={onSlideStateChange}
      >
        <Animated.View
          style={[
            styles.slideLayer,
            {
              transform: [{ translateY }],
            },
          ]}
        >
          {/* 슬라이드 레이어 내용 */}
          <Scroll/>
        </Animated.View>
      </PanGestureHandler>
    </View>
   </GestureHandlerRootView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  slideLayer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 100, // 슬라이드 레이어의 높이
    backgroundColor: 'pink',
    borderTopLeftRadius: 10,
    borderTopRightRadius: 10,
    elevation: 5, // Android에서 그림자 효과
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default SlideLayer;
