import api from "./api";

export async function fetchSearchData(searchContent: string, pgNo: number) {
  try {
    const res = await api.get(
      `/api/v2/main-service/group/search?searchContent=${searchContent}&pgNo=${pgNo}`
    );
    console.log(res.data);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error("unexpected res status: " + res.status);
  } catch (error) {
    console.log("search get data err: " + error);
  }
}

export async function putGroupApply(group_id: number, status: string) {
  try {
    const res = await api.put(
      `/api/v2/main-service/group/${group_id}/apply/${status}`
    );
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }
    throw new Error();
  } catch (error) {
    console.log("put group data error");
  }
}
