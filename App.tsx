import React from 'react';  ``
import { useNavigation, NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Home from './src/Screen/Home';
import Login from './src/Screen/Login';
import Signup from './src/Screen/Signup';
import Blog from './src/Screen/blog/Blog';
import Community from './src/Screen/community';
import Maps from './src/Screen/Maps';
import Editor from './src/Screen/blog/BlogContent';
import Editor_CM from './src/Screen/CommunityEditor';
import UserInfo from './src/Screen/UserInfo';
import BlogData from './src/Screen/blog/BlogData';
import CommunityContent from './src/Screen/CommunityContent';


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
        <Stack.Screen name="UserInfo" component ={UserInfo}/>
        <Stack.Screen name="BlogData" component ={BlogData}/>
        <Stack.Screen name="CommunityContent" component ={CommunityContent}/>
        </Stack.Navigator>
    </NavigationContainer>
  );
};
export default App;
