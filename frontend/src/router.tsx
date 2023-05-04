import {
  createRoutesFromElements,
  createBrowserRouter,
  Route,
} from "react-router-dom";
import ErrorPage from "./error-page";
import Root from "./routes/Root";
import LandingPage from "./routes/LandingPage";
import Group from "./routes/Group";
import Challenge from "./routes/Challenge";
import MainPage from "./routes/MainPage";
import SearchPage from "./routes/SearchPage";
import GroupIntro from "./routes/GroupIntro";

// TODO: add my-page by YeG
export default createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Root />} errorElement={<ErrorPage />}>
      <Route errorElement={<ErrorPage />}>
        <Route index element={<MainPage />} />
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/groups" element={<SearchPage />} />
        <Route path="/groups/me" element={<Group />} />
        {/* <Route path="/groups/:groupId" element={<Group />} /> */}
        <Route path="/groups/:groupId" element={<GroupIntro />} />
        <Route path="/groups/:groupId/c/:challengeId" element={<Challenge />} />
      </Route>
    </Route>
  )
);
