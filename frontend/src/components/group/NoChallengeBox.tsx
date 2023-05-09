import { Layout } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";

const { Content } = Layout;

export default function NoChallengeBox() {
  return (
    <>
      <ChallengeBoxWrapper>
        <NoChallengeContainer>챌린지가 없습니다.</NoChallengeContainer>
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
  word-break: keep-all;
`;

const NoChallengeContainer = styled(Content)`
  width: 15vw;
  height: 15vw;
  max-height: 15vw;
  background-color: ${theme.colors.gray300};
  border-radius: 1rem;
  font-size: 1.6rem;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
`;
