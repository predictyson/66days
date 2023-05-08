import styled from "styled-components";
import { theme } from "../../styles/theme";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/blogBox.png";
import Chart from "./Chart";
import { CheckCircleOutlined } from "@ant-design/icons";
export default function Todo() {
  const x = 20;
  return (
    <Container>
      <Title>Today's todo</Title>
      <TodoContianer>
        <TodoBox>
          <div className="left">
            <img src={Algo} alt="algo" />
            <div className="title">뽀삐의 알고리즘 뿌시기</div>
          </div>
          <div className="right">
            <CheckCircleOutlined
              style={{
                fontSize: "4rem",
                color: "#6CD3C0",
                // padding: "1.8rem",
                cursor: "pointer",
                display: "flex",
                justifyContent: "flex-end",
              }}
            />
            <Chart x={x} />
            <p>{x} / 66</p>
          </div>
        </TodoBox>
        <TodoBox>
          <div className="left">
            <img src={Blog} alt="blog" />
            <div className="title">해피의 CS 스터디</div>
          </div>
          <div className="right">
            <CheckCircleOutlined
              style={{
                fontSize: "4rem",
                color: "#D9D9D9",
                cursor: "pointer",
                display: "flex",
                justifyContent: "flex-end",
              }}
            />
            <Chart x={60} />
            <p>60 / 66</p>
          </div>
        </TodoBox>
      </TodoContianer>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 5% 2%;
  height: 55rem;
  display: flex;
  flex-direction: column;
`;

const Title = styled.div`
  font-size: 3.2rem;
  font-weight: bold;
`;
const TodoContianer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 3.2rem;
`;

const TodoBox = styled.div`
  width: 45%;
  height: 100%;
  border: solid 1px ${theme.colors.gray400};
  border-radius: 15px;
  display: flex;
  img {
    width: 70%;
    display: flex;
    margin: auto 0;
  }
  .left {
    width: 55%;
    display: flex;
    align-items: center;
    flex-direction: column;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.semibold};
  }
  .title {
    font-size: 90%;
    text-align: center;
    display: flex;
    margin: 0 auto;
    margin-bottom: auto;

    white-space: nowrap;
    text-overflow: ellipsis;
    border: solid 1px green;
  }
  .right {
    width: 45%;
    padding: 3rem;
    p {
      font-size: 2rem;
      text-align: center;
      font-weight: bold;
    }
  }
`;
