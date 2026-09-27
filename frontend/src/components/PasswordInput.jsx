import { useState } from 'react'
import { Eye, EyeOff } from 'lucide-react'

// A text input for passwords with a show/hide toggle.
// Used on both the Login and Signup pages so the behaviour stays consistent.

function PasswordInput({ id, label, value, onChange, error, placeholder, autoComplete }) {
  const [visible, setVisible] = useState(false)

  return (
    <div className="form-field">
      <label htmlFor={id}>{label}</label>
      <div className={`input-with-icon ${error ? 'input-error' : ''}`}>
        <input
          id={id}
          name={id}
          type={visible ? 'text' : 'password'}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          autoComplete={autoComplete}
        />
        <button
          type="button"
          className="icon-toggle"
          onClick={() => setVisible((v) => !v)}
          aria-label={visible ? 'Hide password' : 'Show password'}
          tabIndex={-1}
        >
          {visible ? <EyeOff size={18} /> : <Eye size={18} />}
        </button>
      </div>
      {error ? <p className="field-error">{error}</p> : null}
    </div>
  )
}

export default PasswordInput
