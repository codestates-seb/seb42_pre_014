import styled from "styled-components";
import QuestionRow from "./QuestionRow";
import Header1 from "./Header1";
import BlueButtonLink from "./BlueButtonLink";
import { useState, useEffect } from "react";
import axios from "axios";
import RightSidebar from "./Rightsidebar";

const Main_container = styled.div`
    display: flex;
    border-style: solid;
    border-width: 0 0 0 1px;
    border-color: #535353;
    max-width: 1051px;
`;
const Content_container = styled.div`
    height: auto;
`;
const HeaderRow = styled.div`
    display: grid;
    grid-template-columns: 1fr min-content;
    grid-template-rows: 1fr 0.5fr 0.8fr;
    padding: 30px 20px 0 20px;
    align-items: center;
`;
const QuestionNum = styled.div`
    grid-row-start: 3;
    grid-row-end: 4;
`;
const Bluebutton_container = styled.div`
    display: flex;
    justify-content: end;
`;
const Button_container = styled.div`
    display: flex;
    grid-row-start: 3;
    grid-row-end: 4;
    margin-bottom: 10px;
`;
const SortButton = styled.button`
    display: flex;
    height: 30px;
    width: auto;
    padding: 10px;
    align-items: center;
    background-color: transparent;
    color: #969ca0;
    font-size: 12px;
    border-radius: ${(props) => (props.start ? "3px 0 0 3px" : props.end ? "0 3px 3px 0" : "")};
    border: 1px solid #7c858d;
    text-decoration: none;
`;

async function fetchQuestions() {
    try {
        const res = await axios.get("/questions?page=1&size=5");
        return res.data.data;
    } catch (err) {
        console.error(err);
    }
}

function QuestionsPage() {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        async function getQuestions() {
            const data = await fetchQuestions();
            setQuestions(data);
        }
        getQuestions();
        // fetch("/questions?page=1&size=5")
        //     .then((res) => res.json())
        //     // .then((data) => console.log(data.data))
        //     .then((data) => setQuestions(data.data))
        //     .catch((error) => console.error(error));
    }, []);

    return (
        <Main_container>
            <Content_container>
                <HeaderRow>
                    <Header1 style={{ margin: 0 }}>Top Questions</Header1>
                    <Bluebutton_container>
                        {" "}
                        <BlueButtonLink to="./ask">Ask&nbsp;Question</BlueButtonLink>
                    </Bluebutton_container>

                    <QuestionNum></QuestionNum>
                    <Button_container>
                        <SortButton start> Interesting </SortButton>
                        <SortButton> Bountied </SortButton>
                        <SortButton> Hot </SortButton>
                        <SortButton> Week </SortButton>
                        <SortButton end> Month </SortButton>
                    </Button_container>
                </HeaderRow>
                {questions.map((el) => {
                    return <QuestionRow db={el} key={el.questionId} />;
                })}
            </Content_container>
            <RightSidebar />
        </Main_container>
    );
}

export default QuestionsPage;
