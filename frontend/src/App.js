import GlobalStyles from './GlobalStyles';
import { Reset } from 'styled-reset';
import { useState, useEffect } from 'react';
import QuestionsPage from './QuestionPage';
import AskPage from './AskPage';
import Header from './Header';
import { Routes, Route } from 'react-router-dom';
import UserContext from './UserContext';
import LoginPage from './LoginPage';
import axios from 'axios';

function App() {
  const [user, setUser] = useState(null);
  function checkAuth() {
    axios.get('http://localhost:4000/profile', { withCredentials: true })
      .then(response => {
        setUser({ email: response.data });
      })
      .catch(() => {
        setUser(null);
      })
  }
  useEffect(() => {
    checkAuth();
  }, []);

  return (
    <div>
      <Reset />
      <GlobalStyles />
      <UserContext.Provider value={{ user, checkAuth }}>
        <Header />
        <Routes>
          <Route path='/' element={<QuestionsPage />} />
          <Route path='/login' element={<LoginPage />} />
          <Route path='/ask' element={<AskPage />} />
        </Routes>
      </UserContext.Provider>
    </div>
  );
}
export default App;
