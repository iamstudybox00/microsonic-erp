import { Button, Spinner, Table } from 'react-bootstrap';
import { Link, useNavigate, useParams } from "react-router-dom";
import "../../css/MainContentStyle.css";
import { useEffect, useState } from 'react';
import axios from 'axios';


// 파일리스트는 팝업으로 해보기
function TransactionFileList(props) {
  console.log(props);

  const navigate = useNavigate();
  const params = useParams();
  const [respData, setRespData] = useState();
  const [isEndLoading, setIsEndLoading] = useState(false);

  function goView() {
    navigate("/transactionView/" + params.idx);
  }

  const getData = async () => {
    const response = await axios.get(props.baseUrl + "/files/" + params.idx)
    setRespData(response.data);
    setIsEndLoading(true);
  }

  function downloadData(fileIdx, ofile) {
    axios({
      url: props.baseUrl + "/files/download/" + fileIdx,
      method: 'GET',
      responseType: 'blob', // 필수
    }).then((res) => {
      const url = window.URL.createObjectURL(new Blob([res.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', ofile); // 서버에서 파일명 내려주면 그걸로 설정 가능
      document.body.appendChild(link);
      link.click();
      link.remove();
    });
  }

  useEffect(function () {
    getData();
  }, [])

  let fileData = [];

  if (Array.isArray(respData)) {
    respData.forEach(element => {
      fileData.push(
        <tr key={element.fileIdx}>
          <td>{element.ofile}</td>
          <td><Button size="sm" className="basic-button" onClick={() => {
            downloadData(element.fileIdx, element.ofile);
          }}>다운로드</Button></td>
        </tr>
      );
    });
  };

  // 백엔드에서 데이터 가져오기 전 로딩중인걸 표시
  if (!isEndLoading) {
    return <div className="d-flex justify-content-center align-items-center min-vh-100"><Spinner animation="border" role="status" /></div>
  }

  return (<>
    <div className="container">
      <div className="p-3 text-center">
        <div className="d-flex justify-content-center mt-4">
          <Table hover bordered className="text-center">
            <thead>
              <tr>
                <th className="w-75">파일명</th>
                <th>다운로드</th>
              </tr>
            </thead>
            <tbody>
              {fileData}
            </tbody>
          </Table>
        </div>
        <Button className="basic-button" onClick={goView}>닫기</Button>
      </div>
    </div>
  </>
  );
}

export default TransactionFileList;