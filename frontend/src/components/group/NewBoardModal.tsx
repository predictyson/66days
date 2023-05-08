import { Modal } from "antd";
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
        footer={null}
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
          <div className="new__board__btn-list">
            <div className="cancel-btn btn">cancel</div>
            <div className="upload-btn btn">upload</div>
          </div>
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
  padding: 6rem 8rem;

  .modal-title {
    width: 100%;
    text-align: center;
    font-size: 2.4rem;
    font-weight: 700;
  }

  .new__board__btn-list {
    display: flex;
    padding-top: 2rem;
  }

  .new__board__btn-list .btn {
    font-family: "Kanit-Semibold";
    width: 10rem;
    height: 4rem;
    line-height: 4rem;
    font-size: 2rem;
    text-align: center;
    border-radius: 5px;
    cursor: pointer;
  }

  .cancel-btn {
    background-color: ${theme.colors.black};
    color: ${theme.colors.white};

    &:hover {
      border: 2px solid ${theme.colors.black};
      box-sizing: border-box;
      background-color: ${theme.colors.white};
      color: ${theme.colors.black};
    }
  }

  .upload-btn {
    border: 2px solid ${theme.colors.black};
    box-sizing: border-box;
    margin-left: 2rem;

    &:hover {
      border: none;
      background-color: ${theme.colors.black};
      color: ${theme.colors.white};
    }
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
