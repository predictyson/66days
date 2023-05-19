import { Button, Modal } from "antd";
import { Content } from "antd/es/layout/layout";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { useRef } from "react";
import { fetchBoardListByPage, postBoard } from "../../api/group";

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

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  setBoardDataList: React.Dispatch<React.SetStateAction<BoardType>>;
  setTotalPage: React.Dispatch<React.SetStateAction<number>>;
  setBoardPage: React.Dispatch<React.SetStateAction<number>>;
}

interface Board {
  title: string;
  content: string;
}

export default function NewBoardModal(props: PropsType) {
  const titleInputRef = useRef<HTMLInputElement>(null);
  const contentInputRef = useRef<HTMLTextAreaElement>(null);

  async function handleSubmit() {
    // title과 content가 모두 작성되었다면
    if (titleInputRef.current?.value && contentInputRef.current?.value) {
      const newBoard: Board = {
        title: titleInputRef.current.value,
        content: contentInputRef.current.value,
      };
      // TODO: 나중에 하드코딩 된 부분 groupId로 수정
      const resp = await postBoard(3, newBoard);
      if (resp) {
        titleInputRef.current.value = "";
        contentInputRef.current.value = "";
        // TODO: API 나온 후 게시판 리렌더링하기
        fetchAndSetNewBoardList(0);
        props.toggleModal();
      }
    } else {
      console.log("미작성된 부분이 있음");
    }
  }

  async function fetchAndSetNewBoardList(page: number) {
    // TODO: group page api 완성되면 1대신 groupId 전송
    const newBoardList = await fetchBoardListByPage(3, page);
    console.log(newBoardList);
    props.setBoardDataList({ articles: newBoardList.articles[0] });
    props.setTotalPage(Math.ceil(newBoardList.articles[1] / 3) - 1);
    props.setBoardPage(0);
  }

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
            onClick={handleSubmit}
          >
            submit
          </Button>,
        ]}
      >
        <NewBoardModalWrapper>
          <div className="modal-title">게시판 글 작성</div>
          <NewBoardContentContainer>
            <TitleBox>
              <div className="title">제목</div>
              <input
                placeholder="게시판 제목을 입력해주세요."
                ref={titleInputRef}
              />
            </TitleBox>
            <ContentBox>
              <div className="title">내용</div>
              <textarea
                placeholder="게시판 내용을 입력해주세요."
                ref={contentInputRef}
              />
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
