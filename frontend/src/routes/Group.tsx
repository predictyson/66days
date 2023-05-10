import { useEffect, useState } from "react";

import { Layout, Modal } from "antd";
import styled from "styled-components";
import {
  TeamOutlined,
  TrophyFilled,
  SendOutlined,
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
import { mockMemberList, mockBoardData, mockBadgeList } from "../mock/group";
import ChallengeModal from "../components/group/ChallengeModal";
import MemberModal from "../components/group/MemberModal";
import BadgeModal from "../components/group/BadgeModal";
import NewBoardModal from "../components/group/NewBoardModal";
import { fetchGroupMembers, fetchGroupPageData } from "../api/group";
import NoChallengeBox from "../components/group/NoChallengeBox";
import { GroupSettingModal } from "../components/group/GroupSettingModal";

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
  role: string;
}

interface BadgePreviewType {
  image: string;
  category: string;
  count: number;
}

interface BadgeType {
  category: string;
  image: string;
  title: string;
  startDate: string;
  endDate: string;
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

interface BoardType {
  articles: {
    title: string;
    author: string;
    author_role: string;
    time: Date;
  }[];
  pageNo: number;
}

export default function Group() {
  const [boardModal, setBoardModal] = useState(false);

  const [isOpenMemberModal, setOpenMemberModal] = useState(false);
  const [isOpenMemberSettingModal, setOpenMemberSettingModal] = useState(false);
  const [isOpenBadgeModal, setOpenBadgeModal] = useState(false);
  const [isOpenNewChallgeModal, setOpenNewChallgeModal] = useState(false);
  const [isOpenNewBoardModal, setOpenNewBoardModal] = useState(false);

  const [badgePreview, setBadgePreview] = useState<BadgePreviewType[]>([]);
  const [challengeList, setChallengeList] = useState<ChallengeType[]>([]);
  const [memberList, setMemberList] = useState<MemberType[]>([]);
  const [badgeList, setBadgeList] = useState<BadgeType[]>([]);
  const [boardDataList, setBoardDataList] = useState<BoardType>();

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
    console.log(Math.round(diffDate / (1000 * 60 * 60 * 24)));

    return Math.round(diffDate / (1000 * 60 * 60 * 24));
  }

  useEffect(() => {
    // TODO: fetch data
    async function fetchAndSetGroupPageData() {
      const data = await fetchGroupPageData();
      console.log(data);
      setBadgePreview(data.badges);
      setChallengeList(data.challenge);
      setBoardDataList(data.board);
      console.log(data.board);
    }

    async function fetchAndSetGroupMembers() {
      const data = await fetchGroupMembers();
      setMemberList(data["member-list"]);
    }

    fetchAndSetGroupPageData();
    fetchAndSetGroupMembers();
    setBadgeList(mockBadgeList);
  }, []);

  function handlePageChange(page: number) {
    // 해당 pgNo board data 호출
  }

