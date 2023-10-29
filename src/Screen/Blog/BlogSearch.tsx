import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image } from 'react-native';

function SearchBar() {
  const [enroll_company, setEnroll_company] = useState({
    address: '',
  });
  const [popup, setPopup] = useState(false);

  const handleInput = (text) => {
    setEnroll_company({
      ...enroll_company,
      address: text,
    });
  };

  const handleComplete = () => {
    setPopup(!popup);
  };

  const handleSubmit = async () => {
    // 이 부분에서 API 호출 및 결과 처리를 수행합니다.
    // 검색 기능 및 데이터 처리 방법은 리액트 네이티브 환경에 맞게 구현해야 합니다.

    // 예를 들어, 다음과 같이 네비게이션을 사용하여 다른 화면으로 이동할 수 있습니다.
  };

  return (
    <View>
      <TextInput
        style={{
          borderWidth: 3,
          borderColor: 'black',
          padding: 5,
          marginBottom: 10,
          borderTopLeftRadius: 15,
          borderTopRightRadius: 15,
          borderBottomRightRadius:15,
          borderBottomLeftRadius:15,
          width: 380, 
        }}
        placeholder="블로그 내에서 검색"
        onChangeText={handleInput}
        value={enroll_company.address}
      />
      <TouchableOpacity onPress={handleSubmit}>
        <Text>검색</Text>
      </TouchableOpacity>
    </View>
  );
}

export default SearchBar;
