import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import { mockStreak, mockUsers } from "../mock/challenge";
import Chat from "../components/challenge/Chat";
import Member from "../components/challenge/Member";
import { mockMessages } from "../mock/challenge";
// import useChat from "../hooks/useChat";
import { useEffect, useState } from "react";
import Graph from "../components/challenge/Graph";
import { DesktopOutlined } from "@ant-design/icons";

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
      <TitleWrapper>
        <Icon>
          <DesktopOutlined className="icon" />
        </Icon>
        <div>네트워크강의</div>
      </TitleWrapper>
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
          <span>2개</span>
        </StyledFreeze>
      </StyledContainer>
    </div>
  );
}

const TitleWrapper = styled.div`
  font-size: 3rem;
  font-weight: 700;
  display: flex;
  padding-bottom: 1rem;
`;

const Icon = styled.div`
  .icon {
    padding-right: 1rem;
  }
`;

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
    margin-left: 2rem;
  }
`;
