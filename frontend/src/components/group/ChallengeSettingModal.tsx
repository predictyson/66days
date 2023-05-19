import { Modal, Layout, Tabs } from "antd";
import type { TabsProps } from "antd";

import styled from "styled-components";
import { theme } from "../../styles/theme";
import SingleMemberListBox from "./SingleMemberListBox";
import {
  fetchAppliedChallengeMembers,
  handleChallengeApplication,
} from "../../api/group";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  groupChallengeId: number;
  appliedList: MemberType[];
  setAppliedList: React.Dispatch<React.SetStateAction<MemberType[]>>;
}

interface ButtonStyled {
  color?: string;
  hoverColor?: string;
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

const { Content } = Layout;

export default function ChallengeSettingModal(props: PropsType) {
  // 그룹원 관리 Tab
  const FirstTabContent = () => {
    return (
      <>
        <TabContentWrapper>
          {props.appliedList.length === 0 ? (
            <div className="no-applied-members">대기 중인 요청이 없습니다.</div>
          ) : (
            <></>
          )}
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
                  hoverColor={theme.colors.hoverLightBlue}
                  onClick={() =>
                    handleChallengeAcception(1, "ACCEPTED", member.nickname)
                  }
                >
                  수락
                </CommonButton>
                <CommonButton
                  className="setting-btn"
                  color={theme.colors.failure}
                  hoverColor={theme.colors.hoverFailure}
                  onClick={() =>
                    handleChallengeAcception(1, "REJECTED", member.nickname)
                  }
                >
                  거절
                </CommonButton>
              </div>
            </div>
          ))}
        </TabContentWrapper>
      </>
    );
  };

  async function fetchAndUpdateChallengeAppliedMembers(
    groupChallengeId: number
  ) {
    const appliedMembersData = await fetchAppliedChallengeMembers(
      groupChallengeId
    );
    props.setAppliedList(appliedMembersData["apply-list"]);
  }

  async function handleChallengeAcception(
    groupChallengeId: number,
    status: string,
    userName: string
  ) {
    const resp = await handleChallengeApplication(
      groupChallengeId,
      status,
      userName
    );
    console.log(resp);
    if (resp) {
      fetchAndUpdateChallengeAppliedMembers(groupChallengeId);
    }
  }

  const items: TabsProps["items"] = [
    {
      key: "1",
      label: `챌린지원 관리`,
      children: <FirstTabContent />,
    },
  ];

  return (
    <>
      <Modal
        open={props.open}
        onCancel={props.toggleModal}
        width={800}
        footer={null}
      >
        <ChallengeSettingWrapper>
          <div className="modal-title">챌린지 관리</div>
          <TabContainer>
            <Tabs className="tab-list" defaultActiveKey="1" items={items} />
          </TabContainer>
        </ChallengeSettingWrapper>
      </Modal>
    </>
  );
}

const ChallengeSettingWrapper = styled.div`
  padding: 6.4rem 8rem;
  .modal-title {
    font-size: 2.4rem;
    font-weight: 700;
    padding-bottom: 3.2rem;
    text-align: center;
  }
`;

const TabContainer = styled(Content)`
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;

  .ant-tabs,
  .ant-tabs-tab-btn {
    font-size: 1.5rem;
  }

  .ant-tabs-tab-active {
    font-weight: ${theme.fontWeight.bold};
  }
`;

const TabContentWrapper = styled(Content)`
  padding-top: 2rem;

  .no-applied-members {
    font-size: 1.5rem;
    text-align: center;
  }

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

  .manager-desc {
    font-size: 1.5rem;
  }

  .red-dot {
    padding-right: 0.5rem;
    color: ${theme.colors.failure};
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

  &:hover {
    background-color: ${(props) => props.hoverColor};
  }
`;
