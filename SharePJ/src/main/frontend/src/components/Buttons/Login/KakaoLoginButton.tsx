import React, { useEffect} from 'react';
import axios from 'axios';

declare global {
  interface Window {
    Kakao: any;
  }
}
type DataType = {
  loginFlag?: string;
  [key: string]: any;
};
const KakaoLoginButton: React.FC = () => {
  useEffect(() => {
    // 카카오 스크립트 초기화
    const script = document.createElement('script');
    script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
    script.onload = () => {
      window.Kakao.init('eb431bfa4de0d75cf6c18bee90abf367'); // 발급받은 앱 키를 입력
    };
    document.head.appendChild(script);
  }, []);

  const loginWithKakao = () => {
    try {
      return new Promise((resolve, reject) => {
        if (!window.Kakao) {
          reject('Kakao 인스턴스가 존재하지 않습니다.');
        }
        window.Kakao.Auth.login({
          success: function (authObj: any) {
            kakaoResponse(authObj.access_token);
            resolve(authObj);
          },
          fail: function (err: any) {
            alert(JSON.stringify(err));
            reject(err);
          },
        });
      });
    } catch (err) {
      alert(JSON.stringify(err));
    }
  };
  const kakaoResponse = (access_token: string) => {
    axios.get('https://kapi.kakao.com/v2/user/me',
    {
      headers: {
        Authorization: `Bearer ${access_token}`,
      },
    },
    )
    .then(response => {
      console.log(response)
      checkKakaoUser(response.data)
    })
    .catch(error => console.log(error))

  }

  const checkKakaoUser = (data : DataType) => {
  
    data.loginFlag = 'kakao'
    axios.post('/api/login',data)
    .then(response => {
     
    })
    .catch(error => console.log(error))

  }

  

  
  return <button onClick={loginWithKakao}>카카오 계정으로 로그인</button>;
}

export default KakaoLoginButton;