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

import ChallengeBox from "../components/group/ChallengeBox";
import CreateChallengeModal from "../components/group/CreateChallengeModal";
import MemberModal from "../components/group/MemberModal";
import BadgeModal from "../components/group/BadgeModal";
import NewBoardModal from "../components/group/NewBoardModal";
import {
  fetchAppliedMembers,
  fetchBoardListByPage,
  fetchChallengeList,
  fetchGroupBadges,
  fetchGroupMembers,
  fetchGroupPageData,
} from "../api/group";
import NoChallengeBox from "../components/group/NoChallengeBox";
import { GroupSettingModal } from "../components/group/GroupSettingModal";
import { BoardModal } from "../components/group/BoardModal";

import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { getImagePath } from "../util/common";
import BoardBox from "../components/group/BoardBox";
import { useNavigate } from "react-router-dom";

// const navigate = useNavigate();
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
  imagePath: string;
  challengeName: "알고리즘" | "CS" | "블로깅" | "강의" | "개발서적";
  challengeId: number;
  achievementCount: number;
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
  groupChallengeId: number;
  imagePath: string; // challenge badge image
  challengeName: string; // challenge name
  startAt: Date;
  maxMemberCount: number;
  memberCount: number;
  profileImagePathList: string[];
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

interface CategoryType {
  challengeId: number;
  imagePath: string;
  topic: string;
  selected: boolean;
}

