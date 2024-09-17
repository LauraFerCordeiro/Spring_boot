/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      backgroundImage:{
        'cibersecurity': "url('/image/cybersecurity.jpg')",
      }
    },
  },
  plugins: [require("tw-elements/plugin.cjs")],
}

