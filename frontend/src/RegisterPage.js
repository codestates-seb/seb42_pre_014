import { Component, useContext } from "react"
import Header1 from "./Header1";
import styled from "styled-components";
import Input from "./Input";
import BlueButton from "./BlueButton";
import axios from 'axios';
import UserContext from "./UserContext";
import { Navigate } from "react-router-dom";
import ErrorBox from "./ErrorBox";

const Container = styled.div`
    padding: 30px 20px;
`;
class RegisterPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            redirectToHomePage: false,
            error: false,
        }
    }
    register(e) {
        e.preventDefault();
        axios.post('http://localhost:4000/register', {
            email: this.state.email,
            password: this.state.password,
        }, { withCredentials: true })
            .then(() => {
                this.context.checkAuth()
                    .then(() => this.setState({ error: false, redirectToHomePage: true }));
            })
            .catch(error => {
                this.setState({ error: error.response.data });
            });
    }

    render() {
        return (<>
            {this.state.redirectToHomePage && (
                <Navigate to="/" />
            )}
            <Container>
                <Header1 style={{ marginBottom: '20px' }}>Register</Header1>
                {this.state.error && (
                    <ErrorBox>{this.state.error}</ErrorBox>
                )}
                <form onSubmit={(e) => this.register(e)}>
                    <Input
                        placeholder={'email'}
                        type="email"
                        value={this.state.email}
                        onChange={e => this.setState({ email: e.target.value })} />
                    <Input
                        placeholder={'password'}
                        type="password"
                        value={this.state.password}
                        autoComplete={'new-password'}
                        onChange={e => this.setState({ password: e.target.value })} />
                    <BlueButton type={'submit'}>Register</BlueButton>
                </form>
            </Container>
        </>);
    }
}

RegisterPage.contextType = UserContext;

export default RegisterPage;