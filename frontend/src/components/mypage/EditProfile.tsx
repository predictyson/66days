import styled from "styled-components";
import ProfileImg from "../../assets/main/Profile3.png";
import { theme } from "../../styles/theme";

export default function EditProfile() {
  return (
    <Container>
      <img className="profile-img" src={ProfileImg} alt="profile-img" />
      <Nickname>닉네임 수정</Nickname>
      <SearchInput placeholder="뭉치뭉치똥뭉치"></SearchInput>
      <ButtonWrapper>
        <button className="save"> save</button>
        <button className="cancel">cancel</button>
      </ButtonWrapper>
    </Container>
  );
}

const Nickname = styled.div`
  font-size: 2rem;
  font-weight: ${theme.fontWeight.semibold};
  margin-top: 6.5rem;
  margin-right: auto;
`;

const SearchInput = styled.input`
  margin-top: 1rem;
  width: 30rem;
  height: 4.5rem;
  border-radius: 10px;
  border: solid 1px ${theme.colors.gray500};
  font-size: 2rem;
  padding-left: 2rem;
  display: flex;
  margin-right: auto;
  ::placeholder {
    color: ${theme.colors.gray300};
  }
`;

const ButtonWrapper = styled.div`
  margin-top: 3rem;
  display: flex;
  width: 60%;
  margin-right: auto;
  justify-content: space-between;
  button {
    font-size: 2rem;
    width: 8.6rem;
    height: 4.2rem;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
  .save {
    border: solid 2px black;
    background-color: white;
    color: black;
    cursor: pointer;
  }
  .cancel {
    background-color: black;
    color: white;
    border: solid 2px black;
  }
`;
const Container = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  position: relative;
  width: 80%;
  margin: 0 auto;
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
