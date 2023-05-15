import { useEffect, useState } from "react";
import { Button, Col, Divider, Modal, Row } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { mockCategories } from "../../mock/group";

interface PropsType {
  open: boolean;
  toggleNextModal: () => void;
  toggleModal: () => void;
}

interface CategoryType {
  img: string;
  title: string;
  selected: boolean;
}

export default function ChallengeSelectionModal(props: PropsType) {
  const [categories, setCategories] = useState<CategoryType[]>([]);

  function handleContinue() {
    props.toggleModal();
    props.toggleNextModal();
  }

  function changeCategory(idx: number) {
    const copyCategory = categories;
    copyCategory.map((category: CategoryType, index) => {
      if (index === idx) {
        // TODO: 추후에 실제 데이터 넘어오면 이미 진행 중인 카테고리라면 true 처리 막기
        category.selected = true;
      } else {
        category.selected = false;
      }
    });
    setCategories([...copyCategory]);
  }

  useEffect(() => {
    // TODO: 나중에 실제 데이터로 변경
    setCategories(mockCategories);
  }, []);

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
          onClick={handleContinue}
          style={{
            fontFamily: "Kanit-SemiBold",
            height: "inherit",
            fontSize: "2rem",
          }}
        >
          {/* 첫번째 단계일 때만 continue로 표시 */}
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
        {categories.map((category, index) => (
          <Col span={12} key={index}>
            <div
              className={`category ${category.selected ? "active" : ""}`}
              onClick={() => changeCategory(index)}
            >
              <img src={category.img} />
              {category.title}
            </div>
          </Col>
        ))}
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
    border: 1px solid ${theme.colors.gray300};
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 1.6rem;
    font-weight: bold;
    padding: 1rem;
    cursor: pointer;

    &:hover {
      filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.25));
      border: 1px solid ${theme.colors.gray500};
      transition: 0.3s;
    }

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
    }
  }

  .active {
    border-color: #6cd3c0;
    color: #6cd3c0;
  }
`;
