import styled from "styled-components";

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

export default Input;