import styled from "styled-components";
import QuestionRow from "./QuestionRow";
import Header1 from "./Header1";
import BlueButtonLink from "./BlueButtonLink";
import { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFilter } from "@fortawesome/free-solid-svg-icons";
const filtericon = <FontAwesomeIcon icon={faFilter} />;
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
const SortButton = styled.button`
    height: 30px;
    width: 80px;
    background-color: #383838;
    color: #fff;
    border: 0;
    border-radius: 5px;
    border: 1px solid #535353;
    text-decoration: none;
    grid-row-start: 3;
    grid-row-end: 4;
`;
async function fetchQuestions() {
    try {
        const res = await axios.get("http://localhost:3001/questions");
        return res.data;
    } catch (err) {
        console.err(err);
    }
}

function QuestionsPage({ data }) {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        async function getQuestions() {
            const data = await fetchQuestions();
            setQuestions(data);
        }
        getQuestions();
        console.log(questions);
    }, []);

    return (
        <div>
            <HeaderRow>
                <Header1 style={{ margin: 0 }}>Top Questions</Header1>
                <BlueButtonLink to="./ask">Ask&nbsp;Question</BlueButtonLink>
                <QuestionNum>{questions.length} questions</QuestionNum>
                <SortButton>{filtericon} Filter</SortButton>
            </HeaderRow>
            {questions.map((el) => {
                return <QuestionRow db={el} key={el.id} />;
            })}
        </div>
    );
}

export default QuestionsPage;
