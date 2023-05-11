import styled from "styled-components";
import BreadCrumb from "./BreadCrumb";
// import Challenge from "./Challenge";
import GroupItem from "../../components/mypage/GroupItem";
import { useState } from "react";
import { GroupData } from "../../types/main";
import { MyChallengeData } from "../../types/mypage";
import Challenge from "../../components/mypage/Challenge";
interface IProps {
  groups: GroupData[];
  challenges: MyChallengeData[];
}
export default function Group({ groups, challenges }: IProps) {
  const [activeItem, setActiveItem] = useState(0);
  const handleBread = (idx: number) => {
    setActiveItem(idx);
  };
  return (
    <Container>
      <BreadCrumb activeItem={activeItem} handleBread={handleBread} />
      <ContentWrapper>
        {activeItem === 0
          ? groups.map((data, idx) => {
              return <GroupItem key={idx} group={data} />;
            })
          : challenges.map((data, idx) => {
              return <Challenge key={idx} challenge={data} />;
            })}
      </ContentWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  @media (max-width: 1200px) {
    width: 80%;
  }
  @media (max-width: 800px) {
    width: 100%;
  }
`;

const ContentWrapper = styled.div`
  display: flex;
`;
