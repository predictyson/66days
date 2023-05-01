import React from "react";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import LandingGif1 from "../../assets/landing/landing1.gif";
export default function Landing1() {
  return (
    <>
      <Container>
        <LeftWrapper>
          <Title>
            성장하는 개발자가 되기 위한 <br /> <a>66일 </a>간의 여정
          </Title>

          <div className="subcontent" style={{ marginTop: "4.4rem" }}>
            알고리즘 풀이, 기술 블로그 포스팅 ...
            <br />
            작심 삼일의 계획에 그치진 않았나요?
          </div>
          <div className="subcontent">
            이제 <a>66days</a>가 도와줄게요 !
          </div>
          <JoinButton>66days 시작하기</JoinButton>
        </LeftWrapper>
        <GifWrapper>
          <img src={LandingGif1} alt="landing1" />
        </GifWrapper>
      </Container>
    </>
  );
}
const Container = styled.div`
  width: 100%;
  height: 68.8rem;
  display: flex;
  padding: 0 7.5rem;
  font-family: Pretendard;
`;

const Title = styled.div`
  margin-top: 15rem;
  font-size: 4.8rem;
  font-family: Pretendard;
  font-weight: ${theme.fontWeight.semibold};
`;
const LeftWrapper = styled.div`
  width: 60%;
  flex-direction: column;
  .subcontent {
    font-size: 2.4rem;
    color: ${theme.colors.gray500};
    margin-top: 2.2rem;
    font-weight: ${theme.fontWeight.medium};
  }
  a {
    color: ${theme.colors.purple};
    font-weight: bold;
  }
`;

const JoinButton = styled.div`
  margin-top: 4.4rem;
  width: 31rem;
  height: 6rem;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.4rem;
  border-radius: 20px;
  text-align: center;
  background-color: ${theme.colors.purple};
  color: white;
  font-weight: ${theme.fontWeight.semibold};
  transition: transform 0.3s ease-in-out;
  cursor: pointer;
  &:hover {
    transform: scale(1.1);
    background-color: ${theme.colors.gray100};
    color: ${theme.colors.purple};
    border: solid 1px ${theme.colors.purple};
  }
`;
const GifWrapper = styled.div`
  width: 40%;
  display: flex;
  align-items: center;
  justify-content: center;
`;
