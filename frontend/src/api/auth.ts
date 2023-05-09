import instance from "./api";

export async function kakaoLogin(code: string) {
  try {
    const res = await instance.get(`/api/kakao/login?code=${code}`);
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
    throw new Error("invalid");
  } catch (error) {
    console.error("소셜로그인 에러", error);
  }
}

export async function logout() {
  try {
    const res = await instance.get("/api/logout");
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
  } catch (error) {
    console.error("소셜로그아웃 에러", error);
  }
}
