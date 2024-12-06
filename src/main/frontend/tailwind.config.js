/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../resources/templates/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        'primary-light': '#171717',
        'primary-dark': '#404040'
      }
    },
  },
  plugins: [],
}

