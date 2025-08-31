import { Button, Form, InputGroup, Spinner, Table } from 'react-bootstrap';
import ReactModal from 'react-modal';
import "../../css/MainContentStyle.css";
import "../../css/ModalStyle.css";
import { useEffect, useState } from 'react';
import axios from 'axios';
import Page from '../page_template/Page';

function EmpModal(props) {
  const [formData, setFormData] = useState({
    searchField: "empName",
    searchWord: ""
  });
  const [prevSearch, setPrevSearch] = useState({
    searchField: "empName",
    searchWord: ""
  });
  const [respData, setRespData] = useState();
  const [count, setCount] = useState(0);
  const pageSize = 5;
  const blockSize = 3;
  const [isEndLoading, setIsEndLoading] = useState(false);

  const setModalData = (key, value) => {
    props.selectData((prev) => ({
      ...prev,
      [key]: value
    }));
  };

  const formDataHandler = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  }

  const getData = async () => {
    const countResp = await axios.get(props.baseUrl + "/employees/count")
    const response = await axios.get(props.baseUrl + "/employees/page/1/" + pageSize);
    setCount(countResp.data);
    setRespData(response.data);
    setIsEndLoading(true);
  }

  useEffect(function () {
    if (!props.isOpen)   // 닫힐땐 다시 리스트를 가져오지 않게 return 한다
      return;

    getData();

  }, [props.isOpen]);


  const searchData = async (e) => {
    e.preventDefault();
    setPrevSearch({ searchField: formData.searchField, searchWord: formData.searchWord });
    const countResp = await axios.get(props.baseUrl + "/employees/count/" + formData.searchField + "/" + formData.searchWord);
    const response = await axios.get(props.baseUrl + "/employees/" + formData.searchField + "/" + formData.searchWord + "/page/1/" + pageSize);
    setCount(countResp.data);
    setRespData(response.data);
  }

  let trData = [];

  if (Array.isArray(respData)) {
    respData.forEach(element => {
      // console.log(element);
      trData.push(
        <tr key={element.empId}>
          <td>{element.empId}</td>
          <td>{element.deptName}</td>
          <td>{element.empName}</td>
          <td><Button size="sm" className="basic-button" onClick={() => {
            setModalData("empId", element.empId);
            setModalData("empName", element.empName);
            setModalData("deptName", element.deptName);
            setModalData("deptCode", element.deptCode);
            // 다시 초기화
            setPrevSearch({
              searchField: "empName",
              searchWord: ""
            });
            props.closeModal();
          }}>선택</Button></td>
        </tr>
      );
    });
  };

  const movePage = async (e, page, size) => {
    e.preventDefault();
    let response = [];
    if (prevSearch.searchWord !== "") {
      // 다른 검색을 하였을때 
      if (prevSearch.searchField !== formData.searchField || prevSearch.searchWord !== formData.searchWord) {
        response = await axios.get(props.baseUrl + "/employees/" + formData.searchField + "/" + formData.searchWord + "/page/" + page + "/" + size);
      } else {
        response = await axios.get(props.baseUrl + "/employees/" + prevSearch.searchField + "/" + prevSearch.searchWord + "/page/" + page + "/" + size);
      }
    } else {
      response = await axios.get(props.baseUrl + "/employees/page/" + page + "/" + size);
    }
    setRespData(response.data);
  }

  // 백엔드에서 데이터 가져오기 전 로딩중인걸 표시
  if (!isEndLoading) {
    return <div className="d-flex justify-content-center align-items-center min-vh-100"><Spinner animation="border" role="status" /></div>
  }

  return (<>
    <ReactModal overlayClassName="ModalOverlay" className="ModalContent" isOpen={props.isOpen} ariaHideApp={false}>
      <div className="p-3 text-center">
        <div className="d-flex rounded mb-3 justify-content-between">
          {/* 검색하기 */}
          <form onSubmit={searchData} method="post">
            <InputGroup>
              <Form.Control as="select" name="searchField" id="searchField" defaultValue={"empName"} required onChange={formDataHandler}>
                <option value="empName">이름</option>
                <option value="empId">아이디</option>
              </Form.Control>
              <Form.Control className="w-25" type="text" name="searchWord" id="searchWord" placeholder="입력..." onChange={formDataHandler} />
              <Button className="basic-button" type="submit">검색</Button>
              <Button className="basic-button mx-3" onClick={(e) => {
                e.preventDefault();
                setPrevSearch({
                  searchField: "empName",
                  searchWord: ""
                })
                getData();
              }}> 검색 초기화</Button>
            </InputGroup>
          </form>
          <div>
            <Button className="basic-button" onClick={() => {
              setPrevSearch({
                searchField: "empName",
                searchWord: ""
              });
              props.closeModal();
            }}>닫기</Button>
          </div>
        </div>
        <div>
          <Table hover bordered className="text-center">
            <thead>
              <tr>
                <th>사번</th>
                <th>부서</th>
                <th className="w-50">이름</th>
                <th>선택</th>
              </tr>
            </thead>
            <tbody>
              {trData}
            </tbody>
          </Table>
        </div>
        <Page count={count} pageSize={pageSize} blockSize={blockSize} movePage={movePage} />

        <div>
          <Button className="basic-button mt-3" onClick={() => {
            setPrevSearch({
              searchField: "empName",
              searchWord: ""
            });
            props.closeModal();
          }}>닫기</Button>
        </div>
      </div>
    </ReactModal >
  </>
  );
}

export default EmpModal;