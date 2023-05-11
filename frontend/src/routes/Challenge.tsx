import { Space, Typography } from "antd";
import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import { mockGraph, mockUsers } from "../mock/challenge";
import Title from "../components/challenge/Title";
import Chat from "../components/challenge/Chat";
import Member from "../components/challenge/Member";
import Graph from "../components/challenge/Graph";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function Challenge() {
  const { challengeId } = useParams();
  const [members] = useState(mockUsers);

  useEffect(() => {
    // TODO: fetch data from backend
    // setMessages(mockMessages);
    // setMembers(mockUsers);
  });

  return (
    <>
      <Title
        title="김태원의 Figma 정복"
        subtitle="김태원과 아이들의 피그마 정복기"
      />
      <StyledContainer>
        {challengeId && <Chat challengeId={challengeId} />}
        <Member users={members} />
      </StyledContainer>
      <Space style={{ alignItems: "baseline" }}>
        <Typography.Title style={{ fontSize: "2.4rem" }}>
          챌린지 그래프
        </Typography.Title>
        <Typography.Text
          style={{
            color: "#b8a9fb",
            fontSize: "2.4rem",
          }}
        >
          35
        </Typography.Text>
        <Typography.Text type="secondary" style={{ fontSize: "1.6rem" }}>
          일째
        </Typography.Text>
      </Space>
      {/* FIXME: freeze and design */}
      <StyledContainer>
        <Graph commits={mockGraph} />
        <StyledFreeze>
          <img src={FreezeIcon} width={50} height={50} />
          <span>0개</span>
        </StyledFreeze>
      </StyledContainer>
    </>
  );
}

const StyledContainer = styled.div`
  display: flex;
`;

const StyledFreeze = styled.section`
  flex: 1;
  display: flex;
  align-items: center;
  padding: 2.4rem;

  span {
    font-size: 2.4rem;
    margin-left: 1rem;
  }
`;
