import { Layout, Avatar } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { useNavigate } from "react-router-dom";

const { Content } = Layout;

interface ChallengeImgStyled {
  bgImg: string;
  ending?: boolean;
}

export default function ChallengeBox({ ...props }) {
  const navigate = useNavigate();
  return (
    <>
      <ChallengeBoxWrapper onClick={() => navigate("/groups/3/c/1")}>
        {props.notStarted ? (
          props.dueDate == -1 ? (
            <NotStartedChallengeImg bgImg={props.bgImg}>
              <div className="ending-logo">마감 임박</div>
              <div className="not-started-logo">COMING SOON</div>
            </NotStartedChallengeImg>
          ) : (
            <NotStartedChallengeImg bgImg={props.bgImg}>
              <div className="not-started-logo">COMING SOON</div>
            </NotStartedChallengeImg>
          )
        ) : (
          <ChallengeImg bgImg={props.bgImg} />
        )}

        <div className="challenge__box-title">
          <div className="challenge-title">{props.title}</div>
          {props.notStarted ? (
            <div className="due-date red">D{props.dueDate}</div>
          ) : (
            <div className="due-date blue">D+{props.dueDate}</div>
          )}
        </div>
        <div className="challenge__members-box">
          <Avatar.Group
            maxCount={2}
            maxStyle={{
              color: theme.colors.black,
              backgroundColor: "#fde3cf",
            }}
          >
            {props.profileList.map((index: number) => (
              // 추후 participant.image로 수정 예성
              <Avatar
                src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=2"
                key={index}
              />
            ))}
          </Avatar.Group>
          <div className="members__cnt-info">
            {props.memberCnt} / {props.maxCnt} 명
          </div>
        </div>
        {props.notStarted ? (
          <button className="sign-in__challenge-btn">챌린지 신청하기</button>
        ) : (
          <></>
        )}
      </ChallengeBoxWrapper>
    </>
  );
}

const ChallengeBoxWrapper = styled(Content)`
  width: 15vw;
  display: flex;
  flex: initial;
  flex-direction: column;
  cursor: pointer;
  font-size: 1.6rem;

  .challenge__box-title {
    display: flex;
    justify-content: space-between;
    padding: 1rem 0;
  }

  .challenge-title {
    font-weight: 700;
    flex-grow: 1;

    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-all;

    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }

  .due-date {
    min-width: fit-content;
    font-family: "Kanit-Bold";
    padding-left: 0.5rem;
  }

  .red {
    color: ${theme.colors.failure};
  }

  .blue {
    color: ${theme.colors.success};
  }

  .challenge__members-box {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: start;
  }

  .members__cnt-info {
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    background-color: #5f6f94;
    color: ${theme.colors.white};
    text-align: center;
    margin-left: 1rem;

    @media all and (max-width: 830px) {
      margin-top: 1rem;
      margin-left: 0;
    }
  }

  .sign-in__challenge-btn {
    margin-top: 1rem;
    border: none;
    padding: 1rem 0;
    border-radius: 5px;
    background-color: ${theme.colors.lightred};
    color: ${theme.colors.white};
    word-break: keep-all;
    cursor: pointer;
    &:hover {
      background-color: #f88080;
    }
  }
`;

const ChallengeImg = styled(Content)<ChallengeImgStyled>`
  background: url(${(props) => props.bgImg});
  background-size: cover;
  position: relative;
  width: 15vw;
  height: 15vw;
  max-height: 15vw;
  border-radius: 1rem;
  font-size: 1.6rem;
  display: flex;
  justify-content: center;
  align-items: center;
  filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.3));

  &:hover {
    filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.5));
  }
`;

const NotStartedChallengeImg = styled(Content)<ChallengeImgStyled>`
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)),
    url(${(props) => props.bgImg});
  background-size: cover;
  position: relative;
  width: 15vw;
  height: 15vw;
  max-height: 15vw;
  border-radius: 1rem;
  font-size: 1.6rem;
  display: flex;
  justify-content: center;
  align-items: center;
  filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.3));

  &:hover {
    filter: drop-shadow(0px 4px 5px rgba(0, 0, 0, 0.5));
  }

  .ending-logo {
    position: absolute;
    top: 0.8rem;
    right: 0.8rem;
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    background-color: ${theme.colors.failure};
    color: ${theme.colors.white};
    font-size: 1.2rem;
    font-weight: 700;

    @media all and (max-width: 1023px) {
      display: none;
    }
  }

  .not-started-logo {
    width: fit-content;
    max-width: 70%;
    border: 2px solid ${theme.colors.white};
    padding: 0.8rem 1.6rem;
    color: ${theme.colors.white};
    border-radius: 0.4rem;
    font-family: "Kanit-SemiBold";
    text-align: center;

    text-overflow: ellipsis;
    overflow: hidden;
    word-break: keep-all;

    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
`;
