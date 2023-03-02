import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import HideRightSidebar from "./HideRightSidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStackOverflow } from "@fortawesome/free-brands-svg-icons";
import { ReactComponent as Search } from "../src/icons/MobileSearch.svg";
const FaStackOverFlow = <FontAwesomeIcon icon={faStackOverflow} size="0.5x" />;

const SB_container = styled.div`
    box-sizing: border-box;
    display: flex;
    height: auto;
    width: auto;
    justify-content: flex-end;
    padding: 10px 0;
    background-color: #2d2d2d;
    @media screen and (max-width: 980px) {
        display: none;
    }
`;
const SB_item_container = styled.div`
    height: auto;
    width: 298px;
    margin: 20px 20px;
`;
const SB_item_list = styled.ul`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    height: auto;
    width: auto;
    list-style-type: none;
    border-style: solid;
    border-width: 1px;
    border-color: ${(props) => (props.yellow ? "#665c37" : "#494d50")};
    border-radius: 3px;
    align-items: center;
    justify-items: center;
    margin-bottom: 20px;
    /* box-shadow: 1px 1px 1px 1px #535353; */
    /* border: 0 0 0 1px solid; */
    /* position: relative; */
`;
const SB_Item = styled.li`
    display: flex;
    flex-direction: ${(props) => (props.column ? "column" : "row")};
    height: auto;
    padding: 12px 15px;
    width: auto;
    color: ${(props) => (props.title ? "#ffffff" : "#c4c8cc")};
    border-bottom: ${(props) => (props.title ? "1px solid #665c37" : props.title2 ? "1px solid #363739" : "")};
    background-color: ${(props) =>
        props.title ? "#524c38" : props.title2 ? "#393939" : props.orange ? "#464337" : ""};
    margin: auto;
`;
const SB_Itemlink = styled.a`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    color: #c4c8cc;
    text-decoration: none;
    cursor: pointer;
`;
const SB_Button = styled.button`
    height: 30px;
    width: auto;
    margin: auto;
    background-color: #3e4952;
    color: #fff;
    border: 0;
    border-radius: 5px;
    border: 1px solid #678fac;
    text-decoration: none;
    border-radius: 3px;
    color: #9ac0d9;
    cursor: pointer;
`;
const SB_Link = styled(Link)`
    text-decoration: none;
    color: #3ca4ff;
    display: block;
    margin-bottom: 5px;
`;
const SB_Img_container = styled.div``;
const SB_Img = styled.img``;

function RightSidebar() {
    if (HideRightSidebar()) return null;
    return (
        <SB_container>
            <SB_item_container>
                <SB_item_list home yellow>
                    <SB_Item title>The Overflow Blog</SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>
                            Shorten the distance between production data and insight (Ep. 541) sponsored post
                        </SB_Itemlink>
                    </SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>How edge functions move your back end close to your front end</SB_Itemlink>
                    </SB_Item>
                    <SB_Item title>Featured on Meta</SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>Ticket smash for [status-review] tag: Part Deux</SB_Itemlink>
                    </SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>
                            We've added a "Necessary cookies only" option to the cookie consent popup
                        </SB_Itemlink>
                    </SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>The [amazon] tag is being burninated</SB_Itemlink>
                    </SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>Microsoft Azure Collective launch and proposed tag changes</SB_Itemlink>
                    </SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>Temporary policy: ChatGPT is banned</SB_Itemlink>
                    </SB_Item>
                    <SB_Item title>Hot Meta Posts</SB_Item>
                    <SB_Item orange>
                        <SB_Itemlink>Should we merge Google Hangouts tags with Google Chat?</SB_Itemlink>
                    </SB_Item>
                </SB_item_list>
                <SB_item_list>
                    <SB_Item title2>Custon Filters</SB_Item>
                    <SB_Item>
                        <SB_Link>Create a custom filter</SB_Link>
                    </SB_Item>
                </SB_item_list>
                <SB_item_list>
                    <SB_Item title2>Watched Tags</SB_Item>
                    <SB_Item column>
                        <SB_Img_container>
                            {/* <SB_Img width="48" height="48"> */}
                            {/* <Search /> */}
                            {/* </SB_Img> */}
                        </SB_Img_container>
                        <SB_Item>Watch Tags to curate your list of questions.</SB_Item>
                        <SB_Button>Watch a tag</SB_Button>
                    </SB_Item>
                </SB_item_list>
                <SB_item_list>
                    <SB_Item title2>Ignored Tags</SB_Item>
                    <SB_Item>
                        <SB_Button>Add an ignored tag</SB_Button>
                    </SB_Item>
                </SB_item_list>
                <SB_item_list>
                    <SB_Item title2>Collectives</SB_Item>
                    <SB_Item>Create a custom filter</SB_Item>
                </SB_item_list>
            </SB_item_container>
        </SB_container>
    );
}

export default RightSidebar;
