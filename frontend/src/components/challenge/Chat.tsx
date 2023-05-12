import { SendOutlined } from "@ant-design/icons";
import { Button, Form, Input, Space } from "antd";
import styled from "styled-components";
import Message from "./Message";
import useChat from "../../hooks/useChat";
import { useEffect, useRef } from "react";
import { mockMe } from "../../mock/challenge";

interface PropsType {
  challengeId: string;
}

export default function Chat(props: PropsType) {
  const user = mockMe; // TODO: replace with real user
  const { messages, handleSubmit, inputValue, setInputValue } = useChat(
    props.challengeId
  );
  const messagesEndRef = useRef<HTMLDivElement>(null);
  // const [msgValue, setMsgValue] = useState("");
  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  return (
    <StyledChat>
      <div className="chat">
        {messages.map((msg, idx) => (
          <Message
            key={idx}
            message={msg}
            isMe={msg.nickname === user.nickname}
          />
        ))}
        <div ref={messagesEndRef}></div>
      </div>
      <Form autoComplete="off" onFinish={() => handleSubmit()}>
        <Space.Compact style={{ width: "100%" }}>
          <Input
            placeholder="메시지를 입력해주세요..."
            style={{ fontSize: "1.6rem" }}
            value={inputValue}
            onChange={(event: any) => setInputValue(event.target.value)}
          />
          <Button
            htmlType="submit"
            type="primary"
            style={{ fontSize: "1.6rem", height: "100%" }}
            icon={<SendOutlined />}
            // onSubmit={() => sendMessage("hello")}
          >
            Send
          </Button>
        </Space.Compact>
      </Form>
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
