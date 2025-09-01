import { useEffect, useState } from 'react';
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { Link, useNavigate } from "react-router-dom";
import "../../css/MainContentStyle.css";
import ModalController from '../modal/ModalController';
import axios from 'axios';
import { v4 } from 'uuid';


function TransactionWrite(props) {
  let date = new Date();
  // 날짜를 format에 맞춤
  date = date.getFullYear() + "-"
    + (date.getMonth() + 1).toString().padStart(2, "0") + "-"
    + date.getDate().toString().padStart(2, "0") + "T"
    + date.getHours().toString().padStart(2, "0") + ":"
    + date.getMinutes().toString().padStart(2, "0") + ":"
    + date.getSeconds().toString().padStart(2, "0");

  const navigate = useNavigate();
  // modal이 열려있다면 true
  const [isOpenModal, setIsOpenModal] = useState(false);
  // 사용하는 modal의 종류
  const [modalName, setModalName] = useState("NONE");
  const [itemList, setItemList] = useState([]);
  const [recordList, setRecordList] = useState([]);
  // db에 저장하기 위한 파일명을 가진 list
  const [fileList, setFileList] = useState([]);
  // 실제 input으로 받은 파일들
  const [fileUploadList, setFileUploadList] = useState([]);
  const [allItemTotal, setAllItemTotal] = useState(0);
  const [recordTotal, setRecordTotal] = useState({
    debit: 0,
    credit: 0
  });
  const [modalData, setModalData] = useState({
    // 사원
    empId: "",
    empName: "",
    deptName: "",
    deptCode: "",
    // 거래처
    clientCode: "",
    companyName: "",
    clientPicName: "",
    clientContact: "",
    // 제품
    selectPk: "",
    itemCode: "",
    itemName: "",
    itemEaPrice: 0,
    // 차/대변
    selectRecordPk: "",
    accountSubjectCode: "",
    accountSubjectName: "",
  });
  const [formData, setFormData] = useState({
    transactionDate: date,
    clientCode: modalData.clientCode,
    empId: modalData.empId
  });
  // name과 value에 넣을 값이 자동으로 들어감
  const formDataHandlerAuto = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  }

  const fileDataHandlerAuto = (e) => {
    setFileUploadList(e.target.files);
    let files = [];
    for (let i = 0; i < e.target.files.length; i++) {
      files.push({
        sfile: v4() + e.target.files[i].name.substring(e.target.files[i].name.lastIndexOf(".")),
        ofile: e.target.files[i].name
      });
    }
    setFileList(files);
  }

  function goList() {
    navigate("/transactionList/1");
  }

  function openModal() {
    setIsOpenModal(true);
  }

  function closeModal() {
    setIsOpenModal(false);
  }

  // 전표 제품 관련
  function addItem() {
    setItemList([...itemList, { pk: v4(), itemCode: "", itemName: "", amount: 1, itemEaPrice: 0, totalPrice: 0 }]);
  }

  // 제품 삭제
  function ConfirmDeleteItem(deletePk) {
    if (confirm("이 행을 삭제하시겠습니까?")) {
      let updateList = itemList.filter(item => item.pk !== deletePk)
      setItemList(updateList);
      calcAllTotalPrice(updateList);
    }
  }

  // 값 수정시 uuid로 list에서 찾아내어 값 변경
  function updateItem(updatePk, e) {
    setItemList(itemList.map(item => item.pk === updatePk ? { ...item, [e.target.name]: e.target.value } : item));
  }

  // modal에서 선택한 데이터들 실제 적용 totalPrice까지 계산해서 넣기
  function updateItemWithModal(updatePk) {
    let updateList = itemList.map(item => item.pk === updatePk ? { ...item, itemCode: modalData.itemCode, itemName: modalData.itemName, itemEaPrice: modalData.itemEaPrice, totalPrice: modalData.itemEaPrice * item.amount } : item);
    setItemList(updateList);
    calcAllTotalPrice(updateList);
  }

  // 모든 제품들의 totalPrice의 합
  function calcAllTotalPrice(itemList) {
    // 초기화 후 계산
    if (Array.isArray(itemList))
      setAllItemTotal(itemList.reduce((prev, cur) => {
        prev += cur.totalPrice;
        return prev;
      }, 0));
  }

  // amount(개수)가 수정될때마다 totalPrice를 다시 계산
  function calcTotalPrice(updatePk, e) {
    let updateList = itemList.map(item => item.pk === updatePk ? { ...item, amount: e.target.value, totalPrice: item.itemEaPrice * e.target.value } : item);
    setItemList(updateList);
    calcAllTotalPrice(updateList);
  }

  // modal창에서 item선택시
  useEffect(() => {
    updateItemWithModal(modalData.selectPk);
  }, [modalData.itemCode]);

  ///////////////////////////// 전표 제품 관련 끝

  // 차/대변 관련
  function addRecord() {
    setRecordList([...recordList, {
      pk: v4(), accountingRecordCategory: "", accountSubjectCode: "", accountSubject: "",
      accountingRecordAmount: 0, summary: ""
    }]);
  }

  function ConfirmDeleteRecord(deletePk) {
    if (confirm("이 행을 삭제하시겠습니까?")) {
      let updateList = recordList.filter(record => record.pk !== deletePk)
      setRecordList(updateList);
      calcRecordTotalWithList(updateList);
    }
  }

  function updateRecord(updatePk, e) {
    setRecordList(recordList.map(record => record.pk === updatePk ? { ...record, [e.target.name]: e.target.value } : record));
  }

  // 차/대변 확인
  function getCategory(recordPk) {
    for (let record of recordList) {
      if (record.pk === recordPk)
        return record.accountingRecordCategory;
    }
    return null;
  }

  function calcRecordTotal(updatePk, e) {
    // 초기화 후 계산
    let updateTotal = [];
    if (Array.isArray(recordList))
      updateTotal = recordList.reduce((total, cur) => {
        // 차/대변을 나누어 합계를 계산
        var amount = cur.pk === updatePk ? e.target.value : cur.accountingRecordAmount;
        if (cur.accountingRecordCategory === "차변") {
          total.debit += Number(amount);
        } else if (cur.accountingRecordCategory === "대변") {
          total.credit += Number(amount);
        }

        return total;
      }, { debit: 0, credit: 0 });
    setRecordTotal(updateTotal);
  }

  // delete할땐 list형태로 반환받기 때문에 추가
  function calcRecordTotalWithList(recordList) {
    // 초기화 후 계산
    let updateTotal = [];
    if (Array.isArray(recordList))
      updateTotal = recordList.reduce((total, cur) => {
        // 차/대변을 나누어 합계를 계산
        if (cur.accountingRecordCategory === "차변") {
          total.debit += Number(cur.accountingRecordAmount);
        } else if (cur.accountingRecordCategory === "대변") {
          total.credit += Number(cur.accountingRecordAmount);
        }

        return total;
      }, { debit: 0, credit: 0 });
    setRecordTotal(updateTotal);
  }

  useEffect(() => {
    updateAccountSubjectWithModal(modalData.selectRecordPk);
  }, [modalData.accountSubjectCode]);

  function updateAccountSubjectWithModal(updatePk) {
    let updateList = recordList.map(record => record.pk === updatePk ? { ...record, accountSubjectCode: modalData.accountSubjectCode, accountSubjectName: modalData.accountSubjectName } : record);
    setRecordList(updateList);
  }

  ///////////////////////////// 차/대변 관련 끝

  ///////////////////////////// 작성 완료 폼 전달
  const sendForm = async (e) => {
    e.preventDefault();
    if (modalData.empId === "" || modalData.empName === "") {
      alert("담당 사원을 선택해주세요");
      return;
    } else if (modalData.clientCode === "") {
      alert("거래처를 선택해주세요");
      return;
    }

    for (let item of itemList) {
      if (item.itemCode === "") {
        alert("입력되지않은 제품 정보가 있습니다.");
        return;
      }
    }
    // 외부에서 값을 update할때 onchange가 안떠서 submit시 값 설정
    formData.clientCode = modalData.clientCode;
    formData.empId = modalData.empId;
    formData.transactionDate = date;
    formData.itemList = itemList;
    formData.recordList = recordList;
    formData.fileList = fileList;
    formData.totalAmount = allItemTotal;

    let fileData = new FormData();
    for (let i = 0; i < fileUploadList.length; i++) {
      fileData.append("fileList", fileUploadList[i]);
      fileData.append("sfileList", fileList[i].sfile + fileList[i].ofile.substring(fileList[i].ofile.lastIndexOf(".")));
    }

    let response = await axios.post(props.baseUrl + "/transactions", formData);
    if (response.data === 1) { // 성공
      alert("작성 완료");
      //실제 파일들 업로드하기
      if (fileUploadList.length !== 0) {
        response = await axios.post(props.baseUrl + "/files", fileData);
        if (response.data === 1) {
          alert("파일 업로드 완료");
        } else {
          axios.delete(props.baseUrl + "/files", fileData);
        }
      }
      goList();
    } else {
      alert("작성에 실패하였습니다.");
    }
  }


  return (<>
    <div className="container">
      <div className="mt-4 p-4 border rounded">
        <form onSubmit={sendForm} method="POST" encType="multipart/form-data" >
          <Row className="mb-3">
            <Col>
              <strong>거래 일자</strong>
              <Form.Control type="datetime" placeholder="거래 일자" name="transactionDate" value={date} required readOnly />
            </Col>
            <Col>
              <strong>거래처</strong>
              <Form.Control placeholder="거래처" value={modalData.companyName} onClick={() => { setModalName("CLI"); setIsOpenModal(true); }} readOnly />
            </Col>
          </Row>
          <Row className="mb-3">
            <Col>
              <strong>구분</strong>
              <Form.Control as="select" name="transactionDivision" required onChange={formDataHandlerAuto}>
                <option value="">--선택--</option>
                <option value="매출">매출</option>
                <option value="매입">매입</option>
              </Form.Control>
            </Col>
            <Col>
              <strong>증빙</strong>
              <Form.Control as="select" name="proofCategory" required onChange={formDataHandlerAuto}>
                <option value="">--선택--</option>
                <option value="세금계산서">세금계산서</option>
                <option value="계산서">계산서</option>
                <option value="현금영수증">현금영수증</option>
                <option value="기타">기타</option>
              </Form.Control>
            </Col>
          </Row>
          <Row className="mb-3">
            <Col>
              <strong>담당 사원 부서</strong>
              <Form.Control placeholder="담당 사원 부서" key={modalData.deptCode} value={modalData.deptName} onClick={() => { setModalName("EMP"); setIsOpenModal(true); }} readOnly />
            </Col>
            <Col>
              <strong>담당 사원 번호</strong>
              <Form.Control placeholder="담당 사원 번호" value={modalData.empId} onClick={() => { setModalName("EMP"); setIsOpenModal(true); }} readOnly />
            </Col>
            <Col>
              <strong>담당 사원명</strong>
              <Form.Control placeholder="담당 사원명" value={modalData.empName} onClick={() => { setModalName("EMP"); setIsOpenModal(true); }} readOnly />
            </Col>
          </Row>

          {/* 제품 */}
          <Row className="mb-2">
            <Col><strong>제품 정보</strong></Col>
            <Col className="text-end">
              <Button className="basic-button" size="sm" onClick={addItem}>제품 추가</Button>
            </Col>
          </Row>
          <Table bordered size="sm">
            <thead>
              <tr>
                <th className="w-10">제품 코드</th>
                <th className="w-25">제품명</th>
                <th className="w-10">수량</th>
                <th>단가</th>
                <th className="w-25">합계</th>
                <th className="w-10">삭제</th>
              </tr>
            </thead>
            <tbody>
              {itemList.map((element) => (
                <tr key={element.pk}>
                  <td><Form.Control name="itemCode" size="sm" type="text" value={element.itemCode} onClick={() => { setModalName("ITEM"); setIsOpenModal(true); setModalData({ ...modalData, selectPk: element.pk }); }} readOnly /></td>
                  <td><Form.Control name="itemName" size="sm" type="text" value={element.itemName} onClick={() => { setModalName("ITEM"); setIsOpenModal(true); setModalData({ ...modalData, selectPk: element.pk }); }} readOnly /></td>
                  <td><Form.Control name="amount" size="sm" type="number" min={1} value={element.amount} onChange={(e) => { updateItem(element.pk, e); calcTotalPrice(element.pk, e) }} /></td>
                  <td>{element.itemEaPrice}</td>
                  <td>{element.totalPrice}</td>
                  <td className="text-center"><Link onClick={() => { ConfirmDeleteItem(element.pk) }} className="deleteRow">X</Link></td>
                </tr>
              ))}
              <tr>
                <th colSpan={4}>총 합계</th>
                <td>{allItemTotal}</td>
                <th></th>
              </tr>
            </tbody>
          </Table>

          <Row className="mb-3">
            <Col><strong>분개</strong></Col>
            <Col className="text-end">
              <Button className="basic-button" size="sm" onClick={addRecord}>차/대변 추가</Button>
            </Col>
          </Row>
          <Table bordered size="sm">
            <thead>
              <tr>
                <th className="w-10">구분</th>
                <th className="w-10">계정 코드</th>
                <th className="w-10">계정 과목</th>
                <th >차변</th>
                <th >대변</th>
                <th className="w-25">분개적요</th>
                <th className="w-10">삭제</th>
              </tr>
            </thead>
            <tbody>
              {recordList.map((element) => (
                <tr key={element.pk}>
                  <td><Form.Control size="sm" className="text-center" as="select" name="accountingRecordCategory" id="accountingRecordCategory" onChange={(e) => { updateRecord(element.pk, e); }} required >
                    <option value="">--선택--</option>
                    <option value="차변">차변</option>
                    <option value="대변">대변</option>
                  </Form.Control></td>
                  <td><Form.Control name="accountSubjectCode" size="sm" type="text" value={element.accountSubjectCode} onClick={() => { setModalName("ACTSUB"); setIsOpenModal(true); setModalData({ ...modalData, selectRecordPk: element.pk }); }} readOnly /></td>
                  <td><Form.Control name="accountSubjectName" size="sm" type="text" value={element.accountSubjectName} onClick={() => { setModalName("ACTSUB"); setIsOpenModal(true); setModalData({ ...modalData, selectRecordPk: element.pk }); }} readOnly /></td>
                  <td><Form.Control name="accountingRecordAmount" size="sm" type="number" min={0} value={getCategory(element.pk) !== "대변" ? element.accountingRecordAmount : 0} disabled={getCategory(element.pk) !== "차변" ? true : false} onChange={(e) => { updateRecord(element.pk, e); calcRecordTotal(element.pk, e); }} /></td>
                  <td><Form.Control name="accountingRecordAmount" size="sm" type="number" min={0} value={getCategory(element.pk) !== "차변" ? element.accountingRecordAmount : 0} disabled={getCategory(element.pk) !== "대변" ? true : false} onChange={(e) => { updateRecord(element.pk, e); calcRecordTotal(element.pk, e); }} /></td>
                  <td><Form.Control name="summary" size="sm" type="text" value={element.summary} onChange={(e) => { updateRecord(element.pk, e); }} /></td>
                  <td className="text-center"><Link onClick={() => { ConfirmDeleteRecord(element.pk) }} className="deleteRow">X</Link></td>
                </tr>
              ))}
              <tr>
                <th colSpan={3}>총 합계</th>
                <td>{recordTotal.debit}</td>
                <td>{recordTotal.credit}</td>
                <th colSpan={3}></th>
              </tr>
            </tbody>
          </Table>

          {/* 파일 업로드 버튼 */}
          <div className="mb-3">
            <Form.Control name="files" type="file" placeholder="첨부파일 업로드" onChange={fileDataHandlerAuto} multiple></Form.Control>
          </div>

          <div className="d-flex justify-content-center gap-3">
            <Button className="submit-button" type="submit">완료</Button>
            <Button className="cancel-button" onClick={goList}>취소</Button>
          </div>
        </form>
        <ModalController openModal={openModal} closeModal={closeModal} isOpen={isOpenModal} modalName={modalName} selectData={setModalData} baseUrl={props.baseUrl} />
      </div>
    </div>
  </>
  );
}

export default TransactionWrite;
