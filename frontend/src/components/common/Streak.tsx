import styled from "styled-components";
interface IProps {
  commits: number[];
  length: number;
}

const StreakGraph = ({ commits, length }: IProps) => {
  const renderStreaks = () => {
    const cols = Math.ceil(length / 3);
    const streaks = [];
    for (let i = 0; i < cols; i++) {
      const startIndex = i * 3;
      const endIndex = Math.min(startIndex + 3, length);
      const colStreaks = [];
      for (let j = startIndex; j < endIndex; j++) {
        const count = commits[j] || 0;
        const color = count > 0 ? "#50B9C9" : "#D9D9D9";
        colStreaks.push(<Streak key={j} count={count} color={color} />);
      }
      streaks.push(<StreakWrapper key={i}>{colStreaks}</StreakWrapper>);
    }
    return streaks;
  };

  return (
    <Container>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        {renderStreaks()}
      </div>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
`;

const StreakWrapper = styled.div`
  width: 5%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 1.6rem;
  .wrap {
    border: solid 1px red;
    display: flex;
    flex-direction: column;
  }
`;

const Streak = styled.div<{ count: number; color: string }>`
  width: 2.5rem;
  height: 2.5rem;
  margin: 0.5rem;
  border-radius: 5px;
  background-color: ${({ color }) => color};
  opacity: ${({ count }) => (count > 0 ? count / 4 + 0.2 : 0.2)};
`;

export default StreakGraph;
