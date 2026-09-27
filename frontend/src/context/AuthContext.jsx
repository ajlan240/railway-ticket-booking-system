import { createContext, useContext, useState, useEffect } from 'react'

// A small, beginner-friendly way to share "who's logged in" across pages
// without pulling in a state-management library. The Navbar reads from
// this to decide whether to show Login/Sign Up or the profile menu.
//
// Once the Spring Boot backend is connected, `login()` should be called
// with whatever user object POST /api/auth/login returns, and you may
// want to store a real auth token here too.

const AuthContext = createContext(null)
const STORAGE_KEY = 'railease_user'

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    try {
      const stored = localStorage.getItem(STORAGE_KEY)
      return stored ? JSON.parse(stored) : null
    } catch {
      return null
    }
  })

  useEffect(() => {
    try {
      if (user) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(user))
      } else {
        localStorage.removeItem(STORAGE_KEY)
      }
    } catch {
      // If storage isn't available for some reason, the session just
      // won't survive a refresh — not worth failing the app over.
    }
  }, [user])

  function login(userDetails) {
    setUser(userDetails)
  }

  function logout() {
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used inside an <AuthProvider>')
  }
  return context
}
