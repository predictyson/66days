import styled from "styled-components";
import Capibara from "../../assets/main/Capibara.png";
import Badge from "../../assets/main/Bronze.png";
import CoinIcon from "../../assets/main/Coin.png";
import { theme } from "../../styles/theme";

interface IProps {
  memberInfo: MemberInfoData;
}
export default function Banner({ memberInfo }: IProps) {
  return (
    <Container>
      <LeftWrapper>
        <Info>
          <img src={Badge} alt="badge" />
          <div className="email">{memberInfo.email}</div>
        </Info>
        <Nickname>
          게으른 <br /> {memberInfo.animal}
        </Nickname>
        <MoreInfo>
          <div className="exp">{memberInfo.exp} EXP</div>
          <div className="button">한눈에 보기</div>
        </MoreInfo>
        <Coin>
          <img src={CoinIcon} alt="icon" />{" "}
          <span>{memberInfo.point.toLocaleString()}</span>
        </Coin>
      </LeftWrapper>
      <ImageWrapper>
        <img src={Capibara} alt="capibara" />
      </ImageWrapper>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: 60rem;
  display: flex;
  font-family: Pretendard;
  background-color: black;
  padding: 0 5rem;
`;

const LeftWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
`;

const Info = styled.div`
  height: 4.8rem;
  margin: 0 auto;
  margin-top: 12rem;
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
`;

const Nickname = styled.div`
  color: white;
  width: 80%;
  margin: 0 auto;
  margin-top: 3.1rem;
  font-size: 6rem;
  font-weight: 700;
`;
const ImageWrapper = styled.div`
  display: flex;
  width: 50%;
  justify-content: center;
  align-items: center;
  img {
    display: flex;
    margin-top: 3rem;
  }
`;

const MoreInfo = styled.div`
  margin: 0 auto;
  margin-top: 3.4rem;
  width: 80%;
  display: flex;
  .button {
    border-radius: 5px;
    background-color: white;
    width: 15rem;
    height: 4.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
    cursor: pointer;
    margin-left: 5%;
    font-weight: ${theme.fontWeight.semibold};
    &:hover {
      background-color: #444444;
      color: white;
    }
  }

  .exp {
    color: white;
    font-size: 3rem;
    font-weight: bold;
    margin: auto 0;
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
  }
  img {
    width: 2.5rem;
  }
`;
