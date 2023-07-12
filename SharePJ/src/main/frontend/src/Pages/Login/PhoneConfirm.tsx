import React, { useEffect, useState} from 'react';
import { axiosPost } from '../../components/Commons/AxiosHelper';
import { useLocation,useNavigate } from "react-router-dom";

const PhoneConfirm: React.FC = () => {
  // 사용할 state들과 useEffect 훅이 있다면 이곳에 작성하세요.
  const location = useLocation();
  const navigate = useNavigate();
  const loginInfo = location.state.loginInfo;
  const [name, setName] = useState(""); 
  const [phone, setPhone] = useState(""); 
  const confirmPhoneNumber = async () => {
    const postData = {phone};
    const response = await axiosPost('/api/confirmPhoneNumber', postData);
      if(response && response.data == 'using'){
        alert("사용 가능합니다.");
      }else{
        alert("이미 등록 된 계정이 존재합니다.");
        navigate('/login');
      }
  };

  const registUser = async () => {
    loginInfo.phone = phone;
    loginInfo.name = name;
    const postData = loginInfo;
    const response = await axiosPost('/api/registUser', postData);
      if(response && response.data.id){
        alert("회원가입 되었습니다.");
        navigate('/');
      }else{
        alert("인증 정보가 잘못 되었습니다.");
        navigate('/login');
      }
  };

  const handlePhoneChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPhone(event.target.value);  // update the state when the input field changes
  }
  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);  // update the state when the input field changes
  }
  return (
    <div>
      <input value={name} onChange={handleNameChange}></input>
      <input value={phone} onChange={handlePhoneChange}></input>  {/* add value and onChange props to the input field */}
      <button onClick={confirmPhoneNumber}>전화번호 인증하기</button>
      <button onClick={registUser}>가입하기</button>
    </div>
  );
};

export default PhoneConfirm;