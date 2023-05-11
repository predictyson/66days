import styled from "styled-components";
import { theme } from "../../styles/theme";
import Vector from "../../assets/landing/landing4_vector.png";
import View from "../../assets/landing/landing4_view.png";
import LandingGif3 from "../../assets/landing/landing 3.gif";
export default function Landing1() {
  return (
    <>
      <Container>
        <GifWrapper>
          <img src={LandingGif3} alt="landing1" />
        </GifWrapper>
        <LeftWrapper>
          <Title>나의 챌린지와 스트릭 관리</Title>
          <ViewImg src={View} alt="view" />
        </LeftWrapper>
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
  @media (max-width: 1200px) {
    height: 75rem;
  }
  @media (max-width: 800px) {
    height: 65rem;
  }
  @media (max-width: 600px) {
    height: 50rem;
  }
`;

const Title = styled.div`
  margin-top: 8rem;
  font-size: 3rem;
  font-weight: ${theme.fontWeight.bold};
`;
const ViewImg = styled.img`
  width: 90%;
  margin: 4rem auto;
  display: flex;
`;
const LeftWrapper = styled.div`
  width: 55%;
  padding: 0 8%;
  @media (max-width: 1200px) {
    width: 100%;
    img {
      /* height: 70%; */
      /* width: 50%; */
    }
  }
  img {
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    &:hover {
      transform: scale(1.1);
    }
  }
`;

const GifWrapper = styled.div`
  width: 45%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url(${Vector});
  background-position: center;
  background-repeat: no-repeat;
  background-size: 90%;
  img {
    width: 85%;
  }
  @media (max-width: 1200px) {
    width: 0;
  }
`;
