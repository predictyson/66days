import styled from "styled-components";
import { theme } from "../styles/theme";
import GroupItem from "../components/search/GroupItem";
import { SearchData } from "../types/search";
import { fetchSearchData } from "../api/search";
import { useEffect, useRef, useState } from "react";
export default function SearchPage() {
  const [searchValue, setSearchValue] = useState<string>("");
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const sentinelRef = useRef<HTMLDivElement>(null);
  const [groupdata, setGroupdata] = useState<SearchData>({
    result: "success",
    [`group-list`]: [],
  });

  async function getSearchData(page: number) {
    try {
      const data = await fetchSearchData(searchValue, page);
      console.log(page);
      // setGroupdata(data);
      setGroupdata((prev) => ({
        result: data.result,
        [`group-list`]: [...prev[`group-list`], ...data[`group-list`]],
      }));
      console.log(data);
      console.log(data[`group-list`]);
    } catch (error) {
      console.log("Error occurred while fetching search data:", error);
    }
  }

  useEffect(() => {
    // getSearchData(page);
    // Intersection Observer 생성
    const observer = new IntersectionObserver(handleObserver, {
      root: null, // 뷰포트 기준
      rootMargin: "0px",
      threshold: 1.0, // 1.0 이상이면 요소 전체가 뷰포트 안에 있을 때 감지
    });
    if (sentinelRef.current) {
      observer.observe(sentinelRef.current);
    }
    return () => {
      // 컴포넌트 언마운트 시 Intersection Observer 해제
      if (sentinelRef.current) {
        observer.unobserve(sentinelRef.current);
      }
    };
  }, []);

  const handleObserver: IntersectionObserverCallback = (entries) => {
    const target = entries[0];
    if (target.isIntersecting) {
      fetchMoreData();
    }
  };

  async function fetchMoreData() {
    if (!loading) {
      setLoading(true);
      try {
        await getSearchData(page + 1);
        setPage((prev) => prev + 1);
      } catch (err) {
        console.log("fetching more data err");
      }
      setLoading(false);
    }
  }
  const handleSearch = async (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      searchValue !== "" && (await getSearchData(page));
      setSearchValue("");
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
        {/* <BreadCrumb /> */}
      </div>

      <ItemContainer>
        {groupdata[`group-list`] &&
          groupdata[`group-list`].map((data, idx) => {
            return (
              <div key={idx} style={{ marginTop: "6rem" }}>
                <GroupItem key={idx} group={data} />
              </div>
            );
          })}
        <div ref={sentinelRef}></div>
        {loading && <h2>loading.....</h2>}
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
