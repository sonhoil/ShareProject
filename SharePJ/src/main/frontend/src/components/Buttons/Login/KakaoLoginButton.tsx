import React, { useEffect } from 'react';
import { axiosPost } from '../../Commons/AxiosHelper';
import { useNavigate } from 'react-router-dom'; 

// Add a global declaration for window with the Kakao property
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

interface LoginInfo {
  id: string;
  status: string;
  loginFlag: string;
  name?: string;
  phone?: string;
}

const KakaoLoginButton: React.FC = () => {
  const navigate = useNavigate();

  // Load Kakao SDK script
  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
    script.onload = () => {
      if (!window.Kakao.isInitialized()) { 
        window.Kakao.init(process.env.REACT_APP_KAKAO_KEY);
      }
    };
    document.head.appendChild(script);
  }, []);

  // Function for handling Kakao login 
  const loginWithKakao = () => {
    if (!window.Kakao) {
      alert('Kakao 인스턴스가 존재하지 않습니다.');
      return;
    }

    window.Kakao.Auth.login({
      success: (authObj: KakaoAuthObj) => processKakaoResponse(authObj.access_token),
      fail: (err: KakaoError) => alert(JSON.stringify(err))
    });
  };

  // Function for processing the Kakao response
  const processKakaoResponse = async (access_token: string) => {
    const postData = { access_token, loginFlag: 'kakao' };
    const response = await axiosPost('/api/login', postData);

    if (response) {
      handleLoginResponse(response.data);
    }
  };

  // Function for handling the login response from the server
  const handleLoginResponse = (loginInfo: LoginInfo) => {
    if (loginInfo.status === 'regist') {
      navigate('/PhoneConfirm', { state: { loginInfo } });
    } else if (loginInfo.id) {
      navigate('/');
    }
  }

  return <button onClick={loginWithKakao}>카카오 계정으로 로그인</button>;
}

export default KakaoLoginButton;