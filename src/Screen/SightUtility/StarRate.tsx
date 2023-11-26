import React, { useState } from 'react';
import { StyleSheet, Text } from 'react-native';
import { View} from 'react-native';
import Svg, { Path } from 'react-native-svg';

interface StarTestProps {
  setRate: (selectedRate: number) => void;
}
const StarSVG: React.FC<StarTestProps> = () => {
  const [fillColor, setFillColor] = useState('white'); // 초기 fill 색상 설정
  const [fillColor2, setFillColor2] = useState('white'); // 초기 fill 색상 설정
  const [fillColor3, setFillColor3] = useState('white'); // 초기 fill 색상 설정
  const [fillColor4, setFillColor4] = useState('white'); // 초기 fill 색상 설정
  const [fillColor5, setFillColor5] = useState('white'); // 초기 fill 색상 설정
  const [rate,setRates] = useState(0);

  const handlePathClick = (id : string) => {
    // 클릭 시 색상 변경
    const newFillColor = fillColor === 'white' ? 'yellow' : 'white'; // 예시로 색상 토글
    if(id === '1')
    {
      setFillColor(newFillColor);
      setRates(1);
      console.log(rate);
      return rate;
    }
    else if(id==='2')
    {
      setFillColor2(newFillColor);
      setFillColor(newFillColor);
      setRates(2);
      console.log(rate);
      return rate;
    }
    else if(id==='3')
    {
      setFillColor3(newFillColor);
      setFillColor2(newFillColor);
      setFillColor(newFillColor);
      setRates(3);
      console.log(rate);
      return rate;
    }
    else if(id==='4')
    {
      setFillColor4(newFillColor);
      setFillColor3(newFillColor);
      setFillColor2(newFillColor);
      setFillColor(newFillColor);
      setRates(4);
      console.log(rate);
      return rate;
    }
    else
    {
      setFillColor5(newFillColor);
      setFillColor4(newFillColor);
      setFillColor3(newFillColor);
      setFillColor2(newFillColor);
      setFillColor(newFillColor);
      setRates(5);
      console.log(rate);
      return rate;
    }
  };

  return (
    <View style={styles.container}>
      <Svg width={280} height={80} viewBox="0 0 330 60" >
          <Path
            id='1'
            fill={fillColor}
            stroke={'black'}
            d="m25,1 6,17h18l-14,11 5,17-15-10-15,10 5-17-14-11h18z"
            onPress={()=>handlePathClick('1')}
          />
          <Path
            id='2'
            fill={fillColor2}
            stroke={'black'}
            d="m80,1 6,17h18l-14,11 5,17-15-10-15,10 5-17-14-11h18z"
            onPress={()=>handlePathClick('2')}
          />
          <Path
            id='3'
            fill={fillColor3}
            stroke={'black'}
            d="m135,1 6,17h18l-14,11 5,17-15-10-15,10 5-17-14-11h18z"
            onPress={()=>handlePathClick('3')}
          />
           <Path
            id='4'
            fill={fillColor4}
            stroke={'black'}
            d="m190,1 6,17h18l-14,11 5,17-15-10-15,10 5-17-14-11h18z"
            onPress={()=>handlePathClick('4')}
          />
          <Path
            id='5'
            fill={fillColor5}
            stroke={'black'}
            d="m245,1 6,17h18l-14,11 5,17-15-10-15,10 5-17-14-11h18z"
            onPress={()=>handlePathClick('5')}
          />
        </Svg>
    </View>
  );
};
const styles = StyleSheet.create({
  container: {
    marginLeft : 30,
  },
});
export default StarSVG;
