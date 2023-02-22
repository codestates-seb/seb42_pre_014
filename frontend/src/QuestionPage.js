import styled from "styled-components";
import QuestionRow from "./QuestionRow";
import Header1 from "./Header1";
import BlueButtonLink from "./BlueButtonLink";
import { useState, useEffect } from "react";
import axios from "axios";
const HeaderRow = styled.div`
  display: grid;
  grid-template-columns: 1fr min-content;
  padding: 30px 20px;
`;

async function fetchQuestions() {
  try {
    const res = await axios.get("http://localhost:3001/questions");
    return res.data;
  } catch (err) {
    console.err(err);
  }
}

function QuestionsPage() {
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    async function getQuestions() {
      const data = await fetchQuestions();
      setQuestions(data);
    }
    getQuestions();
  }, []);

  return (
    <div>
      <HeaderRow>
        <Header1 style={{ margin: 0 }}>Top Questions</Header1>
        <BlueButtonLink to="./ask">Ask&nbsp;Question</BlueButtonLink>
      </HeaderRow>
      {questions.map((el) => {
        return <QuestionRow db={el} key={el.id} />;
      })}
    </div>
  );
}

export default QuestionsPage;
