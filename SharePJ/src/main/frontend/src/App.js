import React, {useEffect, useState} from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import Login from "./Pages/Login/Login";
import axios from 'axios';
function App() {
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