import styled from "styled-components";
import Profile from "../components/mypage/Profile";
import Badge from "../components/mypage/Badge";
import Streak from "../components/mypage/Streak";
import Group from "../components/mypage/Groups";
// 프로필 수정시
// import EditProfile from "../components/mypage/EditProfile";
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
        {/* <EditProfile /> */}
        <Badge />
      </div>
      <div className="right">
        <Streak commits={commits} length={length} />
        <Group />
      </div>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  height: 70rem;
  .left {
    width: 27%;
    display: flex;
    flex-direction: column;
  }
  .right {
    width: 73%;
  }
`;
