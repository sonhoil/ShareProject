import React, { useEffect } from 'react';

declare global {
  interface Window {
    Kakao: any;
  }
}

const KakaoLoginButton: React.FC = () => {
  useEffect(() => {
    // 카카오 스크립트 초기화
    const script = document.createElement('script');
    script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
    script.onload = () => {
      window.Kakao.init('YOUR_APP_KEY'); // 발급받은 앱 키를 입력
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
            alert(JSON.stringify(authObj));
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

  return <button onClick={loginWithKakao}>카카오 계정으로 로그인</button>;
}

export default KakaoLoginButton;