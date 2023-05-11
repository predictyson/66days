import styled from "styled-components";
import Moongchi from "../../assets/main/moongchi.png";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/blogBox.png";
import CS from "../../assets/landing/csBox.png";
interface ImageWrapperProps {
  imageUrl: string;
}

interface IProps {
  group: GroupData;
}

export default function GroupItem({ group }: IProps) {
  return (
    <BoxWrapper>
      {group.type === "personal" ? (
        <ImageWrapper imageUrl={Moongchi}>
          <span>
            {group.name}님의 <br />
            개인 챌린지
          </span>
        </ImageWrapper>
      ) : (
        <ImageWrapper imageUrl={Moongchi}>
          <span>
            {group.name}님의 <br />
            그룹 챌린지
          </span>
        </ImageWrapper>
      )}
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
  height: 0;
  padding-bottom: 100%; /* 이미지 비율을 1:1로 유지 */
  background-image: url(${(props) => props.imageUrl});
  background-size: cover; /* 이미지를 부모 요소 크기에 맞춤 */
  background-repeat: no-repeat;

  span {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    margin-bottom: 2rem;
    font-size: 2.4rem;
    font-weight: bold;
    padding: 1rem 2rem;
    color: white;
  }
`;
const BoxWrapper = styled.div`
  width: 27%;
  border-radius: 15px;
`;

const ChallengeWrapper = styled.div`
  margin-top: 0.3rem;
  display: flex;
  justify-content: space-between;
`;
const ChallengeBox = styled.img`
  width: 30%;
  cursor: pointer;
`;
