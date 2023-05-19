import styled from "styled-components";
import Bronze from "../../assets/main/Bronze.png";
import Silver from "../../assets/silver.png";
import Coin from "../../assets/main/Coin.png";
import { Progress } from "antd";
import { theme } from "../../styles/theme";
import { MyUserData } from "../../types/mypage";
import { getImagePath } from "../../util/common";
interface IProps {
  myInfo: MyUserData;
  date: number;
  // handleEdit: (state: boolean) => void;
}
export default function Profile({ myInfo, date }: IProps) {
  return (
    <Container>
      <img
        className="profile-img"
        src={getImagePath(myInfo.profileImagePath)}
        alt="profile-img"
      />
      <img className="badge-img" src={Bronze} alt="badge" />
      <Nickname>{myInfo.nickname}</Nickname>
      <Email>{myInfo.email}</Email>
      {/* <EditButton onClick={() => handleEdit(true)}>edit</EditButton> */}
      <ProgressWrapper>
        <ImageWrapper>
          <img src={Bronze} alt="current-badge" />
          <img src={Silver} alt="next-badge" />
        </ImageWrapper>
        <CustomProgress percent={(date / 66) * 100} />
      </ProgressWrapper>
      <CoinWrapper>
        <img src={Coin} alt="" />
        <span>{myInfo.point}</span>
      </CoinWrapper>
    </Container>
  );
}

const CustomProgress = styled(Progress)`
  width: 80%;
  margin: 0 auto;
`;
const CoinWrapper = styled.div`
  margin: auto;
  margin-top: 1rem;
  display: flex;
  align-items: center;
  font-size: 1.6rem;
  margin-right: auto;
  width: 70%;
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
    border-radius: 50%;
  }
  .badge-img {
    position: absolute;
    top: 20.8rem;
    width: 10%;
    /* height: 3.6rem; */
  }
  @media (max-width: 1200px) {
    width: 70%;
    margin: 0 auto;
    .badge-img {
      width: 4rem;
    }
  }
  @media (max-width: 800px) {
    width: 90%;
  }
`;

const ImageWrapper = styled.div`
  display: flex;
  width: 80%;
  margin: 0 auto;
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

// const EditButton = styled.div`
//   width: 60%;
//   height: 3.2rem;
//   border-radius: 5px;
//   font-size: 1.6rem;
//   font-weight: ${theme.fontWeight.semibold};
//   background-color: ${theme.colors.purple};
//   color: white;
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   margin-top: 1.6rem;
//   cursor: pointer;
//   :hover {
//     background-color: white;
//     border: solid 3px ${theme.colors.purple};
//     color: ${theme.colors.purple};
//   }
// `;

const ProgressWrapper = styled.div`
  margin-top: 1.4rem;
  display: flex;
  flex-direction: column;
  width: 80%;
  img {
    width: 2.4rem;
  }
  .ant-progress-bg {
    background-color: ${theme.colors.mint};
  }
`;
