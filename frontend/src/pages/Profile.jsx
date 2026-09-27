import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { User } from 'lucide-react'
import Navbar from '../components/Navbar.jsx'
import Footer from '../components/Footer.jsx'
import { useAuth } from '../context/AuthContext.jsx'

function Profile() {
  const { user } = useAuth()
  const navigate = useNavigate()

  // If someone lands here without being logged in, send them to Login.
  useEffect(() => {
    if (!user) {
      navigate('/login')
    }
  }, [user, navigate])

  if (!user) return null

  const displayName = user.firstName ? `${user.firstName} ${user.lastName || ''}`.trim() : user.email

  return (
    <div className="page">
      <Navbar />

      <main>
        <section className="section profile-section">
          <div className="profile-card">
            <div className="profile-avatar-large">
              <User size={32} />
            </div>
            <h1>{displayName}</h1>
            <p className="auth-subtitle">{user.email}</p>

            <div className="profile-details">
              <div className="profile-detail-row">
                <span className="profile-detail-label">First name</span>
                <span>{user.firstName || '—'}</span>
              </div>
              <div className="profile-detail-row">
                <span className="profile-detail-label">Last name</span>
                <span>{user.lastName || '—'}</span>
              </div>
              <div className="profile-detail-row">
                <span className="profile-detail-label">Email</span>
                <span>{user.email}</span>
              </div>
            </div>

            <p className="profile-note">
              Booking history and saved passengers will appear here once that part of RailEase is built.
            </p>
          </div>
        </section>
      </main>

      <Footer />
    </div>
  )
}

export default Profile
