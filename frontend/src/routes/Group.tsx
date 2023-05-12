import { useEffect, useState } from "react";

import { Layout } from "antd";
import styled from "styled-components";
import {
  TeamOutlined,
  TrophyFilled,
  RightCircleFilled,
  LeftCircleFilled,
} from "@ant-design/icons";
import { theme } from "../styles/theme";
import Algorithms from "../assets/algorithm_badge.png";
import CS from "../assets/cs_badge.png";
import Blog from "../assets/blog_badge.png";
import Lecture from "../assets/lecture_badge.png";
import Book from "../assets/book_badge.jpeg";
import ChallengeBox from "../components/group/ChallengeBox";
import { BoardBox } from "../components/group/BoardBox";
import ChallengeModal from "../components/group/ChallengeModal";
import MemberModal from "../components/group/MemberModal";
import BadgeModal from "../components/group/BadgeModal";
import NewBoardModal from "../components/group/NewBoardModal";
import {
  fetchAppliedMembers,
  fetchBoardListByPage,
  fetchGroupBadges,
  fetchGroupMembers,
  fetchGroupPageData,
} from "../api/group";
import NoChallengeBox from "../components/group/NoChallengeBox";
import { GroupSettingModal } from "../components/group/GroupSettingModal";
import { BoardModal } from "../components/group/BoardModal";

const { Content } = Layout;

// FIXME: 타입이 아직 명확히 설정되지 않았습니다
interface ButtonStyled {
  color?: string;
  font?: string;
  fontWeight?: number;
  margin?: string;
  cursor?: string;
}

interface MemberType {
  image: string;
  nickname: string;
  badge: number;
  authority: "MEMBER" | "OWNER" | "MANAGER";
}

interface BadgePreviewType {
  image: string;
  category: "알고리즘" | "CS" | "블로깅" | "강의" | "개발서적";
  count: number;
}

interface BadgeType {
  image: string;
  challengeName: string;
  startDate: Date;
  endDate: Date;
  category: "알고리즘" | "CS" | "블로깅" | "강의" | "개발서적";
  status: boolean; // 성공 실패 여부
}

interface ChallengeType {
  image: string; // challenge badge image
  name: string; // challenge name
  participants: {
    image: string;
  }[];
  maxParticipant: number;
  startDate: Date;
}

interface ArticleType {
  articleDTO: {
    articleId: number;
    title: string;
    content: string;
    createdAt: Date;
    userId: string;
    nickname: string;
    groupId: number;
    role: string;
  };
}

interface BoardType {
  articles: ArticleType[];
  pageNo: number;
}

interface CommentType {
  commentId: number;
  articleId: number;
  groupId: number;
  userId: string;
  nickname: string;
  profileImagePath: string;
  content: string;
  createdAt: Date;
}

