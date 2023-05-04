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
          <img
            src={View}
            alt="view"
            style={{ width: "41.3rem", margin: "2rem auto", display: "flex" }}
          />
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
  border: solid 1px black;
  display: flex;
  padding: 0 7.5rem;
  font-family: Pretendard;
`;

const Title = styled.div`
  margin-top: 15rem;
  font-size: 4rem;
  font-weight: bold;
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
