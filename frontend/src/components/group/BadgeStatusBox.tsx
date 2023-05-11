import { Progress } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";

interface BadgeType {
  image: string;
  challengeName: string;
  startDate: string;
  endDate: string;
  category: string;
  status: boolean; // 성공 실패 여부
}

interface PropsType {
  badge: BadgeType;
}

interface BadgeImgStyled {
  bgImg: string;
  status: boolean;
}

export default function BadgeStatusBox(props: PropsType) {
  return (
    <>
      <BadgeStatusBoxWrapper>
        {props.badge.status ? (
          <BadgeImage
            bgImg={props.badge.image}
            status={props.badge.status}
          ></BadgeImage>
        ) : (
          <FailedBadgeImg bgImg={props.badge.image} status={props.badge.status}>
            FAIL
          </FailedBadgeImg>
        )}

        <BadgeInfoContainer>
          <div className="badge-title">{props.badge.challengeName}</div>
          <div>
            {props.badge.status ? (
              <Progress percent={100} />
            ) : (
              <Progress percent={60} status="exception" />
            )}
          </div>
          <div className="badge-period">
            챌린지 기간: {props.badge.startDate} ~ {props.badge.endDate}
          </div>
          <div className="badge-category-btn">{props.badge.category}</div>
        </BadgeInfoContainer>
      </BadgeStatusBoxWrapper>
    </>
  );
}

const BadgeStatusBoxWrapper = styled.div`
  display: flex;
  padding-top: 2rem;

  .badge-img {
    width: 16rem;
    height: 16rem;
    border-radius: 10px;
  }
`;

const FailedBadgeImg = styled.div<BadgeImgStyled>`
  width: 16rem;
  height: 16rem;
  border-radius: 10px;
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)),
    url(${(props) => props.bgImg});
  background-size: cover;

  display: flex;
  align-items: center;
  justify-content: center;

  color: ${theme.colors.white};
  font-size: 3.2rem;
  font-family: "Kanit-Bold";
`;

const BadgeImage = styled.div<BadgeImgStyled>`
  width: 16rem;
  height: 16rem;
  border-radius: 10px;
  background: url(${(props) => props.bgImg});
  background-size: cover;
`;

const BadgeInfoContainer = styled.div`
  padding-left: 3.2rem;

  svg {
    width: 2rem;
    height: 2rem;
  }

  .badge-title {
    font-size: 2.5rem;
    font-weight: ${theme.fontWeight.bold};
    padding-bottom: 1rem;

    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-all;

    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }

  .badge-period {
    font-size: 1.6rem;
    padding: 0.5rem 0 1rem;

    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-all;

    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }

  .badge-category-btn {
    padding: 0.4rem 1.6rem;
    width: fit-content;
    background-color: ${theme.colors.purple};
    color: ${theme.colors.white};
    font-size: 1.2rem;
    font-weight: ${theme.fontWeight.bold};
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
  }
`;
