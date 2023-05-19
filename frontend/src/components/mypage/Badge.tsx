import styled from "styled-components";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/csBox.png";
import CS from "../../assets/landing/blogBox.png";
import Lecture from "../../assets/landing/lectureBox.png";
import { getImagePath } from "../../util/common";
interface IProps {
  badges: BadgeData[];
}
export default function Badge({ badges }: IProps) {
  return (
    <Container>
      <div className="title">Badges</div>
      <BadgeWrapper>
        {badges.slice(0, 4).map((b) => {
          return <img src={getImagePath(b.imagePath)} />;
        })}
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
    border-radius: 10px;
  }
`;
