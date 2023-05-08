import { useState } from "react";
import { Button, Col, Divider, Modal, Row } from "antd";
import styled from "styled-components";
import Algorithms from "../../assets/algorithm_badge.png";
import CS from "../../assets/cs_badge.png";
import Blog from "../../assets/blog_badge.png";
import Lecture from "../../assets/lecture_badge.png";
import Book from "../../assets/book_badge.jpeg";
import { theme } from "../../styles/theme";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
}

export default function ChallengeModal(props: PropsType) {
  const [loading, setLoading] = useState(false);

  function handleOk() {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      props.toggleModal();
    }, 3000);
  }

  // function toggleNewChallgenModal() {
  //   setOpenNewChallgeModal((prev) => !prev);
  // }
  return (
    <Modal
      open={props.open}
      onCancel={props.toggleModal}
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
          onClick={handleOk}
          loading={loading}
          style={{
            fontFamily: "Kanit-SemiBold",
            height: "inherit",
            fontSize: "2rem",
          }}
        >
          continue
        </Button>,
      ]}
    >
      <CategorySelectionTitle>
        <div className="title">카테고리를 선택해주세요</div>
        <div className="desc">카테고리당 하나씩만 습관 형성이 가능합니다</div>
      </CategorySelectionTitle>
      <Divider />
      <StyledCategory gutter={[{ xs: 8, sm: 16, md: 24, lg: 32 }, 16]}>
        <Col span={12}>
          <div className="category active">
            <img src={CS} />
            CS 공부
          </div>
        </Col>
        <Col span={12}>
          <div className="category">
            <img src={Book} />
            개발서적
          </div>
        </Col>
        <Col span={12}>
          <div className="category">
            <img src={Blog} />
            블로그 포스팅
          </div>
        </Col>
        <Col span={12}>
          <div className="category">
            <img src={Lecture} />
            강의 시청
          </div>
        </Col>
        <Col span={12}>
          <div className="category">
            <img src={Algorithms} />
            알고리즘
          </div>
        </Col>
      </StyledCategory>
    </Modal>
  );
}

const CategorySelectionTitle = styled.div`
  padding-top: 3rem;

  .title {
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.bold};
  }

  .desc {
    color: ${theme.colors.gray400};
    font-size: 1.6rem;
    font-weight: ${theme.fontWeight.semibold};
  }
`;

const StyledCategory = styled(Row)`
  row-gap: 24;

  .category {
    border-radius: 8px;
    border: 1px solid #dddddd;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 1.6rem;
    font-weight: bold;
    padding: 1rem;

    img {
      width: 6vw;
      height: 6vw;
      min-width: 8rem;
      min-height: 8rem;

      margin-bottom: 0.5rem;

      border-radius: 1rem;
      object-fit: cover;
      cursor: pointer;
      filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.15));

      &:hover {
        filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.3));
      }
    }
  }

  .active {
    border-color: #6cd3c0;
    color: #6cd3c0;
  }
`;
