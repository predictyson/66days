import { Modal } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";

import BronzeBadge from "../.././assets/bronze.png";
import SilverBadge from "../.././assets/silver.png";
import GoldBadge from "../.././assets/gold.png";
import PlatinumBadge from "../.././assets/platinum.png";
import DiamondBadge from "../.././assets/diamond.png";
import MasterBadge from "../.././assets/master.png";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
}

const tierList = [
  {
    tier: "Bronze",
    exp: 0,
    tierImg: BronzeBadge,
  },
  {
    tier: "Silver",
    exp: 3000,
    tierImg: SilverBadge,
  },
  {
    tier: "Gold",
    exp: 8000,
    tierImg: GoldBadge,
  },
  {
    tier: "Platinum",
    exp: 15000,
    tierImg: PlatinumBadge,
  },
  {
    tier: "Diamond",
    exp: 40000,
    tierImg: DiamondBadge,
  },
  {
    tier: "Master",
    exp: 75000,
    tierImg: MasterBadge,
  },
];

export default function TierInfoModal(props: PropsType) {
  return (
    <>
      <Modal
        open={props.open}
        onCancel={props.toggleModal}
        width={1200}
        footer={null}
      >
        <RankInfoWrapper>
          <div className="modal-title">티어 설명</div>
          <TierInfoContainer>
            {tierList.map((tier) => (
              <TierBox key={tier.exp}>
                <div className="tier__name">{tier.tier}</div>
                <img src={tier.tierImg} className="tier__img" />
                <div className="tier__exp">{tier.exp} EXP</div>
              </TierBox>
            ))}
          </TierInfoContainer>
        </RankInfoWrapper>
      </Modal>
    </>
  );
}

const RankInfoWrapper = styled.div`
  padding: 3rem 6rem;

  .modal-title {
    width: 100%;
    text-align: center;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.bold};
  }
`;

const TierInfoContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  color: ${theme.colors.gray400};
  padding-top: 7rem;
`;

const TierBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 3rem;
  border-radius: 5px;
  width: 9rem;
  font-size: 1.6rem;
  padding: 1rem 3rem;

  &:last-child {
    margin-right: 0;
  }

  .tier__name {
    color: ${theme.colors.black};
    padding-bottom: 1rem;
    font-size: 2rem;
    font-weight: 700;
  }

  .tier__img {
    width: 5rem;
    padding-bottom: 1rem;
  }

  .tier__exp {
    color: ${theme.colors.black};
  }
`;
