import styled from "styled-components";
import { Link } from "react-router-dom";

const BlueButtonLink = styled(Link)`
    display: flex;
    align-items: center;
    height: 13px;
    width: auto;
    background-color: #0b63aa;
    font-size: 13px;
    color: #fff;
    border: 0;
    border-radius: 3px;
    padding: 12px 10px;
    text-decoration: none;
`;

export default BlueButtonLink;
