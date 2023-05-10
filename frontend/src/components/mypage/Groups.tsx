import styled from "styled-components";
import BreadCrumb from "./BreadCrumb";
// import Challenge from "./Challenge";
import GroupItem from "../../components/mypage/GroupItem";
export default function Group() {
  return (
    <Container>
      <BreadCrumb />
      <ContentWrapper>
        <GroupItem />
        {/* <Challenge />
        <Challenge /> */}
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
