import React, { useEffect} from 'react';
import { axiosPost } from '../../Commons/AxiosHelper';
import { useNavigate } from 'react-router-dom'; 

declare global {
  interface Window {
    Kakao: any;
  }
}

interface KakaoAuthObj {
  access_token: string;
}

interface KakaoError {
  error: string;
  error_description: string;
}

type DataType = {
  loginFlag?: string;
  [key: string]: any;
};

const KakaoLoginButton: React.FC = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
    script.onload = () => {
      if (!window.Kakao.isInitialized()) { // Kakao.init이 이미 호출되었는지 확인
        window.Kakao.init(process.env.REACT_APP_KAKAO_KEY);
      }
    };
    document.head.appendChild(script);
  }, []);

  const loginWithKakao = async () => {
    if (!window.Kakao) {
      alert('Kakao 인스턴스가 존재하지 않습니다.');
      return;
    }

    window.Kakao.Auth.login({
      success: (authObj: KakaoAuthObj) => kakaoResponse(authObj.access_token),
      fail: (err: KakaoError) => alert(JSON.stringify(err))
    });
  };

 

  const kakaoResponse = async (access_token: string) => {
    const postData = { access_token, loginFlag: 'kakao' };
    console.log("Data sent with the request:", postData);
    const response = await axiosPost('/api/login', postData);
    if (response) {
      checkRegistUser(response.data);
    }
  };

  const checkRegistUser = (flag : String) => {
    if(flag === 'regist'){
      //regist
      navigate('/PhoneConfirm');
    }else if(flag === 'suuccess'){
      //login
    }
  }
  const checkKakaoUser = async (data : DataType) => {
    if (typeof data === 'object' && data !== null) {
      console.log("Data sent with the request:", data);
      data.loginFlag = 'kakao';
      await axiosPost('/api/login', data);
    } else {
      console.error('Data must be an object:', data);
    }
  };

  return <button onClick={loginWithKakao}>카카오 계정으로 로그인</button>;
}

export default KakaoLoginButton;