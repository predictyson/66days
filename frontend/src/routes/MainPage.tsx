import Banner from "../components/main/Banner";
import Todo from "../components/main/Todo";
import Groups from "../components/main/Groups";
// import Ranking from "../components/main/Ranking";
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
    // console.log(data?.todayToto);
  }, []);
  return (
    <>
      {data && <Banner memberInfo={data.userDetail} />}
      {data && <Todo challenges={data.todayTodo} />}
      {data && <Groups groups={data.group} mygroup={data.myGroup} />}
      {/* <Ranking ranking={DUMMY_RANK} /> */}
    </>
  );
}
// const DUMMY_RANK: RankData = {
//   badgeRank: [
//     {
//       image: "/image/image.jpg",
//       name: "해피",
//       animal: "무너",
//       tier: "브론즈",
//       exp: 1000,
//       badge: 13,
//     },
//     {
//       image: "/image/image.jpg",
//       name: "뽀삐",
//       animal: "미니언즈",
//       tier: "브론즈",
//       exp: 800,
//       badge: 12,
//     },
//     {
//       image: "/image/image.jpg",
//       name: "뭉치",
//       animal: "카피바라",
//       tier: "브론즈",
//       exp: 1400,
//       badge: 10,
//     },
//   ],
//   myExpRank: 4,
//   myExp: 600,
//   myBadgeRank: 5,
//   myBadge: 3,
// };
