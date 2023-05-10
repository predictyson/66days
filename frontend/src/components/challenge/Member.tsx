import { TeamOutlined, UserOutlined } from "@ant-design/icons";
import { Avatar, Checkbox, Space, Typography } from "antd";
import styled from "styled-components";

interface PropsType {
  users: {
    name: string;
    profile: string;
  }[];
}

export default function Member(props: PropsType) {
  return (
    <StyledMember>
      <Typography.Title level={3}>
        <TeamOutlined style={{ fontSize: "2rem" }} />
        구성원({props.users.length}인)
      </Typography.Title>
      {props.users.map((user, idx) => (
        <Space key={idx} style={{ justifyContent: "space-between" }}>
          <Typography.Text
            ellipsis={{
              tooltip: `${user.name}`,
            }}
            style={{ width: "100px" }}
          >
            <Avatar
              src={user.profile}
              icon={<UserOutlined />}
              style={{ marginRight: "1rem" }}
            />
            {user.name}
          </Typography.Text>
          <Checkbox
            onChange={() => {}}
            style={{
              flexDirection: "row-reverse",
              justifyContent: "space-between",
            }}
            checked
          />
        </Space>
      ))}
    </StyledMember>
  );
}

const StyledMember = styled.section`
  flex: 1;
  background-color: #f6f6f6;
  padding: 2rem;

  .content {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .ant-checkbox-inner {
      background-color: #6cd3c0;
      border-color: transparent;
      color: #6cd3c0;
    }
  }
`;
