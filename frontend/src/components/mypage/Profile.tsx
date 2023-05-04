import styled from "styled-components";
import ProfileImg from "../../assets/main/Profile3.png";
import Bronze from "../../assets/main/Bronze.png";
import Coin from "../../assets/main/Coin.png";
import { Progress } from "antd";

import { theme } from "../../styles/theme";

export default function Profile() {
  return (
    <Container>
      <img className="profile-img" src={ProfileImg} alt="profile-img" />
      <img className="badge-img" src={Bronze} alt="badge" />
      <Nickname>뭉치뭉치똥뭉치</Nickname>
      <Email>moonchi@naver.com</Email>
      <EditButton>edit</EditButton>
      <ProgressWrapper>
        <ImageWrapper>
          <img src={Bronze} alt="current-badge" />
          <img src={Bronze} alt="next-badge" />
        </ImageWrapper>
        <Progress percent={30} />
      </ProgressWrapper>
      <CoinWrapper>
        <img src={Coin} alt="" />
        <span>32000</span>
      </CoinWrapper>
    </Container>
  );
}
const CoinWrapper = styled.div`
  margin: auto;
  margin-top: 1rem;
  display: flex;
  align-items: center;
  font-size: 1.6rem;
  margin-right: auto;
  width: 29rem;
  font-weight: ${theme.fontWeight.semibold};
  span {
    margin-left: 1rem;
  }
`;

const Container = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  position: relative;
  .profile-img {
    width: 18rem;
    height: 18rem;
    margin-top: 4.8rem;
  }
  .badge-img {
    position: absolute;
    top: 20.8rem;
    width: 3.6rem;
    height: 3.6rem;
  }
`;

const ImageWrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;
const Nickname = styled.div`
  margin-top: 2.9rem;
  font-size: 2.4rem;
  font-weight: bold;
`;

const Email = styled.div`
  font-size: 1.6rem;
  font-family: ${theme.fontWeight.semibold};
  margin-top: 1.8rem;
`;

const EditButton = styled.div`
  width: 18rem;
  height: 3.2rem;
  border-radius: 5px;
  font-size: 1.6rem;
  font-weight: ${theme.fontWeight.semibold};
  background-color: ${theme.colors.purple};
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1.6rem;
  cursor: pointer;
  :hover {
    background-color: white;
    border: solid 3px ${theme.colors.purple};
    color: ${theme.colors.purple};
  }
`;

const ProgressWrapper = styled.div`
  margin-top: 1.4rem;
  display: flex;
  flex-direction: column;
  width: 29rem;
  img {
    width: 2.4rem;
  }
  .ant-progress-bg {
    background-color: ${theme.colors.mint};
  }
`;
