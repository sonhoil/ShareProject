import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login/Login";
import LoginSuccess from "./Pages/Login/LoginSuccess";
import PhoneConfirm from "./Pages/Login/PhoneConfirm";

const App: React.FC = () => {
  const isLogin1 = () => !!localStorage.getItem("sessionId");
  return (
    <Router>
      <div>
        <div style={{ marginLeft: "250px", marginTop: "60px" }}>
          <Routes>
            <Route path="/" />
            <Route path="/login" element={<Login />} />
            <Route path="/phoneConfirm" element={<PhoneConfirm />} />
            {isLogin1() ? <Route path="/loginSuccess" element={<LoginSuccess />} /> : ''}
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;