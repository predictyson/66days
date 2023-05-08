import { Button, Modal } from "antd";
import { Content } from "antd/es/layout/layout";
import styled from "styled-components";
import { theme } from "../../styles/theme";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
}

export default function NewBoardModal(props: PropsType) {
  return (
    <>
      <CustomModal
        open={props.open}
        onCancel={props.toggleModal}
        width={800}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={props.toggleModal}
            style={{
              backgroundColor: "black",
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
            }}
          >
            cancel
          </Button>,
          <Button
            key="submit"
            style={{
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
              marginRight: "6rem",
              marginBottom: "4rem",
            }}
          >
            continue
          </Button>,
        ]}
      >
        <NewBoardModalWrapper>
          <div className="modal-title">게시판 글 작성</div>
          <NewBoardContentContainer>
            <TitleBox>
              <div className="title">제목</div>
              <input placeholder="게시판 제목을 입력해주세요." />
            </TitleBox>
            <ContentBox>
              <div className="title">내용</div>
              <textarea placeholder="게시판 내용을 입력해주세요." />
            </ContentBox>
          </NewBoardContentContainer>
        </NewBoardModalWrapper>
      </CustomModal>
    </>
  );
}

const CustomModal = styled(Modal)`
  .ant-modal-content {
    padding: 0;
  }
`;

const NewBoardModalWrapper = styled(Content)`
  padding: 6rem 8rem 1rem;

  .modal-title {
    width: 100%;
    text-align: center;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.bold};
  }
`;

const NewBoardContentContainer = styled(Content)`
  display: flex;
  flex-direction: column;

  .title {
    font-size: 2rem;
    font-weight: 700;
    padding-bottom: 1rem;
  }
`;

const TitleBox = styled(Content)`
  display: flex;
  flex-direction: column;
  padding: 3rem 0;

  input {
    width: 100%;
    border: 1px solid ${theme.colors.gray400};
    border-radius: 5px;
    font-size: 1.5rem;
    padding: 0 1.5rem;
    height: 3.5rem;
  }
`;

const ContentBox = styled(Content)`
  display: flex;
  flex-direction: column;

  textarea {
    width: 100%;
    border: 1px solid ${theme.colors.gray400};
    border-radius: 5px;
    font-size: 1.5rem;
    padding: 1.5rem;
    height: 25rem;
    resize: none;
  }
`;
