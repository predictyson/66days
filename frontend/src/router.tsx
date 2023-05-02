import {
  createRoutesFromElements,
  createBrowserRouter,
  Route,
} from "react-router-dom";
import ErrorPage from "./error-page";
import Root from "./routes/Root";
import Home from "./routes/Home";
import LandingPage from "./routes/LandingPage";
import Group from "./routes/Group";
import Challenge from "./routes/Challenge";

export default createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Root />} errorElement={<ErrorPage />}>
      <Route errorElement={<ErrorPage />}>
        <Route index element={<Home />} />
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/group" index element={<Group />} />
        <Route
          path="/groups/:groupId/challenges/:challengeId"
          element={<Challenge />}
        />
      </Route>
    </Route>
  )
);
