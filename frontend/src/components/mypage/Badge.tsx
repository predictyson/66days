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
  width: 29rem;
  margin: 0 auto;
  margin-top: 2rem;
  .title {
    font-size: 2.4rem;
    font-weight: bold;
    font-family: "Kanit-bold";
  }
`;

const BadgeWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  img {
    width: 6.4rem;
  }
`;
