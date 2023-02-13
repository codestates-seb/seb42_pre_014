import QuestionsPage from './QuestionPage';
import AskPage from './AskPage';
import { Reset } from 'styled-reset';
import styled, { createGlobalStyle } from 'styled-components';
import Header from './Header';
import { Routes, Route } from 'react-router-dom';

const GlobalStyles = createGlobalStyle`
  @import url('https://fonts.googleapis.com/css2?family=Roboto;wght@300,400;700&display=swap');
  body{
    background: #2d2d2d;
    color:#fff;
  }
`;

function App() {
  return (
    <div>
      <Reset />
      <GlobalStyles />
      <Header />
      <Routes>
        <Route path='/' element={<QuestionsPage />} />
        <Route path='/ask' element={<AskPage />} />
      </Routes>
    </div>
  );
}
export default App;
