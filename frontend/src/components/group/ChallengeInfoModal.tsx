import { Button, DatePicker, Divider, Input, InputNumber, Modal } from "antd";
import { useRef, useState } from "react";
import styled from "styled-components";
import { theme } from "../../styles/theme";

const { TextArea } = Input;

interface PropsType {
  open: boolean;
  togglePreviousModal: () => void;
  toggleModal: () => void;
}

export default function ChallengeInfoModal(props: PropsType) {
  const [loading, setLoading] = useState(false);
  const challengeTitleRef = useRef<HTMLInputElement | null>(null);
  const challengeMemberCntRef = useRef<HTMLInputElement | null>(null);
  const challengeDescRef = useRef<HTMLTextAreaElement | null>(null);
  const startDateRef = useRef<HTMLInputElement | null>(null);

  function handleSubmit() {
    console.log(challengeTitleRef.current?.value);
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 1500);
    props.toggleModal();
  }

  function handleBackClick() {
    props.toggleModal();
    props.togglePreviousModal();
  }

  return (
    <>
      <Modal
        open={props.open}
        onCancel={props.toggleModal}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={handleBackClick}
            style={{
              backgroundColor: "black",
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
            }}
          >
            back
          </Button>,
          <Button
            onClick={handleSubmit}
            loading={loading}
            style={{
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
            }}
          >
            {/* 첫번째 단계일 때만 continue로 표시 */}
            add challenge
          </Button>,
        ]}
      >
        <CategorySelectionTitle>
          <div className="title">챌린지 추가</div>
          <div className="desc">카테고리당 하나씩만 습관 형성이 가능합니다</div>
        </CategorySelectionTitle>
        <Divider />
        <ChallengeInfoWrapper>
          <ChallengeTitleBox>
            <div className="sub-title">
              66 챌린지 명<span className="essential">*</span>
            </div>
            <Input
              className="input-font"
              ref={() => challengeTitleRef}
              placeholder="챌린지 명을 입력해주세요"
            />
          </ChallengeTitleBox>
          <ChallengeCountBox>
            <div className="sub-title">
              인원 수<span className="essential">*</span>
            </div>
            <InputNumber min={2} max={66} className="input-font" />
          </ChallengeCountBox>
          <ChallengeDescBox>
            <div className="sub-title">
              챌린지 설명<span className="essential">*</span>
            </div>
            <TextArea className="input-font" showCount maxLength={50} />
          </ChallengeDescBox>
          <ChallengeDateBox>
            <div className="date-box">
              <div className="sub-title">
                시작 날짜<span className="essential">*</span>
              </div>
              <DatePicker className="date-picker" />
            </div>
            <div className="date-box">
              <div className="sub-title">종료 날짜</div>
              <DatePicker className="date-picker" />
            </div>
          </ChallengeDateBox>
          <div className="challenge-desc">
            <span className="essential">*</span>표시는 필수 입력값입니다.
          </div>
        </ChallengeInfoWrapper>
      </Modal>
    </>
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

const ChallengeInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;

  .sub-title {
    font-size: 2rem;
    font-weight: ${theme.fontWeight.bold};
  }

  .input-font {
    font-size: 1.6rem;
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

  .date-box:last-child {
    padding-left: 5rem;
  }

  .date-picker,
  .ant-picker-input > input {
    font-size: 1.2rem;
  }
`;
