import { SendOutlined } from "@ant-design/icons";
import { Button, Input, Space } from "antd";
import styled from "styled-components";
import { mockMessages } from "../../mock/challenge";
import Message from "./Message";
import useChat from "../../hooks/useChat";

interface PropsType {
  messages: Array<(typeof mockMessages)[0]>;
}

export default function Chat(props: PropsType) {
  // TODO: const [messages, setMessages] = useState();
  const { sendMessage } = useChat();

  return (
    <StyledChat>
      <div className="chat">
        {props.messages.map((msg, idx) => (
          <Message key={idx} message={msg} />
        ))}
      </div>
      <Space.Compact>
        <Input placeholder="메시지를 입력해주세요..." />
        <Button type="primary" icon={<SendOutlined />} onClick={sendMessage}>
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
