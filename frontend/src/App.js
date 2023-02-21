import GlobalStyles from "./GlobalStyles";
import { Reset } from "styled-reset";
import { createContext, useState } from "react";
import { Routes, Route } from "react-router-dom";
import QuestionsPage from "./QuestionPage";
import AskPage from "./AskPage";
import Header from "./Header";
import Footer from "./Footer";
import "./App.css";

const userContext = createContext(null);

function App() {
  const [user, setUser] = useState(null);

  return (
    <div id="App">
      <Reset />
      <GlobalStyles />
      <userContext.Provider value={{ user }}>
        <Header />
        <Routes>
          <Route path="/" element={<QuestionsPage />} />
          <Route path="/ask" element={<AskPage />} />
        </Routes>
        <Footer />
      </userContext.Provider>
    </div>
  );
}
export default App;
