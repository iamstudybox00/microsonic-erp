import { Collapse, Nav } from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";
import '../../css/SideBarStyle.css';


function SideBar() {
  const location = useLocation();

  // id값은 현재 링크가 매개변수로 넣은 링크를 포함한다면 현재 들어온 페이지로 인식에 해당 탭을 하이라이트(색 바꿈) 처리
  // 페이지를 만들때 게시판형태(작성, 수정, 삭제)가 있다면 to에 들어갈 주소를 포함하고 다른 주소와 겹치지 않게 해야 탭 하이라이트 작동 
  // 내역을 보여줄때 List를 붙이지 않는것으로 가정 ex) 전표내역을 /transactionList가 아닌 /transaction으로
  // ex) 전표(/transaction)라면 전표 작성(/transactionWrite), 전표 수정(/transactionEdit)와 같이 이름을 포함할 것
  return (<>
    <div className="sidebar">
      <Nav className="flex-column">
        <Nav.Link className="collapsed sideElement" data-bs-toggle="collapse" data-bs-target="#collapseInventory">
          재고관리
        </Nav.Link>
        <Collapse id="collapseInventory">
          <Nav className="sb-sidenav-menu-nested">
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">품목관리</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">재고관리</Nav.Link>
          </Nav>
        </Collapse>
        <Nav.Link className="collapsed sideElement" data-bs-toggle="collapse" data-bs-target="#collapseBusiness">
          영업관리
        </Nav.Link>
        <Collapse id="collapseBusiness">
          <Nav className="sb-sidenav-menu-nested">
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">견적서</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">수주서</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">거래처관리</Nav.Link>
          </Nav>
        </Collapse>
        <Nav.Link className="collapsed sideElement" data-bs-toggle="collapse" data-bs-target="#collapseAccounting">
          회계관리
        </Nav.Link>
        <Collapse id="collapseAccounting">
          <Nav className="sb-sidenav-menu-nested">
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/transaction") ? "activatedLink" : ""}`} as={Link} to="/transactionList/1">전표</Nav.Link>
          </Nav>
        </Collapse>
        <Nav.Link className="collapsed sideElement" data-bs-toggle="collapse" data-bs-target="#collapseFinance">
          금융관리
        </Nav.Link>
        <Collapse id="collapseFinance">
          <Nav className="sb-sidenav-menu-nested">
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/asset") ? "activatedLink" : ""}`} as={Link} to="/assetMain">자산</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/account") ? "activatedLink" : ""}`} as={Link} to="/accountView">입출금내역</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/transfer") ? "activatedLink" : ""}`} as={Link} to="/transfer">이체</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/outstanding") ? "activatedLink" : ""}`} as={Link} to="/outstandingList">미수금</Nav.Link>
          </Nav>
        </Collapse>
        <Nav.Link className="collapsed sideElement" data-bs-toggle="collapse" data-bs-target="#collapseHumanResource">
          인사관리
        </Nav.Link>
        <Collapse id="collapseHumanResource">
          <Nav className="sb-sidenav-menu-nested">
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">조직도</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">급여 기준 정보</Nav.Link>
            <Nav.Link className="sideLinkDetail" id={`${location.pathname.includes("/여기에 to에 들어갈 링크를 쓰시오") ? "activatedLink" : ""}`} as={Link} to="/">복리후생 등록</Nav.Link>
          </Nav>
        </Collapse>
      </Nav>
    </div>
  </>);
};


export default SideBar;