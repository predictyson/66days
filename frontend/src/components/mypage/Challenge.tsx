import styled from "styled-components";
import { theme } from "../../styles/theme";
import { MyChallengeData } from "../../types/mypage";

interface IProps {
  challenges: MyChallengeData[];
}
export default function Challenge({ challenges }: IProps) {
  return (
    <Container>
      <div className="left-box"></div>
      <div className="right">
        <TagWrapper>
          <DateTag>44일째</DateTag>
          <Tag color="#B8A9FB">알고리즘</Tag>
        </TagWrapper>
        <TitleWrapper>백준 1일 1알고를 풀어봅시다. </TitleWrapper>
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
  &:hover {
    transform: scale(1.05);
  }
  width: 38rem;
  height: 22rem;
  display: flex;
  .left-box {
    width: 2rem;
    background-color: ${theme.colors.purple};
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
const DateTag = styled(Tag)`
  background-color: ${theme.colors.gray400};
`;

const TitleWrapper = styled.div`
  font-size: 2rem;
  font-weight: bold;
  font-family: Pretendard;
  margin-top: 2rem;
`;
