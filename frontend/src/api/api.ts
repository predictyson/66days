import axios from "axios";

// const SERVER_URL = import.meta.env.VITE_SERVER_DOMAIN;

const instance = axios.create({
  withCredentials: true,
  baseURL: "http://k8a705.p.ssafy.io:8080",
  timeout: 1000,
});

export default instance;
