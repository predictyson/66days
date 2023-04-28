import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "./index.css";
import router from "./router";
import { createGlobalStyle, ThemeProvider } from "styled-components";
import { normalize } from "styled-normalize";
import { theme } from "./styles/theme";
import Kanit_Regular from "./assets/font/Kanit-Regular.ttf";
import Kanit_Medium from "./assets/font/Kanit-Medium.ttf";
import Kanit_Bold from "./assets/font/Kanit-Bold.ttf";
import Kanit_SemiBold from "./assets/font/Kanit-SemiBold.ttf";
const GlobalStyle = createGlobalStyle`
  ${normalize}
  html{
    font-size: 62.5%; // percent of the font size of your browser // 1rem = 10px
    box-sizing: border-box;
    background-color: #3E2133;
    /* background: linear-gradient(136.16deg, #3E2133 12.91%, rgba(32, 10, 43, 0.69) 87.68%); */
    color: white;
    overflow-y: hidden;

    font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
  }
  body{
    margin: 0;

  } 
  ::-webkit-scrollbar {
    width: 6px;
  }
  ::-webkit-scrollbar-thumb {
    border-radius: 6px;
  }
  @font-face {
        font-family: "Kanit_Regular";
        src: local("Kanit_Regular"), url(${Kanit_Regular}) format('ttf'); 
        font-weight: regular;
    }
    @font-face {
        font-family: "Kanit_Medium";
        src: local("Kanit_Medium"), url(${Kanit_Medium}) format('ttf');
        font-weight: medium;
    }
    @font-face {
        font-family: "Kanit_SemiBold";
        src: local("Kanit_SemiBold"), url(${Kanit_SemiBold}) format('ttf');
        font-weight: semibold;
    }
   @font-face {
        font-family: "Kanit_Bold";
        src: local("Kanit_Bold"), url(${Kanit_Bold}) format('ttf');
        font-weight: bold;
    }
`;
ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(

  <ThemeProvider theme={theme}>
    <GlobalStyle />
    <React.StrictMode>
   <RouterProvider router={router} />
    </React.StrictMode>
  </ThemeProvider>
);
