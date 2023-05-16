import { UserOutlined } from "@ant-design/icons";
import { Avatar, Space, Typography } from "antd";
import TextArea from "antd/es/input/TextArea";

interface PropsType {
  message: {
    nickname: string;
    profile: string;
    value: string;
    date: string;
    me: boolean;
  };
}

export default function Message(props: PropsType) {
  return (
    <div className={`chat__message ${props.message.me && "chat__message--me"}`}>
      {props.message.profile ? (
        <Avatar src={props.message.profile} />
      ) : (
        <Avatar icon={<UserOutlined />} />
      )}
      <Space direction="vertical" style={{ marginInline: "1rem" }}>
        <Typography.Text style={{ fontWeight: "bold" }}>
          {props.message.nickname}
        </Typography.Text>
        <TextArea
          style={{ width: "100%" }}
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
