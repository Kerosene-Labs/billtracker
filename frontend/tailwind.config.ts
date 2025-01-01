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
          DEFAULT: "#1e3a8a",
          dark: "#2563eb",
        },
      },
      backgroundColor: {
        page: "var(--bg-page)",
        card: "var(--bg-card)",
      },
      textColor: {
        primary: "var(--text-primary)",
        muted: "var(--text-muted)",
      },
    },
  },

  plugins: [],
} satisfies Config;
