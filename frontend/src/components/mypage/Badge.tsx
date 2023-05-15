import styled from "styled-components";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/csBox.png";
import CS from "../../assets/landing/blogBox.png";
import Lecture from "../../assets/landing/lectureBox.png";
export default function Badge() {
  return (
    <Container>
      <div className="title">Badges</div>
      <BadgeWrapper>
        <img src={Algo} />
        <img src={Blog} />
        <img src={CS} />
        <img src={Lecture} />
      </BadgeWrapper>
    </Container>
  );
}

const Container = styled.div`
  width: 80%;
  margin: 0 auto;
  margin-top: 1rem;
  .title {
    font-size: 2.4rem;
    font-weight: bold;
    font-family: "Kanit-bold";
  }
  @media (max-width: 1200px) {
    width: 48%;
  }
  @media (max-width: 800px) {
    width: 65%;
  }
`;

const BadgeWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  img {
    width: 23%;
    cursor: pointer;
  }
`;
