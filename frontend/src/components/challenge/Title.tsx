import { YoutubeFilled } from "@ant-design/icons";
import { Space, Typography } from "antd";

interface PropsType {
  title: string;
  subtitle: string;
}
export default function Title(props: PropsType) {
  return (
    <Space direction="vertical" style={{ marginBottom: "1rem" }}>
      <Typography.Title>
        <YoutubeFilled style={{ fontSize: "4rem", marginRight: "1rem" }} />
        {props.title}
      </Typography.Title>
      <Typography.Text type="secondary">{props.subtitle}</Typography.Text>
    </Space>
  );
}
