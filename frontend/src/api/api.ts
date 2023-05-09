import axios from "axios";

export default axios.create({
  baseURL: "http://k8a705.p.ssafy.io:8080",
  timeout: 1000,
});
