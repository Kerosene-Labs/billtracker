import type { Config } from "tailwindcss";

export default {
  content: ["./src/**/*.{html,js,svelte,ts}"],
  darkMode: "media",
  theme: {
    extend: {
      screens: {
        desktop: "1280px",
      },
      colors: {
        brand: {
        }
      },
    },
  },

  plugins: [],
} satisfies Config;
