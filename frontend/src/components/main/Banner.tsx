import React from "react";
import styled from "styled-components";
import Capibara from "../../assets/main/Capibara.png";
import Badge from "../../assets/main/Bronze.png";
import CoinIcon from "../../assets/main/Coin.png";
import { theme } from "../../styles/theme";

export default function Banner() {
  return (
    <Container>
      <LeftWrapper>
        <Info>
          <img src={Badge} alt="badge" />
          <div className="email">yllydev@gmail.com</div>
        </Info>
        <Nickname>
          게으른 <br /> 카피바라
        </Nickname>
        <MoreInfo>
          <div className="exp">1500 EXP</div>
          <div className="button">한눈에 보기</div>
        </MoreInfo>
        <Coin>
          <img src={CoinIcon} alt="icon" />{" "}
          <span>{(32000 as number).toLocaleString()}</span>
        </Coin>
      </LeftWrapper>
      <ImageWrapper>
        <img src={Capibara} alt="capibara" />
      </ImageWrapper>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: 67.2rem;
  display: flex;
  font-family: Pretendard;
  background-color: black;
  padding: 0 5rem;
`;

const LeftWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
`;

const Info = styled.div`
  height: 4.8rem;
  margin: 0 auto;
  margin-top: 14.8rem;
  width: 80%;
  display: flex;
  align-items: center;
  .email {
    width: 23rem;
    background-color: #444444;
    opacity: 0.5;
    margin-left: 3.2rem;
    color: #ffffff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.6rem;
    border-radius: 10px;
    height: 4rem;
  }
`;

const Nickname = styled.div`
  color: white;
  width: 80%;
  margin: 0 auto;
  margin-top: 3.1rem;
  font-size: 5rem;
  font-weight: 700;
`;
const ImageWrapper = styled.div`
  display: flex;
  width: 50%;
  justify-content: center;
  align-items: center;
`;

const MoreInfo = styled.div`
  margin: 0 auto;
  margin-top: 3.4rem;
  width: 80%;
  display: flex;
  .button {
    border-radius: 5px;
    background-color: white;
    width: 11rem;
    height: 3.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.6rem;
    cursor: pointer;
    margin-left: 5rem;
    font-weight: ${theme.fontWeight.semibold};
    &:hover {
      background-color: #444444;
      color: white;
    }
  }

  .exp {
    color: white;
    font-size: 2.4rem;
    font-weight: bold;
  }
`;

const Coin = styled.div`
  font-size: 1.6rem;
  margin: 0 auto;
  width: 80%;
  margin-top: 3.4rem;
  color: white;
  display: flex;
  align-items: center;
  font-weight: ${theme.fontWeight.semibold};
  span {
    margin-left: 1rem;
  }
`;
