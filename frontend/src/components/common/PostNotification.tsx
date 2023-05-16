import axios from "axios";

const postData = async () => {
  try {
    const data = {
      msg: "아아 앙",
      user_id: "321",
    };

    const response = await axios.post(
      "http://70.12.247.243:8085/notification",
      data
    );
    console.log(response.data); // 성공적으로 포스트된 데이터에 대한 응답 데이터
  } catch (error) {
    console.error(error);
  }
};

export default function MyComponent() {
  const handlePost = () => {
    postData();
  };

  return (
    <div>
      <button onClick={handlePost}>데이터 포스트</button>
    </div>
  );
}
