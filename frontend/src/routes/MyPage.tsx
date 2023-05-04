import styled from "styled-components";
import Profile from "../components/mypage/Profile";
import Badge from "../components/mypage/Badge";
import Streak from "../components/mypage/Streak";

export default function MyPage() {
  const commits = [
    1, 2, 0, 0, 0, 1, 3, 1, 2, 3, 3, 2, 2, 2, 1, 1, 3, 0, 0, 1, 0, 2, 3, 1, 2,
    0, 0, 0, 1, 3, 1, 2, 3, 3, 2, 2, 2, 1, 1, 3, 0, 0, 1, 0, 2, 3, 1, 2, 0, 0,
    0, 1, 3, 1, 2, 3, 3, 2, 2, 2, 1, 1, 3, 0, 0, 1, 0, 2, 3,
  ];
  const length = 66;
  return (
    <Container>
      <div className="left">
        <Profile />
        <Badge />
      </div>
      <div className="right">
        <Streak commits={commits} length={length} />
      </div>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  .left {
    width: 30%;
    display: flex;
    flex-direction: column;
  }
  .right {
    width: 70%;
  }
`;
