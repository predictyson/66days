import styled from "styled-components";
import { theme } from "../../styles/theme";
import { GroupData } from "../../types/main";
import Category from "../../styles/CategoryTheme";
interface IProps {
  group: GroupData;
}
export default function Challenge({ group }: IProps) {
  console.log(group);
  return (
    <Container>
      <div className="left-box"></div>
      <div className="right">
        <TagWrapper>
          {group.badges.map((data, idx) => {
            return (
              <Tag
                key={idx}
                color={
                  Category.find((category) => category.title === data)?.color
                }
              >
                {data}
              </Tag>
            );
          })}
        </TagWrapper>
        <TitleWrapper>{group.name} </TitleWrapper>
        <div className="group-type">
          {group.type === "personal" ? (
            <span>개인 그룹 </span>
          ) : (
            <span>그룹 </span>
          )}
        </div>
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
    color: ${theme.colors.gray300};
  }
  &:hover {
    transform: scale(1.05);
  }
  width: 38rem;
  height: 22rem;
  display: flex;
  .left-box {
    width: 2rem;
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
