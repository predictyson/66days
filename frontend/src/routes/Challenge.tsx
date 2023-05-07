import {
  SendOutlined,
  TeamOutlined,
  UserOutlined,
  YoutubeFilled,
} from "@ant-design/icons";
import { Avatar, Button, Checkbox, Input, Space, Typography } from "antd";
import TextArea from "antd/es/input/TextArea";
import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import FlagIcon from "../assets/flag.svg";
import { mockGraph } from "../mock/challenge";

export default function Challenge2() {
  return (
    <>
      <Space direction="vertical" style={{ marginBottom: "1rem" }}>
        <Typography.Title>
          <YoutubeFilled style={{ fontSize: "4rem", marginRight: "1rem" }} />
          김태원의 Figma 정복
        </Typography.Title>
        <Typography.Text type="secondary">
          김태원과 아이들의 피그마 정복기
        </Typography.Text>
      </Space>
      <StyledContainer>
        <StyledChat>
          <div className="chat">
            <div className="chat__message">
              <Avatar icon={<UserOutlined />} />
              <Space direction="vertical" style={{ marginInline: "1rem" }}>
                <Typography.Text style={{ fontWeight: "bold" }}>
                  뭉치
                </Typography.Text>
                <TextArea
                  style={{ width: "100%" }}
                  size="large"
                  autoSize
                  value="피그마는 어때요?asl;dkfjaskdjf;lskadjflka askldfj aslkfj asdlkfj 피그마는 어때요?  askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj "
                  disabled
                />
              </Space>
              <Typography.Text type="secondary" style={{ marginTop: "auto" }}>
                오후 8:30
              </Typography.Text>
            </div>
            <div className="chat__message">
              <Avatar icon={<UserOutlined />} />
              <Space direction="vertical" style={{ marginInline: "1rem" }}>
                <Typography.Text style={{ fontWeight: "bold" }}>
                  뭉치
                </Typography.Text>
                <TextArea
                  style={{ width: "100%" }}
                  size="large"
                  autoSize
                  value="피그마는 어때요?asl;dkfjaskdjf;lskadjflka askldfj aslkfj asdlkfj 피그마는 어때요?  askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj "
                  disabled
                />
              </Space>
              <Typography.Text type="secondary" style={{ marginTop: "auto" }}>
                오후 8:30
              </Typography.Text>
            </div>
            <div className="chat__message chat__message--me">
              <Avatar icon={<UserOutlined />} />
              <Space direction="vertical" style={{ marginInline: "1rem" }}>
                <Typography.Text style={{ fontWeight: "bold" }}>
                  뭉치
                </Typography.Text>
                <TextArea
                  style={{ width: "100%" }}
                  size="large"
                  autoSize
                  value="피그마는 어때요?asl;dkfjaskdjf;lskadjflka askldfj aslkfj asdlkfj 피그마는 어때요?  askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj "
                  disabled
                />
              </Space>
              <Typography.Text type="secondary" style={{ marginTop: "auto" }}>
                오후 8:30
              </Typography.Text>
            </div>
          </div>
          <Space.Compact>
            <Input placeholder="메시지를 입력해주세요..." />
            <Button type="primary" icon={<SendOutlined />}>
              Send
            </Button>
          </Space.Compact>
        </StyledChat>
        <StyledMember>
          <Typography.Title level={3}>
            <TeamOutlined style={{ fontSize: "2rem" }} />
            구성원(3인)
          </Typography.Title>
          <Space style={{ width: "100%", justifyContent: "space-between" }}>
            <Typography.Text
              ellipsis={{
                tooltip:
                  " 피그마마스터김태원 asldkj asl;kfdj ;askldjf ;laskdf j;aslkd f asdl;fkj as;dlfkj as;kldfj as;dklfj",
              }}
              style={{ width: "10rem" }}
            >
              <Avatar icon={<UserOutlined />} style={{ marginRight: "1rem" }} />
              피그마마스터김태원 asldkj asl;kfdj ;askldjf ;laskdf j;aslkd f
              asdl;fkj as;dlfkj as;kldfj as;dklfj
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
        </StyledMember>
      </StyledContainer>
      <Space style={{ alignItems: "baseline" }}>
        <Typography.Title level={2}>챌린지 그래프</Typography.Title>
        <Typography.Text
          style={{
            color: "#b8a9fb",
          }}
        >
          35
        </Typography.Text>
        <Typography.Text type="secondary">일째</Typography.Text>
      </Space>
      {/* FIXME: freeze and design */}
      <StyledContainer>
        <StyledGraph>
          <div className="graph">
            {mockGraph.map((el, i) => (
              <Checkbox
                key={i}
                indeterminate={el.freeze}
                onChange={() => {}}
                checked={el.checked}
              />
            ))}
          </div>
          <img className="flag" src={FlagIcon} />
        </StyledGraph>
        <StyledFreeze>
          <img src={FreezeIcon} width={50} height={50} />
          <span>0개</span>
        </StyledFreeze>
      </StyledContainer>
    </>
  );
}

const StyledContainer = styled.div`
  display: flex;
`;

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
      }
    }
  }
`;

const StyledGraph = styled.section`
  flex: 3;
  display: flex;
  padding: 2.4rem;
  border: 1px solid #dddddd;
  border-radius: 8px;
  margin-right: 3.2rem;

  .graph {
    flex: 1;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    /* max-height: 4rem; */
    height: 8rem;
    margin: 0;

    label {
      margin: 0;
    }
  }

  .flag {
    width: 8rem;
    height: 8rem;
    display: block;
  }

  .ant-checkbox-inner {
    background-color: #b8a9fb;
    border-color: transparent;
  }
  .ant-checkbox-inner:after {
    border: 0;
  }
  .ant-checkbox-indeterminate {
    .ant-checkbox-inner:after {
      background-color: #6854c4;
    }
  }
`;

const StyledFreeze = styled.section`
  flex: 1;
  display: flex;
  align-items: center;
  padding: 2.4rem;
  border: 1px solid #dddddd;
  border-radius: 8px;

  span {
    font-size: 2.4rem;
    margin-left: 1rem;
  }
`;
