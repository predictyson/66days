import api from "./api";

// main page data 불러오기
export async function getMainPagedData() {
  try {
    const res = await api.get(`/api/v1/page/home`);
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }

    throw new Error();
  } catch (error) {
    console.log("mypage get data err");
  }
}
