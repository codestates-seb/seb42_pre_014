import { Component, useContext } from "react"
import Header1 from "./Header1";
import { faSquareFacebook, faGithub, faGoogle } from "@fortawesome/free-brands-svg-icons";
import { faQuestion, faSort, faTag, faTrophy } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styled from "styled-components";
import Input from "./Input";
import BlueButton from "./BlueButton";
import axios from 'axios';
import UserContext from "./UserContext";
import { Navigate } from "react-router-dom";
import ErrorBox from "./ErrorBox";
import HideSidebar from "./HideLeftSidebar";
import BtnFacebook from "./FacebookButton";
import BtnGithub from "./GithubButton";
import BtnGoogle from "./GoogleButton";
import ReCAPTCHA from "react-google-recaptcha";
import Checkbox from "./CheckBox";

const Container = styled.div`
    margin: auto;
    padding: 30px 20px;
`;
const MainContainer = styled.div`
    display: flex;
    justify-content: space-around;
    margin-left: 120px;
    align-items: center;
    @media screen and (max-width: 900px) {
        margin-left: 0px;
    }
`
const Wrapper = styled.div`
    margin-bottom: 4px;
    width: 325px;
`
const LinkTag = styled.a`
    &:hover{
        color: #6799FF;
    }
`
const Text = styled.div`
    margin-top: 32px;
`
const Hide = styled.div`
    @media screen and (max-width: 900px) {
        display: none;
    }
`
const FormContainer = styled.div`
    margin-left: 50px;
    width: 330px;
    @media screen and (max-width: 900px) {
        margin-left: 0px;
    }
`
class RegisterPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            name: '',
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
            .then(() => this.setState({ error: false, redirectToHomePage: true }))
            .catch(error => {
                this.setState({ error: error.response.data });
            });
    }

    render() {
        const Captcha = () => {
            function onChange(value) {
                console.log('Captcha value:', value);
            }
        }
        return (<>
            {this.state.redirectToHomePage && (
                <Navigate to="/" />
            )}
            <Container>
                <MainContainer>
                    <Hide>
                        <h1>Join the Stack Overflow community</h1>
                        <Text><FontAwesomeIcon icon={faQuestion} size='xl' />&nbsp;&nbsp;&nbsp;&nbsp;Get unstuck — ask a question</Text>
                        <Text><FontAwesomeIcon icon={faSort} size='xl' />&nbsp;&nbsp;&nbsp;&nbsp;Unlock new privileges like voting and commenting</Text>
                        <Text><FontAwesomeIcon icon={faTag} size='xl' />&nbsp;&nbsp;&nbsp;Save your favorite tags, filters, and jobs</Text>
                        <Text><FontAwesomeIcon icon={faTrophy} size='xl' />&nbsp;Earn reputation and badges</Text>
                        <Text>Collaborate and share knowledge with a private group for FREE.
                            <br />
                            <LinkTag href="https://stackoverflow.co/teams/?utm_source=so-owned&utm_medium=product&utm_campaign=free-50&utm_content=public-sign-up" target={'_blank'}>
                                Get Stack Overflow for Teams free for up to 50 users.
                            </LinkTag>
                        </Text>
                    </Hide>
                    <FormContainer>
                        <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                            <Wrapper>
                                <BtnGoogle style={{ width: '100%' }}>
                                    &nbsp;&nbsp;<FontAwesomeIcon icon={faGoogle} size='xl' />&nbsp;Sign In with Google
                                </BtnGoogle>
                            </Wrapper>
                        </div>
                        <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                            <Wrapper>
                                <BtnGithub style={{ width: '100%' }}>
                                    &nbsp;&nbsp;<FontAwesomeIcon icon={faGithub} size='xl' />&nbsp;Sign In with GitHub
                                </BtnGithub>
                            </Wrapper>
                        </div>
                        <div style={{ display: 'flex', flexWrap: 'wrap' }} >
                            <Wrapper>
                                <BtnFacebook style={{ width: '100%' }}>
                                    &nbsp;&nbsp;<FontAwesomeIcon icon={faSquareFacebook} size='xl' />&nbsp;Sign In with Facebook
                                </BtnFacebook>
                            </Wrapper>
                        </div>
                        {this.state.error && (
                            <ErrorBox>{this.state.error}</ErrorBox>
                        )}
                        <div style={{ marginTop: '12px', backgroundColor: '#353535', padding: '12px', borderRadius: '8px', boxShadow: '0px 0px 1px 2px #5D5D5D' }}>
                            <form style={{ marginTop: '16px' }} onSubmit={(e) => this.register(e)}>
                                <label htmlFor="name">&nbsp;Display name</label>
                                <Input style={{ marginTop: '4px' }}
                                    placeholder={'name'}
                                    type="text"
                                    id="name"
                                    value={this.state.name}
                                    onChange={e => this.setState({ name: e.target.value })} />
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
                                    autoComplete={'new-password'}
                                    onChange={e => this.setState({ password: e.target.value })} />
                                <div style={{ fontSize: '0.8em', marginBottom: '24px' }}>Passwords must contain at least eight<br />characters, including at least 1<br /> letter and 1 number.</div>
                                <div style={{ display: 'flex', alignItems: 'center', flexDirection: 'column', justifyContent: 'center', height: '120px', backgroundColor: '#474747', borderRadius: '8px', boxShadow: '0px 0px 1px 2px #5D5D5D' }}>
                                    <ReCAPTCHA
                                        sitekey="6Lf-e7okAAAAALF5CwFeRlMLO_kbIfvyEzKR_hZp"
                                    />
                                </div>
                                <div style={{ marginTop: '12px', fontSize: '0.8em' }}>
                                    <Checkbox>Opt-in to receive occasional product updates,<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user research invitations,<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;company announcements, and digests.</Checkbox>
                                </div>
                                <BlueButton style={{ width: '100%', marginBottom: '20px', marginTop: '12px' }} type={'submit'}>Sign up</BlueButton>
                                <div style={{ marginTop: '12px', fontSize: '0.8em', marginBottom: '12px' }}>By clicking “Sign up”, you agree to our terms of<br /> service, privacy policy and cookie policy</div>
                            </form>
                        </div>
                    </FormContainer>
                </MainContainer>
            </Container>
        </>);
    }
}

RegisterPage.contextType = UserContext;

export default RegisterPage;