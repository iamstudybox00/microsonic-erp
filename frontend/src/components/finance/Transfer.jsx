import { Row, Form, Button, Col } from "react-bootstrap";
import "../../css/MainContentStyle.css";

function Transfer(props) {
  console.log(props);

  return (<>
    <div className="d-flex justify-content-center">
      <div className="container border p-4">
        <div className="mb-2"><strong>내 계좌</strong></div>
        <Row>
          <Col>
            {/* 팝업창이나 select로 처리할것 일단은 select문으로 함 */}
            {/* onchange로 바뀔때마다 select해도 될까? */}
            <Form.Control as={"select"} placeholder="계좌 선택" required>
              <option value="">--선택--</option>
              <option value="ac1">계좌1</option>
              <option value="ac2">계좌2</option>
              <option value="ac3">계좌3</option>
            </Form.Control>
          </Col>
          <Col>
            {/* 계좌 선택하면 알아섯 계좌 번호가 기입되도록 */}
            <Form.Control placeholder="계좌 번호" readOnly />
          </Col>
        </Row>
        
        <div className="mb-2"><strong>상대방 계좌</strong></div>
        <Row>
          <Col><Form.Control placeholder="은행을 입력하세요" required /></Col>
          <Col><Form.Control placeholder="계좌번호를 입력하세요" required /></Col>
        </Row>

        <div className="text-center mb-4 mt-4">
          <Form.Control placeholder="금액을 입력하세요" required />
        </div>

        <div className="d-flex justify-content-center">
          <Button className="submit-button px-4">이체</Button>
        </div>
      </div>
    </div>
  </>
  );
}

export default Transfer;