import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Col, Form, Row, Spinner, Table } from 'react-bootstrap';
import { useNavigate, useParams } from "react-router-dom";
import "../../css/MainContentStyle.css";

function TransactionView(props) {
  const navigate = useNavigate();
  const [respData, setRespData] = useState({
    transactionDate: "", companyName: "", transactionDivision: "",
    proofCategory: "", deptName: "", empId: "", empName: "", itemList: [], recordList: []
  });
  const [allItemTotal, setAllItemTotal] = useState(0);
  const [recordTotal, setRecordTotal] = useState({
    debit: 0,
    credit: 0
  });
  const params = useParams();
  const [isEndLoading, setIsEndLoading] = useState(false);

  function ConfirmDelete() {
    if (confirm("삭제하시겠습니까?")) {
      return true;
    }
    return false;
  }

  function goList() {
    navigate("/transactionList/1");
  }

  function goEdit() {
    navigate("/transactionEdit/" + params.idx);
  }

  function goFileList() {
    navigate("/transactionFileList/" + params.idx);
  }

  const getData = async () => {
    const response = await axios.get(props.baseUrl + "/transactions/" + params.idx)
    setRespData(response.data);
    setAllItemTotal(response.data.itemList.reduce((prev, cur) => {
      prev += cur.totalPrice;
      return prev;
    }, 0));

    setRecordTotal(response.data.recordList.reduce((total, cur) => {
      // 차/대변을 나누어 합계를 계산
      if (cur.accountingRecordCategory === "차변") {
        total.debit += Number(cur.accountingRecordAmount);
      } else if (cur.accountingRecordCategory === "대변") {
        total.credit += Number(cur.accountingRecordAmount);
      }

      return total;
    }, { debit: 0, credit: 0 }));
    setIsEndLoading(true);
  }

  useEffect(function () {
    getData();

  }, []);

  // 백엔드에서 데이터 가져오기 전 로딩중인걸 표시
  if (!isEndLoading) {
    return <div className="d-flex justify-content-center align-items-center min-vh-100"><Spinner animation="border" role="status" /></div>
  }

  return (<>
    <div className="container">
      <div className="mt-4 p-4 border rounded">
        <form action="">
          <Row className="mb-3">
            <Col>
              <strong>거래 일자</strong>
              <Form.Control type="datetime" placeholder="거래 일자" value={respData.transactionDate} readOnly />
            </Col>
            <Col>
              <strong>거래처</strong>
              <Form.Control name="companyName" value={respData.companyName} readOnly />
            </Col>
          </Row>
          <Row className="mb-3">
            <Col>
              <strong>구분</strong>
              <Form.Control name="transactionDivision" value={respData.transactionDivision} readOnly />
            </Col>
            <Col>
              <strong>증빙</strong>
              <Form.Control name="proofCategory" value={respData.proofCategory} readOnly />
            </Col>
          </Row>

          <Row className="mb-3">
            <Col>
              <strong>담당 사원 부서</strong>
              <Form.Control placeholder="담당 사원 부서" value={respData.deptName} readOnly />
            </Col>
            <Col>
              <strong>담당 사원 번호</strong>
              <Form.Control placeholder="담당 사원 번호" value={respData.empId} readOnly />
            </Col>
            <Col>
              <strong>담당 사원명</strong>
              <Form.Control placeholder="담당 사원명" value={respData.empName} readOnly />
            </Col>
          </Row>

          {/* 제품 */}
          <Row className="mb-2">
            <Col><strong>제품 정보</strong></Col>
          </Row>
          <Table bordered size="sm" className="text-center">
            <thead>
              <tr>
                <th className="w-10">제품 코드</th>
                <th className="w-25">제품명</th>
                <th className="w-10">수량</th>
                <th>단가</th>
                <th className="w-25">합계</th>
              </tr>
            </thead>
            <tbody>
              {respData.itemList.map((element) => (
                <tr key={element.transactionItemIdx}>
                  <td>{element.itemCode}</td>
                  <td>{element.itemName}</td>
                  <td>{element.amount}</td>
                  <td>{element.itemEaPrice}</td>
                  <td>{element.totalPrice}</td>
                </tr>
              ))}
              <tr>
                <th colSpan={4}>총 합계</th>
                <td colSpan={2}>{allItemTotal}</td>
              </tr>
            </tbody>
          </Table>

          <Row className="mb-3">
            <Col><strong>분개</strong></Col>
          </Row>
          <Table bordered size="sm" className="text-center">
            <thead>
              <tr>
                <th className="w-10">구분</th>
                <th className="w-10">계정 코드</th>
                <th className="w-10">계정 과목</th>
                <th >차변</th>
                <th >대변</th>
                <th className="w-25">분개적요</th>
              </tr>
            </thead>
            <tbody>
              {respData.recordList.map((element) => (
                <tr key={element.accountingRecordIdx}>
                  <td>{element.accountingRecordCategory}</td>
                  <td>{element.accountSubjectCode}</td>
                  <td>{element.accountSubjectName}</td>
                  <td>{element.accountingRecordCategory === "차변" ? element.accountingRecordAmount : 0}</td>
                  <td>{element.accountingRecordCategory === "대변" ? element.accountingRecordAmount : 0}</td>
                  <td>{element.summary}</td>
                </tr>
              ))}
              <tr>
                <th colSpan={3}>총 합계</th>
                <td>{recordTotal.debit}</td>
                <td>{recordTotal.credit}</td>
                <th></th>
              </tr>
            </tbody>
          </Table>

          {/* 첨부파일 목록 버튼 */}
          <div className="mb-3">
            <Button className="basic-button" onClick={goFileList}>첨부파일 목록 보기</Button>
          </div>

          <div className="d-flex justify-content-center gap-3">
            {/* 작성자인지 체크해라 */}
            <Button className="submit-button" onClick={goEdit}>수정</Button>
            <Button className="cancel-button" onClick={goList}>전표 내역으로</Button>
          </div>
        </form >
      </div >
    </div>
  </>
  );
}

export default TransactionView;