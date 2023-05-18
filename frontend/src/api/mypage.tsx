import api from "./api";

// my page 정보 가져오기
export async function getMyPageInfo() {
  try {
    const res = await api.get(`/api/v1/main-service/page/me`);
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }

    throw new Error();
  } catch (error) {
    console.log("mypage get data err");
  }
}

// 닉네임 수정
export async function patchNickname(nickname: string) {
  try {
    const res = await api.patch(`/api/v1/main-service/user/modify/${nickname}`);
    if (res.status === 200) {
      // console.log(res.data);
      return res.data;
    }
    throw new Error();
  } catch (error) {
    console.log("patch nickname err");
  }
}

// 닉네임 중복 체크
export async function checkNickname(nickname: string) {
  try {
    const res = await api.get(
      `/api/v1/main-service/user/check-nickname/${nickname}`
    );
    if (res.status === 200) {
      // console.log(res.data);
      return res.data;
    }
    throw new Error();
  } catch (error) {
    console.log("nickname check err");
  }
}
// 프로필 이미지 수정
//TODO: 프로필 이미지 수정
// export async function patchImage(image: string) {
//   try {
//     const res = await api.get(`/api/v1/main-service/user/image`);
//     if (res.status === 200) {
//       // console.log(res.data);
//       return res.data;
//     }
//     throw new Error();
//   } catch (error) {
//     console.log("nickname check err");
//   }
// }
// //   try {
// //     const response = await axios.patch('/api/images', formData, {
// //       headers: {
// //         'Content-Type': 'multipart/form-data',
// //       },
// //     });

// //     // 이미지 수정에 대한 응답 처리
// //     console.log(response.data);
// //   } catch (error) {
// //     // 오류 처리
// //     console.error(error);
// //   }
// // }
