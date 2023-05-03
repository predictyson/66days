import styled from "styled-components";
import Moongchi from "../../assets/main/moongchi.png";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/blogBox.png";
import CS from "../../assets/landing/csBox.png";
interface ImageWrapperProps {
  imageUrl: string;
}

export default function GroupItem() {
  return (
    <BoxWrapper>
      <ImageWrapper imageUrl={Moongchi}>
        <span>
          뭉치뭉치똥뭉치님의 <br />
          개인 챌린지
        </span>
      </ImageWrapper>
      <ChallengeWrapper>
        <ChallengeBox src={Algo} />
        <ChallengeBox src={Blog} />
        <ChallengeBox src={CS} />
      </ChallengeWrapper>
    </BoxWrapper>
  );
}

const ImageWrapper = styled.div<ImageWrapperProps>`
  position: relative;
  width: 100%;
  height: 33.2rem;
  background-image: url(${(props) => props.imageUrl});
  background-size: 100% 100%;
  background-repeat: no-repeat;

  span {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    font-size: 2.4rem;
    font-weight: bold;
    padding: 1rem 2rem;
    color: white;
  }
`;
const BoxWrapper = styled.div`
  width: 33.2rem;
  height: 43.8rem;
  border-radius: 15px;
`;

const ChallengeWrapper = styled.div`
  margin-top: 0.3rem;
  display: flex;
  justify-content: space-between;
`;
const ChallengeBox = styled.img`
  width: 10rem;
  height: 10rem;
`;
