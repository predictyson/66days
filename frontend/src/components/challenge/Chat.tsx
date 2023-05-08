import { SendOutlined } from "@ant-design/icons";
import { Button, Input, Space } from "antd";
import styled from "styled-components";
import { messages } from "../../mock/challenge";
import Message from "./Message";

export default function Chat() {
  return (
    <StyledChat>
      <div className="chat">
        {messages.map((msg) => (
          <Message message={msg} />
        ))}
      </div>
      <Space.Compact>
        <Input placeholder="메시지를 입력해주세요..." />
        <Button type="primary" icon={<SendOutlined />}>
          Send
        </Button>
      </Space.Compact>
    </StyledChat>
  );
}

const StyledChat = styled.section`
  flex: 3;
  display: flex;
  flex-direction: column;
  background-color: #f7f5ff;
  height: 40rem;
  padding: 2rem;

  .chat {
    flex: 1;
    overflow-y: scroll;
    margin-bottom: 2rem;

    .chat__message--me {
      flex-direction: row-reverse;
      text-align: right;
    }

    .chat__message {
      display: flex;
      textarea:disabled {
        background-color: white;
        color: black;
        cursor: inherit;
      }
    }
  }
`;
