import React, { useState } from "react";
import { Layout, Checkbox } from "antd";
import type { CheckboxChangeEvent } from "antd/es/checkbox";
import styled from "styled-components";
import { TeamOutlined, TrophyFilled, SettingFilled } from "@ant-design/icons";
import { theme } from "../styles/theme";
import Algorithms from "../assets/algorithm_badge.png";
import CS from "../assets/cs_badge.png";
import Blog from "../assets/blog_badge.png";
import Lecture from "../assets/lecture_badge.png";
import Book from "../assets/book_badge.jpeg";
import ChallengeBox from "../components/ChallengeBox";
import { BoardBox } from "../components/BoardBox";
import { Modal } from "../components/common/Modal";
import SingleMemberListBox from "../components/SingleMemberListBox";

interface ButtonStyled {
  color?: string;
  font?: string;
  fontWeight?: number;
  margin?: string;
  cursor?: boolean;
}

const { Content } = Layout;

const badges = [
  { name: "알고리즘", img: Algorithms, color: theme.colors.purple },
  { name: "CS", img: CS, color: theme.colors.pink },
  { name: "블로깅", img: Blog, color: theme.colors.orange },
  { name: "강의", img: Lecture, color: theme.colors.mint },
  { name: "개발서적", img: Book, color: theme.colors.lightred },
];

