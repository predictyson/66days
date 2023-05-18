import api from "./api";
export async function fetchMainPageData() {
  try {
    const res = await api.get(`/api/v1/page/home`);
    if (res.status === 200) {
      console.log(res.data.mainPage);
      return res.data.mainPage;
    }

    throw new Error();
  } catch (error) {
    console.log("fetch main page data error");
  }
}
