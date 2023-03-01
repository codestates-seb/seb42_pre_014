import styled from "styled-components";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import { Link } from "react-router-dom";
import { fetchPatch } from "./json-server/api";
import Tag from "./Tag";
dayjs.extend(relativeTime);
dayjs.locale("ko");

const StyledQuestionRow = styled.div`
    background-color: #2d2d2d;
    padding: 15px 15px 20px;
    display: grid;
    grid-template-columns: 100px 1fr;
    /* grid-template-rows: repeat(3, 20px); */
    grid-template-rows: 1fr;
    border-top: 1px solid #3b3d3f;
`;

const QuestionStat_container = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 40px;
`;
const QuestionStat = styled.div`
    height: auto;
    width: 55px;
    text-align: end;
    display: inline-block;
    font-size: 0.8rem;
    color: ${(props) => (props.answers ? "#89ca9f" : props.votes ? "#fff" : "#aaa")};
    border: ${(props) => (props.answers ? "1px solid #3a8251" : "")};
    border-radius: 5%;
    border-width: 10%;
    padding: 3px 0;

    span {
        font-size: 0.7rem;
        font-weight: 300;
    }
`;
const QuestionTitleArea = styled.div`
    padding: 0 10px;
`;
const QuestionLink = styled(Link)`
    text-decoration: none;
    color: #3ca4ff;
    font-size: 1rem;
    display: block;
    margin-bottom: 5px;
`;

const WhoAndWhen = styled.div`
    /* display: inline-block; */
    color: #aaa;
    font-size: 0.75rem;
    float: right;
    padding: 10px 0;
`;
const UserLink = styled.a`
    color: #3ca4ff;
`;

function QuestionRow({ db }) {
    const timeString = dayjs(db.writetime).fromNow();

    const viewsUp = () => {
        const views = { views: db.views + 1 };
        fetchPatch("http://localhost:3001/questions/", db.id, views);
    };

    return (
        <StyledQuestionRow>
            <QuestionStat_container>
                <QuestionStat votes>
                    {db.votes}
                    <span> {db.votes === 1 ? "vote" : "votes"}</span>
                </QuestionStat>
                <QuestionStat answers={db.answers}>
                    {db.answers}
                    <span> {db.answers === 1 ? "answer" : "answers"}</span>
                </QuestionStat>
                <QuestionStat>
                    {db.views}
                    <span> {db.views === 1 ? "view" : "views"}</span>
                </QuestionStat>
            </QuestionStat_container>

            <QuestionTitleArea onClick={viewsUp}>
                <QuestionLink to={`./${db.id}`}>{db.title}</QuestionLink>
                <WhoAndWhen>
                    {db.answerer ? `${db.answerer} answered` : `${db.writer} asked`} {timeString} <UserLink></UserLink>
                </WhoAndWhen>
                {db.tags.map((el) => {
                    return <Tag>{el}</Tag>;
                })}
            </QuestionTitleArea>
        </StyledQuestionRow>
    );
}

export default QuestionRow;
