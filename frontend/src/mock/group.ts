import Algorithms from "../assets/algorithm_badge.png";
import CS from "../assets/cs_badge.png";
import Blog from "../assets/blog_badge.png";
import Book from "../assets/book_badge.jpeg";

const mockBoardData = {
  title: "알고리즘 챌린지원을 모집합니다.",
  date: "2023.04.20.",
  writer: "뭉치",
  content:
    "안녕하세요! 그룹장 뭉치입니다. :D 현재 알고리즘 문제를 함께 풀 그룹원들을 모집하고 있습니다. 주변에 관심있는 분들이 있으시다면 알려주시기 바랍니다! 뭉치 올림",
};

const mockChallengeList = [
  {
    bgImg: Algorithms,
    notStarted: true,
    title: "1일 1백준을 풀어봅시다.",
    dueDate: 3,
    profile:
      "https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/03/urban-20230310154400454301.jpg",
    cnt: "5 / 12",
  },
  {
    bgImg: Book,
    notStarted: true,
    ending: true,
    title: "모던자바스크립트 읽기",
    dueDate: 3,
    profile:
      "https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/03/urban-20230310154400454301.jpg",
    cnt: "5 / 12",
  },
  {
    bgImg: Blog,
    notStarted: false,
    title: "뭉치의 TIL 작성 챌린지",
    dueDate: 3,
    profile:
      "https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/03/urban-20230310154400454301.jpg",
    cnt: "5 / 12",
  },
  {
    bgImg: CS,
    notStarted: false,
    title: "김태원의 CS 스터디",
    dueDate: 3,
    profile:
      "https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/03/urban-20230310154400454301.jpg",
    cnt: "5 / 12",
  },
];

const mockMemberList = [
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "뭉치뭉치똥뭉치",
    badge: 2,
    owner: true,
    manager: false,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "초코만먹는초코",
    badge: 3,
    owner: false,
    manager: true,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "해피한해피",
    badge: 5,
    owner: false,
    manager: false,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "초코친구코코",
    badge: 10,
    owner: false,
    manager: false,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "졸린뽀삐",
    badge: 6,
    owner: false,
    manager: false,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "뭉치친구뭉뭉이",
    badge: 7,
    owner: false,
    manager: false,
  },
  {
    profile:
      "https://user-images.githubusercontent.com/55757379/235518955-05b3f322-0590-45a5-9fdf-2e620666f34d.png",
    nickname: "난누구게",
    badge: 4,
    owner: false,
    manager: false,
  },
];

export { mockChallengeList, mockMemberList, mockBoardData };