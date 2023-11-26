import React from 'react';  ``
import {NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Home from './src/Screen/HomeUtility/Home';
import Login from './src/Screen/UserUtility/Login';
import Signup from './src/Screen/UserUtility/Signup';
import Blog from './src/Screen/Blog/Blog';
import Community from './src/Screen/Community/community';
import Maps from './src/Screen/SightUtility/maptest';
import Editor from './src/Screen/Blog/BlogContent';
import Editor_CM from './src/Screen/Community/CommunityEditor';
import UserInfo from './src/Screen/UserUtility/UserInfo';
import BlogData from './src/Screen/Blog/BlogData';
import CommunityContent from './src/Screen/Community/CommunityContent';
import Sight from './src/Screen/SightUtility/Sight';
import Review from './src/Screen/SightUtility/Review';
import myBlog from './src/Screen/Blog/myBlogs';
import SearchMap from './src/Screen/SightUtility/SearchMap';

//가로 확장가능
const App =()=> {
  const Stack = createNativeStackNavigator();
  return(
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component ={Home}/>
        <Stack.Screen name="Login" component ={Login}/>
        <Stack.Screen name="Signup" component ={Signup}/>
        <Stack.Screen name="Community" component ={Community}/>
        <Stack.Screen name="Maps" component ={Maps}/>
        <Stack.Screen name="SearchMap" component ={SearchMap}/>
        <Stack.Screen name="Blog" component ={Blog}/>
        <Stack.Screen name="Editor" component ={Editor}/>
        <Stack.Screen name="Editor_CM" component ={Editor_CM}/>
        <Stack.Screen name="UserInfo" component={UserInfo}/>
        <Stack.Screen name="BlogData" component ={BlogData}/>
        <Stack.Screen name="Sight" component ={Sight}/>
        <Stack.Screen name="Review" component ={Review}/>
        <Stack.Screen name="myBlog" component ={myBlog}/>
        <Stack.Screen name="CommunityContent" component ={CommunityContent}/>
        </Stack.Navigator>
    </NavigationContainer>
  );
};
export default App;
