import styled from "styled-components"
import BlueButton from "./BlueButton"
import Header1 from "./Header1"

const Container = styled.div`
    padding: 30px 20px;
`
const QuestionTitleInput = styled.input`
    background: none;
    border: 1px solid #777;
    border-radius: 3px;
    display: block;
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    margin-bottom: 20px;
    color: #fff;
`
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
`

export default function AskPage() {

    return (
        <Container>
            <Header1 style={{ marginBottom: '20px' }}>Ask a public question</Header1>
            <QuestionTitleInput type="text" placeholder="Title of your question" />
            <QuestionBodyTextarea placeholder="More info about your question. You can use markdown here" />
            <BlueButton>Post question</BlueButton>
        </Container>
    )
}