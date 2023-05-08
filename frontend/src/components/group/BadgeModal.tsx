import { Modal, Carousel } from "antd";
import styled from "styled-components";
import { Content } from "antd/es/layout/layout";
import { theme } from "../../styles/theme";
import { useEffect, useState } from "react";
import BadgeStatusBox from "./BadgeStatusBox";

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
  const [tab, setTab] = useState<number>(0);
  // const [badgeDataList, setBadgeDataList] = useState<BadgeType[]>([]);

  function clickAllTab() {
    document.getElementById("success-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.remove("active-tab");
    document.getElementById("all-tab")?.classList.add("active-tab");
    setTab(0);
  }

  function clickSuccessTab() {
    document.getElementById("all-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.remove("active-tab");
    document.getElementById("success-tab")?.classList.add("active-tab");
    setTab(1);
  }

  function clickFailTab() {
    document.getElementById("all-tab")?.classList.remove("active-tab");
    document.getElementById("success-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.add("active-tab");
    setTab(2);
  }

  function onSlideChange(currentSlide: number) {
    console.log(currentSlide);
    // const slicedBadgeList = props.badges.slice(
    //   (currentSlide - 1) * 2,
    //   currentSlide * 2
    // );

    // setBadgeDataList(slicedBadgeList);
    // console.log(slicedBadgeList);
  }

  // useEffect(() => {
  //   setBadgeDataList(props.badges);
  // }, []);

  return (
    <CustomModal
      open={props.open}
      onCancel={props.toggleModal}
      width={800}
      footer={null}
    >
      <BadgeModalWrapper>
        <TabContainer>
          <ul className="tab-list">
            <li id="all-tab" className="active-tab" onClick={clickAllTab}>
              전체보기(3)
            </li>
            |
            <li id="success-tab" onClick={clickSuccessTab}>
              성공(2)
            </li>
            |
            <li id="fail-tab" onClick={clickFailTab}>
              실패(1)
            </li>
          </ul>
        </TabContainer>
        <BadgeContainer>
          <Carousel afterChange={onSlideChange}>
            <div className="badge__status__box-container">
              {props.badges.map((badge) => (
                <BadgeStatusBox badge={badge} />
              ))}
            </div>
          </Carousel>
        </BadgeContainer>
      </BadgeModalWrapper>
    </CustomModal>
  );
}

const CustomModal = styled(Modal)`
  .ant-modal-content {
    padding: 0;
  }
`;
const BadgeModalWrapper = styled(Content)`
  /* max-height: 90vh; */
  padding: 6rem 8rem;
`;

const TabContainer = styled(Content)`
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;

  .tab-list {
    padding-left: 0;
    display: flex;
  }

  .tab-list li {
    list-style: none;
    padding-left: 1rem;
    padding-right: 1rem;

    color: ${theme.colors.gray400};
    cursor: pointer;

    &:hover {
      color: ${theme.colors.black};
    }
  }

  .tab-list li:first-child {
    padding-left: 0;
  }

  .tab-list li:last-child {
    padding-right: 0;
  }

  .active-tab {
    color: ${theme.colors.black} !important;
    font-weight: 700;
  }
`;

const BadgeContainer = styled.div`
  display: flex;
  flex-direction: column;

  .badge__status__box-container {
    display: flex;
    flex-direction: column;
  }
`;
