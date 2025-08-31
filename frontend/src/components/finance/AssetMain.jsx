import { Col, Row, Table } from "react-bootstrap";
import "../../css/MainContentStyle.css";

function AssetMain(props) {
  console.log(props);

  return (<>
    <div className="container mt-4">
      <div className="border text-center p-2 mb-3">
        <strong>총 자산</strong>
      </div>
      <Row>
        <Col>
          <div className="border d-flex justify-content-center" style={{ height: "300px" }}>자산 현황 그래프</div>
        </Col>
        <Col>
          <div className="table-responsive">
            <Table bordered hover className="text-center">
              <thead>
                <tr>
                  <th>은행명</th>
                  <th>금액</th>
                </tr>
              </thead>
              <tbody>
                <td>테스트은행</td>
                <td>10,000</td>
              </tbody>
            </Table>
          </div>
        </Col>
      </Row>
    </div>
  </>
  );
}

export default AssetMain;