import {
  Avatar,
  Button,
  Card,
  Col,
  Row,
  Space,
  Tag,
  Tooltip,
  Typography,
} from "antd";
import GroupIntroImage from "../assets/group/group-intro.svg";
import { AntDesignOutlined, UserOutlined } from "@ant-design/icons";
import styled from "styled-components";
import { useState } from "react";

// TODO: add an api to ask to join the group

export default function GroupIntro() {
  const [isApplied] = useState(false); // TODO: figure out whether a user applied or not

  return (
    <div style={{ marginInline: "8rem" }}>
      <Typography.Title style={{ fontSize: "3.2rem" }}>
        Group
        <br /> Introduction
      </Typography.Title>
      <StyledContainer>
        <StyledImage>
          <img
            src={GroupIntroImage}
            style={{ width: "100%", height: "100%" }}
          />
        </StyledImage>
        <StyledInfo>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
              height: "100%",
            }}
          >
            <Card
              style={{ width: "80%", margin: "auto", marginTop: "2rem" }}
              bodyStyle={{
                display: "flex",
                justifyContent: "space-around",
                padding: 0,
              }}
            >
              <Typography.Text style={{ fontSize: "2.4rem" }}>
                그룹원
                <br /> 80명
              </Typography.Text>
              <Typography.Text style={{ fontSize: "2.4rem" }}>
                진행중
                <br /> 3건
              </Typography.Text>
              <Typography.Text style={{ fontSize: "2.4rem" }}>
                완료
                <br /> 10건
              </Typography.Text>
            </Card>
            <Space
              direction="vertical"
              style={{ width: "80%", margin: "auto" }}
            >
              <Typography.Title style={{ fontSize: "4.8rem" }}>
                뭉치뭉치똥뭉치네
              </Typography.Title>
              <Typography.Text style={{ fontSize: "2.4rem" }}>
                뭉치의 친구들로 이루어진 뭉치뭉치똥뭉치네 공간입니다.
              </Typography.Text>
              <Button
                size="large"
                style={{
                  fontSize: "2rem",
                  backgroundColor: !isApplied ? "black" : "#7A7A7A",
                  height: "inherit",
                  color: "white",
                  marginBottom: "2rem",
                }}
              >
                {!isApplied ? "그룹 가입 신청" : "신청 취소"}
              </Button>
            </Space>
          </div>
        </StyledInfo>
      </StyledContainer>
      <StyledContainer>
        <StyledChallenge>
          <Typography.Title
            level={3}
            style={{ textAlign: "center", fontSize: "2.4rem" }}
          >
            챌린지 리스트
          </Typography.Title>
          <Space
            direction="vertical"
            size="large"
            style={{ display: "flex", padding: "4rem" }}
          >
            {new Array(4).fill(null).map((_, idx) => (
              <Card
                key={idx}
                headStyle={{ border: 0 }}
                title={
                  <Space
                    style={{ display: "flex", justifyContent: "space-between" }}
                  >
                    <Typography.Text style={{ fontSize: "2rem" }}>
                      1일 1백준 풀어봅시다.
                    </Typography.Text>
                    <Typography.Text type="danger" style={{ fontSize: "2rem" }}>
                      D-3
                    </Typography.Text>
                  </Space>
                }
                bodyStyle={{ display: "flex", flexDirection: "column" }}
                style={{
                  background: "#F6F6F6",
                  boxShadow: "0px 4px 10px 2px rgba(0, 0, 0, 0.15)",
                }}
              >
                <Avatar.Group
                  maxCount={2}
                  size="large"
                  maxStyle={{ color: "#f56a00", backgroundColor: "#fde3cf" }}
                >
                  <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=3" />
                  <Avatar style={{ backgroundColor: "#f56a00" }}>K</Avatar>
                  <Tooltip title="Ant User" placement="top">
                    <Avatar
                      style={{ backgroundColor: "#87d068" }}
                      icon={<UserOutlined />}
                    />
                  </Tooltip>
                  <Avatar
                    style={{ backgroundColor: "#1890ff" }}
                    icon={<AntDesignOutlined />}
                  />
                  <Avatar
                    style={{ backgroundColor: "#1890ff" }}
                    icon={<AntDesignOutlined />}
                  />
                  <Avatar
                    style={{ backgroundColor: "#1890ff" }}
                    icon={<AntDesignOutlined />}
                  />
                </Avatar.Group>
                <Space
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    paddingTop: "1rem",
                  }}
                >
                  <Tag
                    color="#5F6F94"
                    style={{ fontSize: "1.6rem", padding: "1rem 2rem" }}
                  >
                    12 /30 명
                  </Tag>
                  <Tag
                    style={{
                      backgroundColor: "transparent",
                      color: "#6CD3C0",
                      border: "1px solid #6CD3C0",
                      fontSize: "1.6rem",
                      padding: "1rem 2rem",
                    }}
                    color="#6CD3C0"
                  >
                    알고리즘
                  </Tag>
                </Space>
              </Card>
            ))}
          </Space>
        </StyledChallenge>
        <StyledMember>
          <Row gutter={[4, 4]}>
            {new Array(8).fill(null).map((_, idx) => (
              <Col key={idx} span={12}>
                <Space
                  direction="vertical"
                  style={{
                    width: "100%",
                    aspectRatio: "1/1",
                    justifyContent: "center",
                    alignItems: "center",
                    background: "#F6F6F6",
                  }}
                >
                  <Avatar
                    size={100}
                    src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=3"
                  />
                  <Typography.Text
                    ellipsis={{
                      tooltip:
                        "@barabara;alskdjflk;asfjsadl;fjasdkl;jflk;asjdklf;jaslk;djfdlks;aj",
                    }}
                    style={{ width: 100, fontSize: "2.4rem" }}
                  >
                    @barabara;alskdjflk;asfjsadl;fjasdkl;jflk;asjdklf;jaslk;djfdlks;aj
                  </Typography.Text>
                </Space>
              </Col>
            ))}
          </Row>
        </StyledMember>
      </StyledContainer>
    </div>
  );
}

const StyledContainer = styled.div`
  display: flex;
`;

const StyledImage = styled.section`
  flex: 1;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

const StyledInfo = styled.section`
  flex: 1;
  background-color: #dbff73;

  .infoCtn {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 5rem;
    height: 100%;
  }
`;

const StyledChallenge = styled.section`
  flex: 1;
`;

const StyledMember = styled.section`
  flex: 1;
  background-color: ${(props) => props.theme.colors.gray300};
`;
