import styled from "styled-components";
import { theme } from "../../styles/theme";
import Profile1 from "../../assets/main/Profile1.png";
import GroupImg from "../../assets/search/group_image.png";
// import ConfirmModal from "./ConfirmModal";
import { useState } from "react";
import { Modal } from "antd";
import { SearchGroupData } from "../../types/search";
import Category from "../../styles/CategoryTheme";
import { putGroupApply } from "../../api/search";

interface IProps {
  group: SearchGroupData;
}
export default function GroupItem({ group }: IProps) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    //FIXME: group_id 받아와야함
    putGroupApply(1, "APPLIED");
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
    <Container>
      <ProfileWrapper>
        <img src={Profile1} />
        <TitleWrapper>
          {group.ownerName}
          <div className="sub">게으른 카피바라</div>
        </TitleWrapper>
      </ProfileWrapper>
      <img src={GroupImg} style={{ height: "18.8rem" }} />
      <MaterialWrapper>
        <div className="title">{group.name}</div>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            marginTop: "0.7rem",
            alignItems: "center",
          }}
        >
          <div style={{ display: "flex" }}>
            {group.categories?.map((v, idx) => {
              const category = Category.find((c) => c.title === v); // 해당 title의 category를 찾음
              const col = category ? category.color : "gray";
              return (
                <CategoryItem key={idx} color={col}>
                  {v}
                </CategoryItem>
              );
            })}
          </div>

          {group.memberCounts === group.maxMemberCounts ? (
            <span style={{ color: "red" }}>모집 마감</span>
          ) : (
            <span>모집중 </span>
          )}
        </div>
        <div className="content">{group.description}</div>
      </MaterialWrapper>
      <ButtonWrapper>
        {group.memberCounts} / {group.maxMemberCounts}
        {group.memberCounts === group.maxMemberCounts ? (
          <div
            className="button"
            style={{
              backgroundColor: "gray",
              cursor: "default",
              pointerEvents: "none",
            }}
          >
            Full
          </div>
        ) : (
          <div
            className="button"
            style={{ cursor: "pointer" }}
            onClick={showModal}
          >
            Apply
          </div>
        )}
      </ButtonWrapper>
      <CustomModal open={isModalOpen}>
        <div className="modal-title">{group.name}</div>
        <div className="modal-sub">그룹에 가입 신청을 요청하시겠습니까?</div>
        <div className="modal-warning">
          그룹 가입 승인 이후 챌린지 참여가 가능합니다.
        </div>
        <ModalButtonWrapper>
          <button className="ok" onClick={handleOk}>
            예
          </button>
          <button className="no" onClick={handleCancel}>
            아니요
          </button>
        </ModalButtonWrapper>
      </CustomModal>
    </Container>
  );
}

/* Modal  */
const CustomModal = styled(Modal)`
  svg {
    display: none;
  }
  .ant-modal-content {
    height: 48rem;
    display: flex;
    text-align: center;
    flex-direction: column;
  }
  .modal-title {
    margin-top: 10.3rem;
    font-size: 3.2rem;
    font-weight: bold;
  }

  .modal-sub {
    margin-top: 3rem;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.semibold};
  }
  .modal-warning {
    margin-top: 2rem;
    font-size: 1.6rem;
    color: #ff4a4a;
    font-weight: ${theme.fontWeight.semibold};
  }
  .ant-modal-footer {
    background-color: green;
    width: 70%;
    margin: 4.8rem auto;
    button {
      display: none;
    }
  }
`;

const ModalButtonWrapper = styled.div`
  width: 75%;
  display: flex;
  justify-content: space-between;
  margin: 4.9rem auto;
  button {
    width: 16rem;
    height: 4.8rem;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    font-weight: bold;
    font-size: 2rem;
    color: white;
    border: none;
  }

  .ok {
    background-color: ${theme.colors.lightred};
    &:hover {
      border: solid 3px ${theme.colors.lightred};
      color: ${theme.colors.lightred};
      background-color: white;
    }
  }
  .no {
    background-color: ${theme.colors.lightblue};
    &:hover {
      border: solid 3px ${theme.colors.lightblue};
      color: ${theme.colors.lightblue};
      background-color: white;
    }
  }
`;
const Container = styled.div`
  width: 36rem;
  height: 48rem;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  box-shadow: 0 0 5px 2px ${theme.colors.gray200};
  cursor: pointer;
  .ant-modal-content {
    width: 30rem;
    height: 30rem;
    border: solid 2px red;
  }
`;

const ProfileWrapper = styled.div`
  height: 7.2rem;
  display: flex;
  align-items: center;
  padding: 0 1.6rem;
  img {
    width: 4rem;
  }
`;
const TitleWrapper = styled.div`
  margin-left: 1.6rem;
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;
  .sub {
    margin-top: 0.2rem;
    color: ${theme.colors.gray400};
  }
`;

const MaterialWrapper = styled.div`
  padding: 1.7rem 1.6rem;
  display: flex;
  flex-direction: column;
  .title {
    font-size: 2rem;
    font-weight: ${theme.fontWeight.semibold};
  }
  .content {
    margin-top: 1.3rem;
    font-size: 1.6rem;
    color: ${theme.colors.gray500};
  }
  span {
    font-size: 1.6rem;
    color: ${theme.colors.mint};
    font-weight: ${theme.fontWeight.semibold};
  }
`;
const ButtonWrapper = styled.div`
  padding: 1.6rem;
  height: 7.2rem;
  display: flex;
  margin-top: auto;
  align-items: center;
  justify-content: space-between;
  font-weight: ${theme.fontWeight.semibold};
  font-size: 1.6rem;
  color: ${theme.colors.gray500};

  .disable-button {
    width: 11rem;
    height: 4rem;
    border-radius: 155px;
    color: white;
    background-color: red;
    text-align: center;
    padding: 0.5rem 1.6rem;
    display: flex;
    justify-content: center;
    align-items: center;
    /* &:hover {
      color: gray;
      background-color: white;
      border: solid 1px gray;
    } */
  }
  .button {
    width: 11rem;
    height: 4rem;
    border-radius: 155px;
    color: white;
    background-color: ${theme.colors.mint};
    text-align: center;
    padding: 0.5rem 1.6rem;
    display: flex;
    justify-content: center;
    align-items: center;
    &:hover {
      color: gray;
      background-color: white;
      border: solid 1px gray;
    }
  }
`;
interface CategoryItemProps {
  color: string;
}

const CategoryItem = styled.div<CategoryItemProps>`
  color: ${(props) => props.color};
  font-size: 1.2rem;
  font-weight: ${theme.fontWeight.semibold};
  margin-right: 1.6rem;
  padding: 0.5rem 1.6rem;
  border: solid 2px ${(props) => props.color};
  border-radius: 10px;
`;
