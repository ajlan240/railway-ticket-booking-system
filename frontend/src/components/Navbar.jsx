import { useState, useEffect } from 'react'
import { Link, NavLink } from 'react-router-dom'
import { TrainFront, Menu, X } from 'lucide-react'
import { useAuth } from '../context/AuthContext.jsx'
import ProfileMenu from './ProfileMenu.jsx'

function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false)
  const [scrolled, setScrolled] = useState(false)
  const { user, logout } = useAuth()

  useEffect(() => {
    function handleScroll() {
      setScrolled(window.scrollY > 8)
    }
    handleScroll()
    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [])

  const closeMenu = () => setMenuOpen(false)

  return (
    <header className={`navbar ${scrolled ? 'navbar-scrolled' : ''}`}>
      <div className="navbar-inner">
        <Link to="/" className="brand" onClick={closeMenu}>
          <TrainFront size={22} strokeWidth={2.2} />
          <span>RailEase</span>
        </Link>

        <nav className={`nav-links ${menuOpen ? 'nav-links-open' : ''}`}>
          <NavLink to="/" end onClick={closeMenu} className="nav-link">
            Home
          </NavLink>

          {!user ? (
            <>
              <NavLink to="/login" onClick={closeMenu} className="nav-link">
                Login
              </NavLink>
              <NavLink to="/signup" onClick={closeMenu} className="nav-link nav-link-cta">
                Sign Up
              </NavLink>
            </>
          ) : null}
        </nav>

        <div className="navbar-right">
          {user ? <ProfileMenu user={user} onLogout={logout} /> : null}

          <button
            className="menu-toggle"
            onClick={() => setMenuOpen((open) => !open)}
            aria-label={menuOpen ? 'Close menu' : 'Open menu'}
            aria-expanded={menuOpen}
          >
            {menuOpen ? <X size={22} /> : <Menu size={22} />}
          </button>
        </div>
      </div>
    </header>
  )
}

export default Navbar
