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
import { useNavigate } from "react-router";
// TODO: add an api to ask to join the group

export default function GroupIntro() {
  const [isApplied] = useState(false); // TODO: figure out whether a user applied or not
  const navigate = useNavigate();
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
                padding: 5,
              }}
            >
              <Typography.Text style={{ fontSize: "2.2rem" }}>
                그룹원
                <br /> 80명
              </Typography.Text>
              <Typography.Text style={{ fontSize: "2.2rem" }}>
                진행중
                <br /> 3건
              </Typography.Text>
              <Typography.Text style={{ fontSize: "2.2rem" }}>
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
                onClick={() => navigate(`/groups/3`)}
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
            <Card
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
                maxCount={3}
                size="large"
                maxStyle={{ color: "#f56a00", backgroundColor: "#fde3cf" }}
              >
                <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=3" />
                <Avatar style={{ backgroundColor: "#f56a00" }}>K</Avatar>
                <Avatar style={{ backgroundColor: "#f56a00" }}>J</Avatar>
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
                    border: "2px solid #6CD3C0",
                    fontSize: "1.6rem",
                    padding: "1rem 2rem",
                    fontWeight: "bold",
                  }}
                  color="#6CD3C0"
                >
                  알고리즘
                </Tag>
              </Space>
            </Card>

            {/** 2 */}
            <Card
              headStyle={{ border: 0 }}
              title={
                <Space
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <Typography.Text style={{ fontSize: "2rem" }}>
                    같이 기술 블로그 포스팅해요
                  </Typography.Text>
                  <Typography.Text type="danger" style={{ fontSize: "2rem" }}>
                    D-5
                  </Typography.Text>
                </Space>
              }
              bodyStyle={{ display: "flex", flexDirection: "column" }}
              style={{
                background: "#F6F6F6",
                boxShadow: "0px 4px 10px 2px rgba(0, 0, 0, 0.15)",
              }}
            >
              {" "}
              <Avatar.Group
                maxCount={2}
                size="large"
                maxStyle={{ color: "#f56a00", backgroundColor: "#fde3cf" }}
              >
                <Avatar style={{ backgroundColor: "green" }}>G</Avatar>

                <Avatar style={{ backgroundColor: "blue" }}>S</Avatar>
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
                  6/30 명
                </Tag>
                <Tag
                  style={{
                    backgroundColor: "transparent",
                    color: "#F37F3A",
                    border: "2px solid #F37F3A",
                    fontSize: "1.6rem",
                    padding: "1rem 2rem",
                    fontWeight: "bold",
                  }}
                  color="#F37F3A"
                >
                  기술 블로그
                </Tag>
              </Space>
            </Card>
            {/*3/ */}
            <Card
              headStyle={{ border: 0 }}
              title={
                <Space
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <Typography.Text style={{ fontSize: "2rem" }}>
                    인프런 완주하실분
                  </Typography.Text>
                  <Typography.Text type="danger" style={{ fontSize: "2rem" }}>
                    D-1
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
                maxCount={3}
                size="large"
                maxStyle={{ color: "#f56a00", backgroundColor: "#fde3cf" }}
              >
                <Avatar style={{ backgroundColor: "yellow" }}>B</Avatar>
                <Avatar style={{ backgroundColor: "green" }}>L</Avatar>
                <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=3" />
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
                  28 /30 명
                </Tag>
                <Tag
                  style={{
                    backgroundColor: "transparent",
                    color: "#B8A9FB",
                    border: "2px solid #B8A9FB",
                    fontSize: "1.6rem",
                    padding: "1rem 2rem",
                    fontWeight: "bold",
                  }}
                  color="#B8A9FB"
                >
                  강의 시청
                </Tag>
              </Space>
            </Card>
          </Space>
        </StyledChallenge>
        <StyledMember>
          <Row gutter={[4, 4]}>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "#6CD3C0",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>M</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  moonchi
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "#B8A9FB",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>예</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  예지
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "orange",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>P</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  PPoppi
                </Typography.Text>
              </Space>
            </Col>

            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "#FF8383",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h2>둥둥</h2>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  둥둥이
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "skyblue",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>H</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  Happy
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "##FBA9D6",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>C</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  Choco
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "lightgreen",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>코</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  코코
                </Typography.Text>
              </Space>
            </Col>
            <Col span={12}>
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
                  size={80}
                  style={{
                    backgroundColor: "lightgray",
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <h1>빈</h1>
                </Avatar>
                <Typography.Text style={{ width: 100, fontSize: "2.4rem" }}>
                  빈지노
                </Typography.Text>
              </Space>
            </Col>
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
// const DUMMY_INTRO = {
//   "group-name": "18반",
//   "challenge-list": [
//     {
//       groupChallengeId: 5,
//       challengeId: 3,
//       challengeContent: "3번그룹의 알고리즘 챌린지",
//       challengeName: "알고알고",
//       challengeTopic: "강의",
//       profileImagePathList: ["imageimage", "imageputput"],
//       maxMemberCount: 66,
//       memberCount: 2,
//       startAt: "2023-05-15",
//       endAt: "2023-07-20",
//       dday: "D + 4",
//     },
//     {
//       groupChallengeId: 6,
//       challengeId: 1,
//       challengeContent: "알고아록아로고",
//       challengeName: "알고하고싶어",
//       challengeTopic: "알고리즘",
//       profileImagePathList: [],
//       maxMemberCount: 5,
//       memberCount: 0,
//       startAt: "2023-05-15",
//       endAt: "2023-07-20",
//       dday: "D + 4",
//     },
//   ],
//   "group-image-path": "/groups/group.jpg",
//   progress: 2,
//   "group-members": [
//     {
//       image: "tobe",
//       nickname: "jingo1042",
//       badge: 0,
//       authority: "OWNER",
//     },
//     {
//       image: "/users/basic_profile.png",
//       nickname: "dzzxcvs",
//       badge: 0,
//       authority: "MANAGER",
//     },
//     {
//       image: "/users/basic_profile.png",
//       nickname: "권성은",
//       badge: 0,
//       authority: "MEMBER",
//     },
//     {
//       image: "imageputput",
//       nickname: "test",
//       badge: 0,
//       authority: "MANAGER",
//     },
//   ],
//   completed: 0,
//   "group-members-count": 4,
// };
