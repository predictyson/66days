import instance from "./api";

export async function expireUserToken() {
  try {
    const res = await instance.get("/api/v1/logout");
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
  } catch (error) {
    console.error("소셜로그아웃 에러", error);
  }
}

export async function signup(
  nickname: string,
  email: string | null,
  profile: string | null
) {
  try {
    const res = await instance.post(
      `${import.meta.env.VITE_SERVER_AUTH_URL}/user/signup/nickname`,
      {
        nickname,
        email,
        profileImagePath: profile,
      }
    );

    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
  } catch (error) {
    console.error("회원가입 에러", error);
  }
}

/**
 * 
닉네임 중복
DuplicateNicknameException

이미 가입된 유저
UserAlreadyExistsException
 */
