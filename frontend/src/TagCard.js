import styled from "styled-components";
import Tag from "./Tag";

const CardText = styled.div`
    margin-top: 16px;
    font-size: 0.9em;
`

function TagCard(props) {
    return (
        <div style={{ padding: '8px' }}>
            <div style={{ width: "225px", height: '155px', padding: '16px', borderRadius: '4px', boxShadow: '0px 0px 1px 2px #5D5D5D' }}>
                <Tag>{props.tag}</Tag>
                <CardText>{props.text}</CardText>
            </div>
        </div>
    )
}

export default TagCard;