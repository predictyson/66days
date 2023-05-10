import { useState } from "react";
import { Modal, Layout } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import SingleMemberListBox from "./SingleMemberListBox";

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

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  memberList: MemberType[];
  appliedList: MemberType[];
}

const { Content } = Layout;

export function GroupSettingModal(props: PropsType) {
  console.log(props.memberList);
  const [tab, setTab] = useState<number>(0);

  const TabContent = () => {
    if (tab === 0) {
      return (
        <TabContentWrapper>
          {props.memberList.map((member: MemberType, index: number) => (
            <div className="member-setting-container">
              <SingleMemberListBox
                key={index}
                profile={member.image}
                nickname={member.nickname}
                owner={member.role === "owner" ? true : false}
                manager={member.role === "manager" ? true : false}
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
          {props.appliedList.map((member: MemberType) => (
            <div className="member-setting-container">
              <SingleMemberListBox
                profile={member.image}
                nickname={member.nickname}
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
  return (
    <>
      <Modal
        open={props.open}
        onCancel={props.toggleModal}
        width={800}
        footer={null}
      >
        <MemberSettingModalWrapper>
          <div className="modal-title">그룹 관리</div>
          <TabContainer>
            <ul className="tab-list">
              <li id="first-tab" className="active-tab" onClick={clickFirstTab}>
                그룹원 관리
              </li>
              |
              <li id="second-tab" onClick={clickSecondTab}>
                대기 중인 요청
              </li>
            </ul>
          </TabContainer>
          <TabContent />
        </MemberSettingModalWrapper>
      </Modal>
    </>
  );
}

const MemberSettingModalWrapper = styled(Content)`
  height: fit-content;
  max-height: 90vh;
  overflow-y: scroll;
  background-color: ${theme.colors.white};
  padding: 6.4rem 8rem;
  border-radius: 1rem;

  .modal-title {
    font-size: 2.4rem;
    font-weight: 700;
    padding-bottom: 3.2rem;
  }
`;

const TabContainer = styled(Content)`
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;

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
