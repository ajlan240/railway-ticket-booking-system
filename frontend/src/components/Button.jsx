// A single reusable button used across the app so every call-to-action
// looks and behaves the same way.
//
// variant: "primary" | "secondary" | "ghost"
// as: "button" (default) — pass as="link" style usage is handled by the
// caller wrapping this in a <Link>, since keeping this component plain
// makes it easy to understand.

function Button({
  children,
  variant = 'primary',
  type = 'button',
  onClick,
  disabled = false,
  loading = false,
  fullWidth = false,
}) {
  const classNames = [
    'btn',
    `btn-${variant}`,
    fullWidth ? 'btn-full' : '',
  ].join(' ').trim()

  return (
    <button
      type={type}
      className={classNames}
      onClick={onClick}
      disabled={disabled || loading}
    >
      {loading ? <span className="btn-spinner" aria-hidden="true" /> : null}
      <span>{loading ? 'Please wait…' : children}</span>
    </button>
  )
}

export default Button
