import styled from "styled-components";
import { useContext } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStackOverflow } from "@fortawesome/free-brands-svg-icons";
import { Link } from "react-router-dom";
import UserContext from "./UserContext";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { useRef } from "react";
import Leftsidebar from "./Leftsidebar";

import useDetectClose from "./hooks/useDetectClose";

const StyledHeader = styled.header`
    position: sticky;
    top: 0;
    background-color: #393939;
    box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
    display: grid;
    grid-template-columns: 48px 220px 1fr 200px;
    grid-column-gap: 20px;
    @media screen and (max-width: 640px) {
        grid-template-columns: 48px 50px 1fr 200px;
        grid-column-gap: 0px;
    }
`;

export const LogoLink = styled(Link)`
    color: #fff;
    text-decoration: none;
    display: inline-block;
    height: 50px;
    line-height: 30px;
    padding: 0px 15px;
    svg {
        margin-top: 7px;
        display: inline-block;
        float: left;
    }
    span {
        display: inline-block;
        padding-left: 5px;
        padding-top: 10px;
        font-size: 1.2rem;
        font-weight: 300;
    }
    b {
        font-weight: normal;
        display: inline-block;
        margin-left: 2px;
    }
`;

const SearchInput = styled.input`
    display: inline-block;
    box-sizing: border-box;
    width: 100%;
    border-radius: 3px;
    border: 1px solid #777;
    background: rgba(0, 0, 0, 0.1);
    padding: 8px 10px;
    margin-top: 9px;
`;
const MobileInput = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding: 0px 10px 0px 0px;
    font-size: 18px;
    cursor: pointer;
`;

const ProfileLink = styled(Link)`
    color: #fff;
    padding: 0 10px;
    text-decoration: none;
    line-height: 50px;
`;
const Sidebars = styled.div`
    display:none;
    font-size: 20px;
    padding: 15px;
    align-items: center;
    cursor: pointer;
    /* width: 50px; */
    @media screen and (max-width: 640px) {
    display:flex;
    }
`;
const Desktop = styled.span`
    @media screen and (max-width: 640px) {
    display:none;
    }
`;
const Mobile = styled.span`
    display:none;
    @media screen and (max-width: 640px) {
    display:grid
    }
`;
const LSidebar = styled.div`
position: fixed;
  width: 160px;
  height: 100px;
  display:none;
  @media screen and (max-width: 640px) {
    display: block;
  }
`;
const ModalInput = styled.input`
    position: fixed;
    width: 91%;
    top: 50px;
    left: 0px;
    border-radius: 3px;
    border: 1px solid #777;
    background: rgba(45, 45, 45, 1);
    padding: 8px 10px;
    margin: 8px 12px;
    &:focus-within{
    border: 1px solid rgba(0, 103, 194, 0.4);
    box-shadow: 0 0 0 4px rgba(144, 203, 255, 0.4);
    border-radius: 3px;
  }
`;
const InputDropdown = styled.div`
    position: fixed;
    width: 100%;
    height: 50px;
    top: 50px;
    left: 0px;
    background-Color: rgb(64,66,69);
`;

function Header() {
    const dropDownsidebar = useRef();
    const dropDownSearchInput = useRef();
    const [isOpen, setIsOpen] = useDetectClose(dropDownsidebar, false);
    const [isSearchInputOpen, setIsSearchInputOpen] = useDetectClose(dropDownSearchInput, false);
    const { user } = useContext(UserContext);
    return (
        <StyledHeader>
            <div ref={dropDownsidebar}>
                <Sidebars onClick={() => setIsOpen(!isOpen)}><FontAwesomeIcon icon={faBars}  /></Sidebars>
                {isOpen && 
                // <Dropdown><LSidebar><Leftsidebar/></LSidebar></Dropdown>
                <LSidebar><Leftsidebar/></LSidebar>
                }
            </div>
            <Desktop>
                <LogoLink to="./" className="logo">
                    <FontAwesomeIcon icon={faStackOverflow} size="2x" />
                    <span>
                        Stack<b>overflow</b>
                    </span>
                </LogoLink>
            </Desktop>
            <Mobile>
                <LogoLink to="./" className="logo">
                    <FontAwesomeIcon icon={faStackOverflow} size="2x" />
                </LogoLink>
            </Mobile>
            <Desktop>
                <form action="" className="search">
                    <SearchInput type="text" placeholder="Search..."></SearchInput>
                </form>
            </Desktop>
            <Mobile ref={dropDownSearchInput}>
                <MobileInput>
                    <FontAwesomeIcon icon={faMagnifyingGlass} onClick={() => setIsSearchInputOpen(!isSearchInputOpen)} />
                </MobileInput>
                {isSearchInputOpen && 
                <InputDropdown><ModalInput type="text" placeholder="Search..."></ModalInput></InputDropdown>
                }
            </Mobile>
            {user && (
                <ProfileLink to={"./profile"} className="profile">
                    {user.email}
                </ProfileLink>
            )}
            {!user && (
                <div>
                    <ProfileLink to={"/login"} className="profile">
                        Log in
                    </ProfileLink>
                    <ProfileLink to={"/register"} className="profile">
                        Register
                    </ProfileLink>
                </div>
            )}
            
        </StyledHeader>
    );
}

export default Header;
