import styled from "styled-components";
import { theme } from "../styles/theme";
import BreadCrumb from "../components/search/BreadCrumb";
import GroupItem from "../components/search/GroupItem";
// import { getSearchData } from "../api/search";
import { SearchData } from "../types/search";
import { fetchSearchData } from "../api/search";
import { useEffect, useState } from "react";
export default function SearchPage() {
  const [searchValue, setSearchValue] = useState<string>("");
  
  //FIXME: CORS error
  async function getSearchData() {
    try {
      const data = await fetchSearchData(searchValue, "알고리즘");
      console.log(data);
    } catch (error) {
      console.log("Error occurred while fetching search data:", error);
    }
  }
  const handleSearch = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      getSearchData();
    }
  };

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
        value={searchValue}
        onChange={(e) => setSearchValue(e.target.value)}
        onKeyDown={handleSearch}
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
        {DUMMY_DATA_SEARCH.groupList.map((data, idx) => {
          return (
            <div key={idx} style={{ marginTop: "6rem" }}>
              <GroupItem key={idx} group={data} />
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
  padding: 9.6rem 8rem;
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
  display: grid;
  @media (max-width: 1200px) {
    grid-template-columns: repeat(auto-fit, minmax(50%, 1fr));
  }
  @media (max-width: 890px) {
    grid-template-columns: repeat(auto-fit, minmax(100%, 1fr));
  }
  grid-template-columns: repeat(auto-fit, minmax(33%, 1fr));
  justify-content: space-between;
`;

const DUMMY_DATA_SEARCH: SearchData = {
  result: "success",
  groupList: [
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 66,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "블로깅"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
    {
      ownerImage: "/image/image.jpg",
      ownerName: "뭉치",
      image: null,
      name: "뭉치뭉치똥뭉치네",
      categories: ["알고리즘", "강의"],
      description: "같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요",
      memberCounts: 33,
      maxMemberCounts: 66,
      animal: "카피바라",
    },
  ],
};
