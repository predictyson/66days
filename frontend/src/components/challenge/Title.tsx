import { YoutubeFilled } from "@ant-design/icons";
import { Space, Typography } from "antd";
import styled from "styled-components";

interface PropsType {
  title: string;
  subtitle: string;
}
export default function Title(props: PropsType) {
  return (
    <StyledTitleWrapper direction="vertical">
      <StyledTitle>
        <YoutubeFilled style={{ fontSize: "4rem", marginRight: "1rem" }} />
        {props.title}
      </StyledTitle>
      <StyledSubtitle type="secondary">{props.subtitle}</StyledSubtitle>
    </StyledTitleWrapper>
  );
}

const StyledTitleWrapper = styled(Space)`
  margin-bottom: 1rem;
`;

const StyledTitle = styled(Typography.Title)`
  font-size: 3.2rem;
`;

const StyledSubtitle = styled(Typography.Text)`
  font-size: 1.6rem;
  font-weight: bold;
`;
