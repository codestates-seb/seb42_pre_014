import styled from "styled-components";
import Tag from "./Tag";
import Input from "./Input";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import TagCard from "./TagCard";
import Header1 from "./Header1";

const Text = styled.div`
    margin-top: 22px;
    margin-bottom: 22px;
    font-size: 1.1em;
`
const CardContainer = styled.div`
    display: flex;
    justify-content: space-around;
`

function TagsPage() {
    return (
        <div style={{ padding: "24px" }}>
            <div style={{ paddingLeft: "8px" }}>
                <Header1>Tags</Header1>
                <Text>A tag is a keyword or label that categorizes your question with other, similar questions.<br /> Using the right tags makes it easier for others to find and answer your question.</Text>
                <a href="https://stackoverflow.com/tags/synonyms" target={'_blank'}>Show all tag synonyms</a>
                <Input style={{ marginTop: "24px", marginBottom: "8px", width: "200px" }} />
            </div>
            <CardContainer>
                <TagCard tag="javascript" text="For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript). Keep in..." />
                <TagCard tag="python" text="Python is a multi-paradigm, dynamically typed, multi-purpose programming language. It is designed to be quick to learn,..." />
                <TagCard tag="java" text="Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself..." />
                <TagCard tag="c#" text="C# (pronounced 'see sharp') is a high-level, statically typed, multi-paradigm programming language developed by Microsoft. C# code..." />
            </CardContainer>
            <CardContainer>
                <TagCard tag="php" text="PHP is a widely used, open source, general-purpose, multi-paradigm, dynamically typed and interpreted scripting language designed initially..." />
                <TagCard tag="android" text="Android is Google's mobile operating system, used for programming or developing digital devices (Smartphones, Tablets, Automobiles,..." />
                <TagCard tag="html" text="HTML (HyperText Markup Language) is the markup language for creating web pages and other information to be displayed in a web browser..." />
                <TagCard tag="jquery" text="jQuery is a JavaScript library. Consider also adding the JavaScript tag. jQuery is a popular cross-browser JavaScript library that..." />
            </CardContainer>
            <CardContainer>
                <TagCard tag="javascript" text="For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript). Keep in..." />
                <TagCard tag="python" text="Python is a multi-paradigm, dynamically typed, multi-purpose programming language. It is designed to be quick to learn,..." />
                <TagCard tag="java" text="Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself..." />
                <TagCard tag="c#" text="C# (pronounced 'see sharp') is a high-level, statically typed, multi-paradigm programming language developed by Microsoft. C# code..." />
            </CardContainer>
            <CardContainer>
                <TagCard tag="php" text="PHP is a widely used, open source, general-purpose, multi-paradigm, dynamically typed and interpreted scripting language designed initially..." />
                <TagCard tag="android" text="Android is Google's mobile operating system, used for programming or developing digital devices (Smartphones, Tablets, Automobiles,..." />
                <TagCard tag="html" text="HTML (HyperText Markup Language) is the markup language for creating web pages and other information to be displayed in a web browser..." />
                <TagCard tag="jquery" text="jQuery is a JavaScript library. Consider also adding the JavaScript tag. jQuery is a popular cross-browser JavaScript library that..." />
            </CardContainer>
            <CardContainer>
                <TagCard tag="javascript" text="For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript). Keep in..." />
                <TagCard tag="python" text="Python is a multi-paradigm, dynamically typed, multi-purpose programming language. It is designed to be quick to learn,..." />
                <TagCard tag="java" text="Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself..." />
                <TagCard tag="c#" text="C# (pronounced 'see sharp') is a high-level, statically typed, multi-paradigm programming language developed by Microsoft. C# code..." />
            </CardContainer>
            <CardContainer>
                <TagCard tag="php" text="PHP is a widely used, open source, general-purpose, multi-paradigm, dynamically typed and interpreted scripting language designed initially..." />
                <TagCard tag="android" text="Android is Google's mobile operating system, used for programming or developing digital devices (Smartphones, Tablets, Automobiles,..." />
                <TagCard tag="html" text="HTML (HyperText Markup Language) is the markup language for creating web pages and other information to be displayed in a web browser..." />
                <TagCard tag="jquery" text="jQuery is a JavaScript library. Consider also adding the JavaScript tag. jQuery is a popular cross-browser JavaScript library that..." />
            </CardContainer>
        </div >
    )
}

export default TagsPage;