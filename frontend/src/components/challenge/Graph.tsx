import styled from "styled-components";
import { Checkbox, Typography } from "antd";
import FlagIcon from "../../assets/flag.svg";

interface PropsType {
  commits: {
    freeze: boolean;
    checked: boolean;
  }[];
}

const LENGTH_COMMIT = 66;
const EMPTY_COMMITS = new Array(LENGTH_COMMIT).fill({
  freeze: false,
  checked: false,
});

export default function Graph(props: PropsType) {
  const renderStreaks = () => {
    const commits = props.commits.concat(
      EMPTY_COMMITS.slice(0, LENGTH_COMMIT - props.commits.length)
    );
    const cols = Math.ceil(LENGTH_COMMIT / 3);
    const streaks = [];
    for (let i = 0; i < cols; i++) {
      const startIndex = i * 3;
      const endIndex = Math.min(startIndex + 3, LENGTH_COMMIT);
      const colStreaks = [];
      for (let j = startIndex; j < endIndex; j++) {
        colStreaks.push(
          <Checkbox
            key={j}
            indeterminate={commits[j].freeze}
            onChange={() => {}}
            checked={commits[j].checked}
          />
        );
      }
      streaks.push(<StreakWrapper key={i}>{colStreaks}</StreakWrapper>);
    }
    return streaks;
  };

  return (
    <StyledGraph>
      <Typography.Title level={2}>
        챌린지 그래프
        <Typography.Text
          style={{
            color: "#b8a9fb",
          }}
        >
          35
        </Typography.Text>
        <Typography.Text type="secondary">일째</Typography.Text>
      </Typography.Title>
      <div style={{ display: "flex" }}>
        <div className="graph">{renderStreaks()}</div>
        <img className="flag" src={FlagIcon} />
      </div>
    </StyledGraph>
  );
}

const StreakWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 1.6rem;
  .wrap {
    border: solid 1px red;
    display: flex;
    flex-direction: column;
  }
`;

const StyledGraph = styled.section`
  flex: 3;
  padding: 2rem;

  .graph {
    flex: 1;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    /* max-height: 4rem; */
    max-width: 60rem;
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
