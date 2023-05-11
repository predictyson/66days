import { Modal, Tabs } from "antd";
import type { TabsProps } from "antd";
import styled from "styled-components";
import { Content } from "antd/es/layout/layout";
import { theme } from "../../styles/theme";
import BadgeStatusBox from "./BadgeStatusBox";
import { LeftCircleFilled, RightCircleFilled } from "@ant-design/icons";
import { useState } from "react";

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
  const [page, setPage] = useState<number>(1);

  const FirstTabContent = () => {
    return (
      <>
        <BadgeContainer>
          <div className="badge__status__box-container">
            {props.badges.slice(page * 2 - 2, page * 2).map((badge, index) => (
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
      label: `전체보기 (${props.badges.length})`,
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

  function handleClickLeft() {
    if (page > 1) {
      setPage(page - 1);
    }
  }

  function handleClickRight() {
    if (page < Math.ceil(props.badges.length / 2)) {
      setPage(page + 1);
    }
  }

  return (
    <CustomModal
      open={props.open}
      onCancel={props.toggleModal}
      width={800}
      footer={null}
    >
      <BadgeModalWrapper>
        <TabContainer>
          <Tabs className="tab-list" defaultActiveKey="1" items={items} />
          {props.badges.length > 2 ? (
            <div className="pagination-bar-container">
              {page === 1 ? (
                <LeftCircleFilled className="invisible-arrow icon-margin" />
              ) : (
                <LeftCircleFilled
                  className="pagination-icon icon-margin"
                  onClick={handleClickLeft}
                />
              )}
              {page === Math.ceil(props.badges.length / 2) ? (
                <RightCircleFilled className="invisible-arrow" />
              ) : (
                <RightCircleFilled
                  className="pagination-icon"
                  onClick={handleClickRight}
                />
              )}
            </div>
          ) : null}
        </TabContainer>
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
  padding: 6rem 8rem 4rem;
  height: 85vh;
`;

const TabContainer = styled(Content)`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: 1.6rem;
  height: 100%;

  .ant-tabs,
  .ant-tabs-tab-btn {
    font-size: 1.5rem;
  }

  .ant-tabs-tab-active {
    font-weight: ${theme.fontWeight.bold};
  }

  .pagination-bar-container {
    padding-top: 5rem;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  .pagination-icon {
    font-size: 3.2rem;
    color: ${theme.colors.gray500};
    cursor: pointer;

    &:hover {
      color: #777;
    }
  }

  .invisible-arrow {
    font-size: 3.2rem;
    visibility: hidden;
  }

  .icon-margin {
    margin-right: 5rem;
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
