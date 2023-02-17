import GlobalStyles from './GlobalStyles';
import { Reset } from 'styled-reset';
import { createContext, useState } from 'react';
import QuestionsPage from './QuestionPage';
import AskPage from './AskPage';
import Header from './Header';
import { Routes, Route } from 'react-router-dom';
import Question from './Question';
import useFetch from "./json-server/useFetch"

const userContext = createContext(null);

function App() {
  const [data, isPending, error ] = useFetch(`http://localhost:3001/test/1`)
  const [user, setUser] = useState(null);

  return (
    <div>
      <Reset />
      <GlobalStyles />
      { error && <div>{ error }</div> }
      <userContext.Provider value={{ user }}>
        <Header />
        <Routes>
        <Route path='/' element={<QuestionsPage data={data} isPending={isPending}/>} />
        <Route path='/ask' element={<AskPage data={data}/>} />
        <Route path='/:id' element={<Question />} />
      </Routes>
      </userContext.Provider>
    </div>
  );
}
export default App;
