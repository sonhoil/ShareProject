import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login/Login";

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <div style={{ marginLeft: "250px", marginTop: "60px" }}>
          <Routes>
            <Route path="/login" element={<Login />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;