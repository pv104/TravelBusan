import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import Blog from '../Screen/Blog';
import Community from '../Screen/community';
const Stack = createStackNavigator();

function AppStack() {
  return (
    <Stack.Navigator>
        <Stack.Screen name="Blog" component={Blog} />
        <Stack.Screen name="Community" component={Community} />
      {/* 다른 스크린들을 추가할 수 있음 */}
    </Stack.Navigator>
  );
};

export default AppStack;