  return (
    <>
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
                  onClick={() => setOpenBadgeModal((prev) => !prev)}
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
            {boardDataList?.articles.map((boardData, index) => (
              <BoardBox
                key={index}
                title={boardData.title}
                date={boardData.time}
                writer={boardData.author}
                setBoardModal={setBoardModal}
              />
            ))}
          </BoardList>
          <div className="pagination-bar-container">
            <LeftCircleFilled className="pagination-icon" />
            <RightCircleFilled className="pagination-icon" />
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
      />

      <BadgeModal
        open={isOpenBadgeModal}
        toggleModal={() => setOpenBadgeModal((prev) => !prev)}
        badges={badgeList}
      />
      <ChallengeModal
        open={isOpenNewChallgeModal}
        toggleModal={() => setOpenNewChallgeModal((prev) => !prev)}
      />
      <NewBoardModal
        open={isOpenNewBoardModal}
        toggleModal={() => setOpenNewBoardModal((prev) => !prev)}
      />
      <Modal
        open={boardModal}
        width={800}
        footer={null}
        onCancel={() => setBoardModal(false)}
      >
        <BoardModalWrapper>
          <div className="modal-title">{mockBoardData.title}</div>
          <div className="board__modal-info">
            <div className="board-date">{mockBoardData.date}</div>
            <div className="board-writer">작성자: {mockBoardData.writer}</div>
          </div>
          <BoardContentBox>
            <div className="board-content">{mockBoardData.content}</div>
          </BoardContentBox>
          <BoardCommentContainer>
            <div className="comment-box-title">댓글</div>
            <CommentBox>
              <SingleComment>
                <div className="writer-profile-box">
                  <img
                    className="comment-writer-profile"
                    src="data:image/jpeg;base64,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"
                  />
                </div>
                <div className="writer-info-box">
                  <div className="writer-info-top">
                    <div className="comment-writer-nickname">해피</div>
                    <div className="comment-date">2023.04.19.</div>
                  </div>
                  <div className="comment-content">
                    해피한뽀삐해피한뽀삐해피한뽀삐해피한
                    해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐해피한뽀삐
                  </div>
                </div>
                <div className="comment-delete-box">
                  <div className="delete-btn">Delete</div>
                </div>
              </SingleComment>
              <SingleComment>
                <div className="writer-profile-box">
                  <img
                    className="comment-writer-profile"
                    src="data:image/jpeg;base64,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"
                  />
                </div>
                <div className="writer-info-box">
                  <div className="writer-info-top">
                    <div className="comment-writer-nickname">해피</div>
                    <div className="comment-date">2023.04.19.</div>
                  </div>
                  <div className="comment-content">
                    해피한뽀삐해피한뽀삐해피한뽀삐해피한
                  </div>
                </div>
                <div className="comment-delete-box">
                  <div className="delete-btn">Delete</div>
                </div>
              </SingleComment>
              <SingleComment>
                <div className="writer-profile-box">
                  <img
                    className="comment-writer-profile"
                    src="data:image/jpeg;base64,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"
                  />
                </div>
                <div className="writer-info-box">
                  <div className="writer-info-top">
                    <div className="comment-writer-nickname">해피</div>
                    <div className="comment-date">2023.04.19.</div>
                  </div>
                  <div className="comment-content">
                    해피한뽀삐해피한뽀삐해피한뽀삐해피한
                  </div>
                </div>
                <div className="comment-delete-box">
                  <div className="delete-btn">Delete</div>
                </div>
              </SingleComment>
            </CommentBox>
            <CommentInputBox>
              <input
                className="comment-input"
                placeholder="댓글을 입력해주세요"
              />
              <div className="send-btn">
                Send
                <SendOutlined className="send-icon" />
              </div>
            </CommentInputBox>
          </BoardCommentContainer>
        </BoardModalWrapper>
      </Modal>
    </>
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

  .pagination-icon:first-child {
    margin-right: 5rem;
  }
`;

const BoardList = styled(Content)`
  display: flex;
  justify-content: space-between;
`;

const BoardModalWrapper = styled(Content)`
  display: flex;
  flex-direction: column;
  height: fit-content;
  max-height: 90vh;
  overflow-y: scroll;
  background-color: ${theme.colors.white};
  font-size: 1.6rem;
  padding: 6.4rem;
  border-radius: 1rem;

  .modal-title {
    font-size: 2.4rem;
    font-weight: 700;
    padding-bottom: 3.2rem;
  }

  .board__modal-info {
    display: flex;
    justify-content: space-between;
    color: ${theme.colors.gray500};
    padding-bottom: 1rem;
    border-bottom: 2px solid ${theme.colors.gray300};
  }
`;

const BoardContentBox = styled(Content)`
  padding: 2rem 0;
  box-sizing: border-box;

  .board-content {
    padding-bottom: 2rem;
    border-bottom: 2px solid ${theme.colors.gray300};
    overflow-y: scroll;
    max-height: 20vh;
    font-size: 1.6rem;
    font-weight: 700;
  }
`;

const BoardCommentContainer = styled(Content)`
  padding: 2.4rem 0;

  .comment-box-title {
    font-size: 2rem;
    font-weight: 700;
    padding-bottom: 1rem;
  }
`;

const CommentBox = styled(Content)`
  display: flex;
  flex-direction: column;
  height: 25vh;
  min-height: min-content;
  overflow-y: scroll;
  margin-bottom: 2rem;
`;

const CommentInputBox = styled(Content)`
  display: flex;
  font-size: 1.6rem;

  .comment-input {
    flex-grow: 1;
    padding-left: 2rem;
    border-radius: 0.8rem;
    border: 1px solid ${theme.colors.gray300};
  }

  .send-btn {
    background-color: #6350b6;
    border-radius: 0.8rem;
    padding: 0.8rem 1.8rem;
    font-family: "Kanit-Regular";
    color: ${theme.colors.white};
    margin-left: 1.6rem;
    cursor: pointer;
  }

  .send-icon {
    padding-left: 1rem;
  }
`;

const SingleComment = styled(Content)`
  display: flex;
  align-items: start;
  /* min-height: 6rem; */
  min-height: fit-content;
  padding-top: 1rem;
  border-bottom: 1px solid ${theme.colors.gray300};
  font-size: 1.6rem;

  .comment-writer-profile {
    width: 4.8rem;
    height: 4.8rem;
    object-fit: cover;
    border-radius: 50%;
    cursor: pointer;
  }

  .writer-profile-box {
    width: 4.8rem;
  }

  .writer-info-box {
    padding: 0 2rem 1rem;
    flex-grow: 1;
  }

  .writer-info-top {
    display: flex;
    padding-bottom: 0.5rem;
  }

  .comment-writer-nickname {
    font-weight: 700;
    padding-right: 2rem;
  }

  .comment-date {
    color: ${theme.colors.gray400};
  }

  .comment-delete-box {
    display: flex;
    align-items: center;
    height: max-content;
    padding-left: 1rem;
    justify-content: center;
  }

  .delete-btn {
    background-color: ${theme.colors.failure};
    border-radius: 0.8rem;
    padding: 0.8rem 1.8rem;
    font-family: "Kanit-Regular";
    color: ${theme.colors.white};
    cursor: pointer;
  }
`;
