import GlobalStyles from "./GlobalStyles";
import { Reset } from "styled-reset";
import { createContext, useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import QuestionsPage from "./QuestionPage";
import AskPage from "./AskPage";
import Header from "./Header";
import Footer from "./Footer";
import Leftsidebar from "./Leftsidebar";
import "./App.css";
import UserContext from "./UserContext";
import LoginPage from "./LoginPage";
import axios from "axios";
import RegisterPage from "./RegisterPage";

const userContext = createContext(null);

function App() {
  const [user, setUser] = useState(null);

  function checkAuth() {
    return new Promise((resolve, reject) => {
      axios
        .get("http://localhost:4000/profile", { withCredentials: true })
        .then((response) => {
          setUser({ email: response.data });
          resolve(response.data);
        })
        .catch(() => {
          setUser(null);
          reject(null);
        });
    });
  }

  useEffect(() => {
    checkAuth();
  }, []);

  return (
    <div id="App">
      <Reset />
      <GlobalStyles />
      <UserContext.Provider value={{ user, checkAuth }}>
        <Header />
        <div className="Main-container">
          <Leftsidebar></Leftsidebar>
          <Routes>
            <Route path="/ask" element={<AskPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/" element={<QuestionsPage />} />
          </Routes>
        </div>

        <Footer />
      </UserContext.Provider>
    </div>
  );
}
export default App;
