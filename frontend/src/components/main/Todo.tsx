import React from "react";
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
            <img
              src={Algo}
              alt="algo"
              style={{
                width: "24rem",
                height: "24rem",
                marginTop: "4.2rem",
              }}
            />
            <p>뽀삐의 알고리즘 뿌시기</p>
          </div>
          <div className="right">
            <CheckCircleOutlined
              style={{
                fontSize: "4.8rem",
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
            <img
              src={Blog}
              alt="blog"
              style={{
                width: "24rem",
                height: "24rem",
                marginTop: "4.2rem",
              }}
            />
            <p>해피의 CS 스터디</p>
          </div>
          <div className="right">
            <CheckCircleOutlined
              style={{
                fontSize: "4.8rem",
                color: "#D9D9D9",
                // padding: "1.8rem",
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
  padding: 7.6rem 8rem;
  height: 70rem;
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
  width: 55.7rem;
  height: 38.3rem;
  border: solid 1px ${theme.colors.gray400};
  border-radius: 15px;
  display: flex;
  .left {
    width: 55%;
    display: flex;
    align-items: center;
    flex-direction: column;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.semibold};
  }
  .right {
    width: 45%;
    padding: 3rem;
    p {
      font-size: 2rem;
      text-align: center;
      font-weight: 800;
    }
  }
`;
