import React, { useState } from 'react';
import { View, TouchableOpacity, Text, StyleSheet } from 'react-native';

const NavigationBarButton = () => {
  const [isActive, setIsActive] = useState(false);

  const toggleButton = () => {
    setIsActive(!isActive);
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={[styles.button, isActive ? styles.activeButton : styles.inactiveButton]}
        onPress={toggleButton}
      >
        <Text style={styles.buttonText}>{isActive ? 'On' : 'Off'}</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'flex-start',
  },
  button: {
    padding: 10,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: 'gray',
  },
  activeButton: {
    backgroundColor: 'green',
  },
  inactiveButton: {
    backgroundColor: 'black',
  },
  buttonText: {
    color: 'white',
    fontWeight: 'bold',
  },
});

export default NavigationBarButton;