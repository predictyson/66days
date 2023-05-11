import styled from "styled-components";
import Vector from "../../assets/landing/landing3_vector.png";
import View from "../../assets/landing/landing3_view.png";
import LandingGif3 from "../../assets/landing/landing 2.gif";
export default function Landing1() {
  return (
    <>
      <Container>
        <LeftWrapper>
          <Title>그룹 형성과 챌린지 생성</Title>
          <ViewImg src={View} alt="view" />
        </LeftWrapper>
        <GifWrapper>
          <img src={LandingGif3} alt="landing1" />
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
const ViewImg = styled.img`
  width: 90%;
  margin: 4rem auto;
  display: flex;
`;
const Title = styled.div`
  margin-top: 8rem;
  font-size: 3rem;
  font-weight: bold;
`;
const LeftWrapper = styled.div`
  width: 55%;
  padding: 0 8%;
  @media (max-width: 1200px) {
    width: 100%;
    img {
    }
  }
  img {
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    width: 95%;
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
  background-size: 95%;
  img {
    width: 85%;
  }
  @media (max-width: 1200px) {
    width: 0;
  }
`;
