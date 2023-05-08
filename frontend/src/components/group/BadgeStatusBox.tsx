import { Progress } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { useState } from "react";

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
  function calcPeriod(oldDate: string, newDate: string) {
    console.log(new Date(oldDate), new Date(newDate));

    let diff = Math.abs(
      new Date(newDate).getTime() - new Date(oldDate).getTime()
    );
    diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
    console.log((diff / 66) * 100);
  }

  return (
    <>
      <BadgeStatusBoxWrapper>
        {props.badge.status ? (
          <BadgeImage
            bgImg={props.badge.badgeImg}
            status={props.badge.status}
          ></BadgeImage>
        ) : (
          <FailedBadgeImg
            bgImg={props.badge.badgeImg}
            status={props.badge.status}
          >
            FAIL
          </FailedBadgeImg>
        )}

        <BadgeInfoContainer>
          <div className="badge-title">{props.badge.title}</div>
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
