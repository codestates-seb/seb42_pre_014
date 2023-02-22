import React from "react";
import styled from "styled-components";

const Sidebar_container = styled.div`
  display: flex;
  height: auto;
  width: 20%;
  position: sticky;
  top: 0;
  justify-content: flex-end;
  padding: 10px 0;
  background-color: #383838;
  border-style: solid;
  border-width: 0 1px 0 0;
  border-color: #535353;
`;
const Sidebar_item = styled.div`
  color: #ffffff;
`;

function Leftsidebar() {
  return (
    <Sidebar_container>
      <div>Home</div>
    </Sidebar_container>
  );
}

export default Leftsidebar;
