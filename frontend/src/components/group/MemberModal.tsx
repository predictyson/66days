import { Modal } from "antd";
import SingleMemberListBox from "../../components/SingleMemberListBox";
import styled from "styled-components";
import { Content } from "antd/es/layout/layout";

interface MemberType {
  profile: string;
  nickname: string;
  owner: boolean;
  manager: boolean;
  badge: number;
}

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  members: MemberType[];
}

export default function MemberModal(props: PropsType) {
  return (
    <Modal open={props.open} onCancel={props.toggleModal} footer={null}>
      <MemberModalWrapper>
        <div className="member__modal-title">그룹원 목록</div>
        <MemberListContainer>
          {props.members.map((member) => (
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
  );
}
const MemberModalWrapper = styled(Content)`
  max-height: 70vh;
  overflow-y: scroll;
  padding: 4.8rem;
  border-radius: 1rem;

  .member__modal-title {
    font-size: 2.4rem;
    font-weight: 700;
    padding-bottom: 3.2rem;
  }
`;

const MemberListContainer = styled(Content)`
  display: flex;
  flex-direction: column;
`;
