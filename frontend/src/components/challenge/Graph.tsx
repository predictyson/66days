import styled from "styled-components";
import { Checkbox } from "antd";
import FlagIcon from "../../assets/flag.svg";

interface PropsType {
  commits: {
    freeze: boolean;
    checked: boolean;
  }[];
}

export default function Graph(props: PropsType) {
  return (
    <StyledGraph>
      <div className="graph">
        {props.commits.map((el, i) => (
          <Checkbox
            key={i}
            indeterminate={el.freeze}
            onChange={() => {}}
            checked={el.checked}
          />
        ))}
      </div>
      <img className="flag" src={FlagIcon} />
    </StyledGraph>
  );
}

const StyledGraph = styled.section`
  flex: 3;
  display: flex;
  padding: 2.4rem;

  .graph {
    flex: 1;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    /* max-height: 4rem; */
    height: 8rem;
    margin: 0;

    label {
      margin: 0;
    }
  }

  .flag {
    width: 8rem;
    height: 8rem;
    display: block;
  }

  .ant-checkbox-inner {
    background-color: #b8a9fb;
    border-color: transparent;
  }
  .ant-checkbox-inner:after {
    border: 0;
  }
  .ant-checkbox-indeterminate {
    .ant-checkbox-inner:after {
      background-color: #6854c4;
    }
  }
`;
