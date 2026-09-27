import { useState, useMemo } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { TrainFront, CheckCircle2 } from 'lucide-react'
import PasswordInput from '../components/PasswordInput.jsx'
import Button from '../components/Button.jsx'
import { registerUser } from '../api/authApi.js'

function validateEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

function validatePhone(phone) {
  // Accepts digits, spaces, +, -, and () — then checks there are enough digits.
  const digitsOnly = phone.replace(/\D/g, '')
  return digitsOnly.length >= 10
}

// Returns a score from 0 (empty) to 4 (very strong) based on simple rules.
// This is intentionally simple so it's easy to follow and extend later.
function getPasswordStrength(password) {
  if (!password) return 0
  let score = 0
  if (password.length >= 8) score += 1
  if (/[A-Z]/.test(password) && /[a-z]/.test(password)) score += 1
  if (/\d/.test(password)) score += 1
  if (/[^A-Za-z0-9]/.test(password)) score += 1
  return score
}

const STRENGTH_LABELS = ['Very weak', 'Weak', 'Fair', 'Good', 'Strong']

const initialForm = {
  firstName: '',
  lastName: '',
  email: '',
  phone: '',
  password: '',
  confirmPassword: '',
}

function Signup() {
  const navigate = useNavigate()
  const [form, setForm] = useState(initialForm)
  const [errors, setErrors] = useState({})
  const [loading, setLoading] = useState(false)
  const [success, setSuccess] = useState(false)

  const strength = useMemo(() => getPasswordStrength(form.password), [form.password])

  function handleChange(event) {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: value }))
    setErrors((prev) => ({ ...prev, [name]: undefined }))
  }

  function validate() {
    const nextErrors = {}

    if (!form.firstName.trim()) nextErrors.firstName = 'First name is required.'
    if (!form.lastName.trim()) nextErrors.lastName = 'Last name is required.'

    if (!form.email.trim()) {
      nextErrors.email = 'Email is required.'
    } else if (!validateEmail(form.email)) {
      nextErrors.email = 'Enter a valid email address.'
    }

    if (!form.phone.trim()) {
      nextErrors.phone = 'Phone number is required.'
    } else if (!validatePhone(form.phone)) {
      nextErrors.phone = 'Enter a valid phone number (at least 10 digits).'
    }

    if (!form.password) {
      nextErrors.password = 'Password is required.'
    } else if (form.password.length < 8) {
      nextErrors.password = 'Password must be at least 8 characters.'
    }

    if (!form.confirmPassword) {
      nextErrors.confirmPassword = 'Please confirm your password.'
    } else if (form.confirmPassword !== form.password) {
      nextErrors.confirmPassword = 'Passwords do not match.'
    }

    setErrors(nextErrors)
    return Object.keys(nextErrors).length === 0
  }

  async function handleSubmit(event) {
    event.preventDefault()
    if (!validate()) return

    setLoading(true)
    try {
      // Not connected to the real backend yet — see src/api/authApi.js
      // for where the POST /api/auth/register call will go.
      await registerUser(form)
      setSuccess(true)
      setTimeout(() => navigate('/login'), 1200)
    } catch (err) {
      setErrors({ form: 'Something went wrong. Please try again.' })
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card auth-card-wide">
        <Link to="/" className="brand auth-brand">
          <TrainFront size={22} strokeWidth={2.2} />
          <span>RailEase</span>
        </Link>

        <h1>Create Your Account</h1>
        <p className="auth-subtitle">Join us and make your next journey easier.</p>

        {success ? (
          <div className="success-banner">
            <CheckCircle2 size={20} />
            <span>Account created. Taking you to login…</span>
          </div>
        ) : (
          <form onSubmit={handleSubmit} noValidate>
            <div className="form-row">
              <div className="form-field">
                <label htmlFor="firstName">First Name</label>
                <input
                  id="firstName"
                  name="firstName"
                  type="text"
                  placeholder="Jordan"
                  value={form.firstName}
                  onChange={handleChange}
                  className={errors.firstName ? 'input-error' : ''}
                  autoComplete="given-name"
                />
                {errors.firstName ? <p className="field-error">{errors.firstName}</p> : null}
              </div>

              <div className="form-field">
                <label htmlFor="lastName">Last Name</label>
                <input
                  id="lastName"
                  name="lastName"
                  type="text"
                  placeholder="Rivera"
                  value={form.lastName}
                  onChange={handleChange}
                  className={errors.lastName ? 'input-error' : ''}
                  autoComplete="family-name"
                />
                {errors.lastName ? <p className="field-error">{errors.lastName}</p> : null}
              </div>
            </div>

            <div className="form-field">
              <label htmlFor="email">Email</label>
              <input
                id="email"
                name="email"
                type="email"
                placeholder="you@example.com"
                value={form.email}
                onChange={handleChange}
                className={errors.email ? 'input-error' : ''}
                autoComplete="email"
              />
              {errors.email ? <p className="field-error">{errors.email}</p> : null}
            </div>

            <div className="form-field">
              <label htmlFor="phone">Phone Number</label>
              <input
                id="phone"
                name="phone"
                type="tel"
                placeholder="+1 234 567 8900"
                value={form.phone}
                onChange={handleChange}
                className={errors.phone ? 'input-error' : ''}
                autoComplete="tel"
              />
              {errors.phone ? <p className="field-error">{errors.phone}</p> : null}
            </div>

            <PasswordInput
              id="password"
              label="Password"
              placeholder="Create a password"
              value={form.password}
              onChange={handleChange}
              error={errors.password}
              autoComplete="new-password"
            />

            {form.password ? (
              <div className="password-strength">
                <div className="strength-bar">
                  {[0, 1, 2, 3].map((i) => (
                    <span
                      key={i}
                      className={`strength-segment ${i < strength ? `strength-${strength}` : ''}`}
                    />
                  ))}
                </div>
                <span className="strength-label">{STRENGTH_LABELS[strength]}</span>
              </div>
            ) : null}

            <PasswordInput
              id="confirmPassword"
              label="Confirm Password"
              placeholder="Re-enter your password"
              value={form.confirmPassword}
              onChange={handleChange}
              error={errors.confirmPassword}
              autoComplete="new-password"
            />

            {errors.form ? <p className="field-error form-error">{errors.form}</p> : null}

            <Button type="submit" fullWidth loading={loading}>
              Create Account
            </Button>
          </form>
        )}

        <p className="auth-switch">
          Already have an account? <Link to="/login">Login</Link>
        </p>
      </div>
    </div>
  )
}

export default Signup
