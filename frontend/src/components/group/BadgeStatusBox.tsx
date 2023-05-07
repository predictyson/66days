import styled from "styled-components";
import { theme } from "../../styles/theme";

interface BadgeType {
  category: string;
  badgeImg: string;
  title: string;
  startDate: string;
  endDate: string;
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
        <BadgeImg
          bgImg={props.badge.badgeImg}
          status={props.badge.status}
          className="failed-badge-img"
        >
          FAIL
        </BadgeImg>

        <BadgeInfoContainer>
          <div className="badge-title">{props.badge.title}</div>
          <div>상태 바</div>
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

const BadgeImg = styled.div<BadgeImgStyled>`
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

const BadgeInfoContainer = styled.div`
  padding-left: 3.2rem;

  .badge-title {
    font-size: 2.5rem;
    font-weight: ${theme.fontWeight.bold};
    padding-bottom: 1rem;
  }

  .badge-period {
    padding: 1rem 0;
  }

  .badge-category-btn {
    padding: 0.4rem 1.6rem;
    width: fit-content;
    background-color: ${theme.colors.purple};
    color: ${theme.colors.white};
    font-weight: ${theme.fontWeight.bold};
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
  }
`;
