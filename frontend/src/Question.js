import { useState, useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import useFetch from "./json-server/useFetch";
import { fetchCreate, fetchPatch } from "./json-server/api";
import styled from "styled-components";
import BlueButton from "./BlueButton";
import BlueButtonLink from "./BlueButtonLink";
import Answers from "./Answers";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark as farBookmark } from "@fortawesome/free-regular-svg-icons";
import { faBookmark as fasBookmark } from "@fortawesome/free-solid-svg-icons";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import { faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { faCaretDown } from "@fortawesome/free-solid-svg-icons";
import { faFacebookSquare } from "@fortawesome/free-brands-svg-icons";
import { faTwitter } from "@fortawesome/free-brands-svg-icons";
import { faDev } from "@fortawesome/free-brands-svg-icons";
import useDetectClose from "./hooks/useDetectClose";
import { CopyToClipboard } from "react-copy-to-clipboard";
import RightSidebar from "./Rightsidebar";

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
`;
const HeaderRow = styled.div`
    display: grid;
    grid-template-columns: 1fr min-content;
    padding: 10px 0px;
    justify-content: center;
`;
const Questionarticle = styled.div`
    grid-template-columns: 1fr min-content;
    padding: 10px 20px 0px 20px;
    max-width: 1051px;
`;
const Questioninfo = styled.div`
    display: flex;
    flex-direction: row;
    padding: 5px 0px 15px 0px;
    border-bottom: 1px solid #777;
`;
const Buttondiv = styled.div`
    display: flex;
    flex-direction: row;
    padding: 30px 0px 15px 0px;
    /* border-bottom: 1px solid #777; */
`;
const Answerbutton = styled.div`
    display: flex;
    grid-template-rows: 3fr;
    width: 50px;
    justify-content: center;
    cursor: pointer;
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
    cursor: pointer;
`;
const StyledQuestionRow = styled.div`
    background-color: rgba(57, 57, 57, 0.05);
    padding: 15px 15px 10px;
    display: flex;
    /* border-top: 1px solid #555; */
`;
const QuestionStatcontainer = styled.div`
    margin-left: 10px;
`;
const Leftbuttons = styled.div`
    display: flex;
    flex-direction: column;
    text-align: center;
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
    background-color: rgb(52, 75, 60);
    border: 2px solid rgba(255, 127, 80, 0.1);
    border-color: rgba(52, 75, 60, 0.4);
    border-radius: 3px;
    /* box-shadow: 0 0.5rem 1rem rgb(0 0 0 / 15%); */
    height: 40px;
    width: 500px;
    padding: 0px 5px 2px 13px;
    text-align: center;
    font-size: 13px;
    color: lightgrey;
    display: flex;
    align-items: center;
    justify-content: start;
    position: fixed;
    top: 20px;
    margin: 0 auto;
    left: 0;
    right: 0;
`;
const Container = styled.div`
    display: flex;
    flex-direction: row;
`;
const Section = styled.div`
    flex-direction: column;
    margin: 0px 20px 0px 0px;
`;
const RSidebar = styled.div`
    @media screen and (max-width: 980px) {
        display: none;
    }
`;
const Bodytext = styled.div`
    padding: 0px 0px 30px 0px;
`;

const Question = () => {
    const { id } = useParams();
    const [data, isPending, error] = useFetch(`http://localhost:3001/questions/${id}`);
    const [answer, setAnswer] = useState("");
    const [quest, setQuest] = useState([]);
    const dropDownRef = useRef();
    const [isOpen, setIsOpen] = useDetectClose(dropDownRef, false);
    const [copyText, setCopyText] = useState(`http://localhost:3000/${id}`);
    const [copied, setCopied] = useState(false);

    // const handleSubmit = (e) => {
    //     // e.preventDefault(); //새로고침 막기
    //     const data = {answer}
    //     fetchCreate("http://localhost:3001/test/?id=1", data)
    // }
    const handleSubmit = () => {
        // e.preventDefault(); //새로고침 막기
        const data = { answer };
        fetchPatch("http://localhost:3001/questions/", id, data);
        console.log(answer);
    };
    const voteUp = () => {
        const votes = { votes: data.votes + 1 };
        fetchPatch("http://localhost:3001/questions/", id, votes);
    };
    const voteDown = () => {
        const votes = { votes: data.votes - 1 };
        fetchPatch("http://localhost:3001/questions/", id, votes);
    };
    const bookMarkClick = () => {
        // setBookmark(!bookmark);
        if (data.save === "false") {
            const saves = { save: (data.save = "true") };
            fetchPatch("http://localhost:3001/questions/", id, saves);
        } else {
            const saves = { save: (data.save = "false") };
            fetchPatch("http://localhost:3001/questions/", id, saves);
        }
    };
    const elapsedTime = (date) => {
        const start = new Date(date);
        const end = new Date();

        const diff = (end - start) / 1000;

        const times = [
            { name: "years", milliSeconds: 60 * 60 * 24 * 365 },
            { name: "weeks", milliSeconds: 60 * 60 * 24 * 30 },
            { name: "days", milliSeconds: 60 * 60 * 24 },
            { name: "times", milliSeconds: 60 * 60 },
            { name: "mins", milliSeconds: 60 },
        ];

        for (const value of times) {
            const betweenTime = Math.floor(diff / value.milliSeconds);

            if (betweenTime > 0) {
                return `${betweenTime}${value.name} ago`;
            }
        }
        return "now";
    };
    const handleChange = (e) => {
        setCopyText(e.target.value);
    };

    useEffect(() => {
        fetch("http://localhost:3001/questions/?id=1")
            .then((res) => res.json())
            .then((json) => setQuest(json))
            .catch((err) => console.err(err));
    }, []);

    useEffect(() => {
        let timer = setTimeout(() => {
            setCopied(false);
            console.log("복사완료!");
        }, 1500);
        return () => {
            clearTimeout(timer);
        };
    }, [copied]);

    //   console.log(quest);
    return (
        <>
            {isPending}
            {error && <div>{error}</div>}
            {data && (
                <Questionarticle>
                    <HeaderRow>
                        <h1>{data.title}</h1>
                        <BlueButtonLink to="../ask">Ask&nbsp;Question</BlueButtonLink>
                    </HeaderRow>
                    <Questioninfo>
                        <Divfont>Asked&nbsp;</Divfont>
                        {elapsedTime(data.writetime)}
                        <Divfont>&nbsp;&nbsp;Modified&nbsp;</Divfont>
                        {elapsedTime(data.modifyday)}
                        <Divfont>&nbsp;&nbsp;Viewed&nbsp;</Divfont>
                        {data.views + " times"}
                    </Questioninfo>
                    <Container>
                        <Section>
                            <StyledQuestionRow>
                                <Leftbuttons>
                                    <QuestionStat>
                                        <FontAwesomeIcon icon={faCaretUp} size="4x" onClick={voteUp} />
                                    </QuestionStat>
                                    <div>{data.votes}</div>
                                    <QuestionStat>
                                        <FontAwesomeIcon icon={faCaretDown} size="4x" onClick={voteDown} />
                                    </QuestionStat>
                                    <QuestionStat>
                                        {data.save === "false" ? (
                                            <FontAwesomeIcon icon={farBookmark} size="2x" onClick={bookMarkClick} />
                                        ) : (
                                            <FontAwesomeIcon icon={fasBookmark} size="2x" onClick={bookMarkClick} />
                                        )}
                                    </QuestionStat>
                                    <QuestionStat>
                                        <FontAwesomeIcon icon={faClockRotateLeft} size="2x" />
                                    </QuestionStat>
                                </Leftbuttons>
                                <QuestionStatcontainer>
                                    <Bodytext>{data.body}</Bodytext>
                                    {data.tags.map((el) => {
                                        return <Tag>{el}</Tag>;
                                    })}
                                    <div ref={dropDownRef}>
                                        <Buttondiv>
                                            <Answerbutton onClick={() => setIsOpen(!isOpen)}>Share</Answerbutton>
                                            <Answerbutton onClick={() => setIsOpen(false)}>Edit&nbsp;</Answerbutton>
                                            <Answerbutton onClick={() => setIsOpen(false)}>Follow</Answerbutton>
                                        </Buttondiv>
                                        {isOpen && (
                                            <Dropdown>
                                                <Sharetext>
                                                    <b>Share a link to this question</b> (Includes your user id)
                                                </Sharetext>
                                                <Dropinput value={copyText} onChange={handleChange} />
                                                <Dropbuttons>
                                                    <CopyToClipboard text={copyText} onCopy={() => setCopied(true)}>
                                                        <Copybutton type="button">Copy link</Copybutton>
                                                    </CopyToClipboard>
                                                    {copied ? <Alert>Link copied to clipboard.</Alert> : null}
                                                    <LicenseLink
                                                        href="https://creativecommons.org/licenses/by-sa/4.0/"
                                                        target="_blank"
                                                        rel="noreferrer"
                                                    >
                                                        CC BY-SA 4.0
                                                    </LicenseLink>
                                                    <Sharebuttons>
                                                        <Sharebtn
                                                            onClick={() =>
                                                                window.open(
                                                                    "https://www.facebook.com/sharer/sharer.php?u=https://stackoverflow.com/",
                                                                    "_blank",
                                                                    "width=650 height=550"
                                                                )
                                                            }
                                                        >
                                                            <FontAwesomeIcon icon={faFacebookSquare} size="lg" />
                                                        </Sharebtn>
                                                        <Sharebtn
                                                            onClick={() =>
                                                                window.open(
                                                                    "https://www.twitter.com/sharer/sharer.php?u=https://stackoverflow.com/",
                                                                    "_blank",
                                                                    "width=650 height=550"
                                                                )
                                                            }
                                                        >
                                                            <FontAwesomeIcon icon={faTwitter} size="lg" />
                                                        </Sharebtn>
                                                        <Sharebtn
                                                            onClick={() =>
                                                                window.open(
                                                                    "https://dev.to/new?prefill=https://stackoverflow.com/",
                                                                    "_blank",
                                                                    "width=650 height=550"
                                                                )
                                                            }
                                                        >
                                                            <FontAwesomeIcon icon={faDev} size="lg" />
                                                        </Sharebtn>
                                                    </Sharebuttons>
                                                </Dropbuttons>
                                            </Dropdown>
                                        )}
                                    </div>
                                </QuestionStatcontainer>
                            </StyledQuestionRow>
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
                                    onChange={(e) =>
                                        setAnswer({
                                            answerBody: e.target.value,
                                            votes: 0,
                                            save: "false",
                                            number: 2,
                                        })
                                    }
                                ></QuestionBodyTextarea>
                                <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
                            </div>
                            <h3>
                                Not the answer you're looking for? Browse other questions tagged
                                r&nbsp;string&nbsp;stringr or ask your own question.
                            </h3>
                        </Section>
                        <RSidebar>
                            <RightSidebar />
                        </RSidebar>
                    </Container>
                </Questionarticle>
            )}
        </>
    );
};

export default Question;
