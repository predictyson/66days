import api from "./api";

export async function getMyPageInfo() {
  try {
    const res = await api.get(`/api/v1/me`);
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }

    throw new Error();
  } catch (error) {
    console.log("mypage get data err");
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}
