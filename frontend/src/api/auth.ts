import instance from "./api";

export async function fetchKakaoURL() {
  try {
    const res = await instance.get("/api/v1/oauth/kakao/login");

    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
    throw new Error("invalid");
  } catch (error) {
    console.error("소셜로그인 에러", error);
  }
}

export async function passKakaoCode() {
  try {
    const res = await instance.post("/api/v1/oauth/kakao/token", {
      authorizationCode: new URLSearchParams(window.location.search).get(
        "code"
      ),
    });

    if (res.status === 200) {
      return res.data;
    }
    throw new Error("invalid");
  } catch (error) {
    console.error("소셜로그인 에러", error);
  }
}

export async function logout() {
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