export default function Group() {
  const [isOpenMemberModal, setOpenMemberModal] = useState(false);
  const [isOpenMemberSettingModal, setOpenMemberSettingModal] = useState(false);
  const [isOpenBadgeModal, setOpenBadgeModal] = useState(false);
  const [isOpenNewChallgeModal, setOpenNewChallgeModal] = useState(false);
  const [isOpenNewBoardModal, setOpenNewBoardModal] = useState(false);
  const [isOpenBoardModal, setOpenBoardModal] = useState(false);

  const [boardPage, setBoardPage] = useState<number>(1);

  const [badgePreview, setBadgePreview] = useState<BadgePreviewType[]>([]);
  const [challengeList, setChallengeList] = useState<ChallengeType[]>([]);
  const [memberList, setMemberList] = useState<MemberType[]>([]);
  const [appliedList, setAppliedList] = useState<MemberType[]>([]);
  const [badgeList, setBadgeList] = useState<BadgeType[]>([]);
  const [filteredBadgeList, setFilteredBadgeList] = useState<BadgeType[]>([]);
  const [boardDataList, setBoardDataList] = useState<BoardType>();
  const [boardData, setBoardData] = useState<ArticleType>();
  const [commentData, setCommentData] = useState<CommentType[]>([]);

  function getCategoryColor(category: string) {
    if (category === "알고리즘") {
      return theme.colors.purple;
    } else if (category === "CS") {
      return theme.colors.pink;
    } else if (category === "블로깅") {
      return theme.colors.orange;
    } else if (category === "강의") {
      return theme.colors.purple;
    } else if (category === "개발서적") {
      return theme.colors.lightred;
    }
  }

  // dummy 연결 해제 후 삭제 예정
  function getCategoryImage(category: string) {
    if (category === "알고리즘") {
      return Algorithms;
    } else if (category === "CS") {
      return CS;
    } else if (category === "블로깅") {
      return Blog;
    } else if (category === "강의") {
      return Lecture;
    } else if (category === "개발서적") {
      return Book;
    }
  }

  function calcDueDate(dueDate: Date) {
    const today = new Date();
    const startDate = new Date(dueDate);

    const diffDate = today.getTime() - startDate.getTime();

    return Math.round(diffDate / (1000 * 60 * 60 * 24));
  }

  // 선택한 category 대로 배지 data filter
  function filterBadgesByCategory(category: string) {
    const filteredBadges = badgeList.filter(
      (badge) => badge.category === category
    );
    setFilteredBadgeList(filteredBadges);
  }

  function clickBadgeCategory(category: string) {
    filterBadgesByCategory(category); // badge data filter by selected category
    setOpenBadgeModal((prev) => !prev); // 모달 오픈
  }

  async function fetchAndSetNewBoardList(page: number) {
    const newBoardList = await fetchBoardListByPage(1, page);
    newBoardList.pgNo = boardPage - 1;
    setBoardDataList(newBoardList);
  }

  function handleClickLeft() {
    if (boardPage > 1) {
      setBoardPage(boardPage - 1);
      // 이전 페이지 게시글 리스트 호출
      fetchAndSetNewBoardList(boardPage - 1);
      // setBoardDataList(newBoardList);
    }
  }

  function handleClickRight() {
    if (boardPage < Math.ceil(4 / 2)) {
      setBoardPage(boardPage + 1);
      // 이후 페이지 게시글 리스트 호출
      fetchAndSetNewBoardList(boardPage + 1);
    }
  }

  const tmpAddBadgeData: BadgeType[] = [
    {
      image: Algorithms,
      challengeName: "알고리즘 하장",
      startDate: new Date("2023-03-06"),
      endDate: new Date("2023-05-11"),
      category: "알고리즘",
      status: true,
    },
    {
      image: Book,
      challengeName: "개발서적 하루 30분씩 읽을 사람",
      startDate: new Date("2023-02-23"),
      endDate: new Date("2023-04-08"),
      category: "개발서적",
      status: false,
    },
    {
      image: Algorithms,
      challengeName: "코테 부수기 챌린지",
      startDate: new Date("2023-03-06"),
      endDate: new Date("2023-04-11"),
      category: "알고리즘",
      status: false,
    },
    {
      image: Book,
      challengeName: "모던 자바스크립트 정독",
      startDate: new Date("2023-02-23"),
      endDate: new Date("2023-03-27"),
      category: "개발서적",
      status: false,
    },
  ];

  useEffect(() => {
    // TODO: fetch data
    async function fetchAndSetGroupPageData() {
      const data = await fetchGroupPageData();
      setBadgePreview(data.badges);
      setChallengeList(data.challenge);
      setBoardDataList(data.board);
    }

    async function fetchAndSetGroupSettingData() {
      const membersData = await fetchGroupMembers();
      setMemberList(membersData["member-list"]);
      const appliedData = await fetchAppliedMembers();
      setAppliedList(appliedData["apply-list"]);
    }

    async function fetchAndSetGroupBadgesData() {
      const badgesData = await fetchGroupBadges();
      // dummy data 길이가 불충분하여 임시로 데이터 이어붙임
      setBadgeList([...badgesData["badge-list"], ...tmpAddBadgeData]);
    }

    fetchAndSetGroupPageData();
    fetchAndSetGroupSettingData();
    fetchAndSetGroupBadgesData();
  }, []);

  return (
    <div style={{ marginInline: "8rem" }}>
      <GroupWrapper>
        <div className="group__title-container">
          <div className="title">
            <TeamOutlined className="title-icon" />
            뭉치뭉치똥뭉치네
          </div>
        </div>
        <GroupBadges>
          <div className="title-box">
            <div className="small__title">
              <TrophyFilled className="badge-icon" />
              뭉치뭉치똥뭉치네 업적
              <TrophyFilled className="badge-icon" />
            </div>
            <div className="btn-wrapper">
              <CommonButton
                color={theme.colors.black}
                margin="0 1rem 0 0"
                cursor="true"
                onClick={() => setOpenMemberSettingModal((prev) => !prev)}
              >
                그룹 관리
              </CommonButton>
              <CommonButton
                color={theme.colors.gray500}
                cursor="true"
                onClick={() => setOpenMemberModal((prev) => !prev)}
              >
                그룹원 보기
              </CommonButton>
            </div>
          </div>
          <BadgesContainer>
            {badgePreview.map((badge, index) => (
              <BadgeBox key={index}>
                <div className="badge-cnt">x{badge.count}</div>
                <CommonButton
                  color={getCategoryColor(badge.category)}
                  font="Kanit-Bold"
                  fontWeight={700}
                  margin="0 0 1rem 0"
                >
                  {badge.category}
                </CommonButton>
                <img
                  className="badge-img"
                  src={getCategoryImage(badge.category)}
                  onClick={() => clickBadgeCategory(badge.category)}
                />
              </BadgeBox>
            ))}
          </BadgesContainer>
        </GroupBadges>
        <GroupChallenges>
          <div className="title-box">
            <div className="small__title">66 챌린지 목록</div>
            <div className="btn-wrapper">
              <CommonButton
                color={theme.colors.gray500}
                font="Kanit-Regular"
                cursor="true"
                onClick={() => setOpenNewChallgeModal((prev) => !prev)}
              >
                챌린지 추가
              </CommonButton>
            </div>
          </div>
          <ChallengesContainer>
            {challengeList.map((challenge, index) => (
              <ChallengeBox
                key={index}
                bgImg={Algorithms}
                notStarted={calcDueDate(challenge.startDate) < 0 ? true : false}
                title={challenge.name}
                dueDate={calcDueDate(challenge.startDate)}
                participants={challenge.participants}
                maxCnt={challenge.maxParticipant}
              />
            ))}
            {[...Array(5 - challengeList.length)].map((index) => {
              return <NoChallengeBox key={index} />;
            })}
          </ChallengesContainer>
        </GroupChallenges>
        <BoardContainer>
          <div className="title-box">
            <div className="small__title">게시판</div>
            <div className="btn-wrapper">
              <CommonButton
                color={theme.colors.gray500}
                font="Kanit-Regular"
                cursor="true"
                onClick={() => setOpenNewBoardModal(true)}
              >
                게시글 작성
              </CommonButton>
            </div>
          </div>
          <BoardList>
            {boardDataList?.articles.map((article, index) => (
              <BoardBox
                key={index}
                // title={boardData.title}
                // boardId={boardData.articleId}
                // createdAt={boardData.createdAt}
                // writer={boardData.nickname}
                data={article}
                setBoardData={setBoardData}
                setCommentData={setCommentData}
                setBoardModal={setOpenBoardModal}
              />
            ))}
          </BoardList>
          <div className="pagination-bar-container">
            {boardPage === 1 ? (
              <LeftCircleFilled className="invisible-arrow icon-margin" />
            ) : (
              <LeftCircleFilled
                className="pagination-icon icon-margin"
                onClick={handleClickLeft}
              />
            )}
            {boardPage === Math.ceil(4 / 3) ? (
              <RightCircleFilled className="invisible-arrow" />
            ) : (
              <RightCircleFilled
                className="pagination-icon"
                onClick={handleClickRight}
              />
            )}
          </div>
        </BoardContainer>
      </GroupWrapper>
      <MemberModal
        open={isOpenMemberModal}
        toggleModal={() => setOpenMemberModal((prev) => !prev)}
        members={memberList}
      />
      <GroupSettingModal
        open={isOpenMemberSettingModal}
        toggleModal={() => setOpenMemberSettingModal((prev) => !prev)}
        memberList={memberList}
        appliedList={appliedList}
      />

      <BadgeModal
        open={isOpenBadgeModal}
        toggleModal={() => setOpenBadgeModal((prev) => !prev)}
        badges={filteredBadgeList}
      />
      <ChallengeModal
        open={isOpenNewChallgeModal}
        toggleModal={() => setOpenNewChallgeModal((prev) => !prev)}
      />
      <NewBoardModal
        open={isOpenNewBoardModal}
        toggleModal={() => setOpenNewBoardModal((prev) => !prev)}
      />
      <BoardModal
        open={isOpenBoardModal}
        toggleModal={() => setOpenBoardModal((prev) => !prev)}
        boardData={boardData}
        commentData={commentData}
      />
    </div>
  );
}

