import GlobalStyles from "./GlobalStyles";
import { Reset } from "styled-reset";
import { useState, useEffect } from "react";
import QuestionsPage from "./QuestionPage";
import AskPage from "./AskPage";
import Header from "./Header";
import { Routes, Route } from "react-router-dom";
import Question from "./Question";
import useFetch from "./json-server/useFetch";
import UserContext from "./UserContext";
import LoginPage from "./LoginPage";
import axios from "axios";
import RegisterPage from "./RegisterPage";
import Leftsidebar from "./Leftsidebar";
import Footer from "./Footer";
import "./App.css";

function App() {
  const [data, isPending, error] = useFetch(`http://localhost:3001/test/1`);
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
      {error && <div>{error}</div>}
      <UserContext.Provider value={{ user, checkAuth }}>
        <Header />
        <div className="Main-container">
          <Leftsidebar></Leftsidebar>
          <Routes>
            <Route path="/" element={<QuestionsPage data={data} isPending={isPending} />} />
            <Route path="/ask" element={<AskPage data={data} />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/:id" element={<Question />} />
          </Routes>
        </div>

        <Footer />
      </UserContext.Provider>
    </div>
  );
}
export default App;
