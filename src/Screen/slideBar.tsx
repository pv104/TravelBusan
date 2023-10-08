import React, { useState } from 'react';
import { View, Text, StyleSheet} from 'react-native';
import Slider  from  'react-native-slider';

const SliderComponent = () => {
  const [sliderValue, setSliderValue] = useState(0);

  const onSliderValueChange = (value) => {
    setSliderValue(value);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Slider Value: {sliderValue.toFixed(2)}</Text>
      <Slider
        style={styles.slider}
        minimumValue={0}
        maximumValue={100}
        value={sliderValue}
        step={1}
        onValueChange={onSliderValueChange}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    fontSize: 20,
    marginBottom: 20,
  },
  slider: {
    width: 300,
  },
});

export default SliderComponent;
