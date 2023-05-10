import axios from "axios";

// const SERVER_URL = import.meta.env.VITE_SERVER_DOMAIN;

const instance = axios.create({
  withCredentials: true,
  baseURL: import.meta.env.VITE_SERVER_DOMAIN,
  timeout: 1000,
});

export default instance;
