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
import RegisterPage from './RegisterPage';

function App() {
  const [user, setUser] = useState(null);

  function checkAuth() {
    return new Promise(((resolve, reject) => {
      axios.get('http://localhost:4000/profile', { withCredentials: true })
        .then(response => {
          setUser({ email: response.data });
          resolve(response.data);
        })
        .catch(() => {
          setUser(null);
          reject(null);
        })
    }))
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
          <Route path='/ask' element={<AskPage />} />
          <Route path='/login' element={<LoginPage />} />
          <Route path='/register' element={<RegisterPage />} />
          <Route path='/' element={<QuestionsPage />} />
        </Routes>
      </UserContext.Provider>
    </div>
  );
}
export default App;
