import { Modal, Layout, Tabs } from "antd";
import type { TabsProps } from "antd";
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
  authority: "MEMBER" | "OWNER" | "MANAGER";
}

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  memberList: MemberType[];
  appliedList: MemberType[];
}

const { Content } = Layout;

export function GroupSettingModal(props: PropsType) {
  // 그룹원 관리 Tab
  const FirstTabContent = () => {
    return (
      <>
        <TabContentWrapper>
          {props.memberList.map((member: MemberType, index: number) => (
            <div className="member-setting-container">
              <SingleMemberListBox
                key={index}
                profile={member.image}
                nickname={member.nickname}
                owner={member.authority === "OWNER" ? true : false}
                manager={member.authority === "MANAGER" ? true : false}
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
      </>
    );
  };

  // 대기 중인 요청 Tab
  const SecondTabContent = () => {
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
      </>
    );
  };

  const items: TabsProps["items"] = [
    {
      key: "1",
      label: `그룹원 관리`,
      children: <FirstTabContent />,
    },
    {
      key: "2",
      label: `대기 중인 요청`,
      children: <SecondTabContent />,
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
        <MemberSettingModalWrapper>
          <div className="modal-title">그룹 관리</div>
          <TabContainer>
            <Tabs className="tab-list" defaultActiveKey="1" items={items} />
          </TabContainer>
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

  .ant-tabs,
  .ant-tabs-tab-btn {
    font-size: 1.5rem;
  }

  .ant-tabs-tab-active {
    font-weight: ${theme.fontWeight.bold};
  }
`;

const TabContentWrapper = styled(Content)`
  padding-top: 5rem;

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
