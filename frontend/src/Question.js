import { useState } from "react";
import{ useParams } from "react-router-dom";
import useFetch from "./json-server/useFetch"
import { fetchCreate } from "./json-server/api"


const Question = () => {
    const { id } = useParams();
    const [data, isPending, error ] = useFetch(`http://localhost:3001/test/${id}`)
    const [answer, setAnswer] = useState('');

    const handleSubmit = (e) => {
        // e.preventDefault(); //새로고침 막기
        const data = {answer}
        fetchCreate("http://localhost:3001/test/", data)
    }

    return (
        <>
            { isPending }
            { error && <div>{ error }</div> }
            { data && (
                <article>
                    <h1>{ data.questionTitle }</h1>
                    <div>{ data.questionBody }</div>
                    <button>Share</button>
                    <button>Edit</button>
                    <button>Delete</button>
                </article>
            )}
            { data && (
                <article>
                    <h2>1 Answer</h2>
                    <div>{ data.answer.answerBody }</div>
                    <button>Share</button>
                    <button>Edit</button>
                    <button>Delete</button>
                </article>
            )}
            <div className="container">
                <div>Your Answer</div>
                <textarea
                    onChange={e => setAnswer(e.target.value)}>{answer}</textarea>
                <button onClick={handleSubmit}>Post Your Answer</button>
            </div>
        </>
    )
}

export default Question