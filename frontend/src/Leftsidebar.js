import React from "react";
import styled from "styled-components";
import HideLeftSidebar from "./HideLeftSidebar";
import { ReactComponent as AlertCircle } from "./icons/AlertCircle.svg";
import { ReactComponent as Earth } from "./icons/Earth.svg";
import { ReactComponent as Star } from "./icons/Star.svg";
import { ReactComponent as Briefcase } from "./icons/briefcase.svg";

const Sidebar_container = styled.div`
    position: sticky;
    display: flex;
    height: auto;
    width: auto;
    top: 70px;
    justify-content: flex-end;
    margin-top: 15px;
    padding: 5px 0;
    background-color: #2d2d2d;
`;
const Sidebar_items_container = styled.div`
    /* position: sticky; */
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
`;
const Sidebar_items = styled.li`
    display: flex;
    padding: 10px 0;
    padding-left: 22px;
    border-style: solid;
    border-width: ${(props) => (props.home ? "0 3px 0 0" : "null")};
    border-color: ${(props) => (props.home ? "orange" : "null")};
    color: #c4c8cc;
    align-items: center;
    cursor: pointer;
`;
const Sidebar_items_title = styled.li`
    display: flex;
    justify-content: space-between;
`;
const Sidebar_items_emoji = styled.li`
    display: flex;
    padding: 10px 0;
    color: #c4c8cc;
    align-items: center;
    justify-content: space-between;
    margin: ${(props) => (props.question ? "0 53px 0 0" : props.Team ? "0 10px 0 0" : "")};
`;
const Sidebar_items_link = styled.a`
    font-size: ${(props) => (props.eleven ? "11px" : "13px")};
    text-decoration: none;
    color: inherit;
    :hover {
        color: #ffffff;
    }
    margin-right: 5px;
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
                    <Sidebar_items_emoji question>
                        <Earth />
                        <Sidebar_items_link href="/">Questions</Sidebar_items_link>
                    </Sidebar_items_emoji>
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
                    <Sidebar_items_title>
                        <Sidebar_items_link eleven>COLLECTIVES</Sidebar_items_link>
                        <Sidebar_items_link>
                            <AlertCircle width="12" height="12" />
                        </Sidebar_items_link>
                    </Sidebar_items_title>

                    <Sidebar_items_emoji>
                        <Star width="15" height="15" />
                        <Sidebar_items_link>Explore Collectives</Sidebar_items_link>
                    </Sidebar_items_emoji>
                </Sidebar_items_list>
                <Sidebar_items_list>
                    <Sidebar_items_title>
                        <Sidebar_items_link eleven>TEAMS</Sidebar_items_link>
                        <Sidebar_items_link>
                            <AlertCircle width="12" height="12" />
                        </Sidebar_items_link>
                    </Sidebar_items_title>
                    <Sidebar_items_emoji Team>
                        <Briefcase />
                        <Sidebar_items_link>Create free Team</Sidebar_items_link>
                    </Sidebar_items_emoji>
                </Sidebar_items_list>
            </Sidebar_items_container>
        </Sidebar_container>
    );
}

export default Leftsidebar;
