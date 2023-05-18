import Banner from "../components/main/Banner";
import Todo from "../components/main/Todo";
import Groups from "../components/main/Groups";
import Ranking from "../components/main/Ranking";
import { MainData } from "../types/main";
import { useAuthStore } from "../stores/useAuthStore";
import LandingPage from "./LandingPage";
import { useEffect, useState } from "react";
import { fetchMainPageData } from "../api/main";

export default function MainPage() {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  if (!isLoggedIn()) return <LandingPage />;
  const [data, setData] = useState<MainData | null>(null);
  useEffect(() => {
    async function fetchMainPage() {
      const maindata = await fetchMainPageData();
      setData(maindata);
    }
    fetchMainPage();
    console.log(data);
  }, []);
  return (
    <>
      <Banner memberInfo={data?.userDetail} />
      <Todo challenges={MAIN_DUMMY_DATA.challenge} />
      <Groups groups={MAIN_DUMMY_DATA.group} />
      <Ranking ranking={MAIN_DUMMY_DATA.rank} />
    </>
  );
}

const MAIN_DUMMY_DATA: MainData = {
  result: "success",
  memberInfo: {
    tier: "bronze",
    email: "moongchi@ssafy.com",
    exp: 15000,
    point: 33000,
    image: "/image/카피바라.jpg",
    animal: "카피바라",
  },
  challenge: [
    // {
    //   image: "/image/image.jpg",
    //   name: "김영한의 스프링 강의 정복",
    //   participants: ["뽀삐", "뭉치", "해피"],
    //   startDate: "2023-05-03",
    //   status: false,
    // },
  ],
  rank: {
    expRank: [
      {
        image: "/image/image.jpg",
        name: "뭉치",
        animal: "카피바라",
        tier: "브론즈",
        exp: 1400,
        badge: 10,
      },
      {
        image: "/image/image.jpg",
        name: "해피",
        animal: "무너",
        tier: "브론즈",
        exp: 1000,
        badge: 13,
      },
      {
        image: "/image/image.jpg",
        name: "뽀삐",
        animal: "미니언즈",
        tier: "브론즈",
        exp: 800,
        badge: 12,
      },
    ],
    badgeRank: [
      {
        image: "/image/image.jpg",
        name: "해피",
        animal: "무너",
        tier: "브론즈",
        exp: 1000,
        badge: 13,
      },
      {
        image: "/image/image.jpg",
        name: "뽀삐",
        animal: "미니언즈",
        tier: "브론즈",
        exp: 800,
        badge: 12,
      },
      {
        image: "/image/image.jpg",
        name: "뭉치",
        animal: "카피바라",
        tier: "브론즈",
        exp: 1400,
        badge: 10,
      },
    ],
    myExpRank: 4,
    myExp: 600,
    myBadgeRank: 5,
    myBadge: 3,
  },
  group: [
    {
      image: "/image/image.jpg",
      name: "뭉치뭉치똥뭉치",
      badges: ["알고리즘", "CS"],
      type: "personal",
    },
  ],
};
