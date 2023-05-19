import { DesktopOutlined } from "@ant-design/icons";
import styled from "styled-components";

interface PropsType {
  title: string;
  subtitle: string;
}
export default function Title(props: PropsType) {
  return (
    <TitleWrapper>
      <Icon>
        <DesktopOutlined className="icon" />
      </Icon>
      <div>{props.title}</div>
    </TitleWrapper>
    // <Typography.Text type="secondary">{props.subtitle}</Typography.Text>
  );
}

const TitleWrapper = styled.div`
  font-size: 3rem;
  font-weight: 700;
  display: flex;
  padding-bottom: 1rem;
`;

const Icon = styled.div`
  .icon {
    padding-right: 1rem;
  }
`;
