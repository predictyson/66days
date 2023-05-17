import ReactDOM from "react-dom/client";
import React from "react";
import { RouterProvider } from "react-router-dom";
import router from "./router";
import { ThemeProvider } from "styled-components";
import { theme } from "./styles/theme";
import GlobalStyle from "./styles/GlobalStyle.tsx";
import GlobalFont from "./styles/GlobalFont.tsx";
import { ConfigProvider } from "antd";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import SSENoti from "./components/common/SSENoti.tsx";
import SSENoti2 from "./components/common/SSENoti2.tsx";
ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <GlobalFont />
      <ConfigProvider
        theme={{
          token: {
            colorPrimary: theme.colors.purple,
            colorPrimaryHover: "#9989DF",
            colorSuccess: theme.colors.success,
            colorError: theme.colors.failure,
            colorInfo: theme.colors.update,
            fontFamily: "Kanit-Medium",
            fontSize: 10,
          },
        }}
      >
        <RouterProvider router={router} />
        <SSENoti2 />
      </ConfigProvider>
    </ThemeProvider>
  </React.StrictMode>
);
