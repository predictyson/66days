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
          <img
            src={View}
            alt="view"
            style={{ width: "55rem", margin: "2rem auto", display: "flex" }}
          />
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
`;

const Title = styled.div`
  margin-top: 15rem;
  font-size: 4rem;
  font-weight: ${theme.fontWeight.bold};
`;
const LeftWrapper = styled.div`
  width: 50%;
  padding: 0 10rem;
  img {
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    &:hover {
      transform: scale(1.1);
    }
  }
`;

const GifWrapper = styled.div`
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url(${Vector});
  background-position: center;
  background-repeat: no-repeat;
`;
