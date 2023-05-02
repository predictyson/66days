import React from "react";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/blogBox.png";
import GroupItem from "./GroupItem";
export default function Todo() {
  const x = 20;
  return (
    <Container>
      <Title>Groups</Title>
      <GroupContainer>
        <GroupItem />
        <GroupItem />
        <GroupItem />
        <GroupItem />
      </GroupContainer>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 7.6rem 8rem;
  height: 70rem;
  border: solid 1px black;
  display: flex;
  flex-direction: column;
`;

const Title = styled.div`
  font-size: 3.2rem;
  font-weight: bold;
`;
const GroupContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 3.2rem;
  background-color: lemonchiffon;
`;
