import React from "react";
import styled from "styled-components";
import HideLeftSidebar from "./HideLeftSidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleInfo } from "@fortawesome/free-solid-svg-icons";

const Facircleinfo = <FontAwesomeIcon icon={faCircleInfo}></FontAwesomeIcon>;
const Sidebar_container = styled.div`
    /* box-sizing: border-box; */
    display: flex;
    height: auto;
    width: auto;
    position: sticky;
    /* max-width: 164px; */
    top: 70px;
    justify-content: flex-end;
    padding: 10px 0;
    background-color: #2d2d2d;
`;
const Sidebar_items_container = styled.div`
    position: sticky;
    /* top: 80px; */
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
    background-color: ${(props) => (props.home ? "#3d3d3d" : "null")};
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
    color: #c4c8cc;
    background-color: #2d2d2d;
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
    if (HideLeftSidebar()) return null;
    return (
        <Sidebar_container>
            <Sidebar_items_container>
                <Sidebar_items_list home>
                    <Sidebar_items_link href="/">Home</Sidebar_items_link>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items_link eleven>PUBLIC</Sidebar_items_link>
                    <Sidebar_items>
                        <Sidebar_items_link href="/">Questions</Sidebar_items_link>
                    </Sidebar_items>
                    <Sidebar_items>
                        <Sidebar_items_link href="/tags">Tags</Sidebar_items_link>
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
                    <Sidebar_items_link>{Facircleinfo}</Sidebar_items_link>

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
