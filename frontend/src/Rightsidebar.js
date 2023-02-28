import React from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
import HideRightSidebar from "./HideRightSidebar";

const Sidebar_container = styled.div`
    box-sizing: border-box;
    display: flex;
    height: auto;
    width: auto;
    justify-content: flex-end;
    padding: 10px 0;
    background-color: #2d2d2d;
`;
const Sidebar_items_container = styled.div`
    height: auto;
    width: 300px;
    margin: 20px 20px;
    /* max-width: 400px; */
    /* overflow: hidden; */
    /* margin: 0 50px 0 30px; */
    /* padding-left: 15px; */
`;
const Sidebar_items_list = styled.ul`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    height: auto;
    width: auto;
    /* text-align: left; */
    list-style-type: none;
    border-style: solid;
    border-width: 2px;
    border-color: #535353;
    align-items: center;
    justify-items: center;
    margin-bottom: 20px;
    /* box-shadow: 1px 1px 1px 1px #535353; */
    /* border: 0 0 0 1px solid; */
    /* position: relative; */
`;
const Sidebar_items = styled.li`
    display: flex;
    flex-direction: ${(props) => (props.column ? "column" : "row")};
    height: auto;
    padding: 12px 15px;
    width: auto;
    border-style: solid;
    border-width: 1px 0 0 0;
    border-color: #535353;
    color: #aaaaaa;
    border-color: ${(props) => (props.home ? "orange" : "null")};
    background-color: ${(props) => (props.title ? "#524c38" : props.title2 ? "#393939" : "")};
    /* #464337 */
    margin: auto;
`;
const Sidebar_items_link = styled.a`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    text-decoration: none;
    color: inherit;
    :hover {
        color: #ffffff;
    }
`;
const Sidebar_button = styled.button`
    height: 30px;
    width: auto;
    margin: auto;
    background-color: #3e4952;
    color: #fff;
    border: 0;
    border-radius: 5px;
    border: 1px solid #678fac;
    text-decoration: none;
    border-radius: 5%;
    color: #aaaaaa;
`;

function RightSidebar() {
    if (HideRightSidebar()) return null;
    return (
        <Sidebar_container>
            <Sidebar_items_container>
                <Sidebar_items_list home>
                    <Sidebar_items title>The Overflow Blog</Sidebar_items>
                    <Sidebar_items>
                        Shorten the distance between production data and insight (Ep. 541) sponsored post
                    </Sidebar_items>
                    <Sidebar_items>How edge functions move your back end close to your front end</Sidebar_items>
                    <Sidebar_items title>Featured on Meta</Sidebar_items>
                    <Sidebar_items>Ticket smash for [status-review] tag: Part Deux</Sidebar_items>
                    <Sidebar_items>
                        We've added a "Necessary cookies only" option to the cookie consent popup
                    </Sidebar_items>
                    <Sidebar_items>The [amazon] tag is being burninated</Sidebar_items>
                    <Sidebar_items>Microsoft Azure Collective launch and proposed tag changes</Sidebar_items>
                    <Sidebar_items>Temporary policy: ChatGPT is banned</Sidebar_items>
                    <Sidebar_items title>Hot Meta Posts</Sidebar_items>
                    <Sidebar_items>Should we merge Google Hangouts tags with Google Chat?</Sidebar_items>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items title>Custon Filters</Sidebar_items>
                    <Sidebar_items>Create a custom filter</Sidebar_items>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items title>Watched Tags</Sidebar_items>
                    <Sidebar_items column>
                        <Sidebar_items>Watch Tags to curate your list of questions.</Sidebar_items>
                        <Sidebar_button>Watch a tag</Sidebar_button>
                    </Sidebar_items>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items title>Ignored Tags</Sidebar_items>
                    <Sidebar_button>Add an ignored tag</Sidebar_button>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items title>Collectives</Sidebar_items>
                    <Sidebar_items>Create a custom filter</Sidebar_items>
                </Sidebar_items_list>
            </Sidebar_items_container>
        </Sidebar_container>
    );
}

export default RightSidebar;
