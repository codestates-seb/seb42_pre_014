import { useContext, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import BlueButton from "./BlueButton";
import Header1 from "./Header1";
import { Navigate } from "react-router-dom";
import UserContext from "./UserContext";

const Container = styled.div`
    padding: 30px 20px;
`

function ProfilePage() {
    const { checkAuth, user } = useContext(UserContext);
    const [redirectToHomePage, setRedirectToHomePage] = useState(false);
    function logout() {
        axios.post('http://localhost:4000/logout', {}, { withCredentials: true })
            .then(() => {
                checkAuth().catch(() => {
                    setRedirectToHomePage(true);
                })
            });
    }
    return (
        <>
            {redirectToHomePage && (
                <Navigate to={'/'} />
            )}
            <Container>
                <Header1>Profile</Header1>
                {user && (
                    <>
                        <p>Hello {user.email}</p>
                        <BlueButton onClick={() => logout()}>Logout</BlueButton>
                    </>
                )}
                {!user && (
                    <p>You are not logged in</p>
                )}
            </Container>
        </>
    )
}

export default ProfilePage;