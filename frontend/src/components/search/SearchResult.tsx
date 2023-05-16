import { useState, useEffect, useRef } from "react";

// 1안. react-infinite-scroll-component 라이브러리 사용 - 젤 쉬울거같음
// 2안. 직접 구현 - 귀찮음
// 3안. Intersection Observer사용 - 젤 괜찮은 것 같음

export default function SearchResult() {
  const [data, setData] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(1);
  const sentinelRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // 초기 데이터 로드
    fetchData();
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

  const fetchData = () => {
    // 데이터를 가져오는 비동기 함수 호출
    // 예를 들어, API 요청을 사용할 수 있습니다.
    // 새로운 데이터를 가져와서 기존 데이터와 병합합니다.
    const newData = ["새로운 데이터 1", "새로운 데이터 2", "새로운 데이터 3"];
    setData((prevData) => [...prevData, ...newData]);
    setPage((prevPage) => prevPage + 1);
  };

  const handleObserver: IntersectionObserverCallback = (entries) => {
    const target = entries[0];
    if (target.isIntersecting) {
      // Intersection Observer가 요소 진입을 감지하면 추가 데이터를 가져옵니다.
      fetchMoreData();
    }
  };

  const fetchMoreData = () => {
    if (!loading) {
      setLoading(true);
      // 추가 데이터를 불러오기 위한 비동기 함수 호출
      // fetchData()를 호출하여 새로운 데이터를 가져옵니다.
      fetchData();
      setLoading(false);
    }
  };

  return (
    <div>
      {data.map((item, index) => (
        <div key={index}>{item}</div>
      ))}

      <div ref={sentinelRef}></div>  {/* intersectino observer가 감지할 요소 참조 */}
      {loading && <h4>Loading...</h4>}
    </div>
  );
}
