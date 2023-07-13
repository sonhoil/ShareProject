import React, { useEffect, useState } from 'react';
import { axiosPost } from '../../components/Commons/AxiosHelper';
import { useLocation, useNavigate } from "react-router-dom";

const PHONE_UNCONFIRMED = 0;
const PHONE_CONFIRMED = 1;

const PhoneConfirm: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const loginInfo = location.state.loginInfo;

  // Initialize state variables
  const [name, setName] = useState(""); 
  const [phone, setPhone] = useState(""); 
  const [phoneConfirmStatus, setPhoneConfirmStatus] = useState(PHONE_UNCONFIRMED); 

  // Handler for the phone confirmation button click event
  const confirmPhoneNumber = async () => {
    const postData = { phone };
    const response = await axiosPost('/api/confirmPhoneNumber', postData);

    if (response && response.data === 'using') {
      alert("사용 가능합니다.");
      setPhoneConfirmStatus(PHONE_CONFIRMED);
    } else {
      alert("이미 등록 된 계정이 존재합니다.");
      navigate('/login');
    }
  };

  // Handler for the registration button click event
  const registUser = async () => {
    if (phoneConfirmStatus !== PHONE_CONFIRMED) {
      alert("휴대폰 인증을 완료해 주세요");
      return;
    }
    
    const postData = {
      ...loginInfo, 
      phone,
      name
    };
    
    const response = await axiosPost('/api/registUser', postData);

    if(response && response.data.id){
      alert("회원가입 되었습니다.");
      navigate('/');
    } else {
      alert("인증 정보가 잘못 되었습니다.");
      navigate('/login');
    }
  };

  return (
    <div>
      <input value={name} onChange={event => setName(event.target.value)}></input>
      <input value={phone} onChange={event => setPhone(event.target.value)}></input>
      <button onClick={confirmPhoneNumber}>전화번호 인증하기</button>
      <button onClick={registUser}>가입하기</button>
    </div>
  );
};

export default PhoneConfirm;