import styled from "styled-components";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookmark as farBookmark } from "@fortawesome/free-regular-svg-icons";
import { faBookmark as fasBookmark } from "@fortawesome/free-solid-svg-icons";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import { faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { faCaretDown } from "@fortawesome/free-solid-svg-icons";
import { fetchPatch } from "./json-server/api"
import useFetch from "./json-server/useFetch"
import{ useParams } from "react-router-dom";

const QuestionStatcontainer = styled.div`
  grid-template-columns: 1fr min-content;
    /* padding: 10px 20px 0px 20px; */
`;
const QuestionStat = styled.span`
  display: inline-block;
  font-size: 0.6rem;
  color: #aaa;
  /* margin: 3px 0 3px 0; */
  padding: 3px 0 3px 0;
  cursor:pointer;
`;
const QuestionTitleArea = styled.div`
  padding: 0 10px;
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
  display: flex;
  border-top: 1px solid #555;
`;
const Answerbutton = styled.div`
  display: flex;
  grid-template-rows: 3fr;
  width: 50px;
  justify-content: center;
  cursor:pointer;
`;
const Buttondiv = styled.div`
    display: flex;
    flex-direction: row;
`;
const Leftbuttons = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
`;


const Answers = ({ title }) => {
  const { id } = useParams();
  const [data, isPending, error ] = useFetch(`http://localhost:3001/test/${id}`)

  const voteUp = () => {
    const votes = {"vote" : data.answer.vote + 1}
    fetchPatch("http://localhost:3001/test/", id, votes)
    // console.log(data.answer.vote)
    // console.log(id)
  }
  const voteDown = () => {
    const votes = {"vote" : data.answer.vote - 1}
    fetchPatch("http://localhost:3001/test/", id, votes)
  }
  // const bookMarkClick = () => {
  //   // setBookmark(!bookmark);
  //   if(data.save === "false"){
  //       const saves = {"save" : data.save = "true"}
  //       fetchPatch("http://localhost:3001/test/", id, saves)
  //   }
  //   else{
  //       const saves = {"save" : data.save = "false"}
  //       fetchPatch("http://localhost:3001/test/", id, saves)
  //   }
  // }

  return (
    <>
    { isPending }
    { error && <div>{ error }</div> }
    <StyledQuestionRow>
      <QuestionStatcontainer>
      <Leftbuttons>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretUp} size="4x" onClick={voteUp}/>
        </QuestionStat>
            <div>{ title.asvote }</div>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretDown} size="4x" onClick={voteDown}/>
        </QuestionStat>
        <QuestionStat>
            <FontAwesomeIcon icon={farBookmark} size="2x"/>
            <FontAwesomeIcon icon={fasBookmark} size="2x"/>
        </QuestionStat>
        <QuestionStat>
            <FontAwesomeIcon icon={faClockRotateLeft} size="2x"/>
        </QuestionStat>
    </Leftbuttons>
      </QuestionStatcontainer>
      <QuestionTitleArea>
        <QuestionLink>{title}</QuestionLink>
        <Buttondiv>
          <Answerbutton>Share</Answerbutton>
          <Answerbutton>Edit&nbsp;</Answerbutton>
          <Answerbutton>Follow</Answerbutton>
        </Buttondiv>
      </QuestionTitleArea>
    </StyledQuestionRow>
    
    </>
  );
}

export default Answers;
