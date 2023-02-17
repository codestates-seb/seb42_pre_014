import styled from "styled-components";
import QuestionRow from "./QuestionRow";
import Header1 from "./Header1";
import BlueButtonLink from "./BlueButtonLink";
import { useState, useEffect } from "react";
const HeaderRow = styled.div`
  display: grid;
  grid-template-columns: 1fr min-content;
  padding: 30px 20px;
`;

function QuestionsPage() {
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    fetch("http://localhost:3001/questions")
      .then((res) => res.json())
      .then((json) => setQuestions(json))
      .catch((err) => console.err(err));
  }, []);
  console.log(questions);

  return (
    <div>
      <HeaderRow>
        <Header1 style={{ margin: 0 }}>Top Questions</Header1>
        <BlueButtonLink to="./ask">Ask&nbsp;Question</BlueButtonLink>
      </HeaderRow>
      {questions.map((el) => {
        return (
          <QuestionRow
            votes={el.votes}
            answers={el.answers}
            tags={el.tags}
            title={el.title}
            views={el.views}
            key={el.id}
          />
        );
      })}
    </div>
  );
}

export default QuestionsPage;
