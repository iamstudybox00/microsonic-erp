import 'bootstrap/'
import 'bootstrap/dist/css/bootstrap.min.css'
import { Route, Routes } from 'react-router-dom'
import './App.css'
import AccountView from './components/finance/AccountView'
import AssetMain from './components/finance/AssetMain'
import OutstandingList from './components/finance/OutstandingList'
import Transfer from './components/finance/Transfer'
import SideBar from './components/page_template/SideBar'
import TransactionEdit from './components/transaction/TransactionEdit'
import TransactionFileList from './components/transaction/TransactionFileList'
import TransactionView from './components/transaction/TransactionView'
import TransactionWrite from './components/transaction/TransactionWrite'
import TransactionList from './components/transaction/TransactionList'
import GoJsp from './GoJsp'
import TopNavi from './components/TopNavi'
const url = {
  jsp: "http://localhost:8586",
  react: "http://localhost:5173"
}
function App() {
  return (<>
    <div className="app-container">
      <TopNavi />
      <SideBar />
      <div className="main-content">
        <Routes>
          <Route path="/" element={<GoJsp link="/" baseUrl={url.jsp} />}/>
          <Route path="/test" element={<GoJsp link="/test" baseUrl={url.jsp} />}/>
          <Route path="/transactionList" >
            <Route path=":page/:start?/:end?" element={<TransactionList baseUrl={url.jsp} />} ></Route>
          </Route>
          <Route path="/transactionWrite" element={<TransactionWrite baseUrl={url.jsp} />} />
          <Route path="/transactionEdit">
            <Route path=":idx" element={<TransactionEdit baseUrl={url.jsp} />} ></Route>
          </Route>
          <Route path="/transactionView" >
            <Route path=":idx" element={<TransactionView baseUrl={url.jsp} />} ></Route>
          </Route>
          <Route path="/transactionFileList">
            <Route path=":idx" element={<TransactionFileList baseUrl={url.jsp} />} ></Route>
          </Route>
          <Route path="/assetMain" element={<AssetMain baseUrl={url.jsp} />} />
          <Route path="/accountView" element={<AccountView baseUrl={url.jsp} />} />
          <Route path="/transfer" element={<Transfer baseUrl={url.jsp} />} />
          <Route path="/outstandingList" element={<OutstandingList baseUrl={url.jsp} />} />
        </Routes>
      </div>
    </div>
  </>
  )
}

export default App
