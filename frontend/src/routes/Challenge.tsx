import { SendOutlined, TeamOutlined, UserOutlined } from "@ant-design/icons";
import YoutubeFilled from "@ant-design/icons/lib/icons/YoutubeFilled";
import { Avatar, Button, Checkbox, Input } from "antd";
import styled from "styled-components";
import FreezeIcon from "../assets/freeze.svg";
import FlagIcon from "../assets/flag.svg";

const mockGraph = [
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: true, freeze: false },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: true, freeze: false },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: true, freeze: false },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: true, freeze: false },
  { checked: false, freeze: true },
  { checked: false, freeze: true },
  { checked: true, freeze: false },
  { checked: false, freeze: true },
];

export default function Challenge() {
  return (
    <>
      <StyledTitle>
        <h1>
          <YoutubeFilled style={{ fontSize: "4rem", marginRight: "1rem" }} />
          김태원의 Figma 정복
        </h1>
        <div>김태원과 아이들의 피그마 정복기</div>
      </StyledTitle>
      <StyledContainer>
        <StyledChat>
          <div className="chat">
            <div className="chat__message">
              <Avatar icon={<UserOutlined />} />
              <div className="content">
                <div>뭉치</div>
                <p>
                  피그마는 어때요? askldfj aslkfj asdlkfj 피그마는 어때요?
                  askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj
                  피그마는 어때요? askldfj aslkfj asdlkfj
                </p>
              </div>
              <div className="time">오후 8:30</div>
            </div>
            <div className="chat__message chat__message--me">
              <Avatar icon={<UserOutlined />} />
              <div className="content">
                <div>뭉치</div>
                <p>
                  피그마는 어때요? askldfj aslkfj asdlkfj 피그마는 어때요?
                  askldfj aslkfj asdlkfj 피그마는 어때요? askldfj aslkfj asdlkfj
                  피그마는 어때요? askldfj aslkfj asdlkfj
                </p>
              </div>
              <div className="time">오후 8:30</div>
            </div>
            <div className="chat__message chat__message--me">
              <Avatar icon={<UserOutlined />} />
              <div className="content">
                <div>뭉치</div>
                <p>피그마는 어때요?</p>
              </div>
              <div className="time">오후 8:30</div>
            </div>
          </div>
          <div className="send">
            <Input placeholder="메시지를 입력해주세요..." />
            <Button icon={<SendOutlined />}>Send</Button>
          </div>
        </StyledChat>
        <StyledMember>
          <div className="title">
            <TeamOutlined style={{ fontSize: "2rem" }} />
            구성원(3인)
          </div>
          <div className="content">
            <div>
              <Avatar icon={<UserOutlined />} style={{ marginRight: "1rem" }} />
              {"피그마마스터김태원 asldkj asl;kfdj ;askldjf ;laskdf j;aslkd f asdl;fkj as;dlfkj  as;kldfj as;dklfj "
                .substring(0, 10)
                .concat("...")}
            </div>
            <Checkbox
              onChange={() => {}}
              style={{
                flexDirection: "row-reverse",
                justifyContent: "space-between",
              }}
              checked
            />
          </div>
        </StyledMember>
      </StyledContainer>
      <StyledTitle>
        <h2 className="title">
          챌린지 그래프 <span className="title__days">35</span>
          <span className="title__desc">일째</span>
        </h2>
      </StyledTitle>
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

const StyledContainer = styled.div`
  display: flex;
  margin-bottom: 4rem;
`;

const StyledMember = styled.section`
  flex: 1;
  background-color: #f6f6f6;
  border-radius: 8px;
  min-height: 40rem;
  padding: 2rem;
  font-size: 1.6rem;

  .title {
    margin-bottom: 1.4rem;
  }

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
  margin-right: 3.2rem;
  display: flex;
  flex-direction: column;
  background-color: #f7f5ff;
  border-radius: 8px;
  min-height: 40rem;
  padding: 2rem;

  .chat {
    flex: 1;

    .chat__message--me {
      flex-direction: row-reverse;
      text-align: right;
    }

    .chat__message {
      display: flex;
      font-size: 1.6rem;

      .content {
        max-width: 40vw;
        margin: 0 1rem;
        font-size: 1.6rem;
        font-weight: bold;

        p {
          font-weight: normal;
          display: inline-block;
          padding: 1rem;
          line-height: 2.4rem;
          background-color: #fff;
          border: 1px solid rgba(0, 0, 0, 0.05);
          border-radius: 8px;
          box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.05);
        }
      }
      .time {
        color: #aaaaaa;
        vertical-align: bottom;
        margin-top: auto;
      }
    }
  }

  .send {
    display: flex;
    width: 80%;
    margin: 2.4rem auto;

    input {
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.05);
    }

    button {
      margin-left: 1.6rem;
      background-color: #6350b6;
      color: white;
    }
  }
`;

const StyledTitle = styled.section`
  margin-top: 4rem;
  margin-bottom: 1.6rem;
  h1 {
    font-size: 3.2rem;
    font-family: "Kanit-Bold";
    margin: 0;
  }
  h2.title {
    font-size: 2.4rem;
    font-family: "Kanit-Bold";

    .title__days {
      color: #b8a9fb;
    }
    .title__desc {
      font-size: 1.6rem;
    }
  }
  div {
    font-size: 1.6rem;
    margin-left: 5rem;
    color: #b4b4b4;
  }
`;
