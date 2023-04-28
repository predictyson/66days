import { createGlobalStyle } from "styled-components";
import Kanit_Regular from "../assets/font/Kanit-Regular.woff2";
import Kanit_Medium from "../assets/font/Kanit-Medium.woff2";
import Kanit_Bold from "../assets/font/Kanit-Bold.woff2";
import Kanit_SemiBold from "../assets/font/Kanit-SemiBold.woff2";

export default createGlobalStyle`
  @font-face {
        font-family: "Kanit-Regular";
        src:  url(${Kanit_Regular}) format('woff2'); 
    }
    @font-face {
        font-family: "Kanit-Medium";
        src: url(${Kanit_Medium}) format('woff2');
    }
    @font-face {
        font-family: "Kanit-SemiBold";
        src: url(${Kanit_SemiBold}) format('woff2');
    }
   @font-face {
        font-family: "Kanit-Bold";
        src: url(${Kanit_Bold}) format('woff2');
    }
`;
