import React from "react";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStackOverflow } from "@fortawesome/free-brands-svg-icons";

const StackOverflow = <FontAwesomeIcon icon={faStackOverflow} size="2x" />;
const Foooter = styled.footer`
    box-sizing: border-box;
    height: auto;
    width: 100%;
    background-color: #232629;
`;
const Container = styled.div`
    max-width: 1264px;
    display: flex;
    margin: 0 auto;
    padding: 32px 12px 12px 12px;
    flex-flow: row wrap;
    flex-wrap: wrap;
`;
const Logo_container = styled.div`
    flex: 0 0 64px;
    display: flex;
    flex-wrap: wrap;
    @media screen and (max-width: 832px) {
        /* flex-direction: column; */
    }
    @media screen and (max-width: 640px) {
        display: none;
    }
`;
const Menu_container = styled.div`
    display: flex;
    flex: 2 1 auto;
    flex-wrap: wrap;

    @media screen and (max-width: 980px) {
        width: 70%;
        flex-direction: column;
    }
`;
const Copyright_container = styled.div`
    flex: 1 1 150px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
`;
const Logo_box = styled.div`
    flex-wrap: wrap;
`;
const Logo_link = styled.a``;

const Menu_list = styled.div`
    flex: 1 0 auto;
    padding: 0 12px 24px 0;
`;
const Menu_title = styled.h5`
    margin-bottom: 15px;
    color: #babfc4;
    font-size: 12px;
    font-weight: 700;
`;
const Menu_items = styled.ul`
    display: flex;
    flex-direction: column;
    height: auto;
    list-style: none;
    @media screen and (max-width: 980px) {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        column-gap: 12px;
        row-gap: 8px;
    }
`;
const Menu_item = styled.li`
    height: 25px;
    font-size: 12px;
    margin-top: ${(props) => (props.API ? "16px" : "0")};
    @media screen and (max-width: 980px) {
        margin: 0 5px 0 0;
    }
`;
const Menu_item_link = styled.a`
    padding: 4px 0;
    color: #9199a1;
    font-weight: 400;
    cursor: pointer;
`;

const Copyright_items = styled.ul`
    display: flex;
`;
const Copyright_item = styled.li`
    margin-left: ${(props) => (props.left ? "12px" : "0")};
`;
const Copyright_link = styled.a`
    font-size: 11px;
    color: #9199a1;
    cursor: pointer;
`;
const Copyright = styled.p`
    font-size: 11px;
    color: #9199a1;
    margin: auto 0 24px 0;
`;

function Footer() {
    return (
        <Foooter>
            <Container>
                <Logo_container>
                    <Logo_box>
                        <Logo_link href="/">{StackOverflow}</Logo_link>
                    </Logo_box>
                </Logo_container>
                <Menu_container>
                    <Menu_list>
                        <Menu_title>STACK OVERFLOW</Menu_title>
                        <Menu_items>
                            <Menu_item>
                                <Menu_item_link>Questions</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Help</Menu_item_link>
                            </Menu_item>
                        </Menu_items>
                    </Menu_list>
                    <Menu_list>
                        <Menu_title>PRODUCTS</Menu_title>
                        <Menu_items>
                            <Menu_item>
                                <Menu_item_link>Teams</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Advertising</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Collectives</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Talent</Menu_item_link>
                            </Menu_item>
                        </Menu_items>
                    </Menu_list>
                    <Menu_list>
                        <Menu_title>COMPANY</Menu_title>
                        <Menu_items>
                            <Menu_item>
                                <Menu_item_link>About</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Press</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Work Here</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Legal</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Privacy Policy</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Terms of Service</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Contact Us</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Cookie Setting</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Cookie Policy</Menu_item_link>
                            </Menu_item>
                        </Menu_items>
                    </Menu_list>
                    <Menu_list>
                        <Menu_title>STACK EXCHANGE NETWORK</Menu_title>
                        <Menu_items>
                            <Menu_item>
                                <Menu_item_link>Technology</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Culture & recreation</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Life & arts</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Science</Menu_item_link>
                            </Menu_item>
                            <Menu_item API>
                                <Menu_item_link>API</Menu_item_link>
                            </Menu_item>
                            <Menu_item>
                                <Menu_item_link>Data</Menu_item_link>
                            </Menu_item>
                        </Menu_items>
                    </Menu_list>
                </Menu_container>
                <Copyright_container>
                    <Copyright_items>
                        <Copyright_item>
                            <Copyright_link>Blog</Copyright_link>
                        </Copyright_item>
                        <Copyright_item left>
                            <Copyright_link>Facebook</Copyright_link>
                        </Copyright_item>
                        <Copyright_item left>
                            <Copyright_link>Twitter</Copyright_link>
                        </Copyright_item>
                        <Copyright_item left>
                            <Copyright_link>LinkedIn</Copyright_link>
                        </Copyright_item>
                        <Copyright_item left>
                            <Copyright_link>Instagram</Copyright_link>
                        </Copyright_item>
                    </Copyright_items>
                    <Copyright>
                        Site design / logo © 2023 Stack Exchange Inc; user contributions licensed under CC BY-SA. rev
                        2023.3.1.43269
                    </Copyright>
                </Copyright_container>
            </Container>
        </Foooter>
    );
}

export default Footer;
