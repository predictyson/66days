import Banner from "../components/main/Banner";
import Todo from "../components/main/Todo";
import Groups from "../components/main/Groups";
import Ranking from "../components/main/Ranking";
import { MainIData } from "../types/main";

export default function MainPage() {
  return (
    <>
      <Banner />
      <Todo />
      <Groups />
      <Ranking />
    </>
  );
}

const DUMMYMAINDATA: MainIData = {
  result: "success",
  memberInfo: {
    tier: "bronze",
    email: "moongchi@ssafy.com",
    exp: 1500,
    point: 32000,
    image: "/image/카피바라.jpg",
    animal: "카피바라",
  },
  challenge: [
    {
      image: "/image/image.jpg",
      name: "김영한의 스프링 강의 정복",
      participants: ["뽀삐", "뭉치", "해피"],
      startDate: "2023-03-03",
      status: false,
    },
    {
      image: "/image/image.jpg",
      name: "김태원의 5조",
      participants: ["태원", "귀렬", "해피"],
      startDate: "2023-02-27",
      status: true,
    },
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
    {
      image: "/image/image.jpg",
      name: "범블비식구들",
      badges: ["알고리즘", "CS", "개발서적", "블로깅", "강의"],
      type: "group",
    },
  ],
};
