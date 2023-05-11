import { UserOutlined } from "@ant-design/icons";
import { Avatar, Space, Typography } from "antd";
import TextArea from "antd/es/input/TextArea";

interface PropsType {
  message: MessageType;
  isMe: boolean;
}

export default function Message(props: PropsType) {
  return (
    <div className={`chat__message ${props.isMe && "chat__message--me"}`}>
      <Avatar icon={<UserOutlined />} />
      <Space direction="vertical" style={{ marginInline: "1rem" }}>
        <Typography.Text style={{ fontWeight: "bold", fontSize: "1.6rem" }}>
          {props.message.nickname}
        </Typography.Text>
        <TextArea
          style={{ width: "100%", fontSize: "1.6rem" }}
          size="large"
          autoSize
          value={props.message.value}
          disabled
        />
      </Space>
      <Typography.Text type="secondary" style={{ marginTop: "auto" }}>
        {props.message.date}
      </Typography.Text>
    </div>
  );
}
