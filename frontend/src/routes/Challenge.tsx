import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import { mockStreak, mockUsers } from "../mock/challenge";
import Title from "../components/challenge/Title";
import Chat from "../components/challenge/Chat";
import Member from "../components/challenge/Member";
import { mockMessages } from "../mock/challenge";
// import useChat from "../hooks/useChat";
import { useEffect, useState } from "react";
import Graph from "../components/challenge/Graph";

const TEMP_TOTAL_PARTICIPANTS = 3;
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
      {/* <Space style={{ alignItems: "baseline", textAlign: "center" }}>
        <Typography.Title level={2}>
          챌린지 그래프
          <Typography.Text
            style={{
              color: "#b8a9fb",
            }}
          >
            35
          </Typography.Text>
          <Typography.Text type="secondary">일째</Typography.Text>
        </Typography.Title>
      </Space> */}
      {/* FIXME: freeze and design */}
      <StyledContainer>
        <Graph
          commits={mockStreak.map((el) => ({
            checked: el.count % TEMP_TOTAL_PARTICIPANTS === 0,
            freeze: el.count % TEMP_TOTAL_PARTICIPANTS !== 0,
          }))}
        />
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
  justify-content: center;
  align-items: center;
  padding: 2rem;

  span {
    font-size: 2.4rem;
    margin-left: 1rem;
  }
`;
