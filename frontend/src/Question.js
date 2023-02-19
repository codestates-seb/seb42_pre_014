import { useState, useEffect } from "react";
import{ useParams } from "react-router-dom";
import useFetch from "./json-server/useFetch"
import { fetchCreate } from "./json-server/api"
import styled from "styled-components";
import BlueButton from "./BlueButton"
import BlueButtonLink from "./BlueButtonLink";
import Answers from "./Answers";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookmark } from "@fortawesome/free-regular-svg-icons";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import { faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { faCaretDown } from "@fortawesome/free-solid-svg-icons";

const QuestionBodyTextarea = styled.textarea`
    background: none;
    border: 1px solid #777;
    border-radius: 3px;
    display: block;
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    min-height: 200px;
    margin-bottom: 20px;
    color: #fff;
    font-family: inherit;
`
const HeaderRow = styled.div`
    display: grid;
    grid-template-columns: 1fr min-content;
    padding: 10px 0px;
    justify-content: center;
`;
const Questionarticle = styled.div`
    grid-template-columns: 1fr min-content;
    padding: 10px 20px 0px 20px;
`;
const Buttondiv = styled.div`
    display: flex;
    flex-direction: row;
    padding: 5px 0px 15px 0px;
`;
const Answerbutton = styled.div`
  display: flex;
  grid-template-rows: 3fr;
  width: 50px;
  justify-content: center;
  cursor:pointer;
`;
const Divfont = styled.div`
    color: grey;
`;
const QuestionStat = styled.span`
  display: inline-block;
  font-size: 0.6rem;
  color: #aaa;
  /* margin: 3px 0 3px 0; */
  padding: 3px 0 3px 0;
  cursor:pointer;
`;
const StyledQuestionRow = styled.div`
  background-color: rgba(255, 255, 255, 0.05);
  padding: 15px 15px 10px;
  display: flex;
  border-top: 1px solid #555;
`;
const QuestionStatcontainer = styled.div`
  margin-left: 10px;
`;
const Leftbuttons = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
`;


const Question = () => {
    const { id } = useParams();
    const [data, isPending, error ] = useFetch(`http://localhost:3001/test/${id}`)
    const [answer, setAnswer] = useState('');
    const [quest, setQuest] = useState([]);

    const handleSubmit = (e) => {
        // e.preventDefault(); //새로고침 막기
        const data = {answer}
        fetchCreate("http://localhost:3001/test/", data)
    }

    useEffect(() => {
        fetch("http://localhost:3001/test/")
          .then((res) => res.json())
          .then((json) => setQuest(json))
          .catch((err) => console.err(err));
      }, []);
    //   console.log(quest);
    return (
        <>
            { isPending }
            { error && <div>{ error }</div> }
            { data && (
                
                <Questionarticle>
                    <HeaderRow>
                        <h1>{ data.questionTitle }</h1>
                        <BlueButtonLink to="../ask">Ask&nbsp;Question</BlueButtonLink>
                    </HeaderRow>
                    <Buttondiv>
                        <Divfont>Asked&nbsp;</Divfont>{ data.askday }
                        <Divfont>&nbsp;&nbsp;Modified&nbsp;</Divfont>{ data.modifyday }
                        <Divfont>&nbsp;&nbsp;Viewed&nbsp;</Divfont>{ data.viewed }
                    </Buttondiv>
                    <StyledQuestionRow>
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
                    <QuestionStatcontainer>
                        <div>{ data.questionBody }</div>
                        <Buttondiv>
                            <Answerbutton>Share</Answerbutton>
                            <Answerbutton>Edit&nbsp;</Answerbutton>
                            <Answerbutton>Follow</Answerbutton>
                        </Buttondiv>
                    </QuestionStatcontainer>
                    </StyledQuestionRow>
                </Questionarticle>

            )}
            <Questionarticle>
            <h2>4 Answer</h2>
            {quest.map((el) => {
                return (
                    <Answers
                        title={el.answer.answerBody}
                        key={el.answer.number}
                    />
                
                );
            })}
            <div>
                <h2>Your Answer</h2>
                <QuestionBodyTextarea
                    onChange={e => setAnswer(e.target.value)}>
                </QuestionBodyTextarea>
                <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
            </div>
            <h3>Not the answer you're looking for? Browse other questions tagged r&nbsp;string&nbsp;stringr or ask your own question.</h3>
            </Questionarticle>
        </>
    )
}

export default Question