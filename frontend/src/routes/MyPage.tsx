import styled from "styled-components";
import Profile from "../components/mypage/Profile";

export default function MyPage() {
  return (
    <Container>
      <div className="left">
        <Profile />
      </div>
      <div className="right">dd</div>
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
    background-color: pink;
  }
`;
