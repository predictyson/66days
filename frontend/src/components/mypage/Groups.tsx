import styled from "styled-components";
import BreadCrumb from "./BreadCrumb";
// import Challenge from "./Challenge";
import GroupItem from "../../components/mypage/GroupItem";
import { useState } from "react";
import { GroupData } from "../../types/main";
import { MyChallengeData } from "../../types/mypage";
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
        {activeItem === 0 ? (
          <GroupItem groups={groups} />
        ) : (
          <Challenge challenges={challenges} />
        )}
      </ContentWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
`;

const ContentWrapper = styled.div`
  display: flex;
`;
