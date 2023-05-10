import api from "./api";

async function fetchGroupPageData(id = 1) {
  try {
    const res = await api.get(`/groups/${id}`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

// 그룹 관리 or 그룹원 보기 모달의 그룹원 리스트 데이터 fetch
async function fetchGroupMembers(id = 1) {
  try {
    const res = await api.get(`/group/${id}/manage/members`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

// 그룹 가입 신청원 리스트 데이터 fetch
async function fetchAppliedMembers(id = 1) {
  try {
    const res = await api.get(`/group/${id}/manage/apply`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

// async function

export { fetchGroupPageData, fetchGroupMembers, fetchAppliedMembers };
