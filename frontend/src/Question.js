import { useState, useEffect } from "react";
import{ useParams } from "react-router-dom";
import useFetch from "./json-server/useFetch"
import { fetchCreate, fetchPatch } from "./json-server/api"
import styled from "styled-components";
import BlueButton from "./BlueButton"
import BlueButtonLink from "./BlueButtonLink";
import Answers from "./Answers";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookmark as farBookmark } from "@fortawesome/free-regular-svg-icons";
import { faBookmark as fasBookmark } from "@fortawesome/free-solid-svg-icons";
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
    // const handleSubmit = (e) => {
    //     // e.preventDefault(); //새로고침 막기
    //     const data = {answer}
    //     fetchCreate("http://localhost:3001/test/?id=1", data)
    // }
    const handleSubmit = () => {
        // e.preventDefault(); //새로고침 막기
        const data = {answer}
        fetchPatch("http://localhost:3001/test/", id, data)
        console.log(answer)
    }
    const voteUp = () => {
        const votes = {"vote" : data.vote + 1}
        fetchPatch("http://localhost:3001/test/", id, votes)
    }
    const voteDown = () => {
        const votes = {"vote" : data.vote - 1}
        fetchPatch("http://localhost:3001/test/", id, votes)
    }
    const bookMarkClick = () => {
        // setBookmark(!bookmark);
        if(data.save === "false"){
            const saves = {"save" : data.save = "true"}
            fetchPatch("http://localhost:3001/test/", id, saves)
        }
        else{
            const saves = {"save" : data.save = "false"}
            fetchPatch("http://localhost:3001/test/", id, saves)
        }
    }
    const elapsedTime = (date) => {
        const start = new Date(date);
        const end = new Date();
      
        const diff = (end - start) / 1000;
        
        const times = [
          { name: 'years', milliSeconds: 60 * 60 * 24 * 365 },
          { name: 'weeks', milliSeconds: 60 * 60 * 24 * 30 },
          { name: 'days', milliSeconds: 60 * 60 * 24 },
          { name: 'times', milliSeconds: 60 * 60 },
          { name: 'mins', milliSeconds: 60 },
        ];
      
        for (const value of times) {
          const betweenTime = Math.floor(diff / value.milliSeconds);
      
          if (betweenTime > 0) {
            return `${betweenTime}${value.name} ago`;
          }
        }
        return 'now';
      }
    useEffect(() => {
        fetch("http://localhost:3001/test/?id=1")
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
                        <Divfont>Asked&nbsp;</Divfont>{ elapsedTime(data.askday) }
                        <Divfont>&nbsp;&nbsp;Modified&nbsp;</Divfont>{ elapsedTime(data.modifyday) }
                        <Divfont>&nbsp;&nbsp;Viewed&nbsp;</Divfont>{ data.viewed }
                    </Buttondiv>
                    <StyledQuestionRow>
                    <Leftbuttons>
                        <QuestionStat>
                            <FontAwesomeIcon icon={faCaretUp} size="4x" onClick={voteUp}/>
                        </QuestionStat>
                            <div>{ data.vote }</div>
                        <QuestionStat>
                            <FontAwesomeIcon icon={faCaretDown} size="4x" onClick={voteDown}/>
                        </QuestionStat>
                        <QuestionStat>
                            {data.save === "false" ? <FontAwesomeIcon icon={farBookmark} size="2x" onClick={bookMarkClick}/> : <FontAwesomeIcon icon={fasBookmark} size="2x" onClick={bookMarkClick}/>}
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
            <h2>1 Answer</h2>
            {quest.map((el) => {
                return (
                    <Answers
                        title={el.answer.answerBody}
                        number={el.answer.number}
                        // id={id}
                    />
                
                );
            })}
            <div>
                <h2>Your Answer</h2>
                <QuestionBodyTextarea
                    onChange={e => 
                        setAnswer({
                            "answerBody": e.target.value,
                            "vote": 0,
                            "save": "false",
                            "number": 2
                        })}></QuestionBodyTextarea>
                <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
            </div>
            <h3>Not the answer you're looking for? Browse other questions tagged r&nbsp;string&nbsp;stringr or ask your own question.</h3>
            </Questionarticle>
        </>
    )
}

export default Question