import { Space, Typography } from "antd";
import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import { mockGraph, mockUsers } from "../mock/challenge";
import Title from "../components/challenge/Title";
import Chat from "../components/challenge/Chat";
import Member from "../components/challenge/Member";
import Graph from "../components/challenge/Graph";
import { mockMessages } from "../mock/challenge";
// import useChat from "../hooks/useChat";
import { useEffect, useState } from "react";

export default function Challenge() {
  const [messages] = useState(mockMessages);
  const [members] = useState(mockUsers);
  // TODO: const { sendMessage } = useChat("123", "tw", setMessage);

  useEffect(() => {
    // TODO: fetch data from backend
    // setMessages(mockMessages);
    // setMembers(mockUsers);
  });

  return (
    <div style={{ marginInline: "8rem" }}>
      <Title
        title="김태원의 Figma 정복"
        subtitle="김태원과 아이들의 피그마 정복기"
      />
      <StyledContainer>
        <Chat messages={messages} />
        <Member users={members} />
      </StyledContainer>
      <Space style={{ alignItems: "baseline" }}>
        <Typography.Title level={2}>챌린지 그래프</Typography.Title>
        <Typography.Text
          style={{
            color: "#b8a9fb",
          }}
        >
          35
        </Typography.Text>
        <Typography.Text type="secondary">일째</Typography.Text>
      </Space>
      {/* FIXME: freeze and design */}
      <StyledContainer>
        <Graph commits={mockGraph} />
        <StyledFreeze>
          <img src={FreezeIcon} width={50} height={50} />
          <span>0개</span>
        </StyledFreeze>
      </StyledContainer>
    </div>
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
  border: 1px solid #dddddd;
  border-radius: 8px;

  span {
    font-size: 2.4rem;
    margin-left: 1rem;
  }
`;
