import { Link } from 'react-router-dom'
import { TrainFront } from 'lucide-react'

function Footer() {
  const year = new Date().getFullYear()

  return (
    <footer className="footer">
      <div className="footer-inner">
        <div className="footer-brand">
          <div className="brand">
            <TrainFront size={20} strokeWidth={2.2} />
            <span>RailEase</span>
          </div>
          <p>Simple and convenient railway ticket booking.</p>
        </div>

        <div className="footer-links">
          <Link to="/">Home</Link>
          <Link to="/login">Login</Link>
          <Link to="/signup">Sign Up</Link>
        </div>
      </div>
      <p className="footer-copyright">© {year} RailEase. All rights reserved.</p>
    </footer>
  )
}

export default Footer
