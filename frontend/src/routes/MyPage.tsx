import styled from "styled-components";

export default function MyPage() {
  return (
    <Container>
      <div className="left">dd</div>
      <div className="right">dd</div>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  .left {
    width: 30%;
    background-color: yellow;
  }
  .right {
    width: 70%;
    background-color: pink;
  }
`;
