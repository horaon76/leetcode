/** @type {import('next').NextConfig} */
// next-app/next.config.js
const isProd = process.env.NODE_ENV === 'production';
const nextConfig = {
     output: 'export', // Enable static export
      basePath: isProd ? '/app' : '', // Set the base path for GitHub Pages
      trailingSlash: true, // Ensure URLs work correctly on GitHub Pages
      images: {
        unoptimized: true, // Disable image optimization
      }
  };

export default nextConfig;
