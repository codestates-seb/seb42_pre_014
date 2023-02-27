import React from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
// import { IconFaceMindBlown } from "@stackoverflow/stacks-icons/icons";
// import { SpotWave } from "@stackoverflow/stacks-icons/spots";

const Sidebar_container = styled.div`
    box-sizing: border-box;
    display: flex;
    height: auto;
    /* position: relative; */
    /* max-width: 164px; */
    top: 0;
    justify-content: flex-end;
    padding: 10px 0;
    background-color: #383838;
    border-style: solid;
    border-width: 0 1px 0 0;
    border-color: #535353;
`;
const Sidebar_items_container = styled.div`
    position: sticky;
    top: 80px;
    height: 500px;
    max-width: 164px;
    margin-left: 50px;
    padding-left: 15px;
`;
const Sidebar_items_list = styled.ul`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};

    padding: 10px 0 10px 5px;
    height: auto;
    width: 140px;
    list-style-type: none;
    border-style: solid;
    border-width: ${(props) => (props.home ? "0 3px 0 0" : "null")};
    border-color: ${(props) => (props.home ? "orange" : "null")};
    background-color: ${(props) => (props.home ? "#5A5A5A" : "null")};
    /* position: relative; */
`;
const Sidebar_items = styled.li`
    /* padding-left: 15px; */
    height: auto;
    padding: 10px 0;
    padding-left: 22px;
    width: auto;
    border-style: solid;
    border-width: ${(props) => (props.home ? "0 3px 0 0" : "null")};
    border-color: ${(props) => (props.home ? "orange" : "null")};
    color: #aaaaaa;
    background-color: #383838;
    cursor: pointer;
`;
const Sidebar_items_link = styled.a`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    text-decoration: none;
    color: inherit;
    :hover {
        color: #ffffff;
    }
`;

function Leftsidebar() {

    if (useLocation().pathname === '/login') return null;
    return (
        <Sidebar_container>
            <Sidebar_items_container>
                <Sidebar_items_list home>
                    <Sidebar_items_link href="/">Home</Sidebar_items_link>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items_link eleven>PUBLIC</Sidebar_items_link>
                    <Sidebar_items>
                        <Sidebar_items_link>Questions</Sidebar_items_link>
                    </Sidebar_items>
                    <Sidebar_items>
                        <Sidebar_items_link>Tags</Sidebar_items_link>
                    </Sidebar_items>
                    <Sidebar_items>
                        <Sidebar_items_link>Users</Sidebar_items_link>
                    </Sidebar_items>
                    <Sidebar_items>
                        <Sidebar_items_link>Companies</Sidebar_items_link>
                    </Sidebar_items>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items_link eleven>COLLECTIVES</Sidebar_items_link>

                    <Sidebar_items>
                        <Sidebar_items_link>Explore Collectives</Sidebar_items_link>
                    </Sidebar_items>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items_link eleven>TEAMS</Sidebar_items_link>
                    <Sidebar_items>
                        <Sidebar_items_link>Create free Team</Sidebar_items_link>
                    </Sidebar_items>
                </Sidebar_items_list>
            </Sidebar_items_container>
        </Sidebar_container>
    );
}

export default Leftsidebar;
