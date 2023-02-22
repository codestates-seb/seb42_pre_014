import styled from "styled-components";
import BlueButton from "./BlueButton";
import Header1 from "./Header1";
import ReactMarkdown from "react-markdown";
import gfm from "remark-gfm";
import { useState } from "react";
import { fetchCreate } from "./json-server/api";
import Input from "./Input";

const Container = styled.div`
  padding: 30px 20px;
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
`;

const PreviewArea = styled.div`
  padding: 10px 20px;
  background-color: #444;
  border-radius: 5px;
  margin-bottom: 20px;
`;

export default function AskPage() {
  const [questionTitle, setQuestionTitle] = useState("");
  const [questionBody, setQuestionBody] = useState("");
  const [askday, setAskday] = useState(new Date());
  const [modifyday, setModifyday] = useState(new Date());
  const [viewed, setViewed] = useState("0 times");
  const [vote, setVote] = useState(0);
  const [save, setSave] = useState("false");

  const handleSubmit = () => {
    const data = { questionTitle, questionBody, askday, modifyday, viewed, vote, save };
    fetchCreate("http://localhost:3001/test/", data);
  };

  return (
    <Container>
      <Header1 style={{ marginBottom: "20px" }}>Ask a public question</Header1>

      <Input
        type="text"
        value={questionTitle}
        onChange={(e) => setQuestionTitle(e.target.value)}
        placeholder="Title of your question"
      />
      <div>{}</div>
      <QuestionBodyTextarea
        onChange={(e) => setQuestionBody(e.target.value)}
        placeholder="More info about your question. You can use markdown here"
      >
        {questionBody}
      </QuestionBodyTextarea>
      <BlueButton onClick={handleSubmit}>Post question</BlueButton>
      <PreviewArea>
        <ReactMarkdown plugins={[gfm]} children={questionBody} />
      </PreviewArea>
    </Container>
  );
}
