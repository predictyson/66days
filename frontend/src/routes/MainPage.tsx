import React from "react";
import styled from "styled-components";
import Banner from "../components/main/Banner";
import Todo from "../components/main/Todo";
import Groups from "../components/main/Groups";
import Ranking from "../components/main/Ranking";
export default function MainPage() {
  return (
    <>
      <Banner />
      <Todo />
      <Groups />
      <Ranking />
    </>
  );
}
