import api from "./api";

export async function fetchSearchData(searchContent: string, filterBy: string) {
  try {
    const res = await api.get(
      `/api/v1/main-service​/group​/search=${searchContent}&filterBy=${filterBy}`
    );
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }

    throw new Error("unexpected res status: " + res.status);
  } catch (error) {
    console.log("search get data err: " + error);
  }
}
