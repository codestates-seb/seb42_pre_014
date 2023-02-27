import styled from "styled-components";
// import BlueButton from "./BlueButton";
// import Header1 from "./Header1";
import ReactMarkdown from "react-markdown";
import gfm from "remark-gfm";
import { useState } from "react";
import { fetchCreate } from "./json-server/api";
import { Reset } from "styled-reset";
// import Input from "./Input";

const Askpage = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  width: 70%;
  @media screen and (max-width: 1200px) {
    width: 80%;
  }
  @media screen and (max-width: 819px) {
    width: 100%;
  }
  @media screen and (max-width: 768px) {
    width: 100%;
  }
  flex-shrink: 0;
`;
const Container = styled.div`
  padding: 0px 20px;
  width: 100%;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
`;
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
  &:focus-within{
    border: 1px solid rgba(0, 103, 194, 0.4);
    box-shadow: 0 0 0 4px rgba(144, 203, 255, 0.4);
    border-radius: 3px;
  }
`;
const Input = styled.input`
    background: none;
    border: 1px solid #777;
    border-radius: 3px;
    display: block;
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    /* margin-bottom: 20px; */
    color: #fff;
    &:focus-within{
    border: 1px solid rgba(0, 103, 194, 0.4);
    box-shadow: 0 0 0 4px rgba(144, 203, 255, 0.4);
    border-radius: 3px;
  }
`;
const PreviewArea = styled.div`
  padding: 10px 20px;
  background-color: #444;
  border-radius: 5px;
  margin-bottom: 20px;
`;
const QuestionContainer = styled.div`
  background-color: #2d2d2d;
  border: 1px solid grey;
  border-radius: 2px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  padding: 24px;
  position: relative;
  margin-top: 17px;
  min-width: 390px;
  width: 70%;
  flex-shrink: 0;
  @media screen and (max-width: 1200px) {
    width: 100%;
  }
  @media screen and (max-width: 819px) {
    width: 100%;
  }
`;
const TagsInputContainer = styled.div`
  &:focus-within{
    border: 1px solid rgba(0, 103, 194, 0.4);
    box-shadow: 0 0 0 4px rgba(144, 203, 255, 0.4);
    border-radius: 3px;
  }
`;
const Header = styled.h1`
  display: flex;
  font-size: 1.8rem;
  padding: 0px 0px 0px 20px;
  justify-content: space-between;
  width: 90%;
`;
const Header1 = styled.h1`
  font-weight: 500;
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  align-content: space-between;
  font-size: 1.8rem;
  @media screen and (max-width: 1200px) {
    text-align: start;
  }
  @media screen and (max-width: 819px) {
    width: 100%;
  }
  @media screen and (max-width: 767px) {
    width: 100%;
  }
  flex-shrink: 0;
`;
const Topimg = styled.img`
  display: flex;
  @media screen and (max-width: 970px) {
    display: none;
  }
`;
const Subtitle = styled.div`
  font-size: 13px;
  padding: 0px 0px 10px 0px;
  color: #b7bfc6;
`;
const BlueButton = styled.button`
    background-color: #378ad3;
    color: #fff;
    border: 0;
    border-radius: 5px;
    padding: 12px 20px;
    text-decoration: none;
    font-size: 1.1rem;
    width: 150px;
    margin: 20px 0px 20px 20px;
`;
const Titletip = styled.div`
  box-sizing: border-box;
  width: 100%;
  margin: 20px;
  background-color: #2d2d2d;
  border: 1px solid grey;
  border-radius: 3px;
  padding: 24px;
  @media screen and (max-width: 1200px) {
    margin: 20px 0px 0px 0px;

  }
`;
const Containerbox = styled.div`
  display: flex;
  justify-content: flex-end;
  flex-direction: row-reverse;
  @media screen and (max-width: 1200px) {
    flex-direction: column;
  }
  &:focus-within{
    justify-content: space-between;
  }
`;


export default function AskPage() {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [writetime, setWritetime] = useState(new Date());
  const [modifyday, setModifyday] = useState(new Date());
  const [views, setViews] = useState("0 times");
  const [votes, setVotes] = useState(0);
  const [save, setSave] = useState("false");
  const [focustitle, setFocustitle] = useState(false);
  const [focusbody, setFocusbody] = useState(false);
  const [focustags, setFocustags] = useState(false);
  const [focusdup, setFocusdup] = useState(false);

  const handleSubmit = () => {
    const data = { title, body, writetime, modifyday, views, votes, save };
    fetchCreate("http://localhost:3001/questions/", data);
  };

  return (
    <Askpage>
    <Header>
      <Header1>Ask a public question</Header1><Topimg src="https://user-images.githubusercontent.com/101001956/221417297-e599a2a5-32c0-4bc1-b594-e1586a87f365.png" alt="" width={550}/>
    </Header>
    <Container>
      <Containerbox>
        <Titletip style={{display:focustitle===false?"none":""}}>
          Writing a good title
        </Titletip>
        <QuestionContainer>
          <h3>Title</h3>
          <Subtitle>Be specific and imagine you’re asking a question to another person.</Subtitle>
          <Input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            onFocus={(e) =>setFocustitle(true)}
            onBlur={(e) =>setFocustitle(false)}
            placeholder="Title of your question"
          />
        </QuestionContainer>
      </Containerbox>
      <Containerbox>
        <Titletip style={{display:focusbody===false?"none":""}}>
          Proof-read before posting
        </Titletip>
        <QuestionContainer>
          <h3>Body</h3>
          <Subtitle>The body of your question contains your problem details and results. Minimum 30 characters.</Subtitle>
          <QuestionBodyTextarea
            onChange={(e) => setBody(e.target.value)}
            onFocus={(e) =>setFocusbody(true)}
            onBlur={(e) =>setFocusbody(false)}
            placeholder="More info about your question. You can use markdown here"
          >
            {body}
          </QuestionBodyTextarea>
          <PreviewArea>
            <ReactMarkdown plugins={[gfm]} children={body} />
          </PreviewArea>
        </QuestionContainer>
      </Containerbox>
      <Containerbox>
        <Titletip style={{display:focustags===false?"none":""}}>
          Adding tags
        </Titletip>
        <QuestionContainer>
          <h3>Tags</h3>
          <Subtitle>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</Subtitle>
          <TagsInputContainer>
            <Input
                type="text"
                onFocus={(e) =>setFocustags(true)}
                onBlur={(e) =>setFocustags(false)}
                placeholder="Tags"
              />
          </TagsInputContainer>
          
        </QuestionContainer>
      </Containerbox>
      <QuestionContainer>
        <h3>Review questions already on Stack Overflow to see if your question is a duplicate.</h3>
        <Subtitle>Clicking on these questions will open them in a new tab for you to review. Your progress here will be saved so you can come back and continue.</Subtitle>
        {/* 드롭다운 넣기 */}
      </QuestionContainer>
    </Container>
    <BlueButton onClick={handleSubmit}>Post question</BlueButton>
    </Askpage>
  );
}
