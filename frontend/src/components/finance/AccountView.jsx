import { Button, Col, Form, InputGroup, Row, Table } from "react-bootstrap";
import "../../css/MainContentStyle.css";
import { useState } from "react";

function AccountView(props) {
  console.log(props);
  const [date, setDate] = useState('');

  return (<>
    <div className="d-flex justify-content-center text-center">
      <div className="container p-3">
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

        <Row className="mt-3">
          <Col>
            <InputGroup className="w-50">
              <Form.Control type="date" value={date} onChange={(e) => setDate(e.target.value)}></Form.Control>
              <Button className="basic-button">검색</Button>
            </InputGroup>
          </Col>
        </Row>

        <div className="text-center py-2 mb-2">
          <strong>입출금 내역</strong>
        </div>

        <div className="table-responsive">
          <Table bordered hover className="text-center">
            <thead>
              <tr>
                <th>구분</th>
                <th>날짜</th>
                <th>카테고리</th>
                <th>거래처</th>
                <th>금액</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>입금</td>
                <td>2025-08-11</td>
                <td>수출</td>
                <td>안녕회사</td>
                <td>50,000</td>
              </tr>
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  </>
  );
}

export default AccountView;