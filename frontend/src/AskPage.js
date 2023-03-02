import styled from "styled-components";
// import BlueButton from "./BlueButton";
// import Header1 from "./Header1";
import ReactMarkdown from "react-markdown";
import gfm from "remark-gfm";
import { useState } from "react";
import { fetchCreate } from "./json-server/api";
import { Reset } from "styled-reset";
// import Input from "./Input";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";

const Askpage = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    margin: 0 auto;
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
    &:focus-within {
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
    /* &:focus-within {
        border: 1px solid rgba(0, 103, 194, 0.4);
        box-shadow: 0 0 0 4px rgba(144, 203, 255, 0.4);
        border-radius: 3px;
    } */
`;
const PreviewArea = styled.div`
    padding: 10px 20px;
    background-color: #444;
    border-radius: 5px;
    margin-bottom: 20px;
`;
const QuestionContainer = styled.div`
    background-color: #2d2d2d;
    border: 1px solid #3c3d3f;
    border-radius: 2px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    padding: 24px;
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
  display: flex;
  flex-wrap: wrap;
  padding: 0 8px 0px 8px;
  border: 1px solid rgb(214, 216, 218);
  border-radius: 3px;
  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 8px 0 7px 0;
    > .tag {
      display: flex;
      flex-wrap: wrap;
      align-content: center;
      margin: 0px 5px 0px 0px;
      background-color: #3e4a52;
      color: #9cc3db;
      padding: 7px;
      border-radius: 4px;
      font-size: 0.7rem;
      > span > b{
        /* display: flex; */
        cursor: pointer;
        padding: 0px 4px 1px 4px;
        border-radius: 2px;
        font-size: 0.8rem;
      }
      > span > b:hover{
        color: #3e4a52;
        background-color: #9cc3db;
      }
    }
  }
  > input {
    flex: 1;
    border: none;
    height: 46px;
    font-size: 14px;
    padding: 4px 0 0 0;
    :focus {
      outline: transparent;
    }
  }
  &:focus-within {
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
const Tipbox = styled.div`
    box-sizing: border-box;
    width: 100%;
    height: 50%;
    margin: 20px;
    color: #e6e9eb;
    box-shadow: 0px 0px 10px rgb(54, 54, 54);
    @media screen and (max-width: 1200px) {
        margin: 20px 0px 0px 0px;
    }
`;
const Tip1 = styled.div`
    box-sizing: border-box;
    width: 100%;
    background-color: rgb(57, 57, 57);
    border: 1px solid #414345;
    border-radius: 3px 3px 0px 0px;
    padding: 15px;
`;
const Tip2 = styled.div`
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    width: 100%;
    font-size: 11px;
    background-color: #2d2d2d;
    border: 1px solid #414345;
    border-radius: 0px 0px 3px 3px;
    padding: 15px;
`;
const Tiptext = styled.div`
    padding: 10px;
`;
const Containerbox = styled.div`
    display: flex;
    justify-content: flex-end;
    flex-direction: row-reverse;
    @media screen and (max-width: 1200px) {
        flex-direction: column;
    }
    &:focus-within {
        justify-content: space-between;
    }
`;


export default function AskPage() {
    const [title, setTitle] = useState("");
    const [contents, setContents] = useState("");
    // const [writetime, setWritetime] = useState(new Date());
    // const [modifyday, setModifyday] = useState(new Date());
    // const [views, setViews] = useState("0 times");
    // const [votes, setVotes] = useState(0);
    // const [save, setSave] = useState("false");
    const [focustitle, setFocustitle] = useState(false);
    const [focusbody, setFocusbody] = useState(false);
    const [focustags, setFocustags] = useState(false);
    const [focusdup, setFocusdup] = useState(false);
    const [tag, setTag] = useState([]);
    const [memberId, setMemberId] = useState(1);

    const handleSubmit = () => {
        const data = { title, contents, tag, memberId};
        // fetchCreate("/questions/", data);
        console.log(data)
    };
    const titlefocus = () => {
        setFocustitle(true);
        setFocusbody(false);
        setFocustags(false);
    };
    const bodyfocus = () => {
        setFocustitle(false);
        setFocusbody(true);
        setFocustags(false);
    };
    const tagsfocus = () => {
        setFocustitle(false);
        setFocusbody(false);
        setFocustags(true);
    };
    const removeTags = (indexToRemove) => {
      const deletetags = tag.filter(item => item !== tag[indexToRemove])
      setTag(deletetags)
    };
  
    const addTags = (e) => {
      if (e.target.value.length !== 0 && e.key === 'Enter' && !tag.includes(e.target.value)) {
        let updatedTagList = [...tag, e.target.value]
        setTag(updatedTagList)
        e.target.value = '';
      }
    };

    return (
        <Askpage>
            <Header>
                <Header1>Ask a public question</Header1>
                <Topimg
                    src="https://user-images.githubusercontent.com/101001956/221417297-e599a2a5-32c0-4bc1-b594-e1586a87f365.png"
                    alt=""
                    width={550}
                />
            </Header>
            <Container>
                <Containerbox>
                    <Tipbox style={{ display: focustitle === false ? "none" : "" }}>
                        <Tip1>Writing a good title</Tip1>
                        <Tip2>
                            <FontAwesomeIcon icon={faPen} size="3x" style={{ padding: "10px" }} />
                            <Tiptext>
                                Your title should summarize the problem.
                                <br />
                                <br />
                                You might find that you have a better idea of your title after writing out the rest of
                                the question.
                            </Tiptext>
                        </Tip2>
                    </Tipbox>
                    <QuestionContainer>
                        <h3>Title</h3>
                        <Subtitle>Be specific and imagine you’re asking a question to another person.</Subtitle>
                        <Input
                            type="text"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            onFocus={(e) => titlefocus()}
                            placeholder="Title of your question"
                        />
                    </QuestionContainer>
                </Containerbox>
                <Containerbox>
                    <Tipbox style={{ display: focusbody === false ? "none" : "" }}>
                        <Tip1>Proof-read before posting</Tip1>
                        <Tip2>
                            <FontAwesomeIcon icon={faPen} size="3x" style={{ padding: "10px" }} />
                            <Tiptext>
                                Now that you’re ready to post your question, read through it from start to finish. Does
                                it make sense?
                                <br />
                                <br />
                                Add any details you missed and read through it again. Now is a good time to make sure
                                that your title still describes the problem!
                            </Tiptext>
                        </Tip2>
                    </Tipbox>
                    <QuestionContainer>
                        <h3>Body</h3>
                        <Subtitle>
                            The body of your question contains your problem details and results. Minimum 30 characters.
                        </Subtitle>
                        <QuestionBodyTextarea
                            onChange={(e) => setContents(e.target.value)}
                            onFocus={(e) => bodyfocus()}
                            placeholder="More info about your question. You can use markdown here"
                        >
                            {contents}
                        </QuestionBodyTextarea>
                        <PreviewArea>
                            <ReactMarkdown plugins={[gfm]} children={contents} />
                        </PreviewArea>
                    </QuestionContainer>
                </Containerbox>
                <Containerbox>
                    <Tipbox style={{ display: focustags === false ? "none" : "" }}>
                        <Tip1>Adding tags</Tip1>
                        <Tip2>
                            <FontAwesomeIcon icon={faPen} size="3x" style={{ padding: "10px" }} />
                            <Tiptext>
                                Tags help ensure that your question will get attention from the right people.
                                <br />
                                <br />
                                Tag things in more than one way so people can find them more easily. Add tags for
                                product lines, projects, teams, and the specific technologies or languages used.
                            </Tiptext>
                        </Tip2>
                    </Tipbox>
                    <QuestionContainer>
                        <h3>Tags</h3>
                        <Subtitle>
                            Add up to 5 tags to describe what your question is about. Start typing to see suggestions.
                        </Subtitle>
                        <TagsInputContainer>
                          <ul>
                              {tag.map((tag, index) => (
                                <li key={index} className="tag">
                                  <span>{tag}</span>
                                  <span onClick={() => removeTags(index)}>&nbsp;<b>X</b>
                                  </span>
                                </li>
                              ))}
                          </ul>
                          <Input type="text" onFocus={(e) => tagsfocus()} placeholder="Tags" onKeyUp={(e) => {
                              return e.key === 'Enter'? addTags(e) : null
                            }}/>
                        </TagsInputContainer>
                    </QuestionContainer>
                </Containerbox>
                <QuestionContainer>
                    <h3>Review questions already on Stack Overflow to see if your question is a duplicate.</h3>
                    <Subtitle>
                        Clicking on these questions will open them in a new tab for you to review. Your progress here
                        will be saved so you can come back and continue.
                    </Subtitle>
                    {/* 드롭다운 넣기 */}
                </QuestionContainer>
            </Container>
            <BlueButton onClick={handleSubmit}>Post question</BlueButton>
        </Askpage>
    );
}
