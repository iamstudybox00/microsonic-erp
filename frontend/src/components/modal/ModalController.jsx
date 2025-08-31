import "../../css/MainContentStyle.css";
import "../../css/ModalStyle.css";
import AccountSubjectModal from "./AccountSubjectModal";
import ClientModal from "./ClientModal";
import EmpModal from "./EmpModal";
import ItemModal from "./ItemModal";


function ModalController(props) {
  const modalName = {
    "NONE" : null,
    "EMP" : <EmpModal openModal={props.openModal} closeModal={props.closeModal} isOpen={props.isOpen} selectData={props.selectData} baseUrl={props.baseUrl} />,
    "CLI" : <ClientModal openModal={props.openModal} closeModal={props.closeModal} isOpen={props.isOpen} selectData={props.selectData} baseUrl={props.baseUrl} />,
    "ITEM" : <ItemModal openModal={props.openModal} closeModal={props.closeModal} isOpen={props.isOpen} selectData={props.selectData} baseUrl={props.baseUrl} />,
    "ACTSUB" : <AccountSubjectModal openModal={props.openModal} closeModal={props.closeModal} isOpen={props.isOpen} selectData={props.selectData} baseUrl={props.baseUrl} />
  }

  return (<>
    {modalName[props.modalName]}
  </>
  );
}

export default ModalController;