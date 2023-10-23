import React from 'react';  ``
import { View, StyleSheet } from 'react-native';
import Sliding from './src/Screen/slide';
import Nav from './src/Screen/nav';
import SlideBar from './src/Screen/slideBar'
import Scroll from './src/Screen/Scroll';
import { useNavigation, NavigationContainer } from '@react-navigation/native';
import LoginScreen from './src/Screen/Login';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Home from './src/Screen/Home';
import Login from './src/Screen/Login';
import Signup from './src/Screen/Signup';
import Blog from './src/Screen/Blog';
import Community from './src/Screen/community';
import Maps from './src/Screen/Maps';
import Editor from './src/Screen/BlogContent';
import Editor_CM from './src/Screen/CommunityEditor';


const Stack = createNativeStackNavigator();

//가로 확장가능
const App =()=> {
  return(
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component ={Home}/>
        <Stack.Screen name="Login" component ={Login}/>
        <Stack.Screen name="Signup" component ={Signup}/>
        <Stack.Screen name="Community" component ={Community}/>
        <Stack.Screen name="Maps" component ={Maps}/>
        <Stack.Screen name="Blog" component ={Blog}/>
        <Stack.Screen name="Editor" component ={Editor}/>
        <Stack.Screen name="Editor_CM" component ={Editor_CM}/>
        </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
