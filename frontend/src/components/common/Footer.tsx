import { Space, Typography } from "antd";
import { Footer } from "antd/es/layout/layout";
import styled from "styled-components";

export default function CustomFooter() {
  return (
    <StyledFooter>
      <Space style={{ marginRight: "3rem" }}>
        <Typography.Text>성장하는 개발자가 되기 위한</Typography.Text>
        <Typography.Title level={3}>66 Days</Typography.Title>
        <Typography.Text>간의 여정 </Typography.Text>
      </Space>
      <Space style={{ marginRight: "3rem" }}>
        <Typography.Text>made by</Typography.Text>
        <Typography.Title level={3}>A705</Typography.Title>
        <Typography.Text>FE - 김태원 성다연 손예지</Typography.Text>
        <Typography.Text>BE - 권성은 김진호 박귀렬</Typography.Text>
      </Space>
      <Space>
        <Typography.Text>in </Typography.Text>
        <Typography.Title level={3}>SSAFY</Typography.Title>
      </Space>
    </StyledFooter>
  );
}

const StyledFooter = styled(Footer)`
  display: flex;
  justify-content: center;
  color: #fff;
  background-color: #6d6d6d;
  padding-inline: 5rem;

  h1,
  h2,
  h3 {
    margin: 0;
    color: white;
  }

  span {
    color: white;
  }
`;
