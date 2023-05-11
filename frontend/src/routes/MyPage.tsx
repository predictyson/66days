import styled from "styled-components";
import Profile from "../components/mypage/Profile";
import Badge from "../components/mypage/Badge";
import StreakGraph from "../components/common/Streak";
import Group from "../components/mypage/Groups";
import { MyPageData } from "../types/mypage";
import { theme } from "../styles/theme";
import Line from "../assets/mypage/Line.png";
import { useEffect, useState } from "react";
import { getMyPageInfo } from "../api/mypage";
import EditProfile from "../components/mypage/EditProfile";
import { Breadcrumb } from "antd";

// // 프로필 수정시
export default function MyPage() {
  // const [mypageInfo, setMyPageInfo] = useState<MyPageData | null>(null);
  // TODO:  마이페이지 데이터 api 연결
  // const [mypageInfo, setMyPageInfo] = useState<MyPageData | null>(null);

  const [isEdit, setIsEdit] = useState<boolean>(false);

  useEffect(() => {
    getMyPageInfo();
  }, []);
  const handleEdit = (state: boolean) => {
    setIsEdit(state);
  };

  const length = 66;
  return (
    <Container>
      <div className="left">
        {!isEdit ? (
          <Profile
            handleEdit={handleEdit}
            myInfo={DUMMY_DATA_MYPAGE.memberInfo}
            date={DUMMY_DATA_MYPAGE.streak.length}
          />
        ) : (
          <EditProfile handleEdit={handleEdit} />
        )}
        <Badge />
      </div>
      <div className="right">
        <div className="streak-wrapper">
          <Title>
            My 챌린지 그래프
            <div className="accum">
              누적 {DUMMY_DATA_MYPAGE.streak.length}일
            </div>
          </Title>
          <StreakGraph
            commits={DUMMY_DATA_MYPAGE.streak.map((item) => item.count)}
            length={length}
          />
          <SubContent>
            <div>
              현재 진행 중 습관 <span className="count">3</span>개
            </div>
            <StreakDescription>
              <div className="wrap">
                <Streak count={0} color="#50B9C9" />
                <span>4-5건</span>
              </div>
              <div className="wrap">
                <Streak count={0} color="#7EE3F2" />
                <span>2-3건</span>
              </div>

              <div className="wrap">
                <Streak count={0} color="#BBE6EC" />
                <span>1건</span>
              </div>
            </StreakDescription>
          </SubContent>
          <img
            src={Line}
            style={{ margin: "3.2rem auto 2rem auto", width: "100%" }}
          />
        </div>
        <Group
          groups={DUMMY_DATA_MYPAGE.group}
          challenges={DUMMY_DATA_MYPAGE.challenge}
        />
      </div>
    </Container>
  );
}

const SubContent = styled.div`
  margin-top: 1rem;
  display: flex;
  font-size: 1.6rem;
  font-weight: ${theme.fontWeight.semibold};
  color: ${theme.colors.gray500};
  align-items: center;
  .count {
    color: ${theme.colors.mint};
  }
  justify-content: space-between;
`;
const Streak = styled.div<{ count: number; color: string }>`
  width: 2.5rem;
  height: 2.5rem;
  /* height: 90%; */
  margin: auto;
  margin: 0.125rem 0.5rem;
  border-radius: 5px;
  background-color: ${({ color }) => color};
  opacity: ${({ count }) => (count > 0 ? count / 4 + 0.2 : 0.2)};
`;

const StreakDescription = styled.div`
  display: flex;
  width: 40%;
  align-items: center;
  color: ${theme.colors.gray500};
  justify-content: space-between;
  .wrap {
    display: flex;
    width: 30%;
    align-items: center;
    justify-content: space-between;
    /* flex-direction: column; */
  }
`;

const Title = styled.div`
  font-size: 2.4rem;
  color: ${theme.colors.gray500};
  .accum {
    color: black;
    margin-top: 0.5rem;
    font-weight: ${theme.fontWeight.semibold};
  }
`;
const Container = styled.div`
  display: flex;
  height: 70rem;
  margin-inline: 8rem;
  @media (max-width: 1200px) {
    .streak-wrapper {
      display: none;
    }
    display: flex;
    flex-direction: column;
    height: 100%;
  }
  .left {
    width: 30%;
    display: flex;
    flex-direction: column;
    @media (max-width: 1200px) {
      width: 100%;
    }
  }
  .right {
    width: 70%;
    padding: 2%;
    margin: 0 auto;
  }
  @media (max-width: 800px) {
    width: 100%;
  }
`;

const DUMMY_DATA_MYPAGE: MyPageData = {
  badges: [
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
    {
      image: "/image/image.jpg",
    },
  ],
  result: "success",
  memberInfo: {
    image: "/image/image.jpg",
    nickname: "뭉치뭉치똥뭉치",
    tier: "bronze",
    email: "moongchi@ssafy.com",
    currentExp: 1500,
    nextTierExp: 3000,
    point: 32000,
  },
  challenge: [
    {
      category: "강의",
      name: "김영한의 스프링 강의 정복",
      startDate: "2023-03-04T08:01:17.379696",
    },
    {
      category: "알고리즘",
      name: "김태원의 5조",
      startDate: "2023-02-28T08:01:17.379705",
    },
  ],
  streak: [
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 2,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 2,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 2,
    },
    {
      date: "2023-05-09",
      count: 1,
    },
    {
      date: "2023-05-09",
      count: 1,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 1,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
    {
      date: "2023-05-09",
      count: 3,
    },
  ],
  group: [
    {
      image: "/image/image.jpg",
      name: "뭉치뭉치똥뭉치",
      badges: ["알고리즘", "CS"],
      type: "personal",
    },
    {
      image: "/image/image.jpg",
      name: "범블비식구들",
      badges: ["알고리즘", "CS", "개발서적", "블로깅", "강의"],
      type: "group",
    },
  ],
};