export default function Group() {
  const [memberListModal, setMemberListModal] = useState<boolean>(false);
  const [memberSettingModal, setMemberSettingModal] = useState(false);
  const [createChallengeModal, setCreateChallengeModal] = useState(false);
  const [tab, setTab] = useState(0);

  const [challengeList, setChallengeList] = useState([
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
  ]);

  const [memberList, setMemberList] = useState([
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
  ]);

  const TabContent = ({ ...props }) => {
    if (props.tab === 0) {
      return (
        <TabContentWrapper>
          {memberList.map((member) => (
            <div className="member-setting-container">
              <SingleMemberListBox
                profile={member.profile}
                nickname={member.nickname}
                owner={member.owner}
                manager={member.manager}
                badge={member.badge}
              />
              <div className="setting-btn-box">
                <CommonButton
                  className="setting-btn"
                  color={theme.colors.lightblue}
                >
                  매니저 지정
                </CommonButton>
                <CommonButton
                  className="setting-btn"
                  color={theme.colors.failure}
                >
                  강퇴하기
                </CommonButton>
              </div>
            </div>
          ))}
        </TabContentWrapper>
      );
    } else {
      return (
        <TabContentWrapper>
          {memberList.map((member) => (
            <div className="member-setting-container">
              <SingleMemberListBox
                profile={member.profile}
                nickname={member.nickname}
                // owner={member.owner}
                // manager={member.manager}
                badge={member.badge}
              />
              <div className="setting-btn-box">
                <CommonButton
                  className="setting-btn"
                  color={theme.colors.lightblue}
                >
                  수락
                </CommonButton>
                <CommonButton
                  className="setting-btn"
                  color={theme.colors.failure}
                >
                  거절
                </CommonButton>
              </div>
            </div>
          ))}
        </TabContentWrapper>
      );
    }
  };

  function clickFirstTab() {
    document.getElementById("second-tab")?.classList.remove("active-tab");
    document.getElementById("first-tab")?.classList.add("active-tab");
    setTab(0);
  }

  function clickSecondTab() {
    document.getElementById("first-tab")?.classList.remove("active-tab");
    document.getElementById("second-tab")?.classList.add("active-tab");
    setTab(1);
  }

  const onChange = (e: CheckboxChangeEvent) => {
    // 자기 자신을 제외한 체크박스 모두 해제
    console.log(`checked = ${e.target.checked}`);
  };

  return (
    <>
      {createChallengeModal ? (
        <Modal closeModal={() => setCreateChallengeModal(false)}>
          <CreateChallengeModalWrapper>
            <div className="modal-title">카테고리를 선택해주세요</div>
            <div className="modal-info">
              카테고리당 하나씩만 습관 형성이 가능합니다.
            </div>
            <CategoryBoxContainer>
              <div className="category-box">
                <Checkbox className="category-checkbox" onChange={onChange} />
                <img className="category-img" src={Algorithms} />
                <div className="category-info">알고리즘</div>
              </div>
              <div className="category-box">
                <Checkbox className="category-checkbox" onChange={onChange} />
                <img className="category-img" src={Lecture} />
                <div className="category-info">강의 시청</div>
              </div>
              <div className="category-box">
                <Checkbox className="category-checkbox" onChange={onChange} />
                <img className="category-img" src={Blog} />
                <div className="category-info">기술 블로그</div>
              </div>
              <div className="category-box">
                <Checkbox className="category-checkbox" onChange={onChange} />
                <img className="category-img" src={Book} />
                <div className="category-info">개발 서적</div>
              </div>
              <div className="category-box">
                <Checkbox className="category-checkbox" onChange={onChange} />
                <img className="category-img" src={CS} />
                <div className="category-info">CS 공부</div>
              </div>
            </CategoryBoxContainer>
          </CreateChallengeModalWrapper>
        </Modal>
      ) : null}

      {memberSettingModal ? (
        <Modal closeModal={() => setMemberSettingModal(false)}>
          <MemberSettingModalWrapper>
            <div className="modal-title">그룹 관리</div>
            <TabContainer>
              <ul className="tab-list">
                <li
                  id="first-tab"
                  className="active-tab"
                  onClick={clickFirstTab}
                >
                  그룹원 관리
                </li>
                |
                <li id="second-tab" onClick={clickSecondTab}>
                  대기 중인 요청
                </li>
              </ul>
            </TabContainer>
            <TabContent tab={tab} />
          </MemberSettingModalWrapper>
        </Modal>
      ) : null}
      {memberListModal ? (
        <Modal closeModal={() => setMemberListModal(false)}>
          <MemberModalWrapper>
            <div className="modal-title">그룹원 목록</div>
            <MemberListContainer>
              {memberList.map((member) => (
                <SingleMemberListBox
                  profile={member.profile}
                  nickname={member.nickname}
                  owner={member.owner}
                  manager={member.manager}
                  badge={member.badge}
                />
              ))}
            </MemberListContainer>
          </MemberModalWrapper>
        </Modal>
      ) : null}
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
                font="Kanit-Regular"
                margin="0 1rem 0 0"
                cursor={true}
                onClick={() => setMemberSettingModal(true)}
              >
                그룹 관리
              </CommonButton>
              <CommonButton
                color={theme.colors.gray500}
                font="Kanit-Regular"
                cursor={true}
                onClick={() => setMemberListModal(true)}
              >
                그룹원 보기
              </CommonButton>
            </div>
          </div>
          <BadgesContainer>
            {badges.map((badge, index) => (
              <BadgeBox key={index}>
                <div className="badge-cnt">x3</div>
                <CommonButton
                  color={badge.color}
                  font="Kanit-Bold"
                  fontWeight={700}
                  margin="0 0 1rem 0"
                >
                  {badge.name}
                </CommonButton>
                <img className="badge-img" src={badge.img} />
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
                cursor={true}
                onClick={() => setCreateChallengeModal(true)}
              >
                챌린지 추가
              </CommonButton>
            </div>
          </div>
          <ChallengesContainer>
            {challengeList.map((challenge) => (
              <ChallengeBox
                bgImg={challenge.bgImg}
                notStarted={challenge.notStarted}
                title={challenge.title}
                dueDate={challenge.dueDate}
                profile={challenge.profile}
                cnt={challenge.cnt}
              />
            ))}
          </ChallengesContainer>
        </GroupChallenges>
        <BoardContainer>
          <div className="title-box">
            <div className="small__title">게시판</div>
            <div className="btn-wrapper">
              <CommonButton
                color={theme.colors.gray500}
                font="Kanit-Regular"
                cursor={true}
              >
                게시글 작성
              </CommonButton>
            </div>
          </div>
          <BoardList>
            <BoardBox
              title={"혹시 알고리즘 스터디 하실 분?"}
              date={"2023.04.20."}
              writer={"뽀삐"}
            />
            <BoardBox
              title={"혹시 알고리즘 스터디 하실 분?"}
              date={"2023.04.20."}
              writer={"뽀삐"}
              admin={true}
            />
            <BoardBox
              title={"혹시 알고리즘 스터디 하실 분?"}
              date={"2023.04.20."}
              writer={"뽀삐"}
            />
          </BoardList>
        </BoardContainer>
      </GroupWrapper>
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
  cursor: ${(props) => (props.cursor ? "pointer" : null)};
  color: ${theme.colors.white};
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
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
`;

const BoardList = styled(Content)`
  display: flex;
  justify-content: space-between;
`;

const MemberModalWrapper = styled(Content)`
  height: fit-content;
  max-height: 90vh;
  overflow-y: scroll;
  background-color: ${theme.colors.white};
  padding: 4.8rem;
  border-radius: 1rem;
`;

const MemberListContainer = styled(Content)`
  display: flex;
  flex-direction: column;
`;

const MemberSettingModalWrapper = styled(Content)`
  height: fit-content;
  max-height: 90vh;
  overflow-y: scroll;
  background-color: ${theme.colors.white};
  padding: 6.4rem 8rem;
  border-radius: 1rem;
`;

const TabContainer = styled(Content)`
  display: flex;
  flex-direction: column;

  .tab-list {
    padding-left: 0;
    display: flex;
  }

  .tab-list li {
    list-style: none;
    padding-right: 1rem;
    color: ${theme.colors.gray400};
    cursor: pointer;

    &:hover {
      color: ${theme.colors.black};
    }
  }

  .tab-list li:last-child {
    padding-left: 1rem;
    padding-right: 0;
  }

  .active-tab {
    color: ${theme.colors.black} !important;
    font-weight: 700;
  }
`;

const TabContentWrapper = styled(Content)`
  padding-top: 5rem;

  .member-setting-container {
    display: flex;
    align-items: center;
  }

  .setting-btn-box {
    display: flex;
    padding-left: 1rem;
  }

  .setting-btn {
    margin-left: 1rem;
    cursor: pointer;
  }
`;

const CreateChallengeModalWrapper = styled(Content)`
  width: 50vw;
  height: fit-content;
  max-height: 90vh;
  overflow-y: scroll;
  background-color: ${theme.colors.white};
  padding: 6.4rem 8rem;
  border-radius: 1rem;

  .modal-info {
    margin-top: -3rem;
    color: ${theme.colors.gray500};
  }
`;

const CategoryBoxContainer = styled(Content)`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  padding-top: 2rem;

  .category-box {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 2px solid ${theme.colors.gray200};
    border-radius: 1rem;
    width: 45%;
    padding: 1.6rem 5%;
    margin-bottom: 1rem;
    box-sizing: border-box;
  }

  .category-box:nth-child(5),
  .category-box:nth-child(6) {
    margin-bottom: 0;
  }

  .category-checkbox {
    position: absolute;
    top: 1rem;
    right: 1rem;
  }

  .category-img {
    width: 8rem;
    height: 8rem;
    border-radius: 2rem;
    object-fit: cover;
  }

  .category-info {
    font-size: 1.5rem;
    font-weight: 700;
  }
`;
