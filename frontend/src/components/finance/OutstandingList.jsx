import { Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../../css/MainContentStyle.css";

function OutstandingList(props) {
  console.log(props);
  
  // 수금 처리
  function collect(){
    alert("수금 처리 바꾸기");
  }

  return (<>
    <div className="d-flex justify-content-center">
      <div className="container border p-4">
        <div className="text-center mb-3"><strong>미수금 내역</strong></div>
        <div className="table-responsive">
          <Table bordered hover className="text-center">
            <thead>
              <tr>
                <th>미수금 날짜</th>
                <th>금액</th>
                <th>거래처</th>
                <th>수금 여부</th>
                <th>수금 날짜</th>
                <th>수금 처리</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                {/* db에서 값받아 집어넣을것 */}
                <td>0001</td>
                <td>10000</td>
                <td>안녕회사</td>
                <td>미수금</td>
                <td></td>
                <td><Link onClick={collect}>수금 처리</Link></td>
              </tr>
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  </>
  );
}

export default OutstandingList;