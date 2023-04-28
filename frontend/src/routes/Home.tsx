import { useState } from "react";
<<<<<<< HEAD:frontend/src/routes/Home.tsx
import reactLogo from "../assets/react.svg";
import viteLogo from "/vite.svg";

export default function Home() {
=======
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

function App() {
>>>>>>> ff0e4c0 ( feat: Global Style 설정):frontend/src/App.tsx
  const [count, setCount] = useState(0);

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}
<<<<<<< HEAD:frontend/src/routes/Home.tsx
=======

export default App;
>>>>>>> ff0e4c0 ( feat: Global Style 설정):frontend/src/App.tsx
