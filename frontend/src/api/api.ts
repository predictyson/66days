import axios from "axios";

// const SERVER_URL = import.meta.env.VITE_SERVER_DOMAIN;

const instance = axios.create({
  withCredentials: true,
  baseURL: import.meta.env.VITE_SERVER_DOMAIN,
  timeout: 1000,
  headers: {
    // FIXME: replaec this with Bearer token
    Authorization: `f1629861-f09b-11ed-a26b-0242ac110003`,
  },
});

export default instance;
