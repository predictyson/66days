import styled from "styled-components";
import { theme } from "../../styles/theme";
import { MyGroupData } from "../../types/mypage";
import Category from "../../styles/CategoryTheme";
import { useNavigate } from "react-router";
interface IProps {
  group: MyGroupData;
}
export default function GroupITem({ group }: IProps) {
  console.log(group);
  const navigate = useNavigate();
  return (
    <Container onClick={() => navigate(`/groups/${group.groupId}`)}>
      <div className="left-box"></div>
      <div className="right-box">
        <TagWrapper>
          {group.challenges.map((data, idx) => {
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
        {/* <div className="group-type">
          <span>{group.type === "personal" ? "개인 그룹" : "그룹"}</span>
        </div> */}
      </div>
    </Container>
  );
}

const Container = styled.div`
  margin: 0 auto;
  margin-top: 2.5rem;
  margin-right: 5%;
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
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
  width: 38rem !important;
  height: 22rem;
  display: flex;
  .left-box {
    width: 5%;
    background-color: ${theme.colors.gray400};
  }
  .right-box {
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
