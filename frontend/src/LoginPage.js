import { Component, useContext } from "react"
import Header1 from "./Header1";
import styled from "styled-components";
import Input from "./Input";
import BlueButton from "./BlueButton";
import axios from 'axios';
import UserContext from "./UserContext";
import { Navigate } from "react-router-dom";
import ErrorBox from "./ErrorBox";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStackOverflow, faSquareFacebook, faGithub, faGoogle } from "@fortawesome/free-brands-svg-icons";
import BtnGoogle from "./GoogleButton";
import BtnGithub from "./GithubButton";
import BtnFacebook from "./FacebookButton";

const Container = styled.div`
    margin: auto;
    padding: 30px 20px;
`;
const Wrapper = styled.div`
    margin-bottom: 4px;
`
class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            redirectToHomePage: false,
            error: false,
        }
    }
    login(e) {
        e.preventDefault();
        axios.post('http://localhost:4000/login', {
            email: this.state.email,
            password: this.state.password,
        }, { withCredentials: true })
            .then(() => {
                this.context.checkAuth().then(() => {
                    this.setState({ error: false, redirectToHomePage: true });
                });
            })
            .catch(() => this.setState({ error: true }));
    }

    render() {
        return (<>
            {this.state.redirectToHomePage && (
                <Navigate to="/" />
            )}
            <Container style={{ marginTop: '60px' }}>
                <Header1 style={{ marginBottom: '12px', textAlign: "center" }}><FontAwesomeIcon icon={faStackOverflow} size='xl' /></Header1>
                <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                    <Wrapper>
                        <BtnGoogle>
                            &nbsp;&nbsp;<FontAwesomeIcon icon={faGoogle} size='xl' />&nbsp;Sign In with Google
                        </BtnGoogle>
                    </Wrapper>
                </div>
                <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                    <Wrapper>
                        <BtnGithub>
                            &nbsp;&nbsp;<FontAwesomeIcon icon={faGithub} size='xl' />&nbsp;Sign In with GitHub
                        </BtnGithub>
                    </Wrapper>
                </div>
                <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                    <Wrapper>
                        <BtnFacebook>
                            &nbsp;&nbsp;<FontAwesomeIcon icon={faSquareFacebook} size='xl' />&nbsp;Sign In with Facebook
                        </BtnFacebook>
                    </Wrapper>
                </div>
                {this.state.error && (
                    <ErrorBox>Login failed</ErrorBox>
                )}
                <div style={{ marginTop: '12px', backgroundColor: '#353535', padding: '12px', borderRadius: '8px', boxShadow: '0px 0px 1px 2px #5D5D5D' }}>
                    <form style={{ marginTop: '12px' }} onSubmit={e => this.login(e)}>
                        <label htmlFor="email">&nbsp;Email</label>
                        <Input style={{ marginTop: '4px' }}
                            placeholder={'email'}
                            type="email"
                            id="email"
                            value={this.state.email}
                            onChange={e => this.setState({ email: e.target.value })} />
                        <label htmlFor="password">&nbsp;Password</label>
                        <Input style={{ marginTop: '4px' }}
                            placeholder={'password'}
                            type="password"
                            value={this.state.password}
                            onChange={e => this.setState({ password: e.target.value })} />
                        <BlueButton style={{ width: '100%', marginBottom: '12px' }} type={'submit'}>Log in</BlueButton>
                    </form>
                </div>
            </Container>
        </>);
    }
}

LoginPage.contextType = UserContext;

export default LoginPage;