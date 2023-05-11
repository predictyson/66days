import { Modal, Carousel, Tabs } from "antd";
import type { TabsProps } from "antd";
import styled from "styled-components";
import { Content } from "antd/es/layout/layout";
import { theme } from "../../styles/theme";
import BadgeStatusBox from "./BadgeStatusBox";

interface BadgeType {
  image: string;
  challengeName: string;
  startDate: string;
  endDate: string;
  category: string;
  status: boolean; // 성공 실패 여부
}

interface PropsType {
  open: boolean;
  toggleModal: () => void;
  badges: BadgeType[];
}

export default function BadgeModal(props: PropsType) {
  // const [tab, setTab] = useState<number>(0);
  // const [badgeDataList, setBadgeDataList] = useState<BadgeType[]>([]);

  function clickAllTab() {
    document.getElementById("success-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.remove("active-tab");
    document.getElementById("all-tab")?.classList.add("active-tab");
    // setTab(0);
  }

  function clickSuccessTab() {
    document.getElementById("all-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.remove("active-tab");
    document.getElementById("success-tab")?.classList.add("active-tab");
    // setTab(1);
  }

  function clickFailTab() {
    document.getElementById("all-tab")?.classList.remove("active-tab");
    document.getElementById("success-tab")?.classList.remove("active-tab");
    document.getElementById("fail-tab")?.classList.add("active-tab");
    // setTab(2);
  }

  const FirstTabContent = () => {
    return (
      <>
        <BadgeContainer>
          <div className="badge__status__box-container">
            {props.badges.map((badge, index) => (
              <BadgeStatusBox key={index} badge={badge} />
            ))}
          </div>
        </BadgeContainer>
      </>
    );
  };

  const SecondTabContent = () => {
    return <>2</>;
  };

  const ThirdTabContent = () => {
    return <>3</>;
  };

  const items: TabsProps["items"] = [
    {
      key: "1",
      label: `전체보기`,
      children: <FirstTabContent />,
    },
    {
      key: "2",
      label: `성공`,
      children: <SecondTabContent />,
    },
    {
      key: "3",
      label: `실패`,
      children: <ThirdTabContent />,
    },
  ];

  return (
    <CustomModal
      open={props.open}
      onCancel={props.toggleModal}
      width={800}
      footer={null}
    >
      <BadgeModalWrapper>
        <TabContainer>
          {/* <ul className="tab-list">
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
          </ul> */}
          <Tabs className="tab-list" defaultActiveKey="1" items={items} />
        </TabContainer>
        {/* <BadgeContainer>
          <Carousel afterChange={onSlideChange}>
            <div className="badge__status__box-container">
              {props.badges.map((badge, index) => (
                <BadgeStatusBox key={index} badge={badge} />
              ))}
            </div>
          </Carousel>
        </BadgeContainer> */}
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

  .ant-tabs,
  .ant-tabs-tab-btn {
    font-size: 1.5rem;
  }

  .ant-tabs-tab-active {
    font-weight: ${theme.fontWeight.bold};
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
