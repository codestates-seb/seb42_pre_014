import { Component, useContext } from "react"
import Header1 from "./Header1";
import styled from "styled-components";
import Input from "./Input";
import BlueButton from "./BlueButton";
import axios from 'axios';
import UserContext from "./UserContext";
import { Navigate } from "react-router-dom";

const Container = styled.div`
    padding: 30px 20px;
`;
class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            redirectToHomePage: false,
        }
    }
    login() {
        axios.post('http://localhost:4000/login', {
            email: this.state.email,
            password: this.state.password,
        }, { withCredentials: true })
            .then(() => {
                this.context.checkAuth().then(() => {
                    this.setState({ redirectToHomePage: true });
                });
            });
    }

    render() {
        return (<>
            {this.state.redirectToHomePage && (
                <Navigate to="/" />
            )}
            <Container>
                <Header1 style={{ marginBottom: '20px' }}>Log in</Header1>
                <Input
                    placeholder={'email'}
                    type="email"
                    value={this.state.email}
                    onChange={e => this.setState({ email: e.target.value })} />
                <Input
                    placeholder={'password'}
                    type="password"
                    value={this.state.password}
                    onChange={e => this.setState({ password: e.target.value })} />
                <BlueButton onClick={() => this.login()}>Login</BlueButton>
            </Container>
        </>);
    }
}

LoginPage.contextType = UserContext;

export default LoginPage;