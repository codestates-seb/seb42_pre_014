import { createGlobalStyle } from "styled-components";

const GlobalStyles = createGlobalStyle`
  body{
    background: rgb(57,57,57);
    color:#fff;
  }
  b,strong{
    font-weight: 700;
  }
  a{
    color: #fff;
  }
  p{
    margin: 10px 0;
    line-height: 1.5rem;
  }
  h1,h2,h3{
    margin-top: 10px;
    margin-bottom: 10px;
  }
  h1{
    font-size: 1.8rem;
  }
  h2{
    font-size: 1.6rem;
  }
  blockquote{
    background-color: rgba(0, 0, 0, .1);
    padding: 15px;
    border-radius: 4px;
  }
`;

export default GlobalStyles;
