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
import { useState, useEffect, useRef } from "react";
import { faFacebookSquare } from "@fortawesome/free-brands-svg-icons";
import { faTwitter } from "@fortawesome/free-brands-svg-icons";
import { faDev } from "@fortawesome/free-brands-svg-icons";
import useDetectClose from "./hooks/useDetectClose";
import { CopyToClipboard } from 'react-copy-to-clipboard';

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
const Dropdown = styled.div`
  background-color: rgba(255, 255, 255, 0.05);
  width: 350px;
  height: 100px;
  /* position: absolute; */
  /* margin: 10px; */
  padding: 15px 15px 0px 15px;
  border: 10px;
  border-radius: 5px;
`;
const Sharetext = styled.div`
  font-size: 14px;
  padding: 0px 0px 10px 0px;
`;
const Dropinput = styled.input`
  cursor: not-allowed;
  width: 96%;
  height: 35px;
  border: 10px;
  border-radius: 3px;
  font-size: 15px;
  padding: 0px 0px 0px 10px;
  background-color: grey;
`;
const Copybutton = styled.div`
  color: #3ca4ff;
  cursor: pointer;
  padding: 10px 0px 0px 0px;
  font: caption;
  font-size: 15px;
`;
const LicenseLink = styled.a`
  font: caption;
  color: #3ca4ff;
  cursor: pointer;
  padding: 10px 0px 0px 0px;
  font-size: 15px;
`;
const Dropbuttons = styled.a`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;
const Sharebuttons = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 5px 0px 0px 0px;
`;
const Sharebtn = styled.div`
  padding: 5px;
  cursor: pointer;
`;
const Alert = styled.div`
  background-color: rgb(219,240,226);
  border: 2px solid rgba(255, 127, 80, 0.1);
  border-color: rgba(47, 197, 34, 0.4);
  border-radius: 5px;
  /* box-shadow: 0 0.5rem 1rem rgb(0 0 0 / 15%); */
  height: 40px;
  width: 500px;
  padding: 5px 5px 5px 10px;
  text-align: center;
  font-size: 1rem;
  color: black;
  display: flex;
  align-items: center;
  justify-content: start;
  position: fixed;
  top: 20px;
  margin: 0 auto;
  left: 0;
  right: 0;
  
`;


const Answers = ({ title }) => {
  const { id } = useParams();
  const [data, isPending, error ] = useFetch(`http://localhost:3001/questions/${id}`);
  const [number, setNumber] = useState(1);
  const dropDownRef = useRef();
    const [isOpen, setIsOpen] = useDetectClose(dropDownRef, false);
    const [copyText, setCopyText] = useState(`http://localhost:3000/${id}`);
    const [copied, setCopied] = useState(false);

  const voteUp = () => {
    const votes = {
      "answer": 
        {
          "answerBody": data.answer.answerBody,
          "votes": data.answer.votes + 1,
          "save": data.answer.save,
          "number": number
        }
      
    }
    fetchPatch("http://localhost:3001/questions/", id, votes)
    // console.log(data.answer[0].answerBody)
    // console.log(id)
  }
  const voteDown = () => {
    const votes = {
      "answer": {
        "answerBody": data.answer.answerBody,
        "votes": data.answer.votes - 1,
        "save": data.answer.save,
        "number": number
      }
    }
    fetchPatch("http://localhost:3001/questions/", id, votes)
  }
  const bookMarkClick = () => {
    // setBookmark(!bookmark);
    if(data.answer.save === "false"){
        const saves = {
          "answer": {
            "answerBody": data.answer.answerBody,
            "votes": data.answer.votes,
            "save": "true",
            "number": number
          }
      }
        fetchPatch("http://localhost:3001/questions/", id, saves)
    }
    else{
        const saves = {
          "answer": {
            "answerBody": data.answer.answerBody,
            "votes": data.answer.votes,
            "save": "false",
            "number": number
          }
        }
        fetchPatch("http://localhost:3001/questions/", id, saves)
    }
  }
  const handleChange = (e) => {
    setCopyText(e.target.value);
  };
  useEffect(() => {
    let timer = setTimeout(() => {
        setCopied(false);
        console.log("복사완료!")
    }, 1500);
    return () => {
      clearTimeout(timer);
    };
  }, [copied]);

  return (
    <>
    { isPending }
    { error && <div>{ error }</div> }
    { data && (
    <StyledQuestionRow>
      <QuestionStatcontainer>
      <Leftbuttons>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretUp} size="4x" onClick={voteUp}/>
        </QuestionStat>
            <div>{ data.answer.votes }</div>
        <QuestionStat>
            <FontAwesomeIcon icon={faCaretDown} size="4x" onClick={voteDown}/>
        </QuestionStat>
        <QuestionStat>
            {data.answer.save === "false" ? <FontAwesomeIcon icon={farBookmark} size="2x" onClick={bookMarkClick}/> : <FontAwesomeIcon icon={fasBookmark} size="2x" onClick={bookMarkClick}/>}
        </QuestionStat>
        <QuestionStat>
            <FontAwesomeIcon icon={faClockRotateLeft} size="2x"/>
        </QuestionStat>
    </Leftbuttons>
      </QuestionStatcontainer>
      <QuestionTitleArea>
        <QuestionLink>{title}</QuestionLink>
        <div ref={dropDownRef}>
                            <Buttondiv>
                                <Answerbutton onClick={() => setIsOpen(!isOpen)}>Share</Answerbutton>
                                <Answerbutton onClick={() => setIsOpen(false)}>Edit&nbsp;</Answerbutton>
                                <Answerbutton onClick={() => setIsOpen(false)}>Follow</Answerbutton>
                            </Buttondiv>
                            {isOpen && 
                            <Dropdown>
                                <Sharetext><b>Share a link to this question</b> (Includes your user id)</Sharetext>
                                <Dropinput
                                    value={copyText}
                                    onChange={handleChange}
                                />
                                <Dropbuttons>
                                    <CopyToClipboard text={copyText}
                                        onCopy={() => setCopied(true)}>
                                        <Copybutton type="button">Copy link</Copybutton>
                                    </CopyToClipboard>
                                    {copied ? <Alert>Link copied to clipboard.</Alert> : null}
                                    <LicenseLink href="https://creativecommons.org/licenses/by-sa/4.0/" target="_blank" rel="noreferrer">CC BY-SA 4.0</LicenseLink>
                                    <Sharebuttons>
                                        <Sharebtn onClick={() => window.open('https://www.facebook.com/sharer/sharer.php?u=https://stackoverflow.com/', '_blank', "width=650 height=550")}><FontAwesomeIcon icon={faFacebookSquare} size="lg"/></Sharebtn>
                                        <Sharebtn onClick={() => window.open('https://www.twitter.com/sharer/sharer.php?u=https://stackoverflow.com/', '_blank', "width=650 height=550")}><FontAwesomeIcon icon={faTwitter} size="lg"/></Sharebtn>
                                        <Sharebtn onClick={() => window.open('https://dev.to/new?prefill=https://stackoverflow.com/', '_blank', "width=650 height=550")}><FontAwesomeIcon icon={faDev} size="lg"/></Sharebtn>
                                    </Sharebuttons>
                                </Dropbuttons>
                            </Dropdown>}
                        </div>
      </QuestionTitleArea>
    </StyledQuestionRow>
    )}
    </>
  );
}

export default Answers;
