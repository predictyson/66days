import styled from "styled-components";
import { theme } from "../../styles/theme";
import { MyChallengeData } from "../../types/mypage";
import Category from "../../styles/CategoryTheme";

interface IProps {
  challenge: MyChallengeData;
}
export default function Challenge({ challenge }: IProps) {
  function countDays(startDate: string): number {
    const oneDay = 24 * 60 * 60 * 1000; // 1일을 밀리초로 계산
    const startDateTime = new Date(startDate).getTime(); // 시작일을 밀리초로 변환
    const todayDateTime = new Date().getTime(); // 현재 시간을 밀리초로 변환
    const diffDays = Math.round(
      Math.abs((todayDateTime - startDateTime) / oneDay)
    ); // 날짜 차이 계산
    return diffDays;
  }
  return (
    <Container
      color={
        Category.find((category) => category.title === challenge.category)
          ?.color
      }
    >
      <div className="left-box"></div>
      <div className="right">
        <TagWrapper>
          <DateTag>
            {countDays(challenge.startDate.substring(0, 10))}일째
          </DateTag>
          <Tag
            color={
              Category.find((category) => category.title === challenge.category)
                ?.color
            }
          >
            {challenge.category}
          </Tag>
        </TagWrapper>
        <TitleWrapper>{challenge.name}</TitleWrapper>
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
    width: 5%;
    background-color: ${(props) => (props.color ? props.color : "gray")};
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
