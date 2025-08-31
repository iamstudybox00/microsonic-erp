import 'bootstrap/'
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

function GoJsp(props) {
  function hideLink(){
    window.location.href = props.baseUrl + props.link
    return null;
  }
  return (<>
    {hideLink()}
  </>
  )
}

export default GoJsp
