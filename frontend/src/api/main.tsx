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

export async function postChallenge(my_challenge_id: number) {
  try {
    const res = await api.post(
      `/api/v1/challenge/streak/${my_challenge_id}`,
      my_challenge_id
    );
    if (res.status === 200) {
      console.log("post success");
      return true;
    }

    throw new Error();
  } catch (error) {
    console.log("post my challenge error");
  }
}
