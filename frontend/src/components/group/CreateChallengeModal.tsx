import { useEffect, useRef, useState } from "react";
import { Button, Col, DatePicker, Divider, Input, Modal, Row } from "antd";
import type { InputRef } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { mockCategories } from "../../mock/group";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
}

interface CategoryType {
  img: string;
  title: string;
  selected: boolean;
}

export default function CreateChallengeModal(props: PropsType) {
  const [categories, setCategories] = useState<CategoryType[]>([]);
  const [step, setStep] = useState<number>(1);
  const [loading, setLoading] = useState(false);

  const titleRef = useRef<InputRef | null>(null);
  const cntRef = useRef<InputRef | null>(null);
  const descRef = useRef<InputRef | null>(null);
  const startDateRef = useRef<InputRef | null>(null);

  const FirstStepContent = () => {
    return (
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
    );
  };

  const SecondStepContent = () => {
    return (
      <ChallengeInfoWrapper>
        <ChallengeTitleBox>
          <div className="sub-title">
            66 챌린지 명<span className="essential">*</span>
          </div>
          <Input
            className="input-font"
            placeholder="챌린지 명을 입력해주세요"
            ref={titleRef}
          />
        </ChallengeTitleBox>
        <ChallengeCountBox>
          <div className="sub-title">
            인원 수<span className="essential">*</span>
          </div>
          <Input
            type="number"
            className="input-font input-number"
            ref={cntRef}
          />
        </ChallengeCountBox>
        <ChallengeDescBox>
          <div className="sub-title">
            챌린지 설명<span className="essential">*</span>
          </div>
          <Input
            className="input-font input-desc"
            showCount
            maxLength={50}
            ref={descRef}
          />
        </ChallengeDescBox>
        <ChallengeDateBox>
          <div className="date-box">
            <div className="sub-title">
              시작 날짜<span className="essential">*</span>
            </div>
            <DatePicker className="date-picker" />
          </div>
          {/* 시간이 남을 경우 프론트 단에서 예상 종료 날짜 띄워주기 */}
          {/* <div className="date-box">
            <div className="sub-title">종료 날짜</div>
            <DatePicker className="date-picker" />
          </div> */}
        </ChallengeDateBox>
        <div className="challenge-desc">
          <span className="essential">*</span>표시는 필수 입력값입니다.
        </div>
      </ChallengeInfoWrapper>
    );
  };

  // 1단계에서 continue 눌렀을 때 handler function
  function handleNext() {
    if (step === 1) {
      // TODO: 선택 여부 체크하기
      setStep(2);
    } else if (step === 2) {
      // TODO: submit handle
      // console.log(challengeTitleRef.current?.value);
      setLoading(true);
      setTimeout(() => {
        setLoading(false);
      }, 1500);
      handleCreateChallenge();
    }
  }

  // 2단계에서 최종 create 눌렀을 때 handler function
  function handleCreateChallenge() {
    console.log(titleRef.current?.input?.value);
    console.log(cntRef.current?.input?.value);
    console.log(descRef.current?.input);
    console.log(startDateRef);
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

  function cancelModal() {
    props.toggleModal();
    setStep(1);
  }

  function handleBackOrCancel() {
    if (step === 1) {
      props.toggleModal();
    } else if (step === 2) {
      setStep(1);
    }
  }

  useEffect(() => {
    // TODO: 나중에 실제 데이터로 변경
    setCategories(mockCategories);
  }, []);

  return (
    <Modal
      open={props.open}
      onCancel={cancelModal}
      footer={[
        <Button
          key="back"
          type="primary"
          onClick={handleBackOrCancel}
          style={{
            backgroundColor: "black",
            fontFamily: "Kanit-SemiBold",
            height: "inherit",
            fontSize: "2rem",
          }}
        >
          {step === 1 ? "cancel" : "back"}
        </Button>,
        <Button
          onClick={handleNext}
          loading={loading}
          style={{
            fontFamily: "Kanit-SemiBold",
            height: "inherit",
            fontSize: "2rem",
          }}
        >
          {/* 첫번째 단계일 때만 continue로 표시 */}
          {step === 1 ? "continue" : "create"}
        </Button>,
      ]}
    >
      <CategorySelectionTitle>
        <div className="title">
          {step === 1 ? "카테고리 선택" : "챌린지 정보 입력"}
        </div>

        <div className="desc">
          {step === 1
            ? "카테고리당 하나의 챌린지만 참여가 가능합니다."
            : "챌린지 상세 정보를 입력해주세요."}
        </div>
      </CategorySelectionTitle>
      <Divider />
      {step === 1 ? <FirstStepContent /> : <SecondStepContent />}
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
    border-color: ${theme.colors.mint} !important;
    color: ${theme.colors.mint};
  }
`;

const ChallengeInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  transition: all 1s;

  .sub-title {
    font-size: 2rem;
    font-weight: ${theme.fontWeight.bold};
  }

  .input-font {
    font-size: 1.6rem;
  }

  .input-number {
    width: 30%;
  }

  .input-desc {
    height: 8rem;
  }

  .essential {
    color: ${theme.colors.failure};
    padding-left: 0.5rem;
  }

  .challenge-desc {
    font-size: 1.2rem;
  }

  .challenge-desc > .essential {
    padding-right: 0.5rem;
  }
`;

const ChallengeTitleBox = styled.div`
  display: flex;
  flex-direction: column;
  padding-bottom: 2rem;
`;

const ChallengeCountBox = styled.div`
  display: flex;
  flex-direction: column;
  padding-bottom: 2rem;
`;

const ChallengeDescBox = styled.div`
  display: flex;
  flex-direction: column;
  padding-bottom: 2rem;

  .ant-input-data-count {
    bottom: -3rem;
  }
`;

const ChallengeDateBox = styled.div`
  display: flex;
  padding-bottom: 2rem;

  .date-box {
    display: flex;
    flex-direction: column;
  }

  /* .date-box:last-child {
    padding-left: 5rem;
  } */

  .date-picker,
  .ant-picker-input > input {
    font-size: 1.2rem;
  }
`;
