import { useState } from 'react'
import { ImageOff } from 'lucide-react'

// Used anywhere we reference a real photo the developer needs to drop into
// /public/images/. Until that file exists, this shows a clear placeholder
// instead of a broken-image icon, so the page still looks intentional.
function PhotoFrame({ src, alt, caption, className = '' }) {
  const [failed, setFailed] = useState(false)

  if (failed) {
    return (
      <div className={`photo-fallback ${className}`}>
        <ImageOff size={26} strokeWidth={1.5} />
        <span>Add {caption ? `"${caption}"` : 'this photo'} to public/images/</span>
      </div>
    )
  }

  return (
    <img
      src={src}
      alt={alt}
      className={className}
      loading="lazy"
      onError={() => setFailed(true)}
    />
  )
}

export default PhotoFrame
