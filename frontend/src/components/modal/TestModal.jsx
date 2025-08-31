import { Button, Form, InputGroup, Table } from 'react-bootstrap';
import ReactModal from 'react-modal';
import "../../css/MainContentStyle.css";
import "../../css/ModalStyle.css";

function TestModal(props) {
  const setModalData = (key, value) => {
    props.selectData((prev) => ({
      ...prev,
      [key]: value
    }));
  };

  return (<>
    <ReactModal overlayClassName="ModalOverlay" className="ModalContent" isOpen={props.isOpen} ariaHideApp={false}>
      <div className="p-3">
        <div className="d-flex rounded mb-3">
          {/* 검색하기 */}
          <form onSubmit={""} method="post">
            <InputGroup>
              <Form.Control as="select" name="searchField" id="searchField" required>
                <option value="">--선택--</option>
                <option value="test">테스트1</option>
                <option value="test">테스트2</option>
                <option value="test">테스트3</option>
              </Form.Control>
              <Form.Control className="w-25" type="text" name="searchWord" id="searchWord" placeholder="입력..." required></Form.Control>
              <Button className="basic-button" type="submit">검색</Button>
            </InputGroup>
          </form>
        </div>
        <div>
          <Table hover bordered className="text-center">
            <thead>
              <tr>
                <th>사번</th>
                <th>이름</th>
                <th>선택</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>00001</td>
                <td>잉현준</td>
                <td><Button size="sm" onClick={() => {setModalData("empId", "00001"); setModalData("empName", "잉현준"); props.closeModal();}}>선택</Button></td>
              </tr>
            </tbody>
          </Table>
        </div>

        <div>
          <Button className="basic-button" onClick={props.closeModal}>닫기</Button>
        </div>
      </div>
    </ReactModal>
  </>
  );
}

export default TestModal;