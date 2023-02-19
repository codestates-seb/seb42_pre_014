import styled from "styled-components";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookmark } from "@fortawesome/free-regular-svg-icons";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import { faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { faCaretDown } from "@fortawesome/free-solid-svg-icons";

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


function Answers({ title }) {
  return (
    <>
    <StyledQuestionRow>
      <QuestionStatcontainer>
      <Leftbuttons>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretUp} size="4x"/>
        </QuestionStat>
            <div>0</div>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretDown} size="4x"/>
        </QuestionStat>
        <QuestionStat>
            <FontAwesomeIcon icon={faBookmark} size="2x"/>
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
