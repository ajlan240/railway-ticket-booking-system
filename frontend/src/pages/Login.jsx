import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { TrainFront, CheckCircle2 } from "lucide-react";
import PasswordInput from "../components/PasswordInput.jsx";
import Button from "../components/Button.jsx";
import { loginUser } from "../api/authApi.js";
import { useAuth } from "../context/AuthContext.jsx";

function validateEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  function handleChange(event) {
    const { name, value } = event.target;

    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));

    setErrors((prev) => ({
      ...prev,
      [name]: undefined,
      form: undefined,
    }));
  }

  function validate() {
    const nextErrors = {};

    if (!form.email.trim()) {
      nextErrors.email = "Email is required.";
    } else if (!validateEmail(form.email.trim())) {
      nextErrors.email = "Enter a valid email address.";
    }

    if (!form.password) {
      nextErrors.password = "Password is required.";
    }

    setErrors(nextErrors);

    return Object.keys(nextErrors).length === 0;
  }

  async function handleSubmit(event) {
    event.preventDefault();

    if (!validate()) {
      return;
    }

    setLoading(true);
    setErrors({});

    try {
      const data = await loginUser({
        email: form.email.trim(),
        password: form.password,
      });

      console.log("Login successful:", data);

      // Spring Boot HttpSession maintains the actual login session.
      // Update the React authentication state with the returned user.
      if (data.user) {
        login(data.user);
      } else {
        login({
          email: form.email.trim(),
        });
      }

      setSuccess(true);

      setTimeout(() => {
        navigate("/");
      }, 1200);
    } catch (err) {
      console.error("Login failed:", err);

      setErrors({
        form: err.message || "Invalid email or password.",
      });

      setForm((prev) => ({
        ...prev,
        password: "",
      }));
    } finally {
      setLoading(false);
    }
  }

  return (
      <div className="auth-page">
        <div className="auth-card">

          <Link to="/" className="brand auth-brand">
            <TrainFront size={22} strokeWidth={2.2} />
            <span>RailEase</span>
          </Link>

          <h1>Welcome Back</h1>

          <p className="auth-subtitle">
            Sign in to continue your journey.
          </p>

          {success ? (
              <div className="success-banner">
                <CheckCircle2 size={20} />
                <span>
              Logged in successfully. Taking you home…
            </span>
              </div>
          ) : (
              <form onSubmit={handleSubmit} noValidate>

                <div className="form-field">
                  <label htmlFor="email">
                    Email
                  </label>

                  <input
                      id="email"
                      name="email"
                      type="email"
                      placeholder="you@example.com"
                      value={form.email}
                      onChange={handleChange}
                      className={errors.email ? "input-error" : ""}
                      autoComplete="email"
                  />

                  {errors.email ? (
                      <p className="field-error">
                        {errors.email}
                      </p>
                  ) : null}
                </div>

                <PasswordInput
                    id="password"
                    label="Password"
                    placeholder="Enter your password"
                    value={form.password}
                    onChange={handleChange}
                    error={errors.password}
                    autoComplete="current-password"
                />

                <div className="forgot-row">
                  <button
                      type="button"
                      className="link-muted link-button"
                  >
                    Forgot password?
                  </button>
                </div>

                {errors.form ? (
                    <p className="field-error form-error">
                      {errors.form}
                    </p>
                ) : null}

                <Button
                    type="submit"
                    fullWidth
                    loading={loading}
                >
                  Login
                </Button>

              </form>
          )}

          <p className="auth-switch">
            Don&apos;t have an account?{" "}
            <Link to="/signup">
              Create an account
            </Link>
          </p>

        </div>
      </div>
  );
}

export default Login;