import { useState } from "react";
import { Button, Input, Modal, Upload } from "antd";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import { PlusOutlined } from "@ant-design/icons";
import type { UploadFile, UploadProps } from "antd/es/upload/interface";

interface PropsType {
  open: boolean;
  toggleModal: () => void;
}

const { TextArea } = Input;

// const getBase64 = (file: RcFile): Promise<string> =>
//   new Promise((resolve, reject) => {
//     const reader = new FileReader();
//     reader.readAsDataURL(file);
//     reader.onload = () => resolve(reader.result as string);
//     reader.onerror = (error) => reject(error);
//   });

// const beforeUpload = (file: RcFile) => {
//   const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
//   if (!isJpgOrPng) {
//     message.error("You can only upload JPG/PNG file!");
//   }
//   const isLt2M = file.size / 1024 / 1024 < 2;
//   if (!isLt2M) {
//     message.error("Image must smaller than 2MB!");
//   }
//   return isJpgOrPng && isLt2M;
// };

export function CreateGroupModal(props: PropsType) {
  const [fileList, setFileList] = useState<UploadFile[]>([]);

  const handleChange: UploadProps["onChange"] = ({ fileList: newFileList }) =>
    setFileList(newFileList);

  const uploadButton = (
    <div>
      <PlusOutlined />
      <div>Upload</div>
    </div>
  );

  return (
    <>
      <CustomModal
        open={props.open}
        onCancel={props.toggleModal}
        width={800}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={props.toggleModal}
            style={{
              backgroundColor: "black",
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
            }}
          >
            cancel
          </Button>,
          <Button
            key="submit"
            style={{
              fontFamily: "Kanit-SemiBold",
              height: "inherit",
              fontSize: "2rem",
              marginRight: "6rem",
              marginBottom: "4rem",
            }}
          >
            create
          </Button>,
        ]}
      >
        <CreateGroupModalWrapper>
          <div className="modal-title">그룹 생성</div>
          <CreateGroupContentContainer>
            <GroupTitleBox>
              <div className="group-name">그룹명</div>
              <Input className="group-name-input" showCount maxLength={20} />
            </GroupTitleBox>
            <GroupDescBox>
              <div className="group-desc">그룹 설명</div>
              <TextArea
                className="group-desc-input"
                showCount
                maxLength={100}
                style={{ resize: "none" }}
              />
            </GroupDescBox>
            <GroupImageInputBox>
              <div className="group__image-title">그룹 이미지</div>
              <Upload
                className="group__image-input"
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                listType="picture-card"
                fileList={fileList}
                onChange={handleChange}
              >
                {fileList.length >= 1 ? null : uploadButton}
              </Upload>
            </GroupImageInputBox>
          </CreateGroupContentContainer>
        </CreateGroupModalWrapper>
      </CustomModal>
    </>
  );
}

const CustomModal = styled(Modal)`
  .ant-modal-content {
    padding: 0;
  }
`;

const CreateGroupModalWrapper = styled.div`
  padding: 6rem 8rem 1rem;

  .modal-title {
    width: 100%;
    text-align: center;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.bold};
  }
`;

const CreateGroupContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 4rem;
`;

const GroupTitleBox = styled.div`
  display: flex;
  flex-direction: column;

  .group-name {
    font-size: 1.6rem;
    font-weight: 700;
  }

  .group-name-input {
    font-size: 1.6rem;
  }

  .ant-input-suffix {
    align-items: end;
  }
`;

const GroupDescBox = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 3rem;

  .group-desc {
    font-size: 1.6rem;
    font-weight: 700;
  }

  .group-desc-input {
    font-size: 1.6rem;
    height: 10rem;
  }

  .ant-input-data-count {
    bottom: -3rem;
  }
`;

const GroupImageInputBox = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 3rem;

  .group__image-title {
    font-size: 1.6rem;
    font-weight: 700;
  }

  .group__image-input {
    font-size: 1.6rem;
    font-family: "Kanit-Regular";
  }

  /* .anticon-eye {
    display: none;
  } */

  svg {
    width: 2rem;
    height: 2rem;
  }
`;
