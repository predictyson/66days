import { Modal } from "antd";
import styled from "styled-components";
import { Content } from "antd/es/layout/layout";

interface BadgeType {
  category: string;
  badgeImg: string;
  title: string;
  startDate: string;
  endDate: string;
  status: boolean; // 성공 실패 여부
}

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  badges: BadgeType[];
}

export default function BadgeModal(props: PropsType) {
  return (
    <CustomModal
      open={props.open}
      onCancel={props.toggleModal}
      width={800}
      footer={null}
    >
      <BadgeModalWrapper>d</BadgeModalWrapper>
    </CustomModal>
  );
}

const CustomModal = styled(Modal)`
  .ant-modal-content {
    padding: 0;
  }
`;
const BadgeModalWrapper = styled(Content)`
  max-height: 70vh;
  padding: 8rem;
`;
