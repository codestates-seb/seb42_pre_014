import styled from "styled-components";

const QuestionStat_container = styled.div`
  margin-left: 30px;
`;
const QuestionStat = styled.span`
  align-self: stretch;
  text-align: center;
  display: inline-block;
  font-size: 0.8rem;
  color: #aaa;
  margin: 3px 0 3px 0;
  span {
    font-size: 0.7rem;
    font-weight: 300;
    text-align: end;
  }
`;
const QuestionTitleArea = styled.div`
  padding: 0 10px;
`;
const Tag = styled.span`
  display: inline-block;
  margin-right: 5px;
  background-color: #3e4a52;
  color: #9cc3db;
  padding: 7px;
  border-radius: 4px;
  font-size: 0.7rem;
`;
const QuestionLink = styled.a`
  text-decoration: none;
  color: #3ca4ff;
  font-size: 1.1rem;
  display: block;
  margin-bottom: 5px;
`;
const StyledQuestionRow = styled.div`
  background-color: rgba(255, 255, 255, 0.05);
  padding: 15px 15px 10px;
  display: grid;
  grid-template-columns: 85px 1fr;
  /* grid-template-rows: repeat(3, 20px); */
  grid-template-rows: 1fr;
  border-top: 1px solid #555;
`;
const WhoAndWhen = styled.div`
  display: inline-block;
  color: #aaa;
  font-size: 0.8rem;
  float: right;
  padding: 10px 0;
`;
const UserLink = styled.a`
  color: #3ca4ff;
`;

function QuestionRow({ votes, answers, tags, title, views }) {
  return (
    <StyledQuestionRow>
      <QuestionStat_container>
        <QuestionStat>
          {votes}
          <span> votes</span>
        </QuestionStat>
        <QuestionStat>
          {answers}
          <span> answers</span>
        </QuestionStat>
        <QuestionStat>
          {views}
          <span> views</span>
        </QuestionStat>
      </QuestionStat_container>

      <QuestionTitleArea>
        <QuestionLink>{title}</QuestionLink>
        <WhoAndWhen>
          asked 2mins ago <UserLink></UserLink>
        </WhoAndWhen>
        {tags.map((el) => {
          return <Tag>{el}</Tag>;
        })}
      </QuestionTitleArea>
    </StyledQuestionRow>
  );
}

export default QuestionRow;
