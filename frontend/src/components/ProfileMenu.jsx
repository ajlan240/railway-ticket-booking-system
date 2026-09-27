import { useState, useRef, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { ChevronDown, User, LogOut } from 'lucide-react'

function getInitials(user) {
  const first = user?.firstName?.[0] || ''
  const last = user?.lastName?.[0] || ''
  const initials = `${first}${last}`.toUpperCase()
  return initials || (user?.email?.[0] || '?').toUpperCase()
}

function ProfileMenu({ user, onLogout }) {
  const [open, setOpen] = useState(false)
  const menuRef = useRef(null)
  const navigate = useNavigate()

  // Close the dropdown when clicking anywhere outside it.
  useEffect(() => {
    function handleClickOutside(event) {
      if (menuRef.current && !menuRef.current.contains(event.target)) {
        setOpen(false)
      }
    }
    document.addEventListener('mousedown', handleClickOutside)
    return () => document.removeEventListener('mousedown', handleClickOutside)
  }, [])

  function handleLogout() {
    setOpen(false)
    onLogout()
    navigate('/')
  }

  const displayName = user.firstName ? `${user.firstName} ${user.lastName || ''}`.trim() : user.email

  return (
    <div className="user-menu" ref={menuRef}>
      <button
        type="button"
        className="user-menu-trigger"
        onClick={() => setOpen((v) => !v)}
        aria-haspopup="true"
        aria-expanded={open}
      >
        <span className="user-avatar">{getInitials(user)}</span>
        <span className="user-menu-name">{displayName}</span>
        <ChevronDown size={16} className={`user-menu-caret ${open ? 'user-menu-caret-open' : ''}`} />
      </button>

      {open ? (
        <div className="user-dropdown" role="menu">
          <Link to="/profile" className="user-dropdown-item" role="menuitem" onClick={() => setOpen(false)}>
            <User size={16} />
            <span>Your Profile</span>
          </Link>
          <button type="button" className="user-dropdown-item" role="menuitem" onClick={handleLogout}>
            <LogOut size={16} />
            <span>Logout</span>
          </button>
        </div>
      ) : null}
    </div>
  )
}

export default ProfileMenu
