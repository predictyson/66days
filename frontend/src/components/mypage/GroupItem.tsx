import styled from "styled-components";
import { theme } from "../../styles/theme";

export default function Challenge() {
  return (
    <Container>
      <div className="left"></div>
      <div className="right">
        <TagWrapper>
          <Tag color="#B8A9FB">알고리즘</Tag>
          <Tag color="#B8A9FB">알고리즘</Tag>
          <Tag color="#B8A9FB">알고리즘</Tag>
          <Tag color="#B8A9FB">알고리즘</Tag>
        </TagWrapper>
        <TitleWrapper>백준 1일 1알고를 풀어봅시다. </TitleWrapper>
        <div className="group-type">개인 그룹</div>
      </div>
    </Container>
  );
}

const Container = styled.div`
  margin-top: 2.5rem;
  margin-right: 5%;
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  cursor: pointer;
  .group-type {
    font-size: 1.6rem;
    font-weight: bold;
    display: flex;
    margin-top: auto;
    margin-left: auto;
    color: ${theme.colors.gray400};
  }
  &:hover {
    transform: scale(1.05);
  }
  width: 38rem;
  height: 22rem;
  display: flex;
  .left {
    width: 5rem;
    background-color: ${theme.colors.gray400};
  }
  .right {
    width: 95%;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    display: flex;
    flex-direction: column;
    padding: 5%;
  }
`;

const TagWrapper = styled.div`
  display: flex;
`;

const Tag = styled.div`
  width: 8rem;
  height: 3rem;
  border-radius: 10px;
  font-size: 1.2rem;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-family: Pretendard;
  margin-right: 2%;
  background-color: ${(props) => props.color || "gray"};
`;

const TitleWrapper = styled.div`
  font-size: 2rem;
  font-weight: bold;
  font-family: Pretendard;
  margin-top: 2rem;
`;
