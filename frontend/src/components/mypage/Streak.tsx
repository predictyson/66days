import styled from "styled-components";
import { theme } from "../../styles/theme";
import Line from "../../assets/mypage/Line.png";
interface IProps {
  commits: number[];
  length: number;
}

const StreakGraph = ({ commits, length }: IProps) => {
  const renderStreaks = () => {
    const rows = Math.ceil(length / 22);
    const streaks = [];
    for (let i = 0; i < rows; i++) {
      const startIndex = i * 22;
      const endIndex = Math.min(startIndex + 22, length);
      const rowStreaks = [];
      for (let j = startIndex; j < endIndex; j++) {
        const count = commits[j] || 0;
        const color = count > 0 ? "#50B9C9" : "#D9D9D9";
        rowStreaks.push(<Streak key={j} count={count} color={color} />);
      }
      streaks.push(<StreakWrapper key={i}>{rowStreaks}</StreakWrapper>);
    }
    return streaks;
  };

  return (
    <Container>
      <Title>
        My 챌린지 그래프
        <div className="accum">누적 2일</div>
      </Title>
      {renderStreaks()}
      <SubContent>
        <div>
          현재 진행 중 습관 <span className="count">3</span>개
        </div>
        <StreakDescription>
          <Streak count={0} color="#50B9C9" />
          <span>4-5건</span>
          <Streak count={0} color="#7EE3F2" />
          <span>2-3건</span>
          <Streak count={0} color="#BBE6EC" />
          <span>1건</span>
        </StreakDescription>
      </SubContent>
      <img src={Line} style={{ margin: "3.2rem auto", width: "80rem" }} />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  margin: 0 auto;
  margin-top: 4.8rem;
`;

const Title = styled.div`
  font-size: 2.4rem;
  color: ${theme.colors.gray500};
  .accum {
    color: black;
    margin-top: 0.5rem;
    font-weight: ${theme.fontWeight.semibold};
  }
`;
const StreakWrapper = styled.div`
  width: 80rem;
  display: flex;
  justify-content: space-between;
  margin-top: 1.6rem;
`;

const SubContent = styled.div`
  margin-top: 1rem;
  display: flex;
  font-size: 1.6rem;
  font-weight: ${theme.fontWeight.semibold};
  color: ${theme.colors.gray500};
  align-items: center;
  .count {
    color: ${theme.colors.mint};
  }
  justify-content: space-between;
`;

const StreakDescription = styled.div`
  display: flex;
  width: 30%;
  align-items: center;
  color: ${theme.colors.gray500};
  justify-content: space-between;
`;

const Streak = styled.div<{ count: number; color: string }>`
  width: 2.8rem;
  height: 2.8rem;
  margin: 0.125rem 0.5rem;
  border-radius: 5px;
  background-color: ${({ color }) => color};
  opacity: ${({ count }) => (count > 0 ? count / 4 + 0.2 : 0.2)};
`;

export default StreakGraph;
