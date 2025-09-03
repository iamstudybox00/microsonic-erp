import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Form, InputGroup, Spinner, Table } from "react-bootstrap";
import { Link, useNavigate, useParams } from "react-router-dom";
import "../../css/MainContentStyle.css";
import NavigatePage from "../page_template/NavigatePage";

function TransactionList(props) {
  const [date, setDate] = useState({
    start: "",
    end: ""
  });
  // const [prevSearch, setPrevSearch] = useState({
  //   start: "",
  //   end: ""
  // });
  const navigate = useNavigate();
  const [respData, setRespData] = useState();
  const [count, setCount] = useState(0);
  const pageSize = 5;
  const blockSize = 3;
  const [isEndLoading, setIsEndLoading] = useState(false);
  const {page, start, end} = useParams();

  const getData = async () => {
    let response = [];
    let countResp = []
    if (start && end) {
      setDate({ start: start, end: end });
      countResp = await axios.get(props.baseUrl + "/transactions/count/" + start + "/" + end);
      response = await axios.get(props.baseUrl + "/transactions/" + start + "/" + end + "/page/" + page + "/" + pageSize);
    } else {
      setDate({ start: "", end: "" });
      countResp = await axios.get(props.baseUrl + "/transactions/count");
      response = await axios.get(props.baseUrl + "/transactions/page/" + page + "/" + pageSize);
    }
    setCount(countResp.data);
    setRespData(response.data);
    setIsEndLoading(true);
  }

  function ConfirmDelete(transactionIdx) {
    if (confirm("삭제하시겠습니까?")) {
      const deleteData = async () => {
        const response = await axios.delete(props.baseUrl + "/transactions/" + transactionIdx);
        alert(response.data);
        getData();
      }
      deleteData();
    }
  }

  useEffect(function () {
    getData();
  }, [page, start, end]);

  function goView(transactionIdx) {
    navigate("/transactionView/" + transactionIdx);
  }

  let trData = [];
  let amount = { sales: 0, purchase: 0 }
  if (Array.isArray(respData)) {
    respData.forEach(element => {
      trData.push(
        <tr key={element.transactionIdx}>
          <td>{element.transactionDate.toString().split("T")[0]}</td>
          <td>{element.transactionDivision}</td>
          <td>{element.proofCategory}</td>
          <td>{element.transactionDivision === "매입" ? element.totalAmount : 0}</td>
          <td>{element.transactionDivision === "매출" ? element.totalAmount : 0}</td>
          <td>{element.empName}</td>
          <td><Button size="sm" className="basic-button" onClick={() => { goView(element.transactionIdx) }}>보기</Button></td>
          <td><Link onClick={() => { ConfirmDelete(element.transactionIdx) }} className="deleteRow">X</Link></td>
        </tr>
      );
      if (element.transactionDivision === "매입") {
        amount.sales += element.totalAmount;
      } else {
        amount.purchase += element.totalAmount;
      }
    });
  };

  function goWrite() {
    navigate("/transactionWrite");
  }

  const searchData = async (e) => {
    e.preventDefault();
    navigate("/transactionList/1/" + date.start + "/" + date.end);
  }

  function movePage(e, page) {
    e.preventDefault();
    if (date.start != "" && date.end != "") {
      navigate("/transactionList/" + page + "/" + date.start + "/" + date.end);
    } else{
      navigate("/transactionList/" + page);
    }
  }

  // 백엔드에서 데이터 가져오기 전 로딩중인걸 표시
  if (!isEndLoading) {
    return <div className="d-flex justify-content-center align-items-center min-vh-100"><Spinner animation="border" role="status" /></div>
  }

  return (<>
    <div className="container">
      <div className="d-flex justify-content-between rounded mb-3">
        <form onSubmit={searchData} method="get">
          <InputGroup>
            <Form.Control type="date" value={date.start} required onChange={(e) => {
              setDate({ ...date, start: e.target.value })
              if (date.end >= e.target.value || date.end === "") {
                setDate({ ...date, start: e.target.value })
              } else {
                alert("끝 날짜 이후로는 설정할 수 없습니다");
              }
            }}></Form.Control>&nbsp;&nbsp;~&nbsp;&nbsp;
            <Form.Control type="date" value={date.end} required onChange={(e) => {
              if (date.start <= e.target.value || date.start === "") {
                setDate({ ...date, end: e.target.value })
              } else {
                alert("시작 날짜보다 전으로는 설정할 수 없습니다");
              }
            }}></Form.Control>
            <Button className="basic-button" type="submit">검색</Button>
            <Button className="basic-button mx-3" onClick={(e) => {
                e.preventDefault();
                setDate({
                  start: "",
                  end: ""
                })
                navigate("/transactionList/1");
              }}> 검색 초기화</Button>
          </InputGroup>
        </form>
        <Button className="w-25 basic-button" onClick={goWrite}>전표 작성</Button>
      </div>


      <div className="table-responsive">
        <Table bordered hover className="text-center">
          <thead >
            <tr>
              <th>날짜</th>
              <th className="w-10">매입 / 매출</th>
              <th>증빙 유형</th>
              <th className="w-25">매입</th>
              <th className="w-25">매출</th>
              <th>사원명</th>
              <th>보기</th>
              <th>삭제</th>
            </tr>
          </thead>
          <tbody>
            {trData}
            <tr>
              <th colSpan={3}>총 합계</th>
              <td>{amount.sales}</td>
              <td>{amount.purchase}</td>
              <th colSpan={3}></th>
            </tr>
          </tbody>
        </Table>
      </div>

      <NavigatePage key={page + "-" + date.start + "-" + date.end + "-" + count} count={count} pageSize={pageSize} blockSize={blockSize} movePage={movePage} curPage={parseInt(page)}  />
    </div >
  </>
  );
}

export default TransactionList;