const GroupWrapper = styled(Content)`
  margin: 3.5rem 0;

  .small__title {
    font-size: 2.4rem;
    font-weight: 700;
    padding-bottom: 1rem;
  }

  .group__title-container {
    display: flex;
  }

  .btn-wrapper {
    display: flex;
  }

  .title {
    font-size: 3.2rem;
    font-weight: 700;
  }

  .title-icon {
    margin-right: 1rem;
  }

  .title-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
`;

const GroupBadges = styled(Content)`
  padding: 3rem 0;

  .badge-icon {
    margin-right: 1rem;
    color: #ffcc4d;
    font-size: 3.2rem;
  }

  .badge-icon:last-child {
    margin-left: 1rem;
  }
`;

const BadgesContainer = styled(Content)`
  display: flex;
  flex-wrap: wrap;
  padding: 3rem 3rem 2rem;
  width: 100%;
  border-radius: 1rem;
  background-color: ${theme.colors.gray100};
  filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.15));
`;

const BadgeBox = styled(Content)`
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  padding: 0 1rem 1rem;

  .badge-cnt {
    position: absolute;
    bottom: 0;
    right: 15%;
    z-index: 1;
    background-color: ${theme.colors.lightred};
    color: ${theme.colors.white};
    font-size: 1.6rem;
    /* padding: 0.1rem 0.8rem; */
    width: 3.2rem;
    height: 2.4rem;
    text-align: center;
    border-radius: 1.5rem;
  }

  .badge-img {
    width: 10vw;
    height: 10vw;
    border-radius: 1rem;
    object-fit: cover;
    cursor: pointer;
    filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.15));

    &:hover {
      filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.3));
    }
  }
`;

const CommonButton = styled(Content)<ButtonStyled>`
  padding: 0.4rem 1.6rem;
  margin: ${(props) => props.margin};
  background-color: ${(props) => props.color};
  font-family: ${(props) => props.font};
  font-weight: ${(props) => props.fontWeight};
  cursor: ${(props) => (props.cursor === "true" ? "pointer" : null)};
  color: ${theme.colors.white};
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.6rem;
`;

const GroupChallenges = styled(Content)`
  padding: 3rem 0;
`;

const ChallengesContainer = styled(Content)`
  display: flex;
  justify-content: space-between;
`;

const BoardContainer = styled(Content)`
  padding: 3rem 0;

  .pagination-bar-container {
    padding-top: 3rem;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  .pagination-icon {
    font-size: 3.2rem;
    color: ${theme.colors.gray500};
    cursor: pointer;

    &:hover {
      color: #777;
    }
  }

  .invisible-arrow {
    font-size: 3.2rem;
    visibility: hidden;
  }

  .icon-margin {
    margin-right: 5rem;
  }
`;

const BoardList = styled(Content)`
  display: flex;
  justify-content: space-between;
`;
