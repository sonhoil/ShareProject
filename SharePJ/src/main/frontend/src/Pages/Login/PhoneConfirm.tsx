import React, { useEffect} from 'react';
import { axiosPost } from '../../components/Commons/AxiosHelper';


const PhoneConfirm: React.FC = () => {
  // 사용할 state들과 useEffect 훅이 있다면 이곳에 작성하세요.
  const confirmPhoneNumber = async () => {
    const postData = { };
    const response = await axiosPost('/api/login', postData);
    if (response) {
      
    }
  };
  return (
    <div>
      <input></input>
      <button onClick={confirmPhoneNumber}>전화번호 인증하기</button>
    </div>
  );
};

export default PhoneConfirm;