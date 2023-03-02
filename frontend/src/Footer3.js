import React from "react";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStackOverflow } from "@fortawesome/free-brands-svg-icons";
import { useLocation } from "react-router-dom";
import HideSidebar from "./HideLeftSidebar";

const StyledFooter = styled.footer`
    height: auto;
    width: 100%;
`;
const Footer_container = styled.div`
    box-sizing: border-box;
    display: grid;
    height: auto;
    width: 100%;
    justify-content: center;
    grid-template-columns: 50px 50em 30em auto;
    margin: 0 auto;
    /* margin: 0 0px 10px 0px; */
    padding: 32px 2em 50px 2em;
    background-color: #232629;
`;
const Footer_logo = styled.div``;
const Footer_sitemap_container = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(0, 1fr));
    margin-right: 5%;
    /* background-color: blue; */
`;
const Footer_sitemap = styled.div`
    font-size: 13px;

    /* display: grid; */
    h5 {
        color: #babfc3;
        padding: 0 0 12px 0;
    }
    li {
        color: #9199a1;
        padding: 5px 0;
        /* margin: ${(props) => (props.margin === "1" ? "16 0 0 0" : "0")}; */
    }
`;
const Footer_copyright_container = styled.div`
    display: grid;
    grid-template-rows: 1fr 1fr auto;
    /* align-content: space-between; */
    span {
        font-size: 13px;
        color: #9199a1;
        margin-right: 5%;
    }
`;
function Footer() {
    if (HideSidebar()) return null;
    return (
        <StyledFooter>
            <Footer_container>
                <Footer_logo>
                    <FontAwesomeIcon icon={faStackOverflow} size="2x" />
                </Footer_logo>

                <Footer_sitemap_container>
                    <Footer_sitemap>
                        <h5>STACK OVERFLOW</h5>
                        <ul>
                            <li>Questions</li>
                            <li>Help</li>
                        </ul>
                    </Footer_sitemap>
                    <Footer_sitemap>
                        <h5>PRODUCTS</h5>
                        <ul>
                            <li>Teams</li>
                            <li>Advertising</li>
                            <li>Collectives</li>
                            <li>Talent</li>
                        </ul>
                    </Footer_sitemap>
                    <Footer_sitemap>
                        <h5>COMPANY</h5>
                        <ul>
                            <li>About</li>
                            <li>Press</li>
                            <li>Work Here</li>
                            <li>Legal</li>
                            <li>Privacy Policy</li>
                            <li>Terms of Service</li>
                            <li>Contact Us</li>
                            <li>Cookie Settings</li>
                            <li>Cookie Policy</li>
                        </ul>
                    </Footer_sitemap>
                    <Footer_sitemap>
                        <h5>STACK EXCHANGE NETWORK</h5>
                        <ul>
                            <li>Technology</li>
                            <li>Culture & recreation</li>
                            <li>Life & arts</li>
                            <li>Science</li>
                            <li>Professional</li>
                            <li>Business</li>
                            <li margin="1" id="li_margin">
                                API
                            </li>
                            <li>Data</li>
                        </ul>
                    </Footer_sitemap>
                </Footer_sitemap_container>
                <Footer_copyright_container>
                    <div>
                        <span>Blog</span>
                        <span>Facebook</span>
                        <span>Twitter</span>
                        <span>Linkedin</span>
                        <span>Instagram</span>
                    </div>
                    <div></div>
                    <span>Site design / logo © 2023 Stack Exchange Inc; user</span>
                    <span>contributions licensed under CC BY-SA.</span>
                    <span>rev 2023.2.17.43248</span>
                </Footer_copyright_container>
            </Footer_container>
        </StyledFooter>
    );
}

export default Footer;