export default function Group() {
  // const location = useLocation();
  // const groupId = location.state.groupId;
  // console.log(groupId);
  const navigate = useNavigate();

  const [isOpenMemberModal, setOpenMemberModal] = useState(false);
  const [isOpenMemberSettingModal, setOpenMemberSettingModal] = useState(false);
  const [isOpenBadgeModal, setOpenBadgeModal] = useState(false);
  const [isOpenNewChallgeModal, setOpenNewChallgeModal] = useState(false);
  const [isOpenNewBoardModal, setOpenNewBoardModal] = useState(false);
  const [isOpenBoardModal, setOpenBoardModal] = useState(false);

  const [boardPage, setBoardPage] = useState<number>(0);
  const [totalPage, setTotalPage] = useState<number>(0);
  const [groupName, setGroupName] = useState<string>("");
  const [authority, setAuthority] = useState<string>("");

  const [badgePreview, setBadgePreview] = useState<BadgePreviewType[]>([]);
  const [challengeList, setChallengeList] = useState<ChallengeType[]>([]);
  const [memberList, setMemberList] = useState<MemberType[]>([]);
  const [appliedList, setAppliedList] = useState<MemberType[]>([]);
  const [badgeList, setBadgeList] = useState<BadgeType[]>([]);
  const [filteredBadgeList, setFilteredBadgeList] = useState<BadgeType[]>([]);
  const [boardDataList, setBoardDataList] = useState<BoardType>({
    articles: [],
  });
  const [boardData, setBoardData] = useState<ArticleType>();
  const [commentData, setCommentData] = useState<CommentType[]>([]);
  const [categories, setCategories] = useState<CategoryType[]>([]);

  // 챌린지 리스트 캐러셀
  const ChallengeListCarousel = () => {
    // 옵션
    const settings = {
      dots: true,
      infinite: false,
      arrows: true,
      speed: 500,
      slidesToShow: 5,
      slidesToScroll: 5,
    };

    return (
      <div className="carousel">
        <Slider {...settings}>
          {challengeList.map((challenge: ChallengeType) => (
            <ChallengeBox
              key={challenge.groupChallengeId}
              bgImg={getImagePath(challenge.imagePath)}
              notStarted={calcDueDate(challenge.startAt) < 0 ? true : false}
              title={challenge.challengeName}
              dueDate={calcDueDate(challenge.startAt)}
              profileList={challenge.profileImagePathList}
              memberCnt={challenge.memberCount}
              maxCnt={challenge.maxMemberCount}
            />
          ))}
          {[...Array(10 - challengeList.length)].map((index: number) => (
            <NoChallengeBox key={index + 1} />
          ))}
        </Slider>
      </div>
    );
  };

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
    // TODO: group page api 완성되면 1대신 groupId 전송
    const newObj = await fetchBoardListByPage(1, page);
    console.log(newObj.articles);
    setTotalPage(Math.ceil(newObj.articles[1] / 3) - 1);
    console.log(newObj.articles[0]);
    setBoardDataList({ articles: newObj.articles[0] });
  }

  function handleClickLeft() {
    // 첫번째 페이지가 아니면
    if (boardPage > 0) {
      const curPage = boardPage;
      console.log(curPage - 1);
      setBoardPage(curPage - 1);
      // 이전 페이지 게시글 리스트 호출
      fetchAndSetNewBoardList(curPage - 1);
    }
  }

  function handleClickRight() {
    // 마지막 페이지가 아니라면
    if (boardPage < totalPage) {
      const curPage = boardPage;
      setBoardPage(curPage + 1);
      // 이후 페이지 게시글 리스트 호출
      fetchAndSetNewBoardList(curPage + 1);
    }
  }

  async function handleClickCreateChallenge(groupId: number) {
    setOpenNewChallgeModal((prev) => !prev);
    const fetchedChallengeList = await fetchChallengeList(groupId);
    fetchedChallengeList.availableGroupChallengeResponseDTOList.forEach(
      (challenge: CategoryType) => (challenge.selected = false)
    );
    setCategories(fetchedChallengeList.availableGroupChallengeResponseDTOList);
  }

  useEffect(() => {
    // 처음 렌더링 시 그룹 페이지 전체 데이터 fetch 메소드
    async function fetchAndSetGroupPageData() {
      const data = await fetchGroupPageData();
      if (data === false) {
        navigate("/");
        return;
      }
      setGroupName(data["group-name"]);
      setBadgePreview(data.achievements);
      setChallengeList(data.challenges);
      setBoardDataList({ articles: data.articles[0] });
      setTotalPage(Math.ceil(data.articles[1] / 3) - 1);
      setAuthority(data.authority);
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
      setBadgeList(badgesData);
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
            {groupName}
          </div>
        </div>
        <GroupBadges>
          <div className="title-box">
            <div className="small__title ellipsis">
              {groupName} 의 업적
              <TrophyFilled className="badge-icon" />
            </div>
            <div className="btn-wrapper">
              {authority === "MAINTAINER" ? (
                <CommonButton
                  color={theme.colors.black}
                  margin="0 1rem 0 0"
                  cursor="true"
                  onClick={() => setOpenMemberSettingModal((prev) => !prev)}
                >
                  그룹 관리
                </CommonButton>
              ) : (
                <></>
              )}

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
            {badgePreview?.map((badge, index) => (
              <BadgeBox key={index}>
                <div className="badge-cnt">
                  {badge.achievementCount > 0
                    ? `x${badge.achievementCount}`
                    : 0}
                </div>
                <CommonButton
                  color={getCategoryColor(badge.challengeName)}
                  font="Kanit-Bold"
                  fontWeight={700}
                  margin="0 0 1rem 0"
                >
                  {badge.challengeName}
                </CommonButton>
                <img
                  className="badge-img"
                  src={getImagePath(badge.imagePath)}
                  onClick={() => clickBadgeCategory(badge.challengeName)}
                />
              </BadgeBox>
            ))}
          </BadgesContainer>
        </GroupBadges>
        <GroupChallenges>
          <div className="title-box">
            <div className="small__title">66 챌린지 목록</div>
            {/* TODO: 그룹장이랑 매니저만 보이게 하기 */}
            {authority === "MAINTAINER" ? (
              <div className="btn-wrapper">
                <CommonButton
                  color={theme.colors.gray500}
                  font="Kanit-Regular"
                  cursor="true"
                  onClick={() => handleClickCreateChallenge(1)}
                >
                  챌린지 추가
                </CommonButton>
              </div>
            ) : (
              <></>
            )}
          </div>
          <ChallengeListCarousel />
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
            {boardDataList.articles ? (
              boardDataList.articles.map(
                (article: ArticleType, index: number) => (
                  <BoardBox
                    key={index}
                    data={article}
                    setBoardData={setBoardData}
                    setCommentData={setCommentData}
                    setBoardModal={setOpenBoardModal}
                  />
                )
              )
            ) : (
              <div>게시글 목록이 없습니다.</div>
            )}
            {}
          </BoardList>
          <div className="pagination-bar-container">
            {boardPage === 0 ? (
              <LeftCircleFilled className="invisible-arrow icon-margin" />
            ) : (
              <LeftCircleFilled
                className="pagination-icon icon-margin"
                onClick={handleClickLeft}
              />
            )}
            {/* TODO: 추후에 group page api 완성된 후 board 전체 갯수를 3으로 나누기 */}
            {boardPage === totalPage ? (
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
        setMemberList={setMemberList}
        appliedList={appliedList}
        setAppliedList={setAppliedList}
      />

      <BadgeModal
        open={isOpenBadgeModal}
        toggleModal={() => setOpenBadgeModal((prev) => !prev)}
        badges={filteredBadgeList}
      />
      <CreateChallengeModal
        open={isOpenNewChallgeModal}
        toggleModal={() => setOpenNewChallgeModal((prev) => !prev)}
        categories={categories}
        setCategories={setCategories}
        setChallengeList={setChallengeList}
      />
      <NewBoardModal
        open={isOpenNewBoardModal}
        toggleModal={() => setOpenNewBoardModal((prev) => !prev)}
        setBoardDataList={setBoardDataList}
        setTotalPage={setTotalPage}
        setBoardPage={setBoardPage}
      />
      <BoardModal
        open={isOpenBoardModal}
        toggleModal={() => setOpenBoardModal((prev) => !prev)}
        boardData={boardData}
        setBoardData={setBoardData}
        setBoardDataList={setBoardDataList}
        commentData={commentData}
        setCommentData={setCommentData}
        setTotalPage={setTotalPage}
        setBoardPage={setBoardPage}
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
    filter: drop-shadow(4px 4px 2px rgba(0, 0, 0, 0.15));
  }

  .badge-icon:last-child {
    margin-left: 1rem;
  }

  .ellipsis {
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-all;

    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
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
  text-align: center;
  word-break: keep-all;
`;

const GroupChallenges = styled(Content)`
  padding: 3rem 0;
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
