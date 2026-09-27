# RailEase

A simple, professional-looking frontend for a railway ticket booking system, built with React + Vite. This first version only includes three pages: Landing, Login, and Signup.

## Tech used

- React (functional components + hooks only)
- Vite
- React Router
- Plain CSS (no Tailwind/Bootstrap/MUI)
- lucide-react for icons

## Getting started

```bash
npm install
npm run dev
```

Then open the URL Vite prints (usually `http://localhost:5173`).

## Project structure

```
railease/
├── index.html
├── package.json
├── vite.config.js
├── public/
│   └── train-icon.svg
└── src/
    ├── api/
    │   └── authApi.js       # stubbed login/register calls, ready to point at Spring Boot later
    ├── components/
    │   ├── Navbar.jsx
    │   ├── Footer.jsx
    │   ├── Button.jsx
    │   └── PasswordInput.jsx
    ├── pages/
    │   ├── Home.jsx
    │   ├── Login.jsx
    │   └── Signup.jsx
    ├── App.jsx
    ├── main.jsx
    └── index.css
```

## Connecting the real backend later

`src/api/authApi.js` currently just fakes a network delay and returns a success object. When your Spring Boot backend is running at `http://localhost:8080`, replace the body of `loginUser` and `registerUser` with real `fetch` calls to:

- `POST /api/auth/login`
- `POST /api/auth/register`

The example of what that looks like is written as a comment at the top of the file. Because the pages already call `loginUser`/`registerUser` and handle the loading/success/error states, you shouldn't need to touch `Login.jsx` or `Signup.jsx` much — just the API file.

## What's intentionally not included yet

Per the brief, this is just the landing/login/signup shell — no dashboard, train search results, seat selection, payment, or admin panel. Those come later.
