import styled from "styled-components";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import { Link } from "react-router-dom";
dayjs.extend(relativeTime);
dayjs.locale("ko");

const QuestionStat_container = styled.div`
  margin-left: 20px;
`;
const QuestionStat = styled.div`
  align-self: stretch;
  text-align: center;
  display: inline-block;
  font-size: 0.8rem;
  color: ${(props) => (props.answers ? "#89ca9f" : props.votes ? "#fff" : "#aaa")};
  border: ${(props) => (props.answers ? "1px solid #3a8251" : "")};
  border-radius: 5%;
  border-width: 10%;
  padding: 3px 2px 3px 2px;

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
const QuestionLink = styled(Link)`
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
  /* display: inline-block; */
  color: #aaa;
  font-size: 0.8rem;
  float: right;
  padding: 10px 0;
`;
const UserLink = styled.a`
  color: #3ca4ff;
`;

function QuestionRow({ db }) {
  const timeString = dayjs(db.writetime).fromNow();
  return (
    <StyledQuestionRow>
      <QuestionStat_container>
        <QuestionStat votes>
          {db.votes}
          <span> {db.votes === 1 ? "vote" : "votes"}</span>
        </QuestionStat>
        <QuestionStat answers={db.answers}>
          {db.answers}
          <span> {db.answers === 1 ? "answer" : "answers"}</span>
        </QuestionStat>
        <QuestionStat>
          {db.views}
          <span> {db.views === 1 ? "view" : "views"}</span>
        </QuestionStat>
      </QuestionStat_container>

      <QuestionTitleArea>
        <QuestionLink to={`./${db.id}`}>{db.title}</QuestionLink>
        {/* <Link to={`/blogs/${blog.id}`}></Link> */}
        <WhoAndWhen>
          {db.answerer ? `${db.answerer} answered` : `${db.writer} asked`} {timeString} <UserLink></UserLink>
        </WhoAndWhen>
        {db.tags.map((el) => {
          return <Tag>{el}</Tag>;
        })}
      </QuestionTitleArea>
    </StyledQuestionRow>
  );
}

export default QuestionRow;
