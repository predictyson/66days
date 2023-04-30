import { Route, Routes } from "react-router-dom";
import pages from "./pages";

function App() {
  return (
    <Routes>
      <Route path="/" element={<pages.Home />} />
      <Route path="/error" element={<pages.Error />} />
    </Routes>
  );
}

export default App;
