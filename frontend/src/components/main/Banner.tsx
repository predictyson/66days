import { useState } from "react";
import styled from "styled-components";
import CoinIcon from "../../assets/main/Coin.png";
import { theme } from "../../styles/theme";
import { userDetailData } from "../../types/main";
import CountUp from "react-countup";
import { useNavigate } from "react-router";
import TierInfoModal from "./TierInfoModal";
import { getImagePath } from "../../util/common";
interface IProps {
  memberInfo: userDetailData;
}
export default function Banner({ memberInfo }: IProps) {
  const navigate = useNavigate();

  const [isOpenTierModal, setTierModal] = useState(false);
  return (
    <>
      <TierInfoModal
        open={isOpenTierModal}
        toggleModal={() => setTierModal((prev) => !prev)}
      />
      <Container>
        <LeftWrapper>
          <Info>
            <img
              src={getImagePath(memberInfo.tierDTO.imagePath)}
              alt="badge"
              style={{ width: "4rem", cursor: "pointer" }}
              onClick={() => setTierModal((prev) => !prev)}
            />
            <div className="email">{memberInfo.email}</div>
          </Info>
          <Nickname>
            {memberInfo.tierDTO.title} <br /> {memberInfo.animalDTO.animalName}
          </Nickname>
          <MoreInfo>
            <div className="exp">
              <CountUp end={memberInfo.exp} duration={5} /> EXP
            </div>
            <div className="button" onClick={() => navigate("/mypage")}>
              한눈에 보기
            </div>
          </MoreInfo>
          <Coin>
            <img src={CoinIcon} alt="icon" />{" "}
            <span>
              <CountUp end={memberInfo.point} duration={5} />
            </span>
          </Coin>
        </LeftWrapper>
        <ImageWrapper>
          <img
            src={getImagePath(memberInfo.animalDTO.imagePath)}
            alt="capibara"
          />
        </ImageWrapper>
      </Container>
    </>
  );
}

const Container = styled.div`
  width: 100%;
  height: 55rem;
  display: flex;
  font-family: Pretendard;
  background-color: black;
  padding: 0 9%;
`;

const LeftWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  @media (max-width: 1200px) {
    width: 100%;
  }
`;

const Info = styled.div`
  height: 4.8rem;
  margin: 0 auto;
  margin-top: 8rem;
  width: 80%;
  display: flex;
  align-items: center;
  .email {
    width: 25rem;
    background-color: #444444;
    opacity: 0.5;
    margin-left: 5%;
    color: #ffffff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.6rem;
    border-radius: 10px;
    height: 4rem;
  }
  img {
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    &:hover {
      transform: scale(1.1);
    }
  }
`;

const Nickname = styled.div`
  color: white;
  width: 80%;
  text-shadow: 2px 2px 2px gray;
  margin: 0 auto;
  margin-top: 3.1rem;
  font-size: 5.5rem;
  font-weight: 700;
`;
const ImageWrapper = styled.div`
  display: flex;
  width: 50%;
  justify-content: center;
  align-items: center;
  img {
    display: flex;
    margin-top: 5rem;
    width: 90%;
    animation: float 6s ease-in-out infinite;

    @keyframes float {
      0% {
        transform: translatey(0px);
      }
      50% {
        transform: translatey(-30px);
      }
      100% {
        transform: translatey(0px);
      }
    }
  }
  @media (max-width: 1200px) {
    width: 0%;
  }
`;

const MoreInfo = styled.div`
  margin: 0 auto;
  margin-top: 3.4rem;
  width: 80%;
  display: flex;
  .button {
    margin-left: 4rem;
    border-radius: 5px;
    background-color: white;
    width: 35%;
    height: 4.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
    cursor: pointer;
    font-weight: ${theme.fontWeight.semibold};
    &:hover {
      background-color: #444444;
      color: white;
      transform: scale(1.1);
    }
    transition: transform 0.3s ease-in-out;
  }

  .exp {
    width: 15rem;
    color: ${theme.colors.gray200};
    font-size: 2.8rem;
    font-weight: bold;
    margin: auto 0;
    text-shadow: 1px 2px 1px blue;
  }
`;

const Coin = styled.div`
  font-size: 2.5rem;
  margin: 0 auto;
  width: 80%;
  margin-top: 3.4rem;
  color: wheat;
  display: flex;
  align-items: center;
  font-weight: ${theme.fontWeight.semibold};
  span {
    margin-left: 1rem;
    text-shadow: 2px 2px 2px yellowgreen;
  }
  img {
    width: 2.5rem;
  }
`;
