import GlobalStyles from "./GlobalStyles";
import { Reset } from "styled-reset";
import { createContext, useState } from "react";
import QuestionsPage from "./QuestionPage";
import AskPage from "./AskPage";
import Header from "./Header";
import { Routes, Route } from "react-router-dom";
import "./css/reset.css";
const userContext = createContext(null);

function App() {
  const [user, setUser] = useState(null);

  return (
    <div>
      <Reset />
      <GlobalStyles />
      <userContext.Provider value={{ user }}>
        <Header />
        <Routes>
          <Route path="/" element={<QuestionsPage />} />
          <Route path="/ask" element={<AskPage />} />
        </Routes>
      </userContext.Provider>
    </div>
  );
}
export default App;
