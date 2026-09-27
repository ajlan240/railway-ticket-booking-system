export const API_BASE_URL = "http://localhost:8080";

export async function loginUser(credentials) {
  const response = await fetch(
      `${API_BASE_URL}/api/auth/login`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify({
          email: credentials.email,
          password: credentials.password,
        }),
      }
  );

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
        typeof data === "string"
            ? data
            : data.message || "Invalid email or password."
    );
  }

  return data;
}

export async function registerUser(details) {
  const response = await fetch(
      `${API_BASE_URL}/api/auth/register`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(details),
      }
  );

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
        typeof data === "string"
            ? data
            : data.message || "Registration failed."
    );
  }

  return data;
}

export async function checkSession() {
  const response = await fetch(
      `${API_BASE_URL}/api/auth/session`,
      {
        method: "GET",
        credentials: "include",
      }
  );

  return response.ok;
}

export async function logoutUser() {
  const response = await fetch(
      `${API_BASE_URL}/api/auth/logout`,
      {
        method: "POST",
        credentials: "include",
      }
  );

  return response.ok;
}