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
