import {
  createRoutesFromElements,
  createBrowserRouter,
  Route,
} from "react-router-dom";
import ErrorPage from "./error-page";
import Root from "./routes/Root";
import LandingPage from "./routes/LandingPage";
import Group from "./routes/Group";
import GroupIntro from "./routes/GroupIntro";
import Challenge from "./routes/Challenge";
import MainPage from "./routes/MainPage";
import SearchPage from "./routes/SearchPage";
import MyPage from "./routes/MyPage";
import OAuth2Redirect from "./routes/OAuth2Redirect";
import SocialSignup from "./routes/SocialSignup";

export default createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Root />} errorElement={<ErrorPage />}>
      <Route errorElement={<ErrorPage />}>
        <Route index element={<MainPage />} />
        <Route path="/oauth" element={<OAuth2Redirect />} />
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/socialsignup" element={<SocialSignup />} />
        <Route path="/groups" element={<SearchPage />} />
        <Route path="/groups/me" index element={<Group />} />
        <Route path="/groups/:groupId" index element={<GroupIntro />} />
        <Route path="/groups/:groupId/c/:challengeId" element={<Challenge />} />
        <Route path="/group" index element={<Group />} />
        <Route
          path="/groups/:groupId/challenges/:challengeId"
          element={<Challenge />}
        />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/mypage" element={<MyPage />} />
      </Route>
    </Route>
  )
);
