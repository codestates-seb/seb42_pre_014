import QuestionsPage from './QuestionPage';
import AskPage from './AskPage';
import { Reset } from 'styled-reset';
import Header from './Header';
import { Routes, Route } from 'react-router-dom';
import GlobalStyles from './GlobalStyles';

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
