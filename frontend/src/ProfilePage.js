import { useState } from "react";
import axios from "axios";
import styled from "styled-components";
import BlueButton from "./BlueButton";
import Header1 from "./Header1";

const Container = styled.div`
    padding: 30px 20px;
`

function ProfilePage() {
    function logout() {
        axios.post('/logout')
            .then();
    }
    return (
        <>
            <Container>
                <Header1>Profile</Header1>
            </Container>
            <BlueButton></BlueButton>
        </>
    )
}

export default ProfilePage;