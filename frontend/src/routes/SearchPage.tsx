import styled from "styled-components";
import { theme } from "../styles/theme";
import BreadCrumb from "../components/search/BreadCrumb";
import GroupItem from "../components/search/GroupItem";
export default function SearchPage() {
  return (
    <Container>
      <TitleWrapprer>
        SEARCH
        <br /> YOUR GROUP
        <div className="sub">원하시는 그룹을 찾아보세요 :)</div>
      </TitleWrapprer>
      <SearchInput
        type="text"
        placeholder="그룹장 또는 그룹명을 입력해주세요"
      ></SearchInput>
      <div
        style={{
          width: "93%",
          display: "flex",
          margin: "0 auto",
          justifyContent: "flex-end",
        }}
      >
        <BreadCrumb />
      </div>

      <ItemContainer>
        {GROUPS.map((v, idx) => {
          return (
            <div key={idx} style={{ marginTop: idx >= 3 ? "6rem" : "0" }}>
              {v}
            </div>
          );
        })}
      </ItemContainer>
    </Container>
  );
}
const Container = styled.div`
  width: 100%;
  display: flex;
  padding: 9.6rem 3.4rem;
  font-family: Pretendard;
  flex-direction: column;
`;

const TitleWrapprer = styled.div`
  font-family: "Kanit-Bold";
  font-size: 4.8rem;
  line-height: 5rem;
  padding: 0 4rem;
  .sub {
    color: ${theme.colors.gray500};
    margin-top: 3.1rem;
    font-size: 2.4rem;
    font-family: Pretendard;
  }
`;

const SearchInput = styled.input`
  margin-top: 1.3rem;
  width: 43.1rem;
  height: 4.8rem;
  border-radius: 15px;
  border: solid 1px ${theme.colors.gray500};
  text-align: center;
  font-size: 1.6rem;
  display: flex;
  margin: 1.6rem auto;

  ::placeholder {
    color: ${theme.colors.gray300};
  }
`;

const ItemContainer = styled.div`
  margin-top: 2rem;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-content: space-between;
  grid-column-gap: 0;
`;

const GROUPS = [
  <GroupItem />,
  <GroupItem />,
  <GroupItem />,
  <GroupItem />,
  <GroupItem />,
  <GroupItem />,
];