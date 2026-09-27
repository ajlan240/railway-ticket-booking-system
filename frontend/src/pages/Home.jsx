import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { Ticket, Armchair, ShieldCheck, ArrowRight, MapPin, CalendarDays, Star, ChevronDown } from 'lucide-react'
import Navbar from '../components/Navbar.jsx'
import Footer from '../components/Footer.jsx'
import Button from '../components/Button.jsx'
import PhotoFrame from '../components/PhotoFrame.jsx'

const FEATURES = [
  {
    icon: Ticket,
    title: 'Easy Booking',
    description: 'Book your train journey in just a few simple steps.',
  },
  {
    icon: Armchair,
    title: 'Choose Your Seat',
    description: 'Select the seat that works best for you.',
  },
  {
    icon: ShieldCheck,
    title: 'Secure Journey',
    description: 'Manage your bookings easily and securely.',
  },
]

const STEPS = [
  { number: '01', title: 'Search your train', description: 'Enter where you\u2019re travelling from and to, and pick a date.' },
  { number: '02', title: 'Choose your seat', description: 'Compare options and pick the seat that suits you.' },
  { number: '03', title: 'Book your journey', description: 'Confirm your details and you\u2019re ready to travel.' },
]

const STATS = [
  { value: '1,200+', label: 'Stations connected' },
  { value: '3.2M+', label: 'Tickets booked' },
  { value: '98%', label: 'On-time journeys' },
  { value: '24/7', label: 'Support available' },
]

// Placeholder testimonials — swap these for real reviews once you have them.
const TESTIMONIALS = [
  {
    quote: 'Booking used to mean comparing schedules across three different sites. Now I search once and I\u2019m done in minutes.',
    name: 'Aditi Rao',
    role: 'Frequent commuter, Pune',
  },
  {
    quote: 'Seat selection makes a real difference on long routes. I always know exactly where I\u2019ll be sitting before I pay.',
    name: 'Marcus Webb',
    role: 'Business traveler, Chicago',
  },
  {
    quote: 'Simple, fast, and it just works. I\u2019ve recommended it to my whole family.',
    name: 'Priya Nair',
    role: 'Weekend traveler, Kochi',
  },
]

// Ticket type options for the search form. Values match what the
// Spring Boot backend is expected to accept once the search endpoint exists.
const TICKET_TYPES = [
  { value: 'general', label: 'General' },
  { value: 'tatkal', label: 'Tatkal' },
  { value: 'premium-tatkal', label: 'Premium Tatkal' },
  { value: 'ladies', label: 'Ladies' },
  { value: 'lower-berth', label: 'Lower Berth / Senior Citizen' },
  { value: 'duty-pass', label: 'Duty Pass (Railway Employees)' },
]

// Real photos showcasing Indian Railways. Add files with these exact names
// to /public/images/ — until then, a styled placeholder shows instead of a
// broken image. See README.md for where to source free-to-use photos.
const GALLERY = [
  {
    src: 'vande-bharat.jpg',
    alt: 'Vande Bharat Express speeding through the countryside',
    caption: 'Vande Bharat Express',
    tag: 'Modern rail travel',
  },
  {
    src: 'csmt.avif',
    alt: 'Chhatrapati Shivaji Maharaj Terminus, Mumbai, lit up at dusk',
    caption: 'Chhatrapati Shivaji Maharaj Terminus',
    tag: 'Heritage architecture',
  },
  {
    src: 'darjleeing-himalyan-railway.jpg',
    alt: 'Darjeeling Himalayan toy train winding through the hills',
    caption: 'Darjeeling Himalayan Railway',
    tag: 'Scenic hill routes',
  },
]

// A small hand-built illustration instead of a stock photo, so the hero
// stays on-brand and doesn't depend on an external image loading.
function TrainIllustration() {
  return (
    <svg viewBox="0 0 480 360" className="hero-illustration" role="img" aria-label="Illustration of a train travelling along a track">
      <circle cx="380" cy="80" r="46" fill="var(--color-accent-soft)" />
      <path d="M0 300 H480" stroke="var(--color-border)" strokeWidth="2" />
      {[...Array(14)].map((_, i) => (
        <rect key={i} x={10 + i * 34} y="300" width="16" height="6" rx="1.5" fill="var(--color-border)" />
      ))}
      <rect x="60" y="300" width="360" height="6" fill="var(--color-navy)" opacity="0.15" />
      <rect x="60" y="308" width="360" height="6" fill="var(--color-navy)" opacity="0.15" />

      <g>
        <rect x="70" y="176" width="230" height="100" rx="16" fill="var(--color-navy)" />
        <rect x="90" y="196" width="46" height="40" rx="6" fill="var(--color-bg)" />
        <rect x="150" y="196" width="46" height="40" rx="6" fill="var(--color-bg)" />
        <rect x="210" y="196" width="46" height="40" rx="6" fill="var(--color-bg)" />
        <rect x="70" y="252" width="230" height="10" fill="var(--color-accent)" />
        <rect x="300" y="176" width="46" height="100" rx="14" fill="var(--color-navy)" />
        <rect x="314" y="200" width="20" height="26" rx="4" fill="var(--color-bg)" />
        <circle cx="112" cy="286" r="14" fill="var(--color-navy-dark)" />
        <circle cx="112" cy="286" r="5" fill="var(--color-accent)" />
        <circle cx="200" cy="286" r="14" fill="var(--color-navy-dark)" />
        <circle cx="200" cy="286" r="5" fill="var(--color-accent)" />
        <circle cx="270" cy="286" r="14" fill="var(--color-navy-dark)" />
        <circle cx="270" cy="286" r="5" fill="var(--color-accent)" />
      </g>

      <g opacity="0.5">
        <path d="M330 150 h34" stroke="var(--color-text-muted)" strokeWidth="4" strokeLinecap="round" />
        <path d="M330 165 h50" stroke="var(--color-text-muted)" strokeWidth="4" strokeLinecap="round" />
      </g>
    </svg>
  )
}

function Home() {
  const navigate = useNavigate()
  const [search, setSearch] = useState({ from: '', to: '', date: '', ticketType: 'general' })

  function handleSearchChange(event) {
    const { name, value } = event.target
    setSearch((prev) => ({ ...prev, [name]: value }))
  }

  function handleSearchSubmit(event) {
    event.preventDefault()
    // Train search results aren't built yet — this is a visual placeholder
    // that will call the Spring Boot backend once that endpoint exists.
    alert('Search is coming soon. This will look up real trains once the backend is connected.')
  }

  return (
    <div className="page">
      <Navbar />

      <main>
        <section className="hero">
          <div className="hero-inner">
            <div className="hero-copy">
              <h1>Your Journey Starts Here.</h1>
              <p className="hero-subtitle">
                Search trains, choose your seat, and book your journey with ease.
              </p>

              <div className="hero-trust">
                <div className="hero-stars" aria-hidden="true">
                  {[...Array(5)].map((_, i) => (
                    <Star key={i} size={16} fill="var(--color-accent)" stroke="var(--color-accent)" />
                  ))}
                </div>
                <span>4.8 out of 5 &middot; trusted by 12,000+ travelers</span>
              </div>

              <TrainIllustration />
            </div>

            <div className="search-card">
              <div className="search-card-notch search-card-notch-left" />
              <div className="search-card-notch search-card-notch-right" />
              <h2>Find your train</h2>
              <form onSubmit={handleSearchSubmit}>
                <div className="form-field">
                  <label htmlFor="from">From</label>
                  <div className="input-with-icon">
                    <input
                      id="from"
                      name="from"
                      type="text"
                      placeholder="Departure station"
                      value={search.from}
                      onChange={handleSearchChange}
                    />
                    <MapPin size={18} className="input-icon" />
                  </div>
                </div>

                <div className="form-field">
                  <label htmlFor="to">To</label>
                  <div className="input-with-icon">
                    <input
                      id="to"
                      name="to"
                      type="text"
                      placeholder="Arrival station"
                      value={search.to}
                      onChange={handleSearchChange}
                    />
                    <MapPin size={18} className="input-icon" />
                  </div>
                </div>

                <div className="form-field">
                  <label htmlFor="date">Journey Date</label>
                  <div className="input-with-icon">
                    <input
                      id="date"
                      name="date"
                      type="date"
                      value={search.date}
                      onChange={handleSearchChange}
                    />
                    <CalendarDays size={18} className="input-icon" />
                  </div>
                </div>

                <div className="form-field">
                  <label htmlFor="ticketType">Ticket Type</label>
                  <div className="input-with-icon">
                    <Ticket size={16} className="input-icon-left" />
                    <select
                      id="ticketType"
                      name="ticketType"
                      value={search.ticketType}
                      onChange={handleSearchChange}
                    >
                      {TICKET_TYPES.map(({ value, label }) => (
                        <option key={value} value={value}>{label}</option>
                      ))}
                    </select>
                    <ChevronDown size={16} className="select-caret" />
                  </div>
                </div>

                <Button type="submit" fullWidth>Search Trains</Button>
              </form>
            </div>
          </div>
        </section>

        <section className="stats">
          <div className="stats-inner">
            {STATS.map(({ value, label }) => (
              <div className="stat-item" key={label}>
                <span className="stat-value">{value}</span>
                <span className="stat-label">{label}</span>
              </div>
            ))}
          </div>
        </section>

        <section className="section gallery">
          <h2 className="section-title">Experience India by Rail</h2>
          <div className="gallery-grid">
            {GALLERY.map(({ src, alt, caption, tag }) => (
              <figure className="gallery-card" key={caption}>
                <PhotoFrame src={src} alt={alt} caption={caption} className="gallery-photo" />
                <figcaption>
                  <span className="gallery-tag">{tag}</span>
                  <span className="gallery-caption">{caption}</span>
                </figcaption>
              </figure>
            ))}
          </div>
        </section>

        <section className="section features">
          <h2 className="section-title">Everything You Need for a Better Journey</h2>
          <div className="feature-grid">
            {FEATURES.map(({ icon: Icon, title, description }) => (
              <div className="feature-card" key={title}>
                <div className="feature-icon">
                  <Icon size={22} strokeWidth={2} />
                </div>
                <h3>{title}</h3>
                <p>{description}</p>
              </div>
            ))}
          </div>
        </section>

        <section className="section steps">
          <h2 className="section-title">How It Works</h2>
          <div className="steps-grid">
            {STEPS.map(({ number, title, description }) => (
              <div className="step-card" key={number}>
                <span className="step-number">{number}</span>
                <h3>{title}</h3>
                <p>{description}</p>
              </div>
            ))}
          </div>
        </section>

        <section className="section testimonials">
          <h2 className="section-title">Travelers Trust RailEase</h2>
          <div className="testimonial-grid">
            {TESTIMONIALS.map(({ quote, name, role }) => (
              <figure className="testimonial-card" key={name}>
                <blockquote>&ldquo;{quote}&rdquo;</blockquote>
                <figcaption>
                  <span className="testimonial-name">{name}</span>
                  <span className="testimonial-role">{role}</span>
                </figcaption>
              </figure>
            ))}
          </div>
        </section>

        <section className="cta">
          <div className="cta-inner">
            <h2>Ready to start your journey?</h2>
            <Button onClick={() => navigate('/signup')}>
              Get Started <ArrowRight size={18} />
            </Button>
          </div>
        </section>
      </main>

      <Footer />
    </div>
  )
}

export default Home
