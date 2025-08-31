import { useState, useEffect } from 'react';
import logo from '../assets/micro_sonic_logo.png';
import '../App.css';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/';
import { Button } from 'react-bootstrap';


function TopNavi() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    fetch('/api/auth/me', { credentials: 'include' }) // 세션 쿠키 포함
      .then(res => {
        if (res.ok) {
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      })
      .catch(() => setIsAuthenticated(false));
  }, []);

  const handleSignIn = () => {
    window.location.href = '/myLogin.do';
  };

  const handleSignOut = () => {
    window.location.href = '/myLogout.do';
  };

  return (
    // <Navbar bg="dark" variant="dark" fixed="top">
    <Navbar style={styles.navbar} fixed="top">
      <div style={styles.left}>
        <a className="navbar-brand" href="/">
          <img src={logo} alt="logo" style={styles.logo} />
        </a>
      </div>
      <div style={styles.right}>
        {isAuthenticated ? (
          <Button className="cancel-button" onClick={handleSignOut}>
            Sign Out
          </Button>
        ) : (
          <Button className="basic-button" onClick={handleSignIn}>
            Sign In
          </Button>
        )}
      </div>
    </Navbar>
    // </Navbar>
  );
}

const styles = {
  navbar: { display: 'flex', alignItems: 'center', padding: '0 20px', zIndex: '0'},
  left: { display: 'flex', alignItems: 'center', gap: '10px' },
  right: { marginLeft: 'auto' },
  logo: { height: '60px' },
};

export default TopNavi